package view;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Course;
import model.ServiceItem;
import model.Teacher;

public abstract class ServiceItemView extends View {

	protected static final Integer SERVICE_ACTIVE = 1;
	protected static final Integer SERVICE_INACTIVE = 0;

    protected JPanel contentPane = new JPanel();
    protected JTextField nameField = new JTextField();
    protected JFormattedTextField durationField = new JFormattedTextField();
    protected JFormattedTextField valueField = new JFormattedTextField();
    protected JTextArea descriptionField = new JTextArea();
	protected JFrame frame = new JFrame();

	public void buildScreen(ServiceItemView courseFrame, ServiceItem serviceItem){
		createLabelsAndFields(courseFrame, serviceItem);
		createMasks(courseFrame);
		createButtons(courseFrame);
	}

	public abstract void createLabelsAndFields(JFrame frame, ServiceItem serviceItem);
	
	public abstract void createMasks(JFrame frame);
	
	public abstract void createButtons(JFrame frame);

}
