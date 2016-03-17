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
package fr.obeo.dsl.designer.documentation.generator.generator;

import org.eclipse.emf.ecore.EObject;

import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;

/**
 * A descriptor of generator.
 * @author adaussy
 *
 */
public interface IDocumentationGeneratorDescriptor {
	
	/**
	 * @param context
	 * @return <code>true</code> if matching generator is able to handle such an object, <code>false</code> otherwise.
	 */
	boolean appliesOn(EObject context);
	
	/**
	 * @return a human readable label for UI
	 */
	String getLabel();
	
	/**
	 * @return a new instance of the generator.
	 */
	AbstractDocumentationGenerator createGenerator(AbstractRenderer renderer);

}
