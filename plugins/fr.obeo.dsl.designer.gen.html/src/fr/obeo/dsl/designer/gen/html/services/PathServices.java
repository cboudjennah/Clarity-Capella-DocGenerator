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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.osgi.framework.Bundle;

import fr.obeo.dsl.designer.gen.html.Activator;

/**
 * Provide services to initialize folders structure, to configure the different useful paths and get filenames
 * @author sthibaudeau
 *
 */
public class PathServices {
	/**
	 * Folder structure is :
	 * 
	 * - generated
	 *    - images
	 *       - icons
	 *       - representations
	 *    - pages
	 *       - properties
	 *          - name of resource
	 *             - name of EMF package
	 *                - name of EMF class
	 *       - representations
	 *    - resources
	 */
	public static final String GENERATED_FOLDER = "generated";
	public static final String RESOURCES_FOLDER = "resources";
	public static final String PAGES_FOLDER = "pages";
	public static final String IMAGES_FOLDER = "images";
	public static final String ICONS_FOLDER = "icons";
	public static final String REPRESENTATIONS_FOLDER = "representations";
	public static final String PROPERTIES_FOLDER = "properties";

	// Constants corresponding to the full path (starting from the root folder) for the different sub-folders
	public static final String RESOURCES_FULL_PATH = GENERATED_FOLDER + "/" + RESOURCES_FOLDER;
	public static final String PAGES_FULL_PATH = GENERATED_FOLDER + "/" + PAGES_FOLDER;
	public static final String IMAGES_FULL_PATH = GENERATED_FOLDER + "/" + IMAGES_FOLDER;
	public static final String ICONS_FULL_PATH = IMAGES_FULL_PATH + "/" + ICONS_FOLDER + "/";
	public static final String IMAGES_DIAGRAMS_FULL_PATH = IMAGES_FULL_PATH + "/" + REPRESENTATIONS_FOLDER + "/";
	public static final String PAGES_REPRESENTATIONS_FULL_PATH = PAGES_FULL_PATH + "/" + REPRESENTATIONS_FOLDER + "/";
	public static final String PAGES_PROPERTIES_FULL_PATH = PAGES_FULL_PATH + "/" + PROPERTIES_FOLDER + "/";
	
	// Constants corresponding to the relative path for the different sub-folders
	// These paths are just "../" sequences repeated as needed to get to the root folder 
	public static final String REPRESENTATIONS_RELATIVE_TO_ROOT_PATH = StringUtils.repeat("../", PAGES_REPRESENTATIONS_FULL_PATH.split("/").length);
	public static final String PAGES_RELATIVE_TO_ROOT_PATH = StringUtils.repeat("../", PAGES_FULL_PATH.split("/").length);
	// +3 because there are 3 more levels with EResource, EPackage and EClass names
	public static final String PROPERTIES_RELATIVE_TO_ROOT_PATH = StringUtils.repeat("../", PAGES_PROPERTIES_FULL_PATH.split("/").length + 3); 
	
	// Prefixes used for filenames
	public static final String PROPERTIES_PREFIX = "p_";
	public static final String DIAGRAM_PREFIX = "diagram_";
	public static final String TABLE_PREFIX = "table_";
	public static final String TREE_PREFIX = "tree_";

	// HTML extension
	private static final String HTML_FILE_EXTENSION = ".html";

	// Root folder for the generation
	private String rootFolder = null;
	
	/**
	 * Initialize the service, create the folder structure (if the folder already exists it will be deleted)
	 * and unzip the resources (css, javascript, icons)
	 * @param rootFolder Root folder for the generation. It will be used to export icons files
	 */
	public void initPathServices(String rootFolderName) {
		rootFolder = rootFolderName;
		File rootFolder = new File(rootFolderName);
		if (rootFolder.exists() && rootFolder.isDirectory()) {
			// delete folders previously generated
			deleteFile(new File(rootFolderName + "/" + GENERATED_FOLDER));
			
			// create folders hierarchy
			createFolder(rootFolderName + "/" + GENERATED_FOLDER);
			
			createFolder(rootFolderName + "/" + IMAGES_FULL_PATH);
			createFolder(rootFolderName + "/" + ICONS_FULL_PATH);
			createFolder(rootFolderName + "/" + IMAGES_DIAGRAMS_FULL_PATH);
		
			// Refresh the folder
			IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(new Path(rootFolderName + "/" + GENERATED_FOLDER));
			if (container != null) {
				try {
					container.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (CoreException e) {
					final IStatus message = new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage());
					Activator.getDefault().getLog().log(message);
				}
			}
			
			// Unzip the required resources (css, javascript)
			unzipResources();
		}
	}
	
