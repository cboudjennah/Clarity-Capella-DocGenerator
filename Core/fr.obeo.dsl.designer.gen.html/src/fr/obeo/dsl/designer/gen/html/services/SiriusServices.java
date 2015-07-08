/**
 * Copyright  2012 Obeo. All Rights Reserved.
 *
 * This file is part of Obeo Designer.
 *
 * This software and the attached documentation are the exclusive ownership
 * of its authors and was conceded to the profit of Obeo SARL.
 * This software and the attached documentation are protected under the rights
 * of intellectual ownership, including the section "Titre II  Droits des auteurs (Articles L121-1, L123-12)"
 * By installing this software, you acknowledge being aware of this rights and
 * accept them, and as a consequence you must:
 * - be in possession of a valid license of use conceded by Obeo only.
 * - agree that you have read, understood, and will comply with the license terms and conditions.
 * - agree not to do anything that could conflict with intellectual ownership owned by Obeo or its beneficiaries
 * or the authors of this software
 *
 * Should you not agree with these terms, you must stop to use this software and give it back to its legitimate owner.
 *
 * Acceleo and Obeo are trademarks owned by Obeo.
 */
package fr.obeo.dsl.designer.gen.html.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Provide services around viewpoint
 * @author sthibaudeau
 *
 */
public class SiriusServices {
	// List used to cache the root objects of resources contained in the Project Dependencies
	private List<EObject> dependenciesCache;
	
	/**
	 * Initialize the service
	 * The useless parameters are kept to be consistent with other services
	 * @param analysis
	 * @param rootFolder
	 */
	public void initSiriusServices(DAnalysis analysis, String rootFolder) {
		dependenciesCache = null;
	}
	
	/**
	 * Get the name of the project containing a DAnalys
	 * @param analysis
	 * @return
	 */
	public String getProjectName(DAnalysis analysis) {
		if (analysis.eResource() != null) {
			URI uri = analysis.eResource().getURI();
			String path = null;
			if (uri.isPlatform()) {
				path = analysis.eResource().getURI().toPlatformString(true);
			} else if (uri.isFile()) {
				path = analysis.eResource().getURI().toFileString();
			}
			if (path != null) {
				IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
				if (resource == null) {
					resource = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
				}
				if (resource != null) {
					IProject project = resource.getProject();
					if (project != null) {
						return project.getName();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Get the root analysis from an aird file URI
	 * It is important to handle the root analysis so the generator can work on a shared modeling project
	 * @param airdURI
	 * @return Root analysis
	 */
	public static DAnalysis getRootAnalysis(URI airdURI) {
		Session session = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
		if (session == null) {
			return null;
		}
		EObject root = session.getSessionResource().getContents().get(0);
		if (root instanceof DAnalysis) {
			return (DAnalysis)root;
		}
		return null;
	}
	
	/**
	 * Get all analyses from a root analysis.
	 * The list contains the root analysis itself
	 * @param rootAnalysis
	 * @return
	 */
	public List<DAnalysis> getAllAnalyses(DAnalysis rootAnalysis) {
		List<DAnalysis> allAnalyses = new ArrayList<DAnalysis>();
		allAnalyses.add(rootAnalysis);
		
		// We collect all referenced analyses
		// We have to be careful not to cycle about analyses which could reference each other
		List<DAnalysis> toBeHandled = new ArrayList<DAnalysis>(rootAnalysis.getReferencedAnalysis());
		while (!toBeHandled.isEmpty()) {
			DAnalysis analysis = toBeHandled.remove(0);
			
			// If the analysis is already on our result list, it means we've already handled it
			if (!allAnalyses.contains(analysis)) {
				allAnalyses.add(analysis);
				toBeHandled.addAll(new ArrayList<DAnalysis>(analysis.getReferencedAnalysis()));
			}
		}
		
		return allAnalyses;
	}
	
	/**
	 * Get the root object of resources contained in the Project Dependencies
	 * @param rootAnalysis
	 * @return
	 */
	public List<EObject> getProjectDependencies(DAnalysis rootAnalysis) {
		// Check if the dependencies already have been calculated
		if (dependenciesCache != null) {
			return dependenciesCache;
		}
		List<EObject> dependencies = new ArrayList<EObject>();
		
		// We consider all analyses including the referenced ones
		List<DAnalysis> allAnalyses = getAllAnalyses(rootAnalysis);
		
		// Retrieve the root project
		IProject rootProject = null;
		IFile rootAnalaysisFile = WorkspaceSynchronizer.getFile(rootAnalysis.eResource());
		if (rootAnalaysisFile != null) {
			rootProject = rootAnalaysisFile.getProject();
			if (rootProject != null) {
				// We then check if their models are located in the root project
				for (DAnalysis analysis : allAnalyses) {
					for (EObject model : analysis.getModels()) {
						if (!isInProject(model, rootProject)) {
							dependencies.add(model);
						}
					}
				}
			}
		}
		// Put in cache
		dependenciesCache = dependencies;
		return dependencies;
	}
	
	/**
	 * Check if an EObject is contained in the project dependencies
	 * @param object
	 * @return
	 */
	public boolean isInProjectDependencies(EObject object) {
		List<EObject> dependencies = dependenciesCache;
		for (EObject dependency : dependencies) {
			if (EcoreUtil.isAncestor(dependency, object)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if an EObject is contained inside a project
	 * @param object
	 * @param project
	 * @return
	 */
	private boolean isInProject(EObject object, IProject project) {
		Resource resource = object.eResource();
		if (resource != null) {
			if (resource instanceof EObject) {
			    // This means we are using CDO
				return true;
			}
			URI uri = resource.getURI();
			if (uri != null && (uri.isPlatformResource() || uri.isFile())) {
				IFile resourceFile = WorkspaceSynchronizer.getFile(resource);
				if (resourceFile != null) {
                    if (project.getProject().equals(resourceFile.getProject())) {
                        return true;
                    }
                }
			}
		}
		return false;
	}
}
