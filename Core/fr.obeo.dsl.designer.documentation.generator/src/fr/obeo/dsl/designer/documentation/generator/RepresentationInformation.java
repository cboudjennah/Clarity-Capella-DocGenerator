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
package fr.obeo.dsl.designer.documentation.generator;

import org.eclipse.sirius.viewpoint.DRepresentation;

public class RepresentationInformation<T extends DRepresentation> {

	private final String id;

	private final String relativePath;

	private final T represenation;

	public RepresentationInformation(T represenation, String relativePath, String id) {
		this.represenation = represenation;
		this.relativePath = relativePath;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public T getRepresenation() {
		return represenation;
	}

}
