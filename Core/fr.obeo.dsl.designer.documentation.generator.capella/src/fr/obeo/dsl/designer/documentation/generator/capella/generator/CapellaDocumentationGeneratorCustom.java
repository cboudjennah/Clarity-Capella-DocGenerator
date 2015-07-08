/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.designer.documentation.generator.capella.generator;

import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;

/**
 */
public class CapellaDocumentationGeneratorCustom
		extends
		fr.obeo.dsl.designer.documentation.generator.generator.AbstractDocumentationGenerator {
	/**
	 * The name of the module.
	 *
	 * @generated
	 */
	public static final String MODULE_FILE_NAME = "/fr/obeo/dsl/designer/documentation/generator/capella/templates/ComponentTemplate";

	/**
	 * The name of the templates that are to be generated.
	 *
	 * @generated
	 */
	public static final String[] TEMPLATE_NAMES = { "ComponentTemplate" };

	/**
	 * @generated not
	 */
	public CapellaDocumentationGeneratorCustom(AbstractRenderer renderer) {
		super(renderer);
	}

	@Override
	public String getModuleName() {
		return MODULE_FILE_NAME;
	}

	/**
	 * This will be used to get the list of templates that are to be launched by
	 * this launcher.
	 * 
	 * @return The list of templates to call on the module
	 *         {@link #getModuleName()}.
	 * @generated
	 */
	@Override
	public String[] getTemplateNames() {
		return TEMPLATE_NAMES;
	}

	/**
	 * This can be used to update the resource set's package registry with all
	 * needed EPackages.
	 * 
	 * @param resourceSet
	 *            The resource set which registry has to be updated.
	 * @generated
	 */
	@Override
	public void registerPackages(ResourceSet resourceSet) {
		super.registerPackages(resourceSet);
		if (!isInWorkspace(org.eclipse.emf.ecore.EcorePackage.class)) {
			resourceSet.getPackageRegistry().put(
					org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getNsURI(),
					org.eclipse.emf.ecore.EcorePackage.eINSTANCE);
		}
		
		}

		/*
		 * If you want to change the content of this method, do NOT forget to
		 * change the "@generated" tag in the Javadoc of this method to
		 * "@generated NOT". Without this new tag, any compilation of the
		 * Acceleo module with the main template that has caused the creation of
		 * this class will revert your modifications.
		 */

		/*
		 * If you need additional package registrations, you can register them
		 * here. The following line (in comment) is an example of the package
		 * registration for UML.
		 * 
		 * You can use the method "isInWorkspace(Class c)" to check if the
		 * package that you are about to register is in the workspace.
		 * 
		 * To register a package properly, please follow the following
		 * conventions:
		 * 
		 * If the package is located in another plug-in, already installed in
		 * Eclipse. The following content should have been generated at the
		 * beginning of this method. Do not register the package using this
		 * mechanism if the metamodel is located in the workspace.
		 * 
		 * if (!isInWorkspace(UMLPackage.class)) { // The normal package
		 * registration if your metamodel is in a plugin.
		 * resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI,
		 * UMLPackage.eINSTANCE); }
		 * 
		 * If the package is located in another project in your workspace, the
		 * plugin containing the package has not been register by EMF and
		 * Acceleo should register it automatically. If you want to use the
		 * generator in stand alone, the regular registration (seen a couple
		 * lines before) is needed.
		 * 
		 * To learn more about Package Registration, have a look at the Acceleo
		 * documentation (Help -> Help Contents).
		 */
	}


