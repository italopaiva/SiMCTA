package view.decorator;

import javax.swing.JFrame;

import model.Course;
import model.ServiceItem;
import view.ServiceItemView;

public class ServiceItemDecorator extends ServiceItemView {

	private ServiceItemView viewToDecorate;

	public ServiceItemDecorator(ServiceItemView viewToDecorate){
		this.viewToDecorate = viewToDecorate;
	}
	
	@Override
	public void createLabelsAndFields(JFrame frame, ServiceItem serviceItem) {
		this.viewToDecorate.createLabelsAndFields(frame, serviceItem);
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
