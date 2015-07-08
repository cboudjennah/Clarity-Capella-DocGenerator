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
package fr.obeo.dsl.designer.documentation.generator.ui.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin;
import fr.obeo.dsl.designer.documentation.generator.GenerationRunnable;
import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;
import fr.obeo.dsl.designer.documentation.generator.ui.DocumentationGeneratorUIPlugin;

public class SiriusDocumentationHandler extends AbstractHandler {

	public static final String ERROR_MESSAGE = "Errors occured during the generation. See error log view for more details.";

	public static final String CANCEL_MESSAGE = "Generation has been canceled. Files may have been partially generated";

	public static final String DIALOG_TITLE = "Documentation generation";

	public static final String DEFAULT_ERROR_MESSAGE = "Error during documentaiton generation";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection selec = (IStructuredSelection) selection;
			Object selectedElement = selec.getFirstElement();

			if (selectedElement instanceof EObject) {
				final EObject context = (EObject) selectedElement;
				Shell shell = HandlerUtil.getActiveShell(event);
				DocumentationGeneratorDialog dialog = new DocumentationGeneratorDialog(
						shell, getResource(context).getProject().getLocation(),
						DocumentationGeneratorPlugin.getDefault()
								.getGenerators(context), context);
				if (dialog.open() == Dialog.OK) {
					IDocumentationGeneratorDescriptor generatorDescriptor = dialog
							.getGeneratorDescriptor();
					IDocumentationRendererDescriptor rendererDescriptor = dialog
							.getRendererDescriptor();

					if (generatorDescriptor != null
							&& rendererDescriptor != null) {
						GenerationRunnable launcher = new GenerationRunnable(
								generatorDescriptor,//
								rendererDescriptor, context, //
								dialog.getDirectory());
						try {
							PlatformUI.getWorkbench().getProgressService()
									.run(true, true, launcher);

							boolean success = true;
							for (IStatus s : launcher.getStatuses()) {
								if (!s.isOK()) {
									success = false;
								}
								DocumentationGeneratorPlugin.getDefault()
										.getLog().log(s);
							}

							if (success) {
								// Message to indicate end of generation
								MessageDialog.openInformation(
										HandlerUtil.getActiveShell(event),
										"Documentation generation",
										"Representations file was successfully exported into "
												+ dialog.getDirectory());
							}
						} catch (InvocationTargetException e) {
							logException(e);
							MessageDialog.openError(
									HandlerUtil.getActiveShell(event),
									DIALOG_TITLE, ERROR_MESSAGE);
						} catch (InterruptedException e) {
							MessageDialog.openWarning(
									HandlerUtil.getActiveShell(event),
									DIALOG_TITLE, CANCEL_MESSAGE);
						}
					} else {
						MessageDialog
								.openError(HandlerUtil.getActiveShell(event),
										"Uncorrect selection",
										"Can not launch a documentation generation with the current configuration");
						return null;
					}

				}
			}

		}
		return null;
	}

	/**
	 * Utility method to log an error when an exception occurs
	 * 
	 * @param e
	 *            Exception
	 */
	private void logException(Exception e) {
		String message = e.getMessage();
		if (message == null || message.trim().length() == 0) {
			message = DEFAULT_ERROR_MESSAGE;
		}
		IStatus status = new Status(IStatus.ERROR,
				DocumentationGeneratorUIPlugin.PLUGIN_ID, message, e);
		DocumentationGeneratorUIPlugin.getDefault().getLog().log(status);
	}

	private IResource getResource(EObject eObject) {
		Resource eResource = getSession(eObject).getSessionResource();
		URI eUri = eResource.getURI();
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			return ResourcesPlugin.getWorkspace().getRoot()
					.findMember(platformString);
		}
		return null;
	}

	private Session getSession(EObject eObject) {
		URI uri = eObject.eResource().getURI();
		Session session = SessionManager.INSTANCE.getExistingSession(uri);
		if (session == null) {
			// TODO improve this: Should never happen
			session = SessionManager.INSTANCE.getSession(eObject);
		}
		return session;
	}

}
