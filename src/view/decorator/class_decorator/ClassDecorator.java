package view.decorator.class_decorator;

import javax.swing.JFrame;

import model.Class;
import view.ClassView;

public abstract class ClassDecorator extends ClassView {
	
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
