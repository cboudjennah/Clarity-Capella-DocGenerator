/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplateFactory
 * @model kind="package"
 * @generated
 */
public interface WebSiteTemplatePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "WebSiteTemplate";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://DocGen/WebSiteTemplate/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "webSiteTemplate";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebSiteTemplatePackage eINSTANCE = fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl <em>Web Site Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getWebSiteTemplate()
	 * @generated
	 */
	int WEB_SITE_TEMPLATE = 0;

	/**
	 * The feature id for the '<em><b>Stylesheet Files</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_SITE_TEMPLATE__STYLESHEET_FILES = 0;

	/**
	 * The feature id for the '<em><b>Js Files</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_SITE_TEMPLATE__JS_FILES = 1;

	/**
	 * The feature id for the '<em><b>Others</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_SITE_TEMPLATE__OTHERS = 2;

	/**
	 * The number of structural features of the '<em>Web Site Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_SITE_TEMPLATE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Web Site Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_SITE_TEMPLATE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.ExternalResourceImpl <em>External Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.ExternalResourceImpl
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getExternalResource()
	 * @generated
	 */
	int EXTERNAL_RESOURCE = 1;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RESOURCE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RESOURCE__TO = 1;

	/**
	 * The number of structural features of the '<em>External Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RESOURCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>External Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '<em>URL</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.net.URL
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getURL()
	 * @generated
	 */
	int URL = 2;


	/**
	 * Returns the meta object for class '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate <em>Web Site Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Web Site Template</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate
	 * @generated
	 */
	EClass getWebSiteTemplate();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getStylesheetFiles <em>Stylesheet Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stylesheet Files</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getStylesheetFiles()
	 * @see #getWebSiteTemplate()
	 * @generated
	 */
	EReference getWebSiteTemplate_StylesheetFiles();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getJsFiles <em>Js Files</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Js Files</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getJsFiles()
	 * @see #getWebSiteTemplate()
	 * @generated
	 */
	EReference getWebSiteTemplate_JsFiles();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getOthers <em>Others</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Others</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getOthers()
	 * @see #getWebSiteTemplate()
	 * @generated
	 */
	EReference getWebSiteTemplate_Others();

	/**
	 * Returns the meta object for class '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource <em>External Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Resource</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource
	 * @generated
	 */
	EClass getExternalResource();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getFrom()
	 * @see #getExternalResource()
	 * @generated
	 */
	EAttribute getExternalResource_From();

	/**
	 * Returns the meta object for the attribute '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getTo()
	 * @see #getExternalResource()
	 * @generated
	 */
	EAttribute getExternalResource_To();

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
	WebSiteTemplateFactory getWebSiteTemplateFactory();

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
		 * The meta object literal for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl <em>Web Site Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateImpl
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getWebSiteTemplate()
		 * @generated
		 */
		EClass WEB_SITE_TEMPLATE = eINSTANCE.getWebSiteTemplate();

		/**
		 * The meta object literal for the '<em><b>Stylesheet Files</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_SITE_TEMPLATE__STYLESHEET_FILES = eINSTANCE.getWebSiteTemplate_StylesheetFiles();

		/**
		 * The meta object literal for the '<em><b>Js Files</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_SITE_TEMPLATE__JS_FILES = eINSTANCE.getWebSiteTemplate_JsFiles();

		/**
		 * The meta object literal for the '<em><b>Others</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEB_SITE_TEMPLATE__OTHERS = eINSTANCE.getWebSiteTemplate_Others();

		/**
		 * The meta object literal for the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.ExternalResourceImpl <em>External Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.ExternalResourceImpl
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getExternalResource()
		 * @generated
		 */
		EClass EXTERNAL_RESOURCE = eINSTANCE.getExternalResource();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_RESOURCE__FROM = eINSTANCE.getExternalResource_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_RESOURCE__TO = eINSTANCE.getExternalResource_To();

		/**
		 * The meta object literal for the '<em>URL</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.net.URL
		 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplatePackageImpl#getURL()
		 * @generated
		 */
		EDataType URL = eINSTANCE.getURL();

	}

} //WebSiteTemplatePackage
