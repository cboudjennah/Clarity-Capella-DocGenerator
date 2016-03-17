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

public class RendererException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4076348035284373220L;

	public RendererException() {
		super();
	}

	public RendererException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RendererException(String message, Throwable cause) {
		super(message, cause);
	}

	public RendererException(String message) {
		super(message);
	}

	public RendererException(Throwable cause) {
		super(cause);
	}

}
