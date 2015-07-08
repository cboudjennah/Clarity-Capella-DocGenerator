package fr.obeo.dsl.designer.documentation.generator.renderer.website.internal;

import static fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin.createErrorStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.UUID;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.swt.widgets.Display;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.google.common.base.Joiner;

import fr.obeo.dsl.designer.documentation.generator.RendererException;
import fr.obeo.dsl.designer.documentation.generator.RepresentationInformation;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate;

public class WebSiteRenderer extends AbstractRenderer {

	private static final String INDEX_MODAL_ID = "document-index-modal";

	private static final String TOP_NAVIGATION_BAR_ID = "document-navigation-bar";

	private WebSiteTemplate webSiteTemplate;

	private IProgressMonitor monitor;

	public WebSiteRenderer(IFolder container, WebSiteTemplate webSiteTemplate) {
		super(container);
		this.webSiteTemplate = webSiteTemplate;
	}

	protected void render(String filePath, Document doc) throws IOException,
			RendererException, CoreException {

		addExternalResources(doc);

		applyStyle(doc);

		addNavbar(doc);

		addIndex(doc);

		moveInRow(doc);

		addFooterAndHeader(doc);

		generateDiagrams(doc, getMonitor());

		serializeHTML(filePath, doc);
	}

	private void addExternalResources(Document doc) throws CoreException,
			IOException {

		for (ExternalResource resource : webSiteTemplate.getStylesheetFiles()) {
			addCss(doc, resource.getTo(), resource.getFrom(), getMonitor());
		}

		for (ExternalResource resource : webSiteTemplate.getJsFiles()) {
			addJs(doc, resource.getTo(), resource.getFrom(), getMonitor());
		}

		for (ExternalResource resource : webSiteTemplate.getOthers()) {
			addOther(doc, resource.getTo(), resource.getFrom(), getMonitor());
		}

	}

	private void serializeHTML(String filePath, Document doc)
			throws IOException {
		File file = new File(filePath + ".html");
		if (file.createNewFile()) {
			FileWriter writter = new FileWriter(file);
			try {
				writter.write(doc.html());
			} finally {
				writter.close();
			}
		} else {
			addStatus(createErrorStatus("Can create file to " + filePath
					+ ".html"));
		}

	}

	private void generateDiagrams(final Document doc,
			final IProgressMonitor monitor) throws RendererException,
			CoreException {
		for (Entry<DSemanticDiagram, RepresentationInformation<DSemanticDiagram>> diagEntry : getAllGeneratedDiagrams()
				.entrySet()) {
			DSemanticDiagram diag = diagEntry.getKey();
			// Use the DiagramEditPartService to use the figure validation
			// infinite loop safe ViewpointDiagramGraphicalViewer.
			TransactionalEditingDomain editingDomain = null;
			final CopyToImageUtil imageUtility = new DiagramEditPartService();
			if (editingDomain == null) {
				Session session = SessionManager.INSTANCE
						.getExistingSession(diag.eResource().getURI());
				editingDomain = session.getTransactionalEditingDomain();
			}
			final Diagram realOne = (Diagram) editingDomain.getResourceSet()
					.getEObject(EcoreUtil.getURI(getGmfDiagram(diag)), true);

			IFile file = getContainer().getFile(
					diagEntry.getValue().getRelativePath());
			DiagramExporter generateFile = new DiagramExporter(imageUtility,
					diag, realOne, monitor, file);
			Display.getDefault().syncExec(generateFile);
			if (!generateFile.getStatus().isOK()) {
				IStatus s = generateFile.getStatus();
				throw new RendererException(s.getMessage(), s.getException());
			}
		}

	}

	private void moveInRow(Document doc) {
		String oldContent = doc.body().html();
		doc.body().empty()//
				.appendElement("div")//
				.attr("class", "container") //
				.attr("id", MAIN_DOCUMENT_CONTENT_ID).appendElement("div")//
				.attr("class", "row")//
				.appendElement("div")//
				.attr("class", "col-xs-12")//
				.append(oldContent);
	}

