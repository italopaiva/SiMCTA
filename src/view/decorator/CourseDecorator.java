package view.decorator;

import javax.swing.JFrame;

import model.Course;
import view.CourseView;

public class CourseDecorator extends CourseView {

	private CourseView viewToDecorate;

	public CourseDecorator(CourseView viewToDecorate){
		this.viewToDecorate = viewToDecorate;
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, Course course) {
		this.viewToDecorate.createLabelsAndFields(frame, course);
	}

	@Override
	public void createMasks(JFrame frame) {
		this.viewToDecorate.createMasks(frame);		
	}

	@Override
	public void createButtons(JFrame frame) {
		this.viewToDecorate.createButtons(frame);				
	}

}