	/**
	 * Unzip the resources into the right folder
	 */
	private void unzipResources() {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
    	URL fileURL = FileLocator.find(bundle, new Path("resources/resources.zip"), null);
    	
    	File file = new File(rootFolder + "/" + RESOURCES_FULL_PATH);
    	try {
			unzip(fileURL, file);
		} catch (Exception e) {
			final IStatus message = new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage());
			Activator.getDefault().getLog().log(message);
		}
	}
	
	/**
	 * Create a folder
	 * @param folderPath Full path
	 */
	private void createFolder(String folderPath) {
		File folder = new File(folderPath);
		folder.mkdir();
	}
	
	/**
	 * Delete a file and its children
	 * @param file
	 */
	private void deleteFile(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File childFile : file.listFiles()) {
					deleteFile(childFile);
				}
			}
			file.delete();
		}
	}
	
	/**
	 * Unzip a file given its url into a folder
	 * @param zipURL URL of the zip file
	 * @param folder Folder where to unzip
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void unzip(URL zipURL, File folder) throws FileNotFoundException, IOException{

		ZipInputStream zis = new ZipInputStream(zipURL.openStream());

		ZipEntry ze = null;
		try {
			while((ze = zis.getNextEntry()) != null){

				File f = new File(folder.getCanonicalPath(), ze.getName());
				if (ze.isDirectory()) {
					f.mkdirs();
					continue;
				}
				f.getParentFile().mkdirs();
				OutputStream fos = new BufferedOutputStream(
						new FileOutputStream(f));
				try {
					try {
						final byte[] buf = new byte[8192];
						int bytesRead;
						while (-1 != (bytesRead = zis.read(buf)))
							fos.write(buf, 0, bytesRead);
					}
					finally {
						fos.close();
					}
				}
				catch (final IOException ioe) {
					f.delete();
					throw ioe;
				}
			}
		}
		finally {
			zis.close();
		}
	}
	
	/**
	 * Get the full path (starting from the root folder) of the properties page for an object 
	 * @param object
	 * @return Full path starting from root folder
	 */
	public static String getPropertiesPageFilename(EObject object) {
		// First part is built on the resource URI
		String resource = "unknown";
		if (object.eResource() != null && object.eResource().getURI() != null) {
			resource = StringUtils.sanitizeFilename(object.eResource().getURI().lastSegment());
		}
		// Add the position within the ResourceSet to avoid names clashes (if last segment of two URIs are the same)
		ResourceSet set = object.eResource().getResourceSet();
		if (set != null) {
			resource += "_" + set.getResources().indexOf(object.eResource());
		}
		
		// Second part is built on the package's namespace prefix
		String nsPrefix = object.eClass().getEPackage().getNsPrefix();
		if (nsPrefix == null || "".equals(nsPrefix)) {
			nsPrefix = "unknown";
		} else {
			nsPrefix = StringUtils.sanitizeFilename(nsPrefix);
		}
		
		// Third part is build on the EClass's name
		String eClass = StringUtils.sanitizeFilename(object.eClass().getName());
		
		// Last part is built using the URI fragment
		String file = PROPERTIES_PREFIX
				+ StringUtils.sanitizeFilename(object.eResource().getURIFragment(object))
				+ HTML_FILE_EXTENSION;
		
		return PAGES_PROPERTIES_FULL_PATH + resource + "/" + nsPrefix + "/" + eClass + "/" + file;
	}
	
	/**
	 * Get the full path (starting from the root folder) of the page corresponding to a representation 
	 * @param representation
	 * @return Full path starting from root folder
	 */
	public String getRepresentationPageFilename(DRepresentation representation) {
		String prefix = "representation_";
		if (representation instanceof DSemanticDiagram) {
			prefix = DIAGRAM_PREFIX;
		} else if (representation instanceof DTable) {
			prefix = TABLE_PREFIX;
		} else if (representation instanceof DTree) {
			prefix = TREE_PREFIX;
		}
		return PAGES_REPRESENTATIONS_FULL_PATH
					+ prefix + StringUtils.sanitizeFilename(representation.eResource().getURIFragment(representation)) + HTML_FILE_EXTENSION;
	}
	
	public String getIconsFullPath(EObject object) {
		return ICONS_FULL_PATH;
	}
	
	public String getPagesFullPath(EObject object) {
		return PAGES_FULL_PATH;
	}
	
	public String getResourcesFullPath(EObject object) {
		return RESOURCES_FULL_PATH;
	}
	
	public String getRepresentationsRelativeToRootPath(EObject object) {
		return REPRESENTATIONS_RELATIVE_TO_ROOT_PATH;
	}
	
	public String getPropertiesRelativeToRootPath(EObject object) {
		return PROPERTIES_RELATIVE_TO_ROOT_PATH;
	}
	
	public String getPagesRelativeToRootPath(EObject object) {
		return PAGES_RELATIVE_TO_ROOT_PATH;
	}

}
