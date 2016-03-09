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
package fr.obeo.dsl.designer.documentation.generator.capella.templates.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.acceleo.engine.event.AcceleoTextGenerationEvent;
import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.polarsys.capella.core.data.capellacore.TypedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Partition;

import com.google.common.collect.Lists;

import fr.obeo.dsl.designer.documentation.generator.capella.Activator;
import fr.obeo.dsl.designer.documentation.generator.capella.generator.CapellaDocumentationGeneratorCustom;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;

public class DocumentationHelperService {

	private static final String EMPTY_STRING = "";
	private static ComposedAdapterFactory composedAdapterFacotry = null;

	/**
	 * Get the value of the feature and clean it's content to be valid html.
	 * 
	 * @param eObject
	 *            input {@link EObject}
	 * @param featureName
	 *            name of an {@link EAttribute} that returns a HTML content
	 *            string
	 * @return
	 * @throws Exception
	 */
	private static CapellaDocumentationGeneratorCustom getCurrentGenerator() {
		CapellaDocumentationGeneratorCustom currentGeneration = Activator
				.getDefault().getCurrentGeneration();
		if (currentGeneration == null) {
			throw new IllegalStateException(
					"There is no generation currently running");
		}
		return currentGeneration;
	}

	private static ComposedAdapterFactory getComposedAdapterFactory() {
		if (composedAdapterFacotry == null) {
			composedAdapterFacotry = new ComposedAdapterFactory(
					ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			getCurrentGenerator().addGenerationListener(
					new IAcceleoTextGenerationListener() {

						@Override
						public void textGenerated(
								AcceleoTextGenerationEvent event) {
						}

						@Override
						public boolean listensToGenerationEnd() {
							return true;
						}

						@Override
						public void generationEnd(
								AcceleoTextGenerationEvent event) {
							// Do not keep a reference to composed adapter
							// factory
							composedAdapterFacotry.dispose();
							composedAdapterFacotry = null;
						}

						@Override
						public void filePathComputed(
								AcceleoTextGenerationEvent event) {
						}

						@Override
						public void fileGenerated(
								AcceleoTextGenerationEvent event) {
						}
					});
		}
		return composedAdapterFacotry;

	}

	public static String getUIText(EObject input) {
		final String label;
		if (input != null) {
			IItemLabelProvider labelProvider = (IItemLabelProvider) getComposedAdapterFactory()
					.adapt(input, IItemLabelProvider.class);
			if (labelProvider == null) {
				Activator.logError("Unable to retreive the UI test for "
						+ input);
				label = "ERROR: while retreiving the name";
			} else {
				label = labelProvider.getText(input);
			}
		} else {
			label = "";
		}
		return label;
	}

	public static String cleanHTML(EObject eObject, String featureName)
			throws Exception {
		final String result;
		if (eObject != null) {
			EStructuralFeature feature = eObject.eClass()
					.getEStructuralFeature(featureName);

			if (feature == null) {
				Activator.logError("The feature " + featureName
						+ " does not exist on the following element:"
						+ getUIText(eObject));
				result = EMPTY_STRING;
			} else if (feature.isMany()) {
				Activator
						.logError("This service does not handle multi valued feature:"
								+ featureName);
				result = EMPTY_STRING;
			} else {

				Object value = eObject.eGet(feature);
				if (value instanceof String) {
					result = Jsoup.clean((String) value, Whitelist.relaxed());
				} else if (value == null) {
					result = EMPTY_STRING;
				} else {
					Activator
							.logError("This service only handle EAttribute that returns strings:"
									+ featureName);
					result = EMPTY_STRING;
				}
			}

		} else {
			result = EMPTY_STRING;
		}
		return result;
	}

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
			throw new IllegalStateException(
					"Unable to retreive the renderer: Resource ("
							+ eResource.getURI()
							+ ")not contained in a ResourceSet");
		}
		Adapter renderer = EcoreUtil.getAdapter(resourceSet.eAdapters(),
				AbstractRenderer.class);
		if (renderer instanceof AbstractRenderer) {
			return (AbstractRenderer) renderer;
		} else {
			throw new IllegalStateException(
					"Unable to retreive the renderer: No adapter found in the resource set");
		}
	}

	/**
	 * Retrieve all representations whose target
	 * is the specified EObject
	 * 
	 * @param object
	 *            Object which is the target of the representations
	 * @return List of representations
	 */
	public List<DSemanticDiagram> getAssociatedRepresentations(EObject object,
			String diagramRepresentationName) {
		final List<DSemanticDiagram> representations = new ArrayList<DSemanticDiagram>();
		List<DSemanticDiagram> associatedRepresentations = new ArrayList<DSemanticDiagram>();
		Session viewpointSession = SessionManager.INSTANCE.getSession(object);
		if (viewpointSession != null) {

			final EditingDomain editingDomain = viewpointSession
					.getTransactionalEditingDomain();
			if (editingDomain != null) {
				EObject objectInContext = editingDomain.getResourceSet()
						.getEObject(EcoreUtil.getURI(object), true);

				final Collection<DView> ownedViews = viewpointSession
						.getOwnedViews();

				for (DView dView : ownedViews) {
					final EList<DRepresentation> allRepresentations = dView
							.getOwnedRepresentations();
					for (DRepresentation dRepresentation : allRepresentations) {
						if (dRepresentation instanceof DSemanticDiagram) {
							final DSemanticDiagram semanticDiagram = (DSemanticDiagram) dRepresentation;
							if (semanticDiagram.getDescription() != null
									&& EcoreUtil.equals(
											semanticDiagram.getTarget(),
											objectInContext)) {
								representations
										.add((DSemanticDiagram) dRepresentation);
							}
						}
					}
				}

				if (representations != null && !representations.isEmpty()) {
					// Filter representations to keep only those in a
					// selected
					// viewpoint
					Collection<Viewpoint> selectedViewpoints = viewpointSession
							.getSelectedViewpoints(false);
					if (selectedViewpoints != null) {
						for (DRepresentation representation : representations) {
							if (representation.eContainer() instanceof DView) {
								DView dView = (DView) representation
										.eContainer();
								Viewpoint vp = dView.getViewpoint();
								if (selectedViewpoints.contains(vp)) {
									if (representation instanceof DSemanticDiagram
											&& diagramRepresentationName
													.equals(((DSemanticDiagram) representation)
															.getDescription()
															.getName())) {
										associatedRepresentations
												.add((DSemanticDiagram) representation);
									}
								}
							}
						}
					}
				}

			}
		}
		// verify the name

		return associatedRepresentations;
	}

	public String getCleanFileName(String fileName) {
		String newFileName = fileName.replaceAll("[^\\w|\\s]", "");
		newFileName = newFileName.replaceAll("\\s$", "");
		if (newFileName.length() == 0)
			throw new IllegalStateException("File Name " + fileName
					+ " results in a empty fileName!");
		return newFileName;
	}

	/**
	 * get Parts from Components
	 * 
	 */
	public List<Partition> getPartsFromComponents(Component compo) {
		List<Partition> parts = new ArrayList<>();
		EList<TypedElement> typedElement = compo.getTypedElements();
		if (typedElement != null && !typedElement.isEmpty())
			parts.addAll((Collection<? extends Partition>) typedElement);
		return parts;

	}
	
	public List<ExchangeItem> getEisFromFex(EObject fe) {
		
		List<ExchangeItem> eis = new ArrayList<>();
		if (fe instanceof FunctionalExchange)
		{
			eis  = ((FunctionalExchange)fe).getExchangedItems();
			
		}
		
		
		return eis;

	}
}
