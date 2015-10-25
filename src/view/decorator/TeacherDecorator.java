package view.decorator;

import javax.swing.JFrame;

import model.Teacher;
import view.TeacherView;

public abstract class TeacherDecorator extends TeacherView {
	        
	protected TeacherView viewToDecorate;

	public TeacherDecorator (TeacherView viewToDecorate) {
	   this.viewToDecorate = viewToDecorate;
	}
	 
    @Override
	public void createLabelsAndFields(JFrame frame, int fieldStatus, Teacher teacher){
    	this.viewToDecorate.createLabelsAndFields(frame,fieldStatus,teacher);
    }

	@Override
	public void createMasks(JFrame frame,int fieldStatus){
		this.viewToDecorate.createMasks(frame,fieldStatus);
	}
	
	@Override
	public void createButtons(JFrame frame){
		this.viewToDecorate.createButtons(frame);
	}
}
