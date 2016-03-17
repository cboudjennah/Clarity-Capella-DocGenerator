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
package fr.obeo.dsl.designer.documentation.generator;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.internal.extensions.GeneratorExtensionListener;
import fr.obeo.dsl.designer.documentation.generator.internal.extensions.GeneratorRegistry;
import fr.obeo.dsl.designer.documentation.generator.internal.extensions.RendererExtensionListener;
import fr.obeo.dsl.designer.documentation.generator.internal.extensions.RendererRegistry;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;

/**
 * The activator class controls the plug-in life cycle
 */
public class DocumentationGeneratorPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "fr.obeo.dsl.designer.documentation.generator"; //$NON-NLS-1$

	// The shared instance
	private static DocumentationGeneratorPlugin plugin;

	private GeneratorRegistry generatorRegistry;

	private GeneratorExtensionListener generatorExtensionListener;

	private RendererRegistry rendererRegistry;

	private RendererExtensionListener rendererExtensionListener;

	/**
	 * The constructor
	 */
	public DocumentationGeneratorPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		final IExtensionRegistry registry = Platform.getExtensionRegistry();

		generatorRegistry = new GeneratorRegistry();
		generatorExtensionListener = new GeneratorExtensionListener(getLog(), generatorRegistry);
		registry.addListener(generatorExtensionListener, PLUGIN_ID + '.'
				+ GeneratorExtensionListener.DOCUMENTATION_GENERATOR_EXT_POINT);
		generatorExtensionListener.readRegistry(registry);

		rendererRegistry = new RendererRegistry();
		rendererExtensionListener = new RendererExtensionListener(getLog(), rendererRegistry);
		registry.addListener(rendererExtensionListener, PLUGIN_ID + '.'
				+ RendererExtensionListener.DOCUMENTATION_RENDERER_EXT_POINT);
		rendererExtensionListener.readRegistry(registry);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);

		final IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.removeListener(generatorExtensionListener);
		generatorExtensionListener = null;
		generatorRegistry = null;

		registry.removeListener(rendererExtensionListener);
		rendererExtensionListener = null;
		rendererRegistry = null;

	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static DocumentationGeneratorPlugin getDefault() {
		return plugin;
	}

	public Iterable<IDocumentationGeneratorDescriptor> getGenerators(EObject context) {
		return generatorRegistry.getGenerators(context);
	}

	public Iterable<IDocumentationRendererDescriptor> getRenderers(IDocumentationGeneratorDescriptor genDesc) {
		return rendererRegistry.getRenderers(genDesc);

	}

	public static IStatus createErrorStatus(String message, Throwable e) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message, e);
	}

	public static IStatus createWarningStatus(String message) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message);
	}

	public static IStatus createErrorStatus(String message) {
		return new Status(IStatus.ERROR, PLUGIN_ID, message);
	}

	/**
	 * Helper method to log something.
	 * 
	 * @param message
	 */
	public static void logError(String message) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message));
	}

	/**
	 * Helper method to log something.
	 * 
	 * @param message
	 */
	public static void logError(String message, Throwable t) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, t));
	}

	public static void logInfo(String message) {
		getDefault().getLog().log(new Status(IStatus.INFO, PLUGIN_ID, message));
	}

}
