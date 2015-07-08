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
package fr.obeo.dsl.designer.documentation.generator.renderer.word;

import static fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin.createErrorStatus;
import static fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin.createWarningStatus;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.swt.widgets.Display;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import fr.obeo.dsl.designer.documentation.generator.RendererException;
import fr.obeo.dsl.designer.documentation.generator.RepresentationInformation;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate;

public class WordRenderer extends AbstractRenderer {

	private static final String TEMPLATE_RESOURCES_HEADER_HTM = "resources/header.htm";

	private static final String EOL = System.getProperty("line.separator");

	private List<BodyPart> externalResources = new ArrayList<BodyPart>();

	private WordTemplate template;

	public WordRenderer(IFolder container, WordTemplate template) {
		super(container);
		this.template = template;
	}

	protected void render(String filePath, Document doc) throws IOException, RendererException, CoreException {

		setUpNamespaces(doc);

		addTableContent(doc);

		addStyleExternalResources(doc);

		setPageConfiguration(doc);

		createSection(doc);

		createBreakPages(doc);

		createHyperLinks(doc);

		createMainTitle(doc);

		setDisplayMode(doc);

		generateDiagrams(doc);

		serializeMHTML(filePath, doc);

	}

	private void createHyperLinks(Document doc) {
		for (Element objectAnchor : getObjectAnchors(doc)) {
			if (objectAnchor.hasAttr("id")) {
				String id = objectAnchor.attr("id");
				objectAnchor.removeAttr("id");
				objectAnchor.attr("name", id);
			}
		}
		// for (Element link : getObjectLink(doc)) {
		// if (link.hasAttr("href")) {
		// String ref = link.attr("href");
		// ref = ref.substring(1); // Removes the # element since we want to get the ref
		// link.appendChild(new Comment("[if supportFields]>", ""));
		// link.appendChild(new Element(Tag.valueOf("span"), "").attr("style", "mso-element:field-begin"));
		// link.appendText("PAGEREF " + ref + " \\h ");
		// link.appendChild(new Element(Tag.valueOf("span"), "").attr("style", "mso-element:field-end"));
		// link.appendChild(new Comment("[if supportFields]", ""));
		// }
		// }
	}

	private void createMainTitle(Document doc) {
		Element mainTitle = getMainTitleTag(doc);
		Element newMainTitle = new Element(Tag.valueOf("div"), "");
		newMainTitle
				.attr("style",
						"mso-element:para-border-div;border:none;border-bottom:solid #4F81BD 1.0pt;mso-border-bottom-themecolor:accent1;padding:0cm 0cm 4.0pt 0cm");
		newMainTitle.appendElement("p").addClass("MsoTitle").appendChild(mainTitle.clone());
		if (mainTitle.hasAttr("id")) {
			newMainTitle.attr("id", mainTitle.attr("id"));
		}
		mainTitle.replaceWith(newMainTitle);

	}

	private void createBreakPages(Document doc) {
		for (Element pageBreak : getPageBreaks(doc)) {
			Element newStyle = new Element(Tag.valueOf("br"), "");
			newStyle.attr("clear", "all").attr("style",
					"mso-special-character:line-break;page-break-before:always");
			pageBreak.replaceWith(newStyle);
		}

	}

	private void addTableContent(Document doc) {
		Element indexTable = getIndexTag(doc);
		if (indexTable != null) {
			Element tableOfContent = new Element(Tag.valueOf("p"), "");
			StringBuilder toc = new StringBuilder();
			toc.append("<!--[if supportFields]>").append(EOL);
			toc.append("<span style='mso-element:field-begin'></span> ").append(EOL);
			toc.append("TOC \\o ").append(indexTable.attr("from")).append("-").append(indexTable.attr("to"))
					.append(" \\u ").append(EOL);
			toc.append("<span style='mso-element:field-separator'></span> ").append(EOL);
			toc.append("<![endif]--> ").append(EOL);
			toc.append(
					"<span style='mso-no-proof:yes'>Table of content - Please right-clic and choose \"Update fields\".</span> ")
					.append(EOL);
			toc.append("<!--[if supportFields]> ").append(EOL);
			toc.append("<span style='mso-element:field-end'></span> ").append(EOL);
			toc.append("<![endif]-->").append(EOL);
			tableOfContent.addClass("MsoToc1").html(toc.toString());
			indexTable.replaceWith(tableOfContent);
		}

	}

	/**
	 * Adds the requires namespaces for Microsoft word
	 * 
	 * @param doc
	 */
	private void setUpNamespaces(Document doc) {
		doc.select("html").attr("xmlns:v", "urn:schemas-microsoft-com:vml") //
				.attr("xmlns:o", "urn:schemas-microsoft-com:office:office")//
				.attr("xmlns:w", "urn:schemas-microsoft-com:office:word") //
				.attr("xmlns:m", "http://schemas.microsoft.com/office/2004/12/omml")//
				.attr("xmlns", "http://www.w3.org/TR/REC-html40");
	}

