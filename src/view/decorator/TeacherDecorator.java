package view.decorator;

import javax.swing.JFrame;

import view.TeacherView;

public abstract class TeacherDecorator extends TeacherView {
	        
	protected TeacherView viewToDecorate;

	public TeacherDecorator (TeacherView viewToDecorate) {
	   this.viewToDecorate = viewToDecorate;
	}
	 
    @Override
	public void createLabelsAndFields(JFrame frame){
    	this.viewToDecorate.createLabelsAndFields(frame);
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
