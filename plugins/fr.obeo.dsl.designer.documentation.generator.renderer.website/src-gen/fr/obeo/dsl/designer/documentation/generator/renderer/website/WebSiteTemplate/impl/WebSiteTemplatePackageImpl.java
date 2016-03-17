/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl;

import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplateFactory;
import fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WebSiteTemplatePackageImpl extends EPackageImpl implements WebSiteTemplatePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass webSiteTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalResourceEClass = null;

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
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WebSiteTemplatePackageImpl() {
		super(eNS_URI, WebSiteTemplateFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link WebSiteTemplatePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WebSiteTemplatePackage init() {
		if (isInited) return (WebSiteTemplatePackage)EPackage.Registry.INSTANCE.getEPackage(WebSiteTemplatePackage.eNS_URI);

		// Obtain or create and register package
		WebSiteTemplatePackageImpl theWebSiteTemplatePackage = (WebSiteTemplatePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WebSiteTemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WebSiteTemplatePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theWebSiteTemplatePackage.createPackageContents();

		// Initialize created meta-data
		theWebSiteTemplatePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWebSiteTemplatePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WebSiteTemplatePackage.eNS_URI, theWebSiteTemplatePackage);
		return theWebSiteTemplatePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWebSiteTemplate() {
		return webSiteTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebSiteTemplate_StylesheetFiles() {
		return (EReference)webSiteTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebSiteTemplate_JsFiles() {
		return (EReference)webSiteTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWebSiteTemplate_Others() {
		return (EReference)webSiteTemplateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalResource() {
		return externalResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalResource_From() {
		return (EAttribute)externalResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExternalResource_To() {
		return (EAttribute)externalResourceEClass.getEStructuralFeatures().get(1);
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
	public WebSiteTemplateFactory getWebSiteTemplateFactory() {
		return (WebSiteTemplateFactory)getEFactoryInstance();
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
		webSiteTemplateEClass = createEClass(WEB_SITE_TEMPLATE);
		createEReference(webSiteTemplateEClass, WEB_SITE_TEMPLATE__STYLESHEET_FILES);
		createEReference(webSiteTemplateEClass, WEB_SITE_TEMPLATE__JS_FILES);
		createEReference(webSiteTemplateEClass, WEB_SITE_TEMPLATE__OTHERS);

		externalResourceEClass = createEClass(EXTERNAL_RESOURCE);
		createEAttribute(externalResourceEClass, EXTERNAL_RESOURCE__FROM);
		createEAttribute(externalResourceEClass, EXTERNAL_RESOURCE__TO);

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

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(webSiteTemplateEClass, WebSiteTemplate.class, "WebSiteTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWebSiteTemplate_StylesheetFiles(), this.getExternalResource(), null, "stylesheetFiles", null, 0, -1, WebSiteTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWebSiteTemplate_JsFiles(), this.getExternalResource(), null, "jsFiles", null, 0, -1, WebSiteTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWebSiteTemplate_Others(), this.getExternalResource(), null, "others", null, 0, -1, WebSiteTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalResourceEClass, ExternalResource.class, "ExternalResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalResource_From(), this.getURL(), "from", null, 1, 1, ExternalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExternalResource_To(), theEcorePackage.getEString(), "to", null, 0, 1, ExternalResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(urlEDataType, java.net.URL.class, "URL", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //WebSiteTemplatePackageImpl