	/**
	 * Defines the page configuration:
	 * <ul>
	 * <li>Size</li>
	 * <li>Orientation</li>
	 * <li>Margin</li>
	 * <li>Header and footer</li>
	 * </ul>
	 * TODO may be improved to be more configurable
	 * 
	 * @param doc
	 * @param documentObject
	 * @param externalSources
	 * @throws RendererException
	 */
	private void setPageConfiguration(Document doc) throws RendererException {
		StringBuilder pConfig = new StringBuilder();
		// Set the page size an orientation
		pConfig.append("<Style>").append("\n");
		pConfig.append("@page").append(EOL);
		pConfig.append("{size:21cm 29.7cmt;").append(EOL);/* A4 */
		pConfig.append("	margin:2cm 2cm 2cm 2cm;").append(EOL);/* Margins: 2.5 cm on each side */
		pConfig.append("	mso-page-orientation: portrait; ").append(EOL);
		// Insert potential header and footer
		insertFooterAndHeader(doc, pConfig);
		pConfig.append("}").append(EOL);
		pConfig.append("</style>").append(EOL);
		doc.head().append(pConfig.toString());
	}

	/**
	 * Create a Word section and move all the content in it.
	 * <p>
	 * <i>Every part of the document should be included in a section. Currently a unique part is created and
	 * the all the content is moved inside.</i>
	 * </p>
	 * 
	 * @param doc
	 */
	private void createSection(Document doc) {
		StringBuilder pConfig = new StringBuilder();
		/*
		 * Every part of the document should be included in a section. Currently a unique part is created and
		 * the all the content is moved inside.
		 */
		pConfig.append("<style>").append("\n");
		pConfig.append("@page Section1 { }").append(EOL);
		pConfig.append("div.Section1 { page:Section1; }").append(EOL);
		pConfig.append("</style>").append(EOL);
		doc.head().append(pConfig.toString());

		String oldContent = doc.body().html();
		doc.body().empty().appendElement("div").attr("class", "Section1").append(oldContent);

	}

	/**
	 * Forces Word to open the document as a "page" document (not as a web page)
	 * 
	 * @param doc
	 */
	private void setDisplayMode(Document doc) {
		String forcePageMode = "<!--[if gte mso 9]>" //
				+ "<xml>"//
				+ "<w:WordDocument>"//
				+ "<w:View>Print</w:View>"//
				+ "<w:Zoom>100</w:Zoom>"//
				+ "<w:DoNotOptimizeForBrowser/>"//
				+ "</w:WordDocument>"//
				+ "</xml>"//
				+ "<![endif]-->";
		doc.head().append(forcePageMode);
	}

	/**
	 * Insert a footer and header if any.
	 * <p>
	 * In order to insert a footer the attribute {@link PageDocument#getHeaderAndFooter()} should set up.
	 * Moreover the client should call {@link PageDocument#insertFooter()} and or
	 * {@link PageDocument#insertHeader()}
	 * </p>
	 */
	private void insertFooterAndHeader(Document doc, StringBuilder pConfig) throws RendererException {
		Element headerAndFooterDoc = null;
		Element headerTag = getHeaderTag(doc);
		if (headerTag != null) {
			headerAndFooterDoc = loadHeaderAndFooterTemplate();
			String headerId = getUniqueElementId(headerAndFooterDoc, "[style=mso-element: header]");
			if (headerId == null) {
				addStatus(createWarningStatus("Unable to find a header element in:\n "
						+ headerAndFooterDoc.html()));
			} else {
				for (Element header : headerAndFooterDoc.select("#header-content-word")) {
					header.html(headerTag.html());
				}
				pConfig.append("mso-header: url('/doc/header.htm') ").append(headerId).append(";")
						.append(EOL);
			}
			headerTag.remove();
		}
		Element footerTag = getFooterTag(doc);
		if (footerTag != null) {
			if (headerAndFooterDoc == null) {
				headerAndFooterDoc = loadHeaderAndFooterTemplate();
			}

			String footerId = getUniqueElementId(headerAndFooterDoc, "[style=mso-element: footer]");
			if (footerId == null) {
				addStatus(createWarningStatus("Unable to find a footer element in:\n "
						+ headerAndFooterDoc.html()));
			} else {
				for (Element footer : headerAndFooterDoc.select("#footer-content-word")) {
					footer.html(footerTag.html());
				}
				pConfig.append("mso-footer: url('/doc/header.htm') ").append(footerId).append(";")
						.append(EOL);
			}
			footerTag.remove();
		}
		if (headerAndFooterDoc != null) {
			externalResources.add(bodyPart(new StringDataSource("text/html", "/doc/header.htm",
					headerAndFooterDoc.html())));
		}

	}

