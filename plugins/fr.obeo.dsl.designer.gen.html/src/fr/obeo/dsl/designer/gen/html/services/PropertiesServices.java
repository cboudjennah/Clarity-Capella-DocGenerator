/**
 * Copyright  2012 Obeo. All Rights Reserved.
 *
 * This file is part of Obeo Designer.
 *
 * This software and the attached documentation are the exclusive ownership
 * of its authors and was conceded to the profit of Obeo SARL.
 * This software and the attached documentation are protected under the rights
 * of intellectual ownership, including the section "Titre II  Droits des auteurs (Articles L121-1, L123-12)"
 * By installing this software, you acknowledge being aware of this rights and
 * accept them, and as a consequence you must:
 * - be in possession of a valid license of use conceded by Obeo only.
 * - agree that you have read, understood, and will comply with the license terms and conditions.
 * - agree not to do anything that could conflict with intellectual ownership owned by Obeo or its beneficiaries
 * or the authors of this software
 *
 * Should you not agree with these terms, you must stop to use this software and give it back to its legitimate owner.
 *
 * Acceleo and Obeo are trademarks owned by Obeo.
 */
package fr.obeo.dsl.designer.gen.html.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * Provide a service to build an HTML table displaying the properties of an EObject 
 * @author sthibaudeau
 *
 */
public class PropertiesServices {

	// PropertySourceProvider used to retrieve the properties to be displayed for an EObject
	private IPropertySourceProvider propertySourceProvider = null;

	/**
	 * Build and return the HTML code corresponding to a table displaying the properties of an EObject
	 * @param object
	 * @return HTML code of a table
	 */
	public String getPropertiesTable(EObject object) {
		StringBuffer table = new StringBuffer();
		IPropertySource propertySource = getPropertySourceProvider().getPropertySource(object);
		
		// Get all descriptors names and corresponding descriptors
		Map<String, IPropertyDescriptor> descriptors = new HashMap<String, IPropertyDescriptor>();
		for (IPropertyDescriptor propertyDesc : propertySource.getPropertyDescriptors()) {
			descriptors.put(propertyDesc.getDisplayName(), propertyDesc);
		}
		
		// Sort the names
		List<String> sortedNames = new ArrayList<String>(descriptors.keySet());
		Collections.sort(sortedNames);
		
		// Now build the table with this order
		for (String name : sortedNames) {
			IPropertyDescriptor propertyDesc = descriptors.get(name);
			Object propertyValue = propertySource.getPropertyValue(propertyDesc.getId());
			String value = "";
			if (propertyValue instanceof PropertyValueWrapper) {
				value = ((PropertyValueWrapper) propertyValue).getText(object);
			}
			table.append("<tr>");
			if (!"".equals(value)) {
				table.append("<th>");
			} else {
				table.append("<th class=\"empty\">");
			}
			
			// Add a tooltip if there is a description
			String description = propertyDesc.getDescription();
			if (description != null && !"".equals(description)) {
				table.append("<a href=\"#\" title=\"");
				table.append(StringUtils.escapeHtml(description));
				table.append("\">");
			}
			table.append(StringUtils.escapeHtml(name).replaceAll(" ", "&nbsp;"));
			if (description != null && !"".equals(description)) {
				table.append("</a>");
			}
			table.append("&nbsp;:</th><td>");
			table.append(StringUtils.escapeHtml(value));
			table.append("</td></tr>");
		}
		
		return table.toString();
	}
	
	/**
	 * Get and initialize if need the property source provider
	 * @return
	 */
	private IPropertySourceProvider getPropertySourceProvider() {
		if (propertySourceProvider == null) {
			propertySourceProvider = new AdapterFactoryContentProvider(new ComposedAdapterFactory(LabelProviderServices.getAdapterFactory()));
		}
		return propertySourceProvider;
	}
}
