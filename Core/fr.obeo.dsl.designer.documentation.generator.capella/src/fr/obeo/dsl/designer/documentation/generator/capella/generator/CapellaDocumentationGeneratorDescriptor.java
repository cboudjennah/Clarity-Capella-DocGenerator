package fr.obeo.dsl.designer.documentation.generator.capella.generator;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

import fr.obeo.dsl.designer.documentation.generator.capella.Activator;
import fr.obeo.dsl.designer.documentation.generator.generator.AbstractDocumentationGenerator;
import fr.obeo.dsl.designer.documentation.generator.generator.IDocumentationGeneratorDescriptor;
import fr.obeo.dsl.designer.documentation.generator.renderer.AbstractRenderer;

public class CapellaDocumentationGeneratorDescriptor implements
		IDocumentationGeneratorDescriptor {

	public CapellaDocumentationGeneratorDescriptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliesOn(EObject context) {
		// TODO Auto-generated method stub
		return context instanceof CapellaElement;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Capella documentation generator";
	}

	@Override
	public AbstractDocumentationGenerator createGenerator(
			AbstractRenderer renderer) {
		CapellaDocumentationGeneratorCustom currentAbstDocGen = new CapellaDocumentationGeneratorCustom(renderer);
		Activator.getDefault().setCurrentGeneration(currentAbstDocGen);
		return currentAbstDocGen;
	}

}