	/**
	 * Returns the first element that match the cssQuery
	 * 
	 * @param holder
	 * @param cssQuery
	 * @return
	 */
	private String getUniqueElementId(Element holder, String cssQuery) {
		Elements elements = holder.select(cssQuery);
		final Element elem;
		if (elements == null || elements.isEmpty()) {
			elem = null;
		} else if (elements.size() > 1) {
			elem = elements.get(0);
		} else {
			elem = elements.get(0);
		}
		if (elem != null && elem.hasAttr("id")) {
			return elem.attr("id");
		} else {
			return null;
		}
	}

	private Element loadHeaderAndFooterTemplate() throws RendererException {
		try {
			return Jsoup.parse(template.getHeaderAndFooterTemplate().openStream(), null, "");
		} catch (IOException e) {
			throw new RendererException("Unable to retrieve header and footer template", e);
		}
	}

	/**
	 * Includes external documents in charge of the style the word document.
	 * 
	 * @param doc
	 * @param externalSources
	 * @param documentObject
	 * @throws RendererException
	 */
	private void addStyleExternalResources(Document doc) throws RendererException {
		// Insert css word
		URLDataSource wordCssSource = new URLDataSource(template.getCss());
		externalResources.add(bodyPart(wordCssSource));
		doc.head().appendElement("link").attr("rel", "stylesheet").attr("type", "text/css").attr("href",
				wordCssSource.getName());
		// Inserts theme
		URLDataSource thmxDataSource = new URLDataSource(template.getTheme());
		externalResources.add(bodyPart(thmxDataSource));
		doc.head().appendElement("link").attr("rel", "themeData").attr("href", thmxDataSource.getName());

		// Inserts color mapping
		URLDataSource colorSchemeMappingDataSource = new URLDataSource(template.getColorMapping());
		externalResources.add(bodyPart(colorSchemeMappingDataSource));
		doc.head().appendElement("link").attr("rel", "3DcolorSchemeMapping").attr("href",
				colorSchemeMappingDataSource.getName());
	}

	private void generateDiagrams(final Document doc) throws RendererException {
		// Use the DiagramEditPartService to use the figure validation infinite loop safe
		// ViewpointDiagramGraphicalViewer.
		final CopyToImageUtil imageUtility = new DiagramEditPartService();
		EditingDomain editingDomain = null;
		for (Entry<DSemanticDiagram, RepresentationInformation<DSemanticDiagram>> diagEntry : getAllGeneratedDiagrams()
				.entrySet()) {
			DSemanticDiagram diag = diagEntry.getKey();
			if (editingDomain == null) {
				org.eclipse.sirius.business.api.session.Session session = SessionManager.INSTANCE
						.getExistingSession(diag.eResource().getURI());
				editingDomain = session.getTransactionalEditingDomain();
			}
			final Diagram realOne = (Diagram)editingDomain.getResourceSet().getEObject(
					EcoreUtil.getURI(getGmfDiagram(diag)), true);

			RunnableWithResult<BodyPart> generateBodyPart = new DiagramExporter(imageUtility, diagEntry
					.getValue().getRelativePath(), realOne, getMonitor(), doc);
			Display.getDefault().syncExec(generateBodyPart);
			if (generateBodyPart.getStatus().isOK()) {
				externalResources.add(generateBodyPart.getResult());
			} else {
				IStatus s = generateBodyPart.getStatus();
				throw new RendererException(s.getMessage(), s.getException());
			}

		}

	}

	private void serializeMHTML(String filePath, Document doc) throws RendererException {
		// get system properties
		Properties props = new Properties();
		// System.getProperties();
		// create a new session
		javax.mail.Session session = javax.mail.Session.getInstance(props, null);
		// construct a MIME message message
		MimeMessage message = new MimeMessage(session);
		MimeMultipart mpart = new MimeMultipart("related");
		try {
			mpart.addBodyPart(bodyPart(new StringDataSource("text/html", "index.html", doc.html())));
			for (BodyPart bodyPart : externalResources) {
				mpart.addBodyPart(bodyPart);
			}
			message.setContent(mpart);
			message.setSubject("Siruis documentation generation");
			message.addHeader("Content-Location", "index.html");
			File outputFile = new File(filePath + ".doc");
			FileOutputStream out = new FileOutputStream(outputFile);
			getMonitor().subTask("Serializing MHTML document");
			message.writeTo(out);
			out.close();
		} catch (MessagingException e) {
			throw new RendererException("Error while creating the main MIME element", e);
		} catch (IOException e) {
			throw new RendererException("Error while creating the main MIME element", e);
		}
	}

