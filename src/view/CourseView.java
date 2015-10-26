package view;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Course;
import model.Teacher;

public abstract class CourseView extends View {

    protected JPanel contentPane = new JPanel();
    protected JTextField courseNameField = new JTextField();
    protected JFormattedTextField durationField = new JFormattedTextField();
    protected JFormattedTextField valueField = new JFormattedTextField();
    protected JTextArea descriptionField = new JTextArea();
	protected JFrame frame = new JFrame();

	public void buildScreen(CourseView courseFrame, Course course){
		createLabelsAndFields(courseFrame, course);
		createMasks(courseFrame);
		createButtons(courseFrame);
	}

	public abstract void createLabelsAndFields(JFrame frame, Course course);
	
	public abstract void createMasks(JFrame frame);
	
	public abstract void createButtons(JFrame frame);

}
