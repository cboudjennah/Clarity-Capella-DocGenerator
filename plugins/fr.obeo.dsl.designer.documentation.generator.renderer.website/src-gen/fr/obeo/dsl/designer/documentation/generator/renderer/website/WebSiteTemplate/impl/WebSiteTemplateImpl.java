/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl;

import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Web Site Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl#getStylesheetFiles <em>Stylesheet Files</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl#getJsFiles <em>Js Files</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WebSiteTemplateImpl extends MinimalEObjectImpl.Container implements WebSiteTemplate {
	/**
	 * The cached value of the '{@link #getStylesheetFiles() <em>Stylesheet Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStylesheetFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalResource> stylesheetFiles;

	/**
	 * The cached value of the '{@link #getJsFiles() <em>Js Files</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJsFiles()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalResource> jsFiles;

	/**
	 * The cached value of the '{@link #getOthers() <em>Others</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOthers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalResource> others;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebSiteTemplateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebSiteTemplatePackage.Literals.WEB_SITE_TEMPLATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalResource> getStylesheetFiles() {
		if (stylesheetFiles == null) {
			stylesheetFiles = new EObjectContainmentEList<ExternalResource>(ExternalResource.class, this, WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES);
		}
		return stylesheetFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalResource> getJsFiles() {
		if (jsFiles == null) {
			jsFiles = new EObjectContainmentEList<ExternalResource>(ExternalResource.class, this, WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES);
		}
		return jsFiles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalResource> getOthers() {
		if (others == null) {
			others = new EObjectContainmentEList<ExternalResource>(ExternalResource.class, this, WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS);
		}
		return others;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES:
				return ((InternalEList<?>)getStylesheetFiles()).basicRemove(otherEnd, msgs);
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES:
				return ((InternalEList<?>)getJsFiles()).basicRemove(otherEnd, msgs);
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS:
				return ((InternalEList<?>)getOthers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES:
				return getStylesheetFiles();
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES:
				return getJsFiles();
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS:
				return getOthers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES:
				getStylesheetFiles().clear();
				getStylesheetFiles().addAll((Collection<? extends ExternalResource>)newValue);
				return;
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES:
				getJsFiles().clear();
				getJsFiles().addAll((Collection<? extends ExternalResource>)newValue);
				return;
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS:
				getOthers().clear();
				getOthers().addAll((Collection<? extends ExternalResource>)newValue);
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
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES:
				getStylesheetFiles().clear();
				return;
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES:
				getJsFiles().clear();
				return;
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS:
				getOthers().clear();
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
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__STYLESHEET_FILES:
				return stylesheetFiles != null && !stylesheetFiles.isEmpty();
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__JS_FILES:
				return jsFiles != null && !jsFiles.isEmpty();
			case WebSiteTemplatePackage.WEB_SITE_TEMPLATE__OTHERS:
				return others != null && !others.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //WebSiteTemplateImpl
