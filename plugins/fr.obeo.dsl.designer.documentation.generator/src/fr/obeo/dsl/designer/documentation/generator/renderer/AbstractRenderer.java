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
package fr.obeo.dsl.designer.documentation.generator.renderer;

import static fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin.createErrorStatus;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.engine.AcceleoEngineMessages;
import org.eclipse.acceleo.engine.AcceleoEnginePlugin;
import org.eclipse.acceleo.engine.AcceleoEvaluationException;
import org.eclipse.acceleo.engine.generation.strategy.DefaultStrategy;
import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;
import org.eclipse.acceleo.engine.generation.writers.AcceleoStringWriter;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities.EscapeMode;
import org.jsoup.select.Elements;

import fr.obeo.dsl.designer.documentation.generator.RendererException;
import fr.obeo.dsl.designer.documentation.generator.RepresentationInformation;

public abstract class AbstractRenderer extends DefaultStrategy implements Adapter.Internal {

	protected static final String MAIN_DOCUMENT_CONTENT_ID = "document-main-content";

	private static final String MAIN_TITLE_ID = "document-main-title";

	private static final String INDEX_ID = "document-index";

	private static final String DOCUMENT_HEADER_TAG = "document-header-tag";

	private static final String DOCUMENT_FOOTER_TAG = "document-footer-tag";

	private static final String PAGE_BREAK_CLASS = "page-break";

	private static final String OBJECT_ANCHOR_CLASS = "document-object-anchor";

	private static final String OBJECT_LINK_ANCHOR_CLASS = "document-object-link";

	private final IFolder container;

	private List<IStatus> statuses = new ArrayList<IStatus>();

	private IProgressMonitor monitor;

	private Notifier target;

	private final Map<DSemanticDiagram, RepresentationInformation<DSemanticDiagram>> generatedDiagrams = new HashMap<DSemanticDiagram, RepresentationInformation<DSemanticDiagram>>();

	public AbstractRenderer(IFolder container) {
		this.container = container;
	}

	@Override
	public AbstractAcceleoWriter createWriterFor(File file, AbstractAcceleoWriter previous,
			boolean appendMode, boolean hasJMergeTags, String charset) throws IOException {
		final AbstractAcceleoWriter writer;

		if (file.isDirectory()) {
			throw new AcceleoEvaluationException(AcceleoEngineMessages.getString(
					"AcceleoEvaluationContext.FileNameIsDirectory", file)); //$NON-NLS-1$
		}

		if (appendMode && previous != null) {
			writer = previous;
			writer.append(LINE_SEPARATOR);
		} else if (!appendMode && hasJMergeTags) {
			// previous cannot be null if hasJMergeTags is true
			writer = previous;
			/*
			 * We know the writer is an AcceleoStringWriter, reinitializing it allows it to know it should
			 * merge its content through JMerge when it's next closed.
			 */
			writer.reinit();
		} else {
			if (charset != null) {
				if (Charset.isSupported(charset)) {
					writer = new AcceleoStringWriter(file, appendMode, hasJMergeTags, charset);
				} else {
					final String message = AcceleoEngineMessages.getString(
							"AcceleoGenerationStrategy.UnsupportedCharset", charset); //$NON-NLS-1$
					AcceleoEnginePlugin.log(message, false);
					writer = new AcceleoStringWriter(file, appendMode, hasJMergeTags);
				}
			} else {
				writer = new AcceleoStringWriter(file, appendMode, hasJMergeTags);
			}
		}
		return writer;
	}

	protected IProgressMonitor getMonitor() {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		return monitor;
	}

	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void flushWriter(String filePath, Writer writer) throws IOException {
		super.flushWriter(filePath, writer);
		String content = writer.toString();
		Document doc = Jsoup.parseBodyFragment(content);
		doc.outputSettings().escapeMode(EscapeMode.xhtml);
		try {
			render(filePath, doc);
		} catch (RendererException e) {
			addStatus(createErrorStatus("Error while rendering the web document", e));
		} catch (CoreException e) {
			addStatus(createErrorStatus("Error while rendering the web document", e));
		}

	}

	protected Elements getPageBreaks(Document doc) {
		return doc.body().select("." + PAGE_BREAK_CLASS);
	}

