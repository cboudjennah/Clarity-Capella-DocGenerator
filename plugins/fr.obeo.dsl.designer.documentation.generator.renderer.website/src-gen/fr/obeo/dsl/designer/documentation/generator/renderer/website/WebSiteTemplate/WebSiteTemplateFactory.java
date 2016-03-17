/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage
 * @generated
 */
public interface WebSiteTemplateFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WebSiteTemplateFactory eINSTANCE = fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.impl.WebSiteTemplateFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Web Site Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Web Site Template</em>'.
	 * @generated
	 */
	WebSiteTemplate createWebSiteTemplate();

	/**
	 * Returns a new object of class '<em>External Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Resource</em>'.
	 * @generated
	 */
	ExternalResource createExternalResource();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WebSiteTemplatePackage getWebSiteTemplatePackage();

} //WebSiteTemplateFactory
