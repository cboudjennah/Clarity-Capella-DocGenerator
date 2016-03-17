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
import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;

/**
 * Listener in charge of filling the {@link GeneratorRegistry}.
 * 
 * @author adaussy
 */
public class GeneratorExtensionListener extends AbstractRegistryEventListener {

	public static final String DOCUMENTATION_GENERATOR_EXT_POINT = "documentationGenerator";

	private static final String DESCRIPTOR_IMPL_ATTR = "impl";

	private final GeneratorRegistry registry;

	public GeneratorExtensionListener(ILog log, GeneratorRegistry registry) {
		super(DocumentationGeneratorPlugin.PLUGIN_ID, DOCUMENTATION_GENERATOR_EXT_POINT, log);
		this.registry = registry;
	}

	@Override
	protected boolean validateExtensionElement(IConfigurationElement element) {
		try {
			Object impl = element.createExecutableExtension(DESCRIPTOR_IMPL_ATTR);
			if (impl instanceof IDocumentationGeneratorDescriptor) {
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
			registry.add((IDocumentationGeneratorDescriptor)element
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
