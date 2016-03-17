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

import static fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin.logError;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;

import fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;

/**
 * A listener in charge of filling the {@link RendererRegistry}.
 * 
 * @author adaussy
 */
public class RendererExtensionListener extends AbstractRegistryEventListener {

	public static final String DOCUMENTATION_RENDERER_EXT_POINT = "documentationRenderer";

	private static final String DESCRIPTOR_IMPL_ATTR = "impl";

	private final RendererRegistry registry;

	public RendererExtensionListener(ILog log, RendererRegistry registry) {
		super(DocumentationGeneratorPlugin.PLUGIN_ID, DOCUMENTATION_RENDERER_EXT_POINT, log);
		this.registry = registry;
	}

	@Override
	protected boolean validateExtensionElement(IConfigurationElement element) {
		try {
			Object impl = element.createExecutableExtension(DESCRIPTOR_IMPL_ATTR);
			if (impl instanceof IDocumentationRendererDescriptor) {
				return true;
			}
		} catch (CoreException e) {
			logError("Incorrect documentation generator provided from" + element.getContributor().getName(),
					e);
		}
		return false;
	}

	@Override
	protected boolean addedValid(IConfigurationElement element) {
		try {
			registry.add((IDocumentationRendererDescriptor)element
					.createExecutableExtension(DESCRIPTOR_IMPL_ATTR));
		} catch (CoreException e) {
			// Should never happen since check in validateExtension
		}
		return true;
	}

	@Override
	protected boolean removedValid(IConfigurationElement element) {
		return registry.remove(element.getAttribute(DESCRIPTOR_IMPL_ATTR)) != null;
	}

}
