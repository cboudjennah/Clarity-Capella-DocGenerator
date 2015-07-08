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

import java.net.URL;

import org.eclipse.core.resources.IFolder;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplateFactory;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate;

public class WordDocumentationRendererDescriptor implements IDocumentationRendererDescriptor {

	public WordDocumentationRendererDescriptor() {
	}

	@Override
	public String getLabel() {
		return "Word documentation renderer";
	}

	@Override
	public AbstractRenderer createRenderer(IFolder container) {
		return new WordRenderer(container, getDocumentObject());
	}

	@Override
	public boolean appliesOn(IDocumentationGeneratorDescriptor generatorDescriptor) {
		// TODO improve this
		return true;
	}

	public WordTemplate getDocumentObject() {
		WordTemplate wordTemplate = WordDocumentTemplateFactory.eINSTANCE.createWordTemplate();
		wordTemplate.setCss(getResourceURL("resources/wordStyle.css"));
		wordTemplate.setColorMapping(getResourceURL("resources/colorschememapping.xml"));
		wordTemplate.setTheme(getResourceURL("resources/themedata.thmx"));
		wordTemplate.setHeaderAndFooterTemplate(getResourceURL("resources/header.htm"));
		return wordTemplate;
	}

	private URL getResourceURL(String relativePath) {
		return this.getClass().getResource(relativePath);
	}

}
