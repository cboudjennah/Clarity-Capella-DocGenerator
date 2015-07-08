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

import org.apache.commons.lang.StringEscapeUtils;

/**
 * Provide services around Strings
 * @author sthibaudeau
 *
 */
public class StringUtils {

	/**
	 * Escape a message so it can be safely embedded inside HTML code 
	 * @param message
	 * @return Escaped message
	 */
	public static String escapeHtml(String message) {
		return StringEscapeUtils.escapeHtml(message);
	}
	
	/**
	 * Replace forbidden characters with "_" in a filename
	 * @param name
	 * @return sanitized string
	 */
	public static String sanitizeFilename(String name) {
		return name.replaceAll("[:\\\\/*?|<>]", "_");
	}
	
	/**
	 * Repeat a string "count" times
	 * @param msg
	 * @param count
	 * @return concatenated string
	 */
	public static String repeat(String msg, int count) {
		return org.apache.commons.lang.StringUtils.repeat(msg, count);
	}
	
}
