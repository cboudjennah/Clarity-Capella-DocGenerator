package fr.obeo.dsl.designer.documentation.generator.renderer.website.internal;

import java.net.URL;

import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.ecore.EObject;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplateFactory;

public class WebSiteRendererDescriptor implements IDocumentationRendererDescriptor {

	@Override
	public String getLabel() {
		return "Web site rendered";
	}

	@Override
	public boolean appliesOn(IDocumentationGeneratorDescriptor generatorDescriptor) {
		return true;
	}

	@Override
	public AbstractRenderer createRenderer(IFolder container) {
		return new WebSiteRenderer(container, getDocumentObject());
	}

	public WebSiteTemplate getDocumentObject() {
		WebSiteTemplate template = WebSiteTemplateFactory.eINSTANCE.createWebSiteTemplate();
		addCssFile(template, "resources/bootstrap.css", "css/bootstrap.css");
		addCssFile(template, "resources/bootstrap-theme-flatty.css", "css/bootstrap-theme-flatty.css");
		addJsFile(template, "resources/jquery.js", "js/jquery.js");
		addJsFile(template, "resources/bootstrap.min.js", "js/bootstrap.min.js");
		// Leads to correct the hyper link bug
		// addJsFile(document, "resources/bugcorrection.js", "js/bugcorrection.js");
		addOtherFile(template, "resources/bootstrap.css.map", "css/bootstrap.css.map");
		addOtherFile(template, "resources/glyphicons-halflings-regular.eot",
				"font/glyphicons-halflings-regular.eot");
		addOtherFile(template, "resources/glyphicons-halflings-regular.svg",
				"font/glyphicons-halflings-regular.svg");
		addOtherFile(template, "resources/glyphicons-halflings-regular.ttf",
				"font/glyphicons-halflings-regular.ttf");
		addOtherFile(template, "resources/glyphicons-halflings-regular.woff",
				"font/glyphicons-halflings-regular.woff");
		addOtherFile(template, "resources/glyphicons-halflings-regular.woff2",
				"font/glyphicons-halflings-regular.woff2");
		return template;
	}

	private void addCssFile(WebSiteTemplate document, String relativeURL, String to) {
		ExternalResource createExternalResource = WebSiteTemplateFactory.eINSTANCE.createExternalResource();
		createExternalResource.setFrom(getResourceURL(relativeURL));
		createExternalResource.setTo(to);
		document.getStylesheetFiles().add(createExternalResource);
	}

	private void addJsFile(WebSiteTemplate document, String relativeURL, String to) {
		ExternalResource createExternalResource = WebSiteTemplateFactory.eINSTANCE.createExternalResource();
		createExternalResource.setFrom(getResourceURL(relativeURL));
		createExternalResource.setTo(to);
		document.getJsFiles().add(createExternalResource);
	}

	private void addOtherFile(WebSiteTemplate document, String relativeURL, String to) {
		ExternalResource createExternalResource = WebSiteTemplateFactory.eINSTANCE.createExternalResource();
		createExternalResource.setFrom(getResourceURL(relativeURL));
		createExternalResource.setTo(to);
		document.getOthers().add(createExternalResource);
	}

	private URL getResourceURL(String relativePath) {
		return this.getClass().getResource(relativePath);
	}
}
