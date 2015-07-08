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

import java.net.URL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Word Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getTheme <em>Theme</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getCss <em>Css</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getColorMapping <em>Color Mapping</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getHeaderAndFooterTemplate <em>Header And Footer Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#getWordTemplate()
 * @model
 * @generated
 */
public interface WordTemplate extends EObject {
	/**
	 * Returns the value of the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Theme</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Theme</em>' attribute.
	 * @see #setTheme(URL)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#getWordTemplate_Theme()
	 * @model dataType="fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.URL"
	 * @generated
	 */
	URL getTheme();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getTheme <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Theme</em>' attribute.
	 * @see #getTheme()
	 * @generated
	 */
	void setTheme(URL value);

	/**
	 * Returns the value of the '<em><b>Css</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Css</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Css</em>' attribute.
	 * @see #setCss(URL)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#getWordTemplate_Css()
	 * @model dataType="fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.URL"
	 * @generated
	 */
	URL getCss();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getCss <em>Css</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Css</em>' attribute.
	 * @see #getCss()
	 * @generated
	 */
	void setCss(URL value);

	/**
	 * Returns the value of the '<em><b>Color Mapping</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color Mapping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color Mapping</em>' attribute.
	 * @see #setColorMapping(URL)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#getWordTemplate_ColorMapping()
	 * @model dataType="fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.URL"
	 * @generated
	 */
	URL getColorMapping();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getColorMapping <em>Color Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color Mapping</em>' attribute.
	 * @see #getColorMapping()
	 * @generated
	 */
	void setColorMapping(URL value);

	/**
	 * Returns the value of the '<em><b>Header And Footer Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header And Footer Template</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header And Footer Template</em>' attribute.
	 * @see #setHeaderAndFooterTemplate(URL)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage#getWordTemplate_HeaderAndFooterTemplate()
	 * @model dataType="fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.URL"
	 * @generated
	 */
	URL getHeaderAndFooterTemplate();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate#getHeaderAndFooterTemplate <em>Header And Footer Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header And Footer Template</em>' attribute.
	 * @see #getHeaderAndFooterTemplate()
	 * @generated
	 */
	void setHeaderAndFooterTemplate(URL value);

} // WordTemplate
