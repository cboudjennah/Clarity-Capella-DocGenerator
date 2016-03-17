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
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.image.PartPositionInfo;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;

import fr.obeo.dsl.designer.gen.html.Activator;

/**
 * Provide services around viewpoint representations
 * @author sthibaudeau
 *
 */
public class RepresentationsServices {
	
	private static RepresentationsServices INSTANCE = new RepresentationsServices();
	//TODO change to singleton holder
	public static RepresentationsServices getInstance(){
		return INSTANCE;
	}
	
	// Root folder for the generation
	private String rootFolder = null;
	
	private ImageFileFormat imageFileFormat = ImageFileFormat.JPG;
	
	// Sirius session, used to export diagrams
	private Session viewpointSession = null;
	
	// Flag used to store the information if the viewpoint session was already opened
	// or if the generator had to open it
	private boolean sessionAlreadyOpened = false;

	/**
	 * Initialize the services class, especially the Viewpoint session
	 * @param analysis Analysis sued to open a viewpoint session
	 * @param rootFolder Root folder for the generation
	 * @param imageFileFormatAsString Image file format used to export diagrams
	 */
	public void initRepresentationsServices(DAnalysis analysis, String rootFolder, String imageFileFormatAsString) {
		this.rootFolder = rootFolder;
		this.imageFileFormat = ImageFileFormat.resolveImageFormat(imageFileFormatAsString);
		URI airdURI = EcoreUtil.getURI(analysis);
		viewpointSession = SessionManager.INSTANCE.getSession(airdURI, new NullProgressMonitor());
		sessionAlreadyOpened = viewpointSession.isOpen(); 
		if (!sessionAlreadyOpened) {
			viewpointSession.open(new NullProgressMonitor());
		}
	}
	
	/**
	 * Reset the services
	 * @param analysis
	 */
	public void resetRepresentationsServices(DAnalysis analysis) {
		rootFolder = null;
		if (!sessionAlreadyOpened) {
			viewpointSession.close(new NullProgressMonitor());
		}
		viewpointSession = null;
	}
	
	/**
	 * Retrieve all representations whose target is the specified EObject
	 * @param object Object which is the target of the representations
	 * @return List of representations
	 */
	public List<DRepresentation> getAssociatedRepresentations(EObject object) {
		final EditingDomain editingDomain = viewpointSession.getTransactionalEditingDomain();
		EObject objectInContext = editingDomain.getResourceSet().getEObject(EcoreUtil.getURI(object), true);
		Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(objectInContext, viewpointSession);
		
		List<DRepresentation> associatedRepresentations = new ArrayList<DRepresentation>();
		// Filter representations to keep only those in a selected viewpoint
		Collection<Viewpoint> selectedViewpoints = viewpointSession.getSelectedViewpoints(false);
		
		for (DRepresentation representation : representations) {
			if (representation.eContainer() instanceof DView) {
				DView dView = (DView)representation.eContainer();
				Viewpoint vp = dView.getViewpoint();
				if (selectedViewpoints.contains(vp)) {
					associatedRepresentations.add(representation);
				}
			}
		}
		return associatedRepresentations;
	}
	
