/*******************************************************************************
 * Copyright (c) 2008, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.obeo.dsl.designer.documentation.generator.capella;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import fr.obeo.dsl.designer.documentation.generator.capella.generator.CapellaDocumentationGeneratorCustom;
import fr.obeo.dsl.designer.documentation.generator.generator.AbstractDocumentationGenerator;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends Plugin {
	
	private AbstractDocumentationGenerator currentGeneration;
	
//	private CapellaDocumentationGeneratorCustom currentGeneration;
	

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "fr.obeo.dsl.designer.documentation.generator.capella";

    /**
     * The shared instance.
     */
    private static Activator plugin;
    
    /**
     * The constructor.
     */
    public Activator() {
    	
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }
    
    public static void logError(String message) {
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message));
	}

	public static void logError(String message, Throwable t) {
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, t));
	}

	
//	/**
//	 * @param generator
//	 *            the current runnong generator
//	 */
//	// TODO this is bad it should be imporved
//	public void setCurrentGeneration(CapellaDocumentationGeneratorCustom generator) {
//		this.currentGeneration = generator;
//	}
//
//	
//	public CapellaDocumentationGeneratorCustom getCurrentGeneration() {
//		// TODO this is bad it should be imporved
//		return currentGeneration;
//	}
	
	
	public void setCurrentGeneration(AbstractDocumentationGenerator generator) {
		this.currentGeneration = generator;
	}

	
	public AbstractDocumentationGenerator getCurrentGeneration() {
		// TODO this is bad it should be imporved
		return currentGeneration;
	}

}
