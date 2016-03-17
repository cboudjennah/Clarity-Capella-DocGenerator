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
package fr.obeo.dsl.designer.gen.html.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;
import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DAnalysis;

import fr.obeo.dsl.designer.gen.html.services.SiriusServices;

/**
 * Entry point of the 'GenerateSite' generation module.
 *
 * @generated
 */
public class GenerateSite extends AbstractAcceleoGenerator {
	
	/**
     * Default value for the exportProjectDependencies argument
     */
    private static final Boolean EXPORT_PROJECT_DEPENDENCIES_DEFAULT_VALUE = false;
    
    /**
     * File format used to export diagrams as images
     */
    private String imageFileFormat = null;
    
    /**
     * The flag used to indicate if properties pages should be generated for Project Dependencies
     */
    private Boolean exportProjectDependencies = null;
	
    /**
     * The name of the module.
     *
     * @generated
     */
    public static final String MODULE_FILE_NAME = "/fr/obeo/dsl/designer/gen/html/main/generateSite";
    
    /**
     * The name of the templates that are to be generated.
     *
     * @generated
     */
    public static final String[] TEMPLATE_NAMES = { "generateAnalysis" };
    
    /**
     * The list of properties files from the launch parameters (Launch configuration).
     *
     * @generated
     */
    private List<String> propertiesFiles = new ArrayList<String>();

    /**
     * Allows the public constructor to be used. Note that a generator created
     * this way cannot be used to launch generations before one of
     * {@link #initialize(EObject, File, List)} or
     * {@link #initialize(URI, File, List)} is called.
     * <p>
     * The main reason for this constructor is to allow clients of this
     * generation to call it from another Java file, as it allows for the
     * retrieval of {@link #getProperties()} and
     * {@link #getGenerationListeners()}.
     * </p>
     *
     * @generated
     */
    public GenerateSite() {
        // Empty implementation
    }

    /**
     * This allows clients to instantiates a generator with all required information.
     * 
     * @param modelURI
     *            URI where the model on which this generator will be used is located.
     * @param targetFolder
     *            This will be used as the output folder for this generation : it will be the base path
     *            against which all file block URLs will be resolved.
     * @param arguments
     *            If the template which will be called requires more than one argument taken from the model,
     *            pass them here.
     * @throws IOException
     *             This can be thrown in three scenarios : the module cannot be found, it cannot be loaded, or
     *             the model cannot be loaded.
     * @generated
     */
    public GenerateSite(URI modelURI, File targetFolder, List<? extends Object> arguments) throws IOException {
        initialize(modelURI, targetFolder, arguments);
    }

    /**
     * This allows clients to instantiates a generator with all required information.
     * 
     * @param model
     *            We'll iterate over the content of this element to find Objects matching the first parameter
     *            of the template we need to call.
     * @param targetFolder
     *            This will be used as the output folder for this generation : it will be the base path
     *            against which all file block URLs will be resolved.
     * @param arguments
     *            If the template which will be called requires more than one argument taken from the model,
     *            pass them here.
     * @throws IOException
     *             This can be thrown in two scenarios : the module cannot be found, or it cannot be loaded.
     * @generated
     */
    public GenerateSite(EObject model, File targetFolder,
            List<? extends Object> arguments) throws IOException {
        initialize(model, targetFolder, arguments);
    }
    
    /**
     * This allows a client to instantiate a generator with all required specific information
     * @param modelURI
     *            URI where the model on which this generator will be used is located.
     * @param targetFolder
     *            This will be used as the output folder for this generation : it will be the base path
     *            against which all file block URLs will be resolved.
     * @param imageFileFormat
     *            File format used to export diagrams as images
     * @param exportProjectDependencies
     *            Flag to indicate if project dependencies should be exported
     * @throws IOException
     *             This can be thrown in three scenarios : the module cannot be found, it cannot be loaded, or
     *             the model cannot be loaded.
     */
    public GenerateSite(URI modelURI, File targetFolder, String imageFileFormat, Boolean exportProjectDependencies) throws IOException {
    	this(modelURI, targetFolder, null);
    	setImageFileFormat(imageFileFormat);
    	setExportProjectDependencies(exportProjectDependencies);
    }
    