	private void addNavbar(Document doc) throws RendererException {
		Element navigationBarDiv = doc.body().prependElement("div")
				.addClass("navbar").addClass("navbar-default")
				.addClass("navbar-fixed-top").attr("id", TOP_NAVIGATION_BAR_ID);
		Element titleLink = navigationBarDiv.appendElement("div")
				.addClass("navbar-header")//
				.appendElement("a");
		Element mainTitleTag = getMainTitleTag(doc);
		final String title;
		if (mainTitleTag != null) {
			title = mainTitleTag.html();
		} else {
			title = "Missing document title";
		}
		titleLink.addClass("navbar-brand").attr("href", "#").html(title);
	}

	private void addIndex(Document doc) throws RendererException {
		Element index = getIndexTag(doc);
		if (index != null) {
			Elements select = doc.select("#" + TOP_NAVIGATION_BAR_ID);
			if (select.isEmpty()) {
				throw new RendererException(
						"Can not insert index without a navigation bar");
			}
			Element navBar = select.get(0);
			navBar.appendElement("div")
					.attr("class",
							"navbar-collapse collapse navbar-responsive-collapse")
					//
					.appendElement("ul")
					.attr("class", "nav navbar-nav")
					//
					.appendElement("li")
					.attr("class", "dropdown active")
					//
					.appendElement("a").addClass("active")
					.attr("data-toggle", "modal")
					.attr("data-target", "#" + INDEX_MODAL_ID).append("Index");
			// Creates modal
			Element modal = new Element(Tag.valueOf("div"), "")
					.addClass("modal");
			Element modalContent = modal.attr("id", INDEX_MODAL_ID)//
					.appendElement("div").addClass("modal-dialog")//
					.appendElement("div").addClass("modal-content");
			Element modalHeader = modalContent.appendElement("div").addClass(
					"modal-header");//
			modalHeader.appendElement("button").addClass("close")
					.attr("data-dismiss", "modal").attr("aria-hidden", "true")
					.html("x");
			modalHeader.appendElement("h1").addClass("modal-title")
					.html("Index");
			Element modalBody = modalContent.appendElement("div").addClass(
					"modal-body");
			Element list = modalBody.appendElement("ul").addClass("list-group");

			ArrayList<String> titleLevel = new ArrayList<String>();
			for (int level = Integer.valueOf(index.attr("from")); level <= Integer
					.valueOf(index.attr("to")); level++) {
				titleLevel.add("h" + level);
			}
			for (Element htmlTitle : doc
					.select(Joiner.on(",").join(titleLevel))) {
				String elementId = htmlTitle.id();
				if ("".equals(elementId)) {
					elementId = UUID.randomUUID().toString();
					htmlTitle.attr("id", elementId);
				}
				int currentLevel = Integer.valueOf(htmlTitle.tagName().replace(
						"h", ""));
				Element clonedElement = htmlTitle.clone();
				for (Element child : clonedElement.getAllElements()) {
					// Removes id in order to avoid any id duplication
					child.removeAttr("id");
				}
				// TODO finish this
				list.appendElement("li")
						.addClass("list-group-item")
						.appendElement("a")
						.addClass("closemodal")
						.attr("style",
								"padding-left:" + currentLevel * 7 + "px")
						.attr("href", "#" + elementId)
						.html(clonedElement.html());

			}

			Element footer = modalContent.appendElement("div");
			footer.addClass("modal-footer")
					//
					.appendElement("button").attr("type", "button")
					.attr("class", "btn btn-default")
					.attr("data-dismiss", "modal").html("Close");
			footer.append("<script>	$('.closemodal').click(function() {$('#"
					+ INDEX_MODAL_ID + "').modal('hide');});</script>");

			navBar.appendChild(modal);
		}

	}

	private void applyStyle(Document doc) {
		// Table
		for (Element table : doc.select("table")) {
			Element clonedTable = table.clone();
			clonedTable.attr("class",
					"table table-bordered table-striped table-condensed");
			Elements lines = clonedTable.select("tr");
			if (lines.size() > 0) {
				lines.get(0).addClass("active");
			}
			Element section = new Element(Tag.valueOf("section"), "");
			section.attr("class", "col-sm-12");
			section.appendChild(clonedTable);
			table.replaceWith(section);
		}
		// List
		for (Element list : doc.select("ul,ol")) {
			list.addClass("list-group");
			for (Element li : list.select("li")) {
				li.addClass("list-group-item");
			}
		}
		// TODO
		Element mainTitleTag = getMainTitleTag(doc);
		if (mainTitleTag != null) {
			mainTitleTag.addClass("jumbotron");
		}

	}

	private void addFooterAndHeader(Document doc) {
		Element mainContent = getMainContentTag(doc);
		Element header = getHeaderTag(doc);
		if (header != null) {
			Element newHeader = new Element(Tag.valueOf("blockquote"), "");
			newHeader.appendElement("small").html(header.html());
			header.html(newHeader.toString());
			mainContent.prependChild(header);
		}
		Element footer = getFooterTag(doc);
		if (footer != null) {
			Element newFooter = new Element(Tag.valueOf("blockquote"), "");
			newFooter.appendElement("small").html(footer.html());
			footer.html(newFooter.toString());
			mainContent.appendChild(footer);
		}
	}

	private URL getResourceURL(String relativePath) {
		return this.getClass().getResource(relativePath);
	}

	private void addCss(Document doc, String relativePath, URL dataURL,
			IProgressMonitor monitor) throws CoreException, IOException {
		IFile newCss = getContainer().getFile(relativePath);
		// TODO improve this
		createParents((IFolder) newCss.getParent(), monitor);
		newCss.create(dataURL.openStream(), true, monitor);
		doc.head().appendElement("link").attr("href", relativePath)
				.attr("rel", "stylesheet");

	}

	private void addJs(Document doc, String relativePath, URL dataURL,
			IProgressMonitor monitor) throws CoreException, IOException {
		IFile jsFile = getContainer().getFile(relativePath);
		// TODO improve this
		createParents((IFolder) jsFile.getParent(), monitor);
		jsFile.create(dataURL.openStream(), true, monitor);
		doc.head().appendElement("script").attr("src", relativePath);

	}

	private void addOther(Document doc, String relativePath, URL dataURL,
			IProgressMonitor monitor) throws CoreException, IOException {
		IFile otherFile = getContainer().getFile(relativePath);
		// TODO improve this
		createParents((IFolder) otherFile.getParent(), monitor);
		otherFile.create(dataURL.openStream(), true, monitor);

	}

	private void createParents(IContainer iContainer, IProgressMonitor monitor)
			throws CoreException {
		if (!iContainer.exists()) {
			if (iContainer.getParent() instanceof IFolder) {
				createParents(iContainer.getParent(), monitor);
			}
			if (iContainer instanceof IFolder) {
				((IFolder) iContainer).create(true, true, monitor);
			}
		}
	}

	private final class DiagramExporter implements RunnableWithResult<IFile> {
		private final CopyToImageUtil imageUtility;

		private final Diagram realOne;

		private final IProgressMonitor monitor;

		private IStatus status;

		private IFile destinationFile;

		private final DSemanticDiagram diag;

		private DiagramExporter(CopyToImageUtil imageUtility,
				DSemanticDiagram diag, Diagram realOne,
				IProgressMonitor monitor, IFile destinationFile) {
			this.imageUtility = imageUtility;
			this.diag = diag;
			this.realOne = realOne;
			this.monitor = monitor;
			this.destinationFile = destinationFile;
		}

		@Override
		public void run() {
			try {
				createParents((IFolder) destinationFile.getParent(), monitor);
				imageUtility
						.copyToImage(
								realOne,
								destinationFile.getLocation(),
								org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat.PNG,
								monitor, PreferencesHint.USE_DEFAULTS);

			} catch (CoreException e) {
				status = createErrorStatus("Probleme while exporting diagram "
						+ diag.getName(), e);
			}
		}

		@Override
		public void setStatus(IStatus status) {
			this.status = status;
		}

		@Override
		public IStatus getStatus() {
			return status == null ? Status.OK_STATUS : status;
		}

		@Override
		public IFile getResult() {
			// TODO improve this
			return destinationFile;
		}
	}

}
