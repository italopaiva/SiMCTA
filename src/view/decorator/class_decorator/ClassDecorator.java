package view.decorator.class_decorator;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import datatype.CPF;
import model.Class;
import view.ClassView;

public abstract class ClassDecorator extends ClassView {
	
	protected static final String COULDNT_LOAD_TEACHERS = "Não foi possível carregar os professores do banco de dados. Será mantido o professor atual.";
	
	protected Map<String, CPF> teachersMap = new HashMap<String, CPF>();
	protected ClassView classViewToDecorate;

	
	public ClassDecorator(ClassView viewToDecorate){
		this.classViewToDecorate = viewToDecorate;
	}

	@Override
	public void createLabelsAndFields(JFrame frame, Class classInstance){
		this.classViewToDecorate.createLabelsAndFields(frame, classInstance);
	}

	@Override
	public void createMasks(JFrame frame){
		this.classViewToDecorate.createMasks(frame);
	}

	@Override
	public void createButtons(JFrame frame){
		this.classViewToDecorate.createButtons(frame);
	}
}