	protected Elements getObjectAnchors(Document doc) {
		return doc.body().select("." + OBJECT_ANCHOR_CLASS);
	}

	protected Elements getObjectLink(Document doc) {
		return doc.body().select("." + OBJECT_LINK_ANCHOR_CLASS);
	}

	protected Element getMainContentTag(Document doc) {
		Elements mainTile = doc.select("#" + MAIN_DOCUMENT_CONTENT_ID);
		if (mainTile.isEmpty()) {
			return null;
		}
		return mainTile.get(0);
	}

	protected Element getHeaderTag(Document doc) {
		Elements mainTile = doc.select("#" + DOCUMENT_HEADER_TAG);
		if (mainTile.isEmpty()) {
			return null;
		}
		return mainTile.get(0);
	}

	protected Element getFooterTag(Document doc) {
		Elements mainTile = doc.select("#" + DOCUMENT_FOOTER_TAG);
		if (mainTile.isEmpty()) {
			return null;
		}
		return mainTile.get(0);
	}

	protected Element getIndexTag(Document doc) {
		Elements elements = doc.select("." + INDEX_ID);
		if (elements.isEmpty()) {
			return null;
		} else {
			return elements.get(0);
		}
	}

	protected Element getMainTitleTag(Document doc) {
		Elements mainTile = doc.select("#" + MAIN_TITLE_ID);
		if (mainTile.isEmpty()) {
			return null;
		}
		return mainTile.get(0);
	}

	public RepresentationInformation<DSemanticDiagram> getDiagramInfo(DSemanticDiagram diag) {
		RepresentationInformation<DSemanticDiagram> diagInfo = generatedDiagrams.get(diag);
		if (diagInfo == null) {
			String path = getDiagramImageFilename(diag);
			String id = diag.eResource().getURIFragment(diag);
			diagInfo = new RepresentationInformation<DSemanticDiagram>(diag, path, id);
			generatedDiagrams.put((DSemanticDiagram)diag, diagInfo);
		}
		return diagInfo;
	}

	private String getDiagramImageFilename(DSemanticDiagram diagram) {
		return "img/diag/" + sanitizeFilename(diagram.eResource().getURIFragment(diagram)) + "."
				+ ImageFileFormat.PNG.getName();
	}

	private String sanitizeFilename(String name) {
		return name.replaceAll("[:\\\\/*?|<>]", "_");
	}

	/**
	 * Get the GmF Diagram instance corresponding to a viewpoint DSemanticDiagram
	 * 
	 * @param semanticDiagram
	 * @return
	 */
	protected Diagram getGmfDiagram(DSemanticDiagram semanticDiagram) {
		for (final AnnotationEntry annotation : new DDiagramQuery(semanticDiagram)
				.getAnnotation(CustomDataConstants.GMF_DIAGRAMS)) {
			EObject eObject = annotation.getData();
			if (eObject instanceof Diagram) {
				final Diagram diagramInResource = (Diagram)eObject;
				final EObject semanticElement = ViewUtil.resolveSemanticElement(diagramInResource);
				if (semanticElement.equals(semanticDiagram)) {
					return diagramInResource;
				}
			}
		}
		return null;
	}

	protected IFolder getContainer() {
		return container;
	}

	public void addStatus(IStatus status) {
		statuses.add(status);
	}

	public Map<DSemanticDiagram, RepresentationInformation<DSemanticDiagram>> getAllGeneratedDiagrams() {
		return generatedDiagrams;
	}

	private Map<String, String> eObjectMapId = new HashMap<String, String>();

	private int counter = 0;

	public String getObjectId(EObject eObject) {

		// can not use fragment since the syntax my not be correct
		String id = eObjectMapId.get(EcoreUtil.getURI(eObject));
		if (id == null) {
			id = "document-semantic-id" + counter;
			counter++;
		}
		return id;
	}

	protected abstract void render(String filePath, Document doc) throws IOException, RendererException,
			CoreException;

	@Override
	public void notifyChanged(Notification notification) {
	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.target = newTarget;

	}

	@Override
	public boolean isAdapterForType(Object type) {
		return AbstractRenderer.class == type;
	}

	@Override
	public void unsetTarget(Notifier oldTarget) {
		if (target == oldTarget) {
			setTarget(null);
		}
	}

	public List<IStatus> getStatus() {
		return statuses;
	}

}
