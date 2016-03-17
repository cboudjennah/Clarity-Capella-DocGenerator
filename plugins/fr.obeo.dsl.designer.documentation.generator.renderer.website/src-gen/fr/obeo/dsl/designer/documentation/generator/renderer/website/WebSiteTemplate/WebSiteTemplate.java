/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Site Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getStylesheetFiles <em>Stylesheet Files</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getJsFiles <em>Js Files</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplate#getOthers <em>Others</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getWebSiteTemplate()
 * @model
 * @generated
 */
public interface WebSiteTemplate extends EObject {
	/**
	 * Returns the value of the '<em><b>Stylesheet Files</b></em>' containment reference list.
	 * The list contents are of type {@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stylesheet Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stylesheet Files</em>' containment reference list.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getWebSiteTemplate_StylesheetFiles()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalResource> getStylesheetFiles();

	/**
	 * Returns the value of the '<em><b>Js Files</b></em>' containment reference list.
	 * The list contents are of type {@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Js Files</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Js Files</em>' containment reference list.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getWebSiteTemplate_JsFiles()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalResource> getJsFiles();

	/**
	 * Returns the value of the '<em><b>Others</b></em>' containment reference list.
	 * The list contents are of type {@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Others</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Others</em>' containment reference list.
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getWebSiteTemplate_Others()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalResource> getOthers();

} // WebSiteTemplate
