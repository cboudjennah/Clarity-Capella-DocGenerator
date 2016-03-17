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

import org.eclipse.core.resources.IFolder;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;

/**
 * A descriptor of renderer.
 * 
 * @author adaussy
 */
public interface IDocumentationRendererDescriptor {

	/**
	 * @return a human readable label used in UI.
	 */
	String getLabel();

	/**
	 * @param context
	 * @param generatorDescriptor
	 * @return <code>true</code> if this render can be used.
	 */
	boolean appliesOn(IDocumentationGeneratorDescriptor generatorDescriptor);

	/**
	 * @return a new instance of {@link IDocumentationRenderer}
	 */
	AbstractRenderer createRenderer(IFolder container);

}
