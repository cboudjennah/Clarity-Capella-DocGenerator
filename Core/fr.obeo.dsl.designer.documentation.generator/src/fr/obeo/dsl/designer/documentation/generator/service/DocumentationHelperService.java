/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.designer.documentation.generator.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;

import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;

public class DocumentationHelperService {

	public String getObjectId(EObject input, String prefix) {
		return prefix + getRenderer(input).getObjectId(input);
	}

	public String getGeneratedDiagramPath(DSemanticDiagram diag) {
		return getRenderer(diag).getDiagramInfo(diag).getRelativePath();
	}

	public String getGeneratedDiagramId(DSemanticDiagram diag) {
		return getRenderer(diag).getDiagramInfo(diag).getId();
	}

	private AbstractRenderer getRenderer(EObject eObjet) {
		Resource eResource = eObjet.eResource();
		if (eResource == null) {
			throw new IllegalStateException(
					"Unable to retreive the renderer: eObject not contained in a Resource");
		}
		ResourceSet resourceSet = eResource.getResourceSet();
		if (resourceSet == null) {
			throw new IllegalStateException("Unable to retreive the renderer: Resource ("
					+ eResource.getURI() + ")not contained in a ResourceSet");
		}
		Adapter renderer = EcoreUtil.getAdapter(resourceSet.eAdapters(), AbstractRenderer.class);
		if (renderer instanceof AbstractRenderer) {
			return (AbstractRenderer)renderer;
		} else {
			throw new IllegalStateException(
					"Unable to retreive the renderer: No adapter found in the resource set");
		}
	}

	/**
	 * Copied from {@link RepresentationsServices} but do not requirement the initialization of the service.
	 * Retrieve all representations whose target is the specified EObject
	 * 
	 * @param object
	 *            Object which is the target of the representations
	 * @return List of representations
	 */
	public List<DSemanticDiagram> getAssociatedRepresentations(EObject object) {
		List<DSemanticDiagram> associatedRepresentations = new ArrayList<DSemanticDiagram>();
		Session viewpointSession = SessionManager.INSTANCE.getSession(object);
		if (viewpointSession != null) {

			final EditingDomain editingDomain = viewpointSession.getTransactionalEditingDomain();
			if (editingDomain != null) {
				EObject objectInContext = editingDomain.getResourceSet().getEObject(EcoreUtil.getURI(object),
						true);
				//Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(
					//	objectInContext, viewpointSession);
				Collection<DRepresentation> representations = Lists.newArrayList();
		        ECrossReferenceAdapter xref = viewpointSession.getSemanticCrossReferencer();
		        for (EStructuralFeature.Setting setting : xref.getInverseReferences(objectInContext)) {
		        	EObject eo = setting.getEObject();
		        	EStructuralFeature sf =setting.getEStructuralFeature();
		            if (ViewpointPackage.Literals.DREPRESENTATION.isInstance(eo) && sf == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET) {
		            	representations.add((DRepresentation) setting.getEObject());
		            }
		        }
				if (representations != null && !representations.isEmpty()) {
					// Filter representations to keep only those in a selected viewpoint
					Collection<Viewpoint> selectedViewpoints = viewpointSession.getSelectedViewpoints(false);
					if (selectedViewpoints != null) {
						for (DRepresentation representation : representations) {
							if (representation.eContainer() instanceof DView) {
								DView dView = (DView)representation.eContainer();
								Viewpoint vp = dView.getViewpoint();
								if (selectedViewpoints.contains(vp)) {
									if (representation instanceof DSemanticDiagram) {
										associatedRepresentations.add((DSemanticDiagram)representation);
									}
								}
							}
						}
					}
				}
			}
		}
		return associatedRepresentations;
	}
}
