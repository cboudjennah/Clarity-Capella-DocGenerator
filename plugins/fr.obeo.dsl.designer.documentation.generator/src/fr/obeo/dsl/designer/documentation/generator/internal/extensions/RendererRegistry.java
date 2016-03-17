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
package fr.obeo.dsl.designer.documentation.generator.internal.extensions;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;

/**
 * A registry of {@link IDocumentationRenderer}
 * 
 * @author adaussy
 */
public class RendererRegistry {

	private Map<String, IDocumentationRendererDescriptor> renderers = new HashMap<String, IDocumentationRendererDescriptor>();

	IDocumentationRendererDescriptor add(IDocumentationRendererDescriptor newGen) {
		return renderers.put(newGen.getClass().getCanonicalName(), newGen);
	}

	IDocumentationRendererDescriptor remove(String id) {
		return renderers.remove(id);
	}

	public Iterable<IDocumentationRendererDescriptor> getRenderers(
			final IDocumentationGeneratorDescriptor genDesc) {
		return Iterables.filter(renderers.values(), new Predicate<IDocumentationRendererDescriptor>() {

			@Override
			public boolean apply(IDocumentationRendererDescriptor arg0) {
				return arg0.appliesOn(genDesc);
			}
		});
	}

}
