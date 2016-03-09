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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.operation.IRunnableWithProgress;

import fr.obeo.dsl.designer.documentation.generator.generator.AbstractDocumentationGenerator;
import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;

/**
 * Runnable in charge of generating the documentation.
 * 
 * @author adaussy
 */
public class GenerationRunnable implements IRunnableWithProgress {

	private static final String GENERATED_DOCS_FOLDER = "generated_docs";

	private final EObject input;

	private List<IStatus> statuses = new ArrayList<IStatus>();

	private final IDocumentationGeneratorDescriptor generatorDescriptor;

	private final IDocumentationRendererDescriptor rendererDescriptor;

	private final IContainer container;

	private List<Object> extraArguments = new ArrayList<Object>();

	public GenerationRunnable(IDocumentationGeneratorDescriptor generatorDescriptor,
			IDocumentationRendererDescriptor rendererDescriptor, EObject input, IContainer container) {
		this.generatorDescriptor = generatorDescriptor;
		this.rendererDescriptor = rendererDescriptor;
		this.input = input;
		this.container = container;
	}

	public List<Object> getExtraArguments() {
		return extraArguments;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		try {
			IFolder documentationFolder = container.getFolder(new Path(GENERATED_DOCS_FOLDER));
			if (!documentationFolder.exists()) {
				documentationFolder.create(true, true, monitor);
			}
			IFolder templateFolder = documentationFolder.getFolder(new Path(generatorDescriptor.getLabel()));
			if (!templateFolder.exists()) {
				templateFolder.create(true, true, monitor);
			}

			IFolder rendererFolder = templateFolder.getFolder(rendererDescriptor.getLabel());
			if (rendererFolder.exists()) {
				rendererFolder.delete(true, monitor);
			}
			rendererFolder.create(true, true, monitor);
			AbstractDocumentationGenerator generator = generatorDescriptor.createGenerator(rendererDescriptor
					.createRenderer(rendererFolder));
			// Generates semantic files
			generator.initialize(input, new File(rendererFolder.getLocation().toOSString()),
					getExtraArguments());
			generator.doGenerate(BasicMonitor.toMonitor(monitor));
			statuses = generator.getGenerationStrategy().getStatus();
			rendererFolder.refreshLocal(IFolder.DEPTH_INFINITE, monitor);
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}

	public List<IStatus> getStatuses() {
		return statuses;
	}

}
