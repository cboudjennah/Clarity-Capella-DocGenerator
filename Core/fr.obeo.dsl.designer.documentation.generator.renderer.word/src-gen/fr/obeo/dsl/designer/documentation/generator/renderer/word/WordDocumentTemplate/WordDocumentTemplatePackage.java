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
package fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplateFactory
 * @model kind="package"
 * @generated
 */
public interface WordDocumentTemplatePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "WordDocumentTemplate";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://DocGen/WordDocumentTemplate/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "wordDocumentTemplate";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WordDocumentTemplatePackage eINSTANCE = fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordDocumentTemplatePackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl <em>Word Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordDocumentTemplatePackageImpl#getWordTemplate()
	 * @generated
	 */
	int WORD_TEMPLATE = 0;

	/**
	 * The feature id for the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE__THEME = 0;

	/**
	 * The feature id for the '<em><b>Css</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE__CSS = 1;

	/**
	 * The feature id for the '<em><b>Color Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE__COLOR_MAPPING = 2;

	/**
	 * The feature id for the '<em><b>Header And Footer Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE = 3;

	/**
	 * The number of structural features of the '<em>Word Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Word Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORD_TEMPLATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>URL</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.net.URL
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordDocumentTemplatePackageImpl#getURL()
	 * @generated
	 */
	int URL = 1;


	/**
	 * Returns the meta object for class '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate <em>Word Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Word Template</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate
	 * @generated
	 */
	EClass getWordTemplate();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getTheme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Theme</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getTheme()
	 * @see #getWordTemplate()
	 * @generated
	 */
	EAttribute getWordTemplate_Theme();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getCss <em>Css</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Css</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getCss()
	 * @see #getWordTemplate()
	 * @generated
	 */
	EAttribute getWordTemplate_Css();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getColorMapping <em>Color Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color Mapping</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getColorMapping()
	 * @see #getWordTemplate()
	 * @generated
	 */
	EAttribute getWordTemplate_ColorMapping();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getHeaderAndFooterTemplate <em>Header And Footer Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Header And Footer Template</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getHeaderAndFooterTemplate()
	 * @see #getWordTemplate()
	 * @generated
	 */
	EAttribute getWordTemplate_HeaderAndFooterTemplate();

	/**
	 * Returns the meta object for data type '{@link java.net.URL <em>URL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URL</em>'.
	 * @see java.net.URL
	 * @model instanceClass="java.net.URL"
	 * @generated
	 */
	EDataType getURL();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WordDocumentTemplateFactory getWordDocumentTemplateFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl <em>Word Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordDocumentTemplatePackageImpl#getWordTemplate()
		 * @generated
		 */
		EClass WORD_TEMPLATE = eINSTANCE.getWordTemplate();

		/**
		 * The meta object literal for the '<em><b>Theme</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORD_TEMPLATE__THEME = eINSTANCE.getWordTemplate_Theme();

		/**
		 * The meta object literal for the '<em><b>Css</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORD_TEMPLATE__CSS = eINSTANCE.getWordTemplate_Css();

		/**
		 * The meta object literal for the '<em><b>Color Mapping</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORD_TEMPLATE__COLOR_MAPPING = eINSTANCE.getWordTemplate_ColorMapping();

		/**
		 * The meta object literal for the '<em><b>Header And Footer Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE = eINSTANCE.getWordTemplate_HeaderAndFooterTemplate();

		/**
		 * The meta object literal for the '<em>URL</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.net.URL
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordDocumentTemplatePackageImpl#getURL()
		 * @generated
		 */
		EDataType URL = eINSTANCE.getURL();

	}

} //WordDocumentTemplatePackage