	private final class DiagramExporter implements RunnableWithResult<BodyPart> {
		private final CopyToImageUtil imageUtility;

		private final Diagram realOne;

		private final IProgressMonitor monitor;

		private final Document doc;

		private BodyPart result;

		private IStatus status;

		private final String diagramPath;

		private DiagramExporter(CopyToImageUtil imageUtility, String diagramPath, Diagram realOne,
				IProgressMonitor monitor, Document doc) {
			this.imageUtility = imageUtility;
			this.diagramPath = diagramPath;
			this.realOne = realOne;
			this.monitor = monitor;
			this.doc = doc;
		}

		@Override
		public void run() {
			try {
				File imgFile = File.createTempFile("diag", ".png");
				Path p = new Path(imgFile.getAbsolutePath());
				imageUtility.copyToImage(realOne, p, ImageFileFormat.PNG, monitor,
						PreferencesHint.USE_DEFAULTS);
				result = bodyPartPNG(p.toFile(), diagramPath, diagramPath);

				setCorrectSize(doc, diagramPath, imgFile);

			} catch (CoreException e) {
				status = createErrorStatus("Probeme while exporting diagram " + diagramPath, e);
			} catch (MessagingException e) {
				status = createErrorStatus("Probeme while exporting diagram " + diagramPath, e);
			} catch (IOException e) {
				status = createErrorStatus("Probeme while exporting diagram " + diagramPath, e);
			}
		}

		/**
		 * Creates a MIME {@link BodyPart} for the PNG image.
		 * 
		 * @param file
		 * @param name
		 * @param location
		 * @return
		 * @throws MessagingException
		 * @throws IOException
		 */
		private BodyPart bodyPartPNG(File file, String name, String location) throws MessagingException,
				IOException {
			MimeBodyPart body = new MimeBodyPart();
			FileDataSource ds = new FileDataSource(file);
			DataHandler dh = new DataHandler(ds);
			body.setDisposition("inline");
			body.setDataHandler(dh);
			body.setFileName(name);
			// the URL of the file; we set it simply to its name
			body.addHeader("Content-Location", location);
			body.addHeader("Content-Type:", "image/png");
			body.addHeader("Content-Transfer-Encoding", "base64");
			return body;
		}

		private void setCorrectSize(final Document doc, String diagramImageFilename, File imgFile)
				throws IOException {
			// Sets img file in the document
			BufferedImage img = ImageIO.read(imgFile);
			final double pageWidth = 614;// not sure
			final double pageHeigt = 700;// not sure
			double widthRatio = img.getWidth() / pageWidth;
			double heightRatio = img.getHeight() / pageHeigt;
			double maxRatio = Math.max(widthRatio, heightRatio);
			if (maxRatio > 1) {
				// we have to resize the img
				double newWidth = img.getWidth() / maxRatio;
				double newHeight = img.getHeight() / maxRatio;
				for (Element elem : doc.select("img[src=" + diagramImageFilename + "]")) {
					elem.attr("width", String.valueOf(newWidth)).attr("height", String.valueOf(newHeight));
				}
			}
		}

		@Override
		public BodyPart getResult() {
			return result;
		}

		@Override
		public void setStatus(IStatus status) {
			this.status = status;
		}

		@Override
		public IStatus getStatus() {
			return status == null ? Status.OK_STATUS : status;
		}
	}

	/**
	 * Create a MIME {@link BodyPart} from a {@link DataSource}.
	 * 
	 * @param ds
	 * @return
	 * @throws RendererException
	 */
	private BodyPart bodyPart(DataSource ds) throws RendererException {
		MimeBodyPart body = new MimeBodyPart();
		DataHandler dh = new DataHandler(ds);
		try {
			body.setDisposition("inline");
			body.setDataHandler(dh);
			body.setFileName(dh.getName());
			// the URL of the file; we set it simply to its name
			body.addHeader("Content-Location", dh.getName());
		} catch (MessagingException e) {
			throw new RendererException("Unable to create the MIME element for data source: " + ds.getName(),
					e);
		}
		return body;
	}

	private static final class StringDataSource implements DataSource {
		/** Content type of the DataSource */
		private final String contentType;

		/** Name of the DataSource */
		private final String name;

		/** Data */
		private final byte[] data;

		public StringDataSource(String contentType, String name, String data) {
			this.contentType = contentType;
			this.data = data.getBytes();
			this.name = name;
		}

		public String getContentType() {
			return contentType;
		}

		public OutputStream getOutputStream() {
			throw new UnsupportedOperationException();
		}

		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(data);
		}

		public String getName() {
			return name;
		}
	}

}