	/**
	 * Check if a DRepresentationElement has specific associated semantic elements.
	 * @param representationElement
	 * @return true if there is no associated semantic element
	 * 				or if there is only one and it is the same object as the "target"
	 */
	public boolean hasSpecificSemanticElements(DRepresentationElement representationElement) {
		if (representationElement.getSemanticElements().isEmpty()) {
			return false;
		}
		if (representationElement.getSemanticElements().size() == 1 && representationElement.getSemanticElements().contains(representationElement.getTarget())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get the list of DRepresentationElement in a DAnalysis with specific semantic elements.
	 * For each returned DRepresentationElement we will generate a specific properties page
	 * @param analysis
	 * @return
	 */
	public List<DRepresentationElement> getRepresentationElementsWithSpecificSemanticElements(DAnalysis analysis) {
		List<DRepresentationElement> specificElements = new ArrayList<DRepresentationElement>();
		for (TreeIterator<EObject> it = analysis.eAllContents(); it.hasNext();) {
			EObject element = it.next();
			if (element instanceof DRepresentationElement && !(element instanceof DEdge || element instanceof DFeatureColumn)) {
				DRepresentationElement representationElt = (DRepresentationElement) element;
				if (hasSpecificSemanticElements(representationElt)) {
					specificElements.add(representationElt);
				}
			/*
			 * We could prune the iterator when needed to get faster
			 * The code has been commented because I am not sure I could cover all the cases
			} else if (!(element instanceof DAnalysis 
					|| element instanceof DView
					|| element instanceof DRepresentation)) {
				// We don't need to traverse the whole tree to find DRepresentationElement instances
				it.prune();
			*/
			}
		}
		return specificElements;
	}
	
	/**
	 * Get the full path (starting from the root folder) of the generated image for a diagram 
	 * @param diagram
	 * @return Full path starting from root folder
	 */
	public String getDiagramImageFilename(DSemanticDiagram diagram) {
		return PathServices.IMAGES_DIAGRAMS_FULL_PATH
					+ PathServices.DIAGRAM_PREFIX + StringUtils.sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "." + imageFileFormat.getName();
	}

	
	/**
	 * Export a diagram as an image and build an ImageMap HTML code
	 * @param diagram
	 * @return Image map which can be used to click on the image
	 */
	public String exportDiagramAndGetImageMap(DSemanticDiagram diagram) {
		final IPath path = new Path(rootFolder + "/" + getDiagramImageFilename(diagram));
		final Diagram gmfDiagram = getGmfDiagram(diagram);
		
		// Use the DiagramEditPartService to use the figure validation infinite loop safe ViewpointDiagramGraphicalViewer.
		final CopyToImageUtil imageUtility = new DiagramEditPartService();

		final EditingDomain editingDomain = viewpointSession.getTransactionalEditingDomain();
		final Diagram realOne = (Diagram) editingDomain.getResourceSet().getEObject(EcoreUtil.getURI(gmfDiagram), true);

		RunnableWithResult<StringBuffer> generateMap = new RunnableWithResult<StringBuffer>() {

			private StringBuffer result = new StringBuffer("<map id=\"details\" name=\"details\">");

			public void run() {
				try {
					@SuppressWarnings("unchecked")
					List<PartPositionInfo> parts = (List<PartPositionInfo>)imageUtility.copyToImage(realOne,
							path,
							imageFileFormat,
							new NullProgressMonitor(),
							PreferencesHint.USE_DEFAULTS);

					for (PartPositionInfo part : parts) {
						if (part.getSemanticElement() != null
								&& (part.getSemanticElement() instanceof DDiagramElement)
								&& !(part.getSemanticElement() instanceof DEdge)) {
							DDiagramElement element = (DDiagramElement) part.getSemanticElement();
							
							result.append("<area shape=\"rect\" alt=\"");
							result.append(element.getName());
							result.append("\" target=\"properties\" href=\"");
							result.append(PathServices.REPRESENTATIONS_RELATIVE_TO_ROOT_PATH);
							if (hasSpecificSemanticElements(element)) {
								result.append(PathServices.getPropertiesPageFilename(element));
							} else {
								result.append(PathServices.getPropertiesPageFilename(element.getTarget()));
							}
							result.append("\" coords=\"");
							Integer xupper = new Integer(part.getPartX());
							Integer yupper = new Integer(part.getPartY());
							Integer xlower = new Integer(part.getPartX()
									+ part.getPartWidth());
							Integer ylower = new Integer(part.getPartY()
									+ part.getPartHeight());
							result.append(xupper + " , " + yupper + " , " + xlower + " , " + ylower);
							result.append("\"/>");
						}
					}

					result.append("</map>");

				} catch (CoreException e) {
					final IStatus message = new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage());
					Activator.getDefault().getLog().log(message);
				}
			}

			public void setStatus(IStatus status) {
			}

			public IStatus getStatus() {
				return Status.OK_STATUS;
			}

			public StringBuffer getResult() {
				return this.result;
			}
		};
		Display.getDefault().syncExec(generateMap);

		return generateMap.getResult().toString();
	}
	
	/**
	 * Get the GmF Diagram instance corresponding to a viewpoint DSemanticDiagram
	 * @param semanticDiagram
	 * @return
	 */
	private Diagram getGmfDiagram(DSemanticDiagram semanticDiagram) {
		for (final AnnotationEntry annotation : new DDiagramQuery(semanticDiagram).getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
			EObject eObject = annotation.getData();
			if (eObject instanceof Diagram) {
				final Diagram diagramInResource = (Diagram) eObject;
				final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
				if (semanticElement.equals(semanticDiagram)) {
					return diagramInResource;
				}
			}
		}
		return null;
	}
}
