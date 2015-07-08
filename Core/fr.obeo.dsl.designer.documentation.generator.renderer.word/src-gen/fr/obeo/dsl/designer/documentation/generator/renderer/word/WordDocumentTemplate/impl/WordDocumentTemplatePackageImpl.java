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
package fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl;

import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplateFactory;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WordDocumentTemplatePackageImpl extends EPackageImpl implements WordDocumentTemplatePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wordTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType urlEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WordDocumentTemplatePackageImpl() {
		super(eNS_URI, WordDocumentTemplateFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link WordDocumentTemplatePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WordDocumentTemplatePackage init() {
		if (isInited) return (WordDocumentTemplatePackage)EPackage.Registry.INSTANCE.getEPackage(WordDocumentTemplatePackage.eNS_URI);

		// Obtain or create and register package
		WordDocumentTemplatePackageImpl theWordDocumentTemplatePackage = (WordDocumentTemplatePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WordDocumentTemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WordDocumentTemplatePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theWordDocumentTemplatePackage.createPackageContents();

		// Initialize created meta-data
		theWordDocumentTemplatePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWordDocumentTemplatePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WordDocumentTemplatePackage.eNS_URI, theWordDocumentTemplatePackage);
		return theWordDocumentTemplatePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWordTemplate() {
		return wordTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWordTemplate_Theme() {
		return (EAttribute)wordTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWordTemplate_Css() {
		return (EAttribute)wordTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWordTemplate_ColorMapping() {
		return (EAttribute)wordTemplateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWordTemplate_HeaderAndFooterTemplate() {
		return (EAttribute)wordTemplateEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getURL() {
		return urlEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WordDocumentTemplateFactory getWordDocumentTemplateFactory() {
		return (WordDocumentTemplateFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		wordTemplateEClass = createEClass(WORD_TEMPLATE);
		createEAttribute(wordTemplateEClass, WORD_TEMPLATE__THEME);
		createEAttribute(wordTemplateEClass, WORD_TEMPLATE__CSS);
		createEAttribute(wordTemplateEClass, WORD_TEMPLATE__COLOR_MAPPING);
		createEAttribute(wordTemplateEClass, WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE);

		// Create data types
		urlEDataType = createEDataType(URL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(wordTemplateEClass, WordTemplate.class, "WordTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWordTemplate_Theme(), this.getURL(), "theme", null, 0, 1, WordTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWordTemplate_Css(), this.getURL(), "css", null, 0, 1, WordTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWordTemplate_ColorMapping(), this.getURL(), "colorMapping", null, 0, 1, WordTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWordTemplate_HeaderAndFooterTemplate(), this.getURL(), "headerAndFooterTemplate", null, 0, 1, WordTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(urlEDataType, java.net.URL.class, "URL", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //WordDocumentTemplatePackageImpl
