package view.decorator;

import javax.swing.JFrame;

import model.Person;
import model.Teacher;
import view.PersonView;

public abstract class TeacherDecorator extends PersonView {
	        
	protected PersonView viewToDecorate;

	public TeacherDecorator (PersonView viewToDecorate) {
	   this.viewToDecorate = viewToDecorate;
	}
	 
    @Override
	public void createLabelsAndFields(JFrame frame, Person teacher){
    	this.viewToDecorate.createLabelsAndFields(frame, teacher);
    }

	@Override
	public void createMasks(JFrame frame){
		this.viewToDecorate.createMasks(frame);
	}
	
	@Override
	public void createButtons(JFrame frame){
		this.viewToDecorate.createButtons(frame);
	}
}
