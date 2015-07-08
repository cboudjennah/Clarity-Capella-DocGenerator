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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;

/**
 * Provide services to get label and icon for EMF objects
 * @author sthibaudeau
 *
 */
public class LabelProviderServices {
	// File extension used for icons
	private static final String ICON_IMAGE_FILE_EXTENSION = ".png";
	// Image format used for icons
	private static final int ICON_EXPORT_EXTENSION = SWT.IMAGE_PNG;
	
	// Root folder for the generation
	private String rootFolder = null;
	
	// LabelProvider used to get label and image
	private ILabelProvider labelProvider = null;
	
	// Map used to cache already generated icons
	private Map<Image, String> imagesCache = new HashMap<Image, String>();
	
	/**
	 * Initialize the service
	 * @param rootFolder Root folder for the generation. It will be used to export icons files
	 */
	public void initLabelProviderServices(String rootFolder) {
		this.rootFolder = rootFolder;
	}
	
	/**
	 * Get the label for an EObject using a label provider.
	 * Returned string has been escaped so it is safe to embed it inside HTML or XML code
	 * @param object
	 * @return Escaped label
	 */
	public String getLabel(EObject object) {
		return StringUtils.escapeHtml(getLabelProvider().getText(object));
	}
	
	/**
	 * Get the image filename for an EObject using a label provider.
	 * This file will be created with the image data.
	 * @param object
	 * @return Image filename
	 */
	public String getImage(final EObject object) {
		Display display = Display.getCurrent();
		if (display == null) {
			display = Display.getDefault();
		}
		// We have to use the thread UI to cover all cases
		// if not there is a problem when exporting a legacy representations file
		// which has not been expanded yet : ExtendedImageRegistry could not be instantiated 
		RunnableWithResult<Image> runnable = new RunnableWithResult<Image>() {
			private Image image = null;
			public void run() {
				image = getLabelProvider().getImage(object);				
			}
			public Image getResult() {
				return image;
			}
			public void setStatus(IStatus status) {
				// Do nothing
			}
			public IStatus getStatus() {
				return Status.OK_STATUS;
			}
		};
		display.syncExec(runnable);
		Image image = runnable.getResult();
		String alias;
		if (imagesCache.containsKey(image) == false) {
			alias = computeAlias(object);
			imagesCache.put(image, alias);
			exportIcon(image, alias);
		} else {
			alias = imagesCache.get(image);
		}
		return alias + ICON_IMAGE_FILE_EXTENSION;
	}
	
	/**
	 * Retrieves and initialize if needed the label provider
	 * @return Label provider instance
	 */
	private ILabelProvider getLabelProvider() {
		if (labelProvider == null) {
			labelProvider = new AdapterFactoryLabelProvider(new ComposedAdapterFactory(getAdapterFactory()));
		}
		return labelProvider;
	}
	
	// Counter used to avoid colliding names
	private Integer counter = 0;
	/**
	 * Compute an alias for an EObject based on its EClass name and a counter
	 * @param object
	 * @return Alias as a string
	 */
	private String computeAlias(EObject object) {
		synchronized (counter) {
			counter++;
		}
		return object.eClass().getName() + "_" + counter;
	}
	
	/**
	 * Export an image into a file. The file will be located inside the PathServices.ICONS_FULL_PATH folder
	 * @param image Image to be exported
	 * @param name Image filename (not a full path)
	 */
	private void exportIcon(Image image, String name) {
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] {image.getImageData()};
		imageLoader.save(rootFolder + "/" + PathServices.ICONS_FULL_PATH + "/" + name + ICON_IMAGE_FILE_EXTENSION,
				ICON_EXPORT_EXTENSION);
	}
	
	/**
	 * Initialize an AdapterFactory to be used to created the label provider
	 * @return AdapterFactory composed with all the needed factories
	 */
	public static ComposedAdapterFactory getAdapterFactory() {
        List<AdapterFactory> adapterFactories = new ArrayList<AdapterFactory>();
        adapterFactories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        adapterFactories.add(FeatureExtensionsUIManager.INSTANCE.createAdapterFactory());
        adapterFactories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
        adapterFactories.add(new ReflectiveItemProviderAdapterFactory());
        return new ComposedAdapterFactory(adapterFactories);
	} 
	
}