    /**
     * This can be used to launch the generation from a standalone application.
     * 
     * @param args
     *            Arguments of the generation.
     * @generated
     */
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("Arguments not valid : {model, folder}.");
            } else {
                URI modelURI = URI.createFileURI(args[0]);
                File folder = new File(args[1]);
                
                List<String> arguments = new ArrayList<String>();
                
                /*
                 * If you want to change the content of this method, do NOT forget to change the "@generated"
                 * tag in the Javadoc of this method to "@generated NOT". Without this new tag, any compilation
                 * of the Acceleo module with the main template that has caused the creation of this class will
                 * revert your modifications.
                 */

                /*
                 * Add in this list all the arguments used by the starting point of the generation
                 * If your main template is called on an element of your model and a String, you can
                 * add in "arguments" this "String" attribute.
                 */
                
                GenerateSite generator = new GenerateSite(modelURI, folder, arguments);
                
                /*
                 * Add the properties from the launch arguments.
                 * If you want to programmatically add new properties, add them in "propertiesFiles"
                 * You can add the absolute path of a properties files, or even a project relative path.
                 * If you want to add another "protocol" for your properties files, please override 
                 * "getPropertiesLoaderService(AcceleoService)" in order to return a new property loader.
                 * The behavior of the properties loader service is explained in the Acceleo documentation
                 * (Help -> Help Contents).
                 */
                 
                for (int i = 2; i < args.length; i++) {
                    generator.addPropertiesFile(args[i]);
                }
                
                generator.doGenerate(new BasicMonitor());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the generation described by this instance.
     * 
     * @param monitor
     *            This will be used to display progress information to the user.
     * @throws IOException
     *             This will be thrown if any of the output files cannot be saved to disk.
     * @generated NOT
     */
    @Override
    public void doGenerate(Monitor monitor) throws IOException {
        /*
         * TODO if you wish to change the generation as a whole, override this. The default behavior should
         * be sufficient in most cases. If you want to change the content of this method, do NOT forget to
         * change the "@generated" tag in the Javadoc of this method to "@generated NOT". Without this new tag,
         * any compilation of the Acceleo module with the main template that has caused the creation of this
         * class will revert your modifications. If you encounter a problem with an unresolved proxy during the
         * generation, you can remove the comments in the following instructions to check for problems. Please
         * note that those instructions may have a significant impact on the performances.
         */

        //org.eclipse.emf.ecore.util.EcoreUtil.resolveAll(model);

        /*
         * If you want to check for potential errors in your models before the launch of the generation, you
         * use the code below.
         */

        //if (model != null && model.eResource() != null) {
        //    List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> errors = model.eResource().getErrors();
        //    for (org.eclipse.emf.ecore.resource.Resource.Diagnostic diagnostic : errors) {
        //        System.err.println(diagnostic.toString());
        //    }
        //}
    	
    	/*
    	 * Open a session before generating
    	 */
    	URI modelURI = getModel().eResource().getURI();
    	Session viewpointSession = SessionManager.INSTANCE.getSession(modelURI, new NullProgressMonitor());
    	boolean sessionAlreadyOpened = viewpointSession.isOpen();
		if (!sessionAlreadyOpened) {
			viewpointSession.open(new NullProgressMonitor());
		}
		
		super.doGenerate(monitor);
		
		/*
		 * Close the session after the generation if we did open it ourselves
		 */
        if (!sessionAlreadyOpened) {
        	viewpointSession.close(new NullProgressMonitor());
        }
    }
    
    /**
     * If this generator needs to listen to text generation events, listeners can be returned from here.
     * 
     * @return List of listeners that are to be notified when text is generated through this launch.
     * @generated
     */
    @Override
    public List<IAcceleoTextGenerationListener> getGenerationListeners() {
        List<IAcceleoTextGenerationListener> listeners = super.getGenerationListeners();
        /*
         * TODO if you need to listen to generation event, add listeners to the list here. If you want to change
         * the content of this method, do NOT forget to change the "@generated" tag in the Javadoc of this method
         * to "@generated NOT". Without this new tag, any compilation of the Acceleo module with the main template
         * that has caused the creation of this class will revert your modifications.
         */
        return listeners;
    }
    
    /**
     * If you need to change the way files are generated, this is your entry point.
     * <p>
     * The default is {@link org.eclipse.acceleo.engine.generation.strategy.DefaultStrategy}; it generates
     * files on the fly. If you only need to preview the results, return a new
     * {@link org.eclipse.acceleo.engine.generation.strategy.PreviewStrategy}. Both of these aren't aware of
     * the running Eclipse and can be used standalone.
     * </p>
     * <p>
     * If you need the file generation to be aware of the workspace (A typical example is when you wanna
     * override files that are under clear case or any other VCS that could forbid the overriding), then
     * return a new {@link org.eclipse.acceleo.engine.generation.strategy.WorkspaceAwareStrategy}.
     * <b>Note</b>, however, that this <b>cannot</b> be used standalone.
     * </p>
     * <p>
     * All three of these default strategies support merging through JMerge.
     * </p>
     * 
     * @return The generation strategy that is to be used for generations launched through this launcher.
     * @generated
     */
    @Override
    public IAcceleoGenerationStrategy getGenerationStrategy() {
        return super.getGenerationStrategy();
    }
    
    /**
     * This will be called in order to find and load the module that will be launched through this launcher.
     * We expect this name not to contain file extension, and the module to be located beside the launcher.
     * 
     * @return The name of the module that is to be launched.
     * @generated
     */
    @Override
    public String getModuleName() {
        return MODULE_FILE_NAME;
    }
    
    /**
     * If the module(s) called by this launcher require properties files, return their qualified path from
     * here.Take note that the first added properties files will take precedence over subsequent ones if they
     * contain conflicting keys.
     * 
     * @return The list of properties file we need to add to the generation context.
     * @see java.util.ResourceBundle#getBundle(String)
     * @generated
     */
    @Override
    public List<String> getProperties() {
        /*
         * If you want to change the content of this method, do NOT forget to change the "@generated"
         * tag in the Javadoc of this method to "@generated NOT". Without this new tag, any compilation
         * of the Acceleo module with the main template that has caused the creation of this class will
         * revert your modifications.
         */

        /*
         * TODO if your generation module requires access to properties files, add their qualified path to the list here.
         * 
         * Properties files can be located in an Eclipse plug-in or in the file system (all Acceleo projects are Eclipse
         * plug-in). In order to use properties files located in an Eclipse plugin, you need to add the path of the properties
         * files to the "propertiesFiles" list:
         * 
         * final String prefix = "platform:/plugin/";
         * final String pluginName = "org.eclipse.acceleo.module.sample";
         * final String packagePath = "/org/eclipse/acceleo/module/sample/properties/";
         * final String fileName = "default.properties";
         * propertiesFiles.add(prefix + pluginName + packagePath + fileName);
         * 
         * With this mechanism, you can load properties files from your plugin or from another plugin.
         * 
         * You may want to load properties files from the file system, for that you need to add the absolute path of the file:
         * 
         * propertiesFiles.add("C:\Users\MyName\MyFile.properties");
         * 
         * If you want to let your users add properties files located in the same folder as the model:
         *
         * if (EMFPlugin.IS_ECLIPSE_RUNNING && model != null && model.eResource() != null) { 
         *     propertiesFiles.addAll(AcceleoEngineUtils.getPropertiesFilesNearModel(model.eResource()));
         * }
         * 
         * To learn more about Properties Files, have a look at the Acceleo documentation (Help -> Help Contents).
         */
        return propertiesFiles;
    }
    
    /**
     * Adds a properties file in the list of properties files.
     * 
     * @param propertiesFile
     *            The properties file to add.
     * @generated
     * @since 3.1
     */
    @Override
    public void addPropertiesFile(String propertiesFile) {
        this.propertiesFiles.add(propertiesFile);
    }
    
    /**
     * This will be used to get the list of templates that are to be launched by this launcher.
     * 
     * @return The list of templates to call on the module {@link #getModuleName()}.
     * @generated
     */
    @Override
    public String[] getTemplateNames() {
        return TEMPLATE_NAMES;
    }
    
    /**
     * This can be used to update the resource set's package registry with all needed EPackages.
     * 
     * @param resourceSet
     *            The resource set which registry has to be updated.
     * @generated
     */
    @Override
    public void registerPackages(ResourceSet resourceSet) {
        super.registerPackages(resourceSet);
        if (!isInWorkspace(org.eclipse.emf.ecore.EcorePackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getNsURI(), org.eclipse.emf.ecore.EcorePackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.ViewpointPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.ViewpointPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.ViewpointPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.description.DescriptionPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.description.style.StylePackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.viewpoint.description.audit.AuditPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.viewpoint.description.audit.AuditPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.viewpoint.description.audit.AuditPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.DiagramPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.DiagramPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.description.DescriptionPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.description.style.StylePackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.description.tool.ToolPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.description.filter.FilterPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.description.filter.FilterPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.description.filter.FilterPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.diagram.description.concern.ConcernPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.diagram.description.concern.ConcernPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.diagram.description.concern.ConcernPackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.table.metamodel.table.TablePackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE.getNsURI(), org.eclipse.sirius.table.metamodel.table.TablePackage.eINSTANCE);
        }
        if (!isInWorkspace(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.class)) {
            resourceSet.getPackageRegistry().put(org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE.getNsURI(), org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE);
        }
        
        /*
         * If you want to change the content of this method, do NOT forget to change the "@generated"
         * tag in the Javadoc of this method to "@generated NOT". Without this new tag, any compilation
         * of the Acceleo module with the main template that has caused the creation of this class will
         * revert your modifications.
         */
        
        /*
         * If you need additional package registrations, you can register them here. The following line
         * (in comment) is an example of the package registration for UML.
         * 
         * You can use the method  "isInWorkspace(Class c)" to check if the package that you are about to
         * register is in the workspace.
         * 
         * To register a package properly, please follow the following conventions:
         *
         * If the package is located in another plug-in, already installed in Eclipse. The following content should
         * have been generated at the beginning of this method. Do not register the package using this mechanism if
         * the metamodel is located in the workspace.
         *  
         * if (!isInWorkspace(UMLPackage.class)) {
         *     // The normal package registration if your metamodel is in a plugin.
         *     resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
         * }
         * 
         * If the package is located in another project in your workspace, the plugin containing the package has not
         * been register by EMF and Acceleo should register it automatically. If you want to use the generator in
         * stand alone, the regular registration (seen a couple lines before) is needed.
         * 
         * To learn more about Package Registration, have a look at the Acceleo documentation (Help -> Help Contents).
         */
    }

    /**
     * This can be used to update the resource set's resource factory registry with all needed factories.
     * 
     * @param resourceSet
     *            The resource set which registry has to be updated.
     * @generated
     */
    @Override
    public void registerResourceFactories(ResourceSet resourceSet) {
        super.registerResourceFactories(resourceSet);
        /*
         * If you want to change the content of this method, do NOT forget to change the "@generated"
         * tag in the Javadoc of this method to "@generated NOT". Without this new tag, any compilation
         * of the Acceleo module with the main template that has caused the creation of this class will
         * revert your modifications.
         */
        
        /*
         * TODO If you need additional resource factories registrations, you can register them here. the following line
         * (in comment) is an example of the resource factory registration for UML.
         *
         * If you want to use the generator in stand alone, the resource factory registration will be required.
         *  
         * To learn more about the registration of Resource Factories, have a look at the Acceleo documentation (Help -> Help Contents). 
         */ 
        
        // resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
    }

    /**
	 * Initialize the arguments list.
	 * Arguments should include :
	 * - target folder path
	 * - a booolean to indicate if project dependencies should be exported
	 * @return list containing the 2 arguments
	 * @see org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator#getArguments()
	 */
	@Override
	public List<? extends Object> getArguments() {
		List<Object> args = new ArrayList<Object>();
		args.add(getTargetFolder().getPath());
		// File format used for exported diagrams images
		if (imageFileFormat != null) {
			args.add(imageFileFormat);
		} else {
			// Default value is JPG
			args.add(ImageFileFormat.JPG.getName());
		}
		// Flag to indicate if project dependencies should be exported
		if (exportProjectDependencies != null) {
			args.add(exportProjectDependencies);
		} else {
			// Default value used for standalone tests
			args.add(EXPORT_PROJECT_DEPENDENCIES_DEFAULT_VALUE);
		}
		return args;
	}

	/**
	 * Retrieves the root DAnalysis instance so the generator works for shared and not shared modeling projects
	 * @see org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator#initialize(org.eclipse.emf.common.util.URI, java.io.File, java.util.List)
	 */
	@Override
	public void initialize(URI modelURI, File folder, List<?> arguments) throws IOException {
        // Get the root analysis in the viewpoint context
		DAnalysis rootAnalysis = SiriusServices.getRootAnalysis(modelURI);
		initialize(rootAnalysis, folder, arguments);
	}
	
	public void setImageFileFormat(String imageFileFormat) {
		this.imageFileFormat = imageFileFormat;
	}

	public void setExportProjectDependencies(Boolean exportProjectDependencies) {
		this.exportProjectDependencies = exportProjectDependencies;
	}
	
}