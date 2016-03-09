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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.google.common.collect.Lists;

import fr.obeo.dsl.designer.documentation.generator.DocumentationGeneratorPlugin;
import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.IDocumentationRendererDescriptor;
import fr.obeo.dsl.designer.documentation.generator.ui.DocumentationGeneratorUIPlugin;

public class DocumentationGeneratorDialog extends Dialog {
	private static final String DIALOG_TITLE = "Documentation generator configuration";

	private static final String INVALID_DIRECTORY_MESSAGE = "Directory does not exist";

	private static final String ERROR_ICON = "/icons/error.gif";

	private static final String DIRECTORY_DIALOG_TITLE = "Select the destination folder";

	/** Path of the selected file when invoking the dialog */
	private IPath originalPath;

	/** Chosen directory */
	private IContainer directory;

	/** Combo containing the chosen directory */
//	private Combo comboDirectory;

	/** Combo containing the chosen image file format */
	/** Label used to display the error message image */
	private Label lblImageErrorMessage;

	/** Label used to display the error message */
	private Label lblErrorMessage;

	/** Error message image */
	private static Image errorImage = DocumentationGeneratorUIPlugin.imageDescriptorFromPlugin(
			DocumentationGeneratorUIPlugin.PLUGIN_ID, ERROR_ICON).createImage();

	/** Class used to load and save settings */
	private DocumentationGeneratorDialogSetting settings;

	private final Iterable<IDocumentationGeneratorDescriptor> templates;

	private ComboViewer generatorComboViewer;

	private IDocumentationGeneratorDescriptor selectedGenerator;

	private ComboViewer renderComboviewer;

	private EObject context;

	private IDocumentationRendererDescriptor selectedRenderer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public DocumentationGeneratorDialog(Shell parentShell, IPath path,
			Iterable<IDocumentationGeneratorDescriptor> templates, EObject context) {
		super(parentShell);
		this.originalPath = path;
		this.templates = templates;
		this.context = context;
		this.settings = new DocumentationGeneratorDialogSetting();
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite)super.createDialogArea(parent);
		container.setLayout(new GridLayout(4, false));

//		Label lblToDirectory = new Label(container, SWT.NONE);
//		lblToDirectory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		lblToDirectory.setText("To directory :");
//
//		comboDirectory = new Combo(container, SWT.NONE);
//		comboDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
//		initComboDirectory();
//		comboDirectory.addModifyListener(new ModifyListener() {
//			public void modifyText(ModifyEvent e) {
//				validateDirectory(comboDirectory.getText());
//			}
//		});
//
//		Button btnBrowse = new Button(container, SWT.NONE);
//		btnBrowse.setText("Browse...");
//		btnBrowse.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				handleBrowseButtonPressed();
//			}
//		});

		generatorComboViewer = new ComboViewer(container, SWT.NONE);
		generatorComboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		generatorComboViewer.setContentProvider(new ArrayContentProvider());
		generatorComboViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IDocumentationGeneratorDescriptor)element).getLabel();
			}
		});
		ArrayList<IDocumentationGeneratorDescriptor> templatesList = Lists.newArrayList(templates);
		generatorComboViewer.setInput(templatesList);

		renderComboviewer = new ComboViewer(container, SWT.NONE);
		Composite composite = SWTUtil.createCompositeHorizontalFill(container, 2, false);
		renderComboviewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		renderComboviewer.setContentProvider(new ArrayContentProvider());
		renderComboviewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((IDocumentationRendererDescriptor)element).getLabel();
			}
		});
		if (!templatesList.isEmpty()) {
			// TODO improve this
			IDocumentationGeneratorDescriptor defaultGen = templatesList.get(0);
			generatorComboViewer.setSelection(new StructuredSelection(defaultGen));
			setRenderComboInput(defaultGen);
		}

		((GridData)composite.getLayoutData()).horizontalSpan = 4;
		lblImageErrorMessage = SWTUtil.createLabel(composite, errorImage);
		lblImageErrorMessage.setVisible(false);
		lblErrorMessage = SWTUtil.createLabel(composite, "");
		lblErrorMessage.setVisible(false);

		return container;
	}

	private void setRenderComboInput(IDocumentationGeneratorDescriptor gen) {
		Iterable<IDocumentationRendererDescriptor> renderers = DocumentationGeneratorPlugin.getDefault()
				.getRenderers(gen);
		ArrayList<IDocumentationRendererDescriptor> rendereList = Lists.newArrayList(renderers);
		List<IDocumentationRendererDescriptor> orderedlist = reorderRenderList(rendereList);
		renderComboviewer.setInput(reorderRenderList(orderedlist));
		if (!orderedlist.isEmpty()) {
			renderComboviewer.setSelection(new StructuredSelection(orderedlist.get(0)));
		}

	}
	/**
	 * rearrange renderlist to put the web rendor first (word render is not well tested )
	 * @param rendereList
	 * @return orderedrendereList
	 */
	public List<IDocumentationRendererDescriptor> reorderRenderList(List<IDocumentationRendererDescriptor> rendereList){
		
		ArrayList<IDocumentationRendererDescriptor> orderedrendereList = new ArrayList<IDocumentationRendererDescriptor>();

		IDocumentationRendererDescriptor firstrender = null;
		
		for (IDocumentationRendererDescriptor render: rendereList)
		{
			if (render.getLabel().equals("Web site rendered"))
			{
				firstrender = render;
			}
		}
		if (firstrender!=null)
		{
			orderedrendereList.add(firstrender);
		}
		for (int i=0; i< rendereList.size(); i++)
		{
			if (!rendereList.get(i).equals(firstrender))
				orderedrendereList.add(rendereList.get(i));
		}
		return orderedrendereList!=null ? orderedrendereList: rendereList;
	}

//	/**
//	 * Initialize the directory combo with the selected path and the history
//	 */
//	private void initComboDirectory() {
//		String[] directories = settings.loadDirectories();
//		if (directories.length == 0) {
//			comboDirectory.setText(originalPath.toOSString());
//		} else {
//			comboDirectory.setItems(directories);
//			comboDirectory.select(0);
//		}
//	}

	/**
	 * Set the error message and display it
	 * 
	 * @param msg
	 */
	private void setErrorMessage(String msg) {
		lblImageErrorMessage.setVisible(true);
		lblErrorMessage.setText(msg);
		lblErrorMessage.setVisible(true);
		lblErrorMessage.getParent().layout();
	}

	/**
	 * Hide the error message
	 */
	private void unsetErrorMessage() {
		lblImageErrorMessage.setVisible(false);
		lblErrorMessage.setText("");
		lblImageErrorMessage.setVisible(false);
	}

	@Override
	protected void okPressed() {
//		IResource member = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(
//				new Path(comboDirectory.getText()));
//	
//		
//		// Save data into fields
//		// TODO improve this
//		directory = (IContainer)member;

		// TODO improve this
		selectedGenerator = (IDocumentationGeneratorDescriptor)((IStructuredSelection)generatorComboViewer
				.getSelection()).getFirstElement();

		selectedRenderer = (IDocumentationRendererDescriptor)((IStructuredSelection)renderComboviewer
				.getSelection()).getFirstElement();

//		// Save directories names into history
//		String[] newHistory = settings.addToDirectoriesHistory(settings.loadDirectories(), comboDirectory
//				.getText());
//		settings.saveDirectories(newHistory);

		super.okPressed();
	}

	public IDocumentationGeneratorDescriptor getGeneratorDescriptor() {
		return selectedGenerator;
	}

	public IDocumentationRendererDescriptor getRendererDescriptor() {
		return selectedRenderer;
	}

	/**
	 * Let the user select a destination folder
	 */
	private void handleBrowseButtonPressed() {
		final DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
		dialog.setMessage(DIRECTORY_DIALOG_TITLE);
		dialog.setText(DIRECTORY_DIALOG_TITLE);
		
		dialog.open();

//		final String dirName = comboDirectory.getText();
//		if (!dirName.equals("")) {
//			final File path = new File(dirName);
//			if (path.exists()) {
//				dialog.setFilterPath(new Path(dirName).toOSString());
//			}
//		}
//
//		final String selectedDirectory = dialog.open();
//		if (selectedDirectory != null) {
//			comboDirectory.setText(selectedDirectory);
//		}
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		return settings.getSettings();
	}

	@Override
	protected int getDialogBoundsStrategy() {
		// Keep location and size of dialog
		return DIALOG_PERSISTLOCATION | DIALOG_PERSISTSIZE;
	}

	public IContainer getDirectory() {
		return directory;
	}

	/**
	 * Validate a directory to be sure it really exists. If not an error message is displayed and the Ok
	 * button is disabled.
	 * 
	 * @param directory
	 */
	private void validateDirectory(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			setErrorMessage(INVALID_DIRECTORY_MESSAGE);
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		} else {
			unsetErrorMessage();
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(DIALOG_TITLE);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private static class DocumentationGeneratorDialogSetting {
		private static final int HISTORY_MAX_LENGTH = 5;

		private static String KEY_DIRECTORIES = "DIRECTORIES";

		/**
		 * Retrieve the stored directories
		 * 
		 * @return Array of directories names
		 */
		public String[] loadDirectories() {
			String[] directories = getSettings().getArray(KEY_DIRECTORIES);
			if (directories == null) {
				return new String[0];
			}
			return directories;
		}

		/**
		 * Save the directories
		 * 
		 * @param directories
		 */
		public void saveDirectories(String[] directories) {
			getSettings().put(KEY_DIRECTORIES, directories);
		}

		/**
		 * Add a directory to the history. Avoid duplicates and guarantee the history stays under a limited
		 * size
		 * 
		 * @param history
		 * @param directory
		 * @return
		 */
		public String[] addToDirectoriesHistory(String[] history, String directory) {
			List<String> historyAsList = new ArrayList<String>(Arrays.asList(history));

			// Be sure to avoid duplicates
			historyAsList.remove(directory);
			historyAsList.add(0, directory);
			// Truncate the list as needed
			if (historyAsList.size() > HISTORY_MAX_LENGTH) {
				historyAsList.remove(HISTORY_MAX_LENGTH);
			}
			return historyAsList.toArray(new String[historyAsList.size()]);
		}

		/**
		 * Retrieve the dialog settings for this bundle
		 * 
		 * @return
		 */
		public IDialogSettings getSettings() {
			return DocumentationGeneratorUIPlugin.getDefault().getDialogSettings();
		}
	}

}
