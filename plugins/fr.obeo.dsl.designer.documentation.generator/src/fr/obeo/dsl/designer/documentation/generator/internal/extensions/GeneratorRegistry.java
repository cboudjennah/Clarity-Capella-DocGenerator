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

import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;

/**
 * A registry of {@link IDocumentationGeneratorDescriptor}.
 * 
 * @author adaussy
 */
public class GeneratorRegistry {

	private Map<String, IDocumentationGeneratorDescriptor> generators = new HashMap<String, IDocumentationGeneratorDescriptor>();

	IDocumentationGeneratorDescriptor add(IDocumentationGeneratorDescriptor newGen) {
		return generators.put(newGen.getClass().getCanonicalName(), newGen);
	}

	IDocumentationGeneratorDescriptor remove(String id) {
		return generators.remove(id);
	}

	public Iterable<IDocumentationGeneratorDescriptor> getGenerators(final EObject context) {
		return Iterables.filter(generators.values(), new Predicate<IDocumentationGeneratorDescriptor>() {

			@Override
			public boolean apply(IDocumentationGeneratorDescriptor arg0) {
				return arg0.appliesOn(context);
			}
		});
	}

}
