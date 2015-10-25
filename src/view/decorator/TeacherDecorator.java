package view.decorator;

import javax.swing.JFrame;

import view.TeacherView;

public abstract class TeacherDecorator extends TeacherView {
	        
	protected TeacherView viewToDecorate;

	public TeacherDecorator (TeacherView viewToDecorate) {
	   this.viewToDecorate = viewToDecorate;
	}
	 
    @Override
	public void createLabelsAndFields(JFrame frame, int fieldStatus){
    	this.viewToDecorate.createLabelsAndFields(frame,fieldStatus);
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
