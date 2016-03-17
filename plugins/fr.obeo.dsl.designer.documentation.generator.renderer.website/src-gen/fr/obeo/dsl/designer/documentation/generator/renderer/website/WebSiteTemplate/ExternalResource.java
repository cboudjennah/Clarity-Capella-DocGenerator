/**
 */
package fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate;

import java.net.URL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getFrom <em>From</em>}</li>
 *   <li>{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getExternalResource()
 * @model
 * @generated
 */
public interface ExternalResource extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(URL)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getExternalResource_From()
	 * @model dataType="fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.URL" required="true"
	 * @generated
	 */
	URL getFrom();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(URL value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.WebSiteTemplatePackage#getExternalResource_To()
	 * @model
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link fr.obeo.dsl.designer.documentation.generator.renderer.website.WebSiteTemplate.ExternalResource#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

} // ExternalResource
