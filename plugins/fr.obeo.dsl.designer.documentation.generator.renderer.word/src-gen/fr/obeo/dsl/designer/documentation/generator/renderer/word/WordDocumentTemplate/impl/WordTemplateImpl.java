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

import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordDocumentTemplatePackage;
import fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.WordTemplate;

import java.net.URL;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Word Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl#getTheme <em>Theme</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl#getCss <em>Css</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl#getColorMapping <em>Color Mapping</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.word.WordDocumentTemplate.impl.WordTemplateImpl#getHeaderAndFooterTemplate <em>Header And Footer Template</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WordTemplateImpl extends MinimalEObjectImpl.Container implements WordTemplate {
	/**
	 * The default value of the '{@link #getTheme() <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTheme()
	 * @generated
	 * @ordered
	 */
	protected static final URL THEME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTheme() <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTheme()
	 * @generated
	 * @ordered
	 */
	protected URL theme = THEME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCss() <em>Css</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCss()
	 * @generated
	 * @ordered
	 */
	protected static final URL CSS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCss() <em>Css</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCss()
	 * @generated
	 * @ordered
	 */
	protected URL css = CSS_EDEFAULT;

	/**
	 * The default value of the '{@link #getColorMapping() <em>Color Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorMapping()
	 * @generated
	 * @ordered
	 */
	protected static final URL COLOR_MAPPING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColorMapping() <em>Color Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColorMapping()
	 * @generated
	 * @ordered
	 */
	protected URL colorMapping = COLOR_MAPPING_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeaderAndFooterTemplate() <em>Header And Footer Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeaderAndFooterTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final URL HEADER_AND_FOOTER_TEMPLATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeaderAndFooterTemplate() <em>Header And Footer Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeaderAndFooterTemplate()
	 * @generated
	 * @ordered
	 */
	protected URL headerAndFooterTemplate = HEADER_AND_FOOTER_TEMPLATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WordTemplateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WordDocumentTemplatePackage.Literals.WORD_TEMPLATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URL getTheme() {
		return theme;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTheme(URL newTheme) {
		URL oldTheme = theme;
		theme = newTheme;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WordDocumentTemplatePackage.WORD_TEMPLATE__THEME, oldTheme, theme));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URL getCss() {
		return css;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCss(URL newCss) {
		URL oldCss = css;
		css = newCss;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WordDocumentTemplatePackage.WORD_TEMPLATE__CSS, oldCss, css));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URL getColorMapping() {
		return colorMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColorMapping(URL newColorMapping) {
		URL oldColorMapping = colorMapping;
		colorMapping = newColorMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WordDocumentTemplatePackage.WORD_TEMPLATE__COLOR_MAPPING, oldColorMapping, colorMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URL getHeaderAndFooterTemplate() {
		return headerAndFooterTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeaderAndFooterTemplate(URL newHeaderAndFooterTemplate) {
		URL oldHeaderAndFooterTemplate = headerAndFooterTemplate;
		headerAndFooterTemplate = newHeaderAndFooterTemplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WordDocumentTemplatePackage.WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE, oldHeaderAndFooterTemplate, headerAndFooterTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WordDocumentTemplatePackage.WORD_TEMPLATE__THEME:
				return getTheme();
			case WordDocumentTemplatePackage.WORD_TEMPLATE__CSS:
				return getCss();
			case WordDocumentTemplatePackage.WORD_TEMPLATE__COLOR_MAPPING:
				return getColorMapping();
			case WordDocumentTemplatePackage.WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE:
				return getHeaderAndFooterTemplate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WordDocumentTemplatePackage.WORD_TEMPLATE__THEME:
				setTheme((URL)newValue);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__CSS:
				setCss((URL)newValue);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__COLOR_MAPPING:
				setColorMapping((URL)newValue);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE:
				setHeaderAndFooterTemplate((URL)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case WordDocumentTemplatePackage.WORD_TEMPLATE__THEME:
				setTheme(THEME_EDEFAULT);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__CSS:
				setCss(CSS_EDEFAULT);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__COLOR_MAPPING:
				setColorMapping(COLOR_MAPPING_EDEFAULT);
				return;
			case WordDocumentTemplatePackage.WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE:
				setHeaderAndFooterTemplate(HEADER_AND_FOOTER_TEMPLATE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case WordDocumentTemplatePackage.WORD_TEMPLATE__THEME:
				return THEME_EDEFAULT == null ? theme != null : !THEME_EDEFAULT.equals(theme);
			case WordDocumentTemplatePackage.WORD_TEMPLATE__CSS:
				return CSS_EDEFAULT == null ? css != null : !CSS_EDEFAULT.equals(css);
			case WordDocumentTemplatePackage.WORD_TEMPLATE__COLOR_MAPPING:
				return COLOR_MAPPING_EDEFAULT == null ? colorMapping != null : !COLOR_MAPPING_EDEFAULT.equals(colorMapping);
			case WordDocumentTemplatePackage.WORD_TEMPLATE__HEADER_AND_FOOTER_TEMPLATE:
				return HEADER_AND_FOOTER_TEMPLATE_EDEFAULT == null ? headerAndFooterTemplate != null : !HEADER_AND_FOOTER_TEMPLATE_EDEFAULT.equals(headerAndFooterTemplate);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (theme: ");
		result.append(theme);
		result.append(", css: ");
		result.append(css);
		result.append(", colorMapping: ");
		result.append(colorMapping);
		result.append(", headerAndFooterTemplate: ");
		result.append(headerAndFooterTemplate);
		result.append(')');
		return result.toString();
	}

} //WordTemplateImpl
