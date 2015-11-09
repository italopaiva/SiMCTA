package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Class;

public abstract class ClassView extends View {
	
	protected JFrame frame = new JFrame();
	
	protected JFormattedTextField startDateField;
	protected JTextField classIdField;
	protected JComboBox<String> availableCourses;
	protected DefaultComboBoxModel<String> availableCoursesModel;
	protected JLabel classCourseLbl;
	protected JLabel classTeacherLbl;
	protected JComboBox<String> availableTeachers;
	protected DefaultComboBoxModel<String> availableTeachersModel;
	protected JLabel classShiftLbl;
	protected JComboBox<String> shifts;
	protected DefaultComboBoxModel<String> shiftsModel;
	protected JLabel startDateLbl;
	protected JLabel classIdLbl;
	protected JLabel titleLbl;
	protected JButton actionBtn;

	public void buildScreen(JFrame classFrame, Class classInstance){
		createLabelsAndFields(classFrame, classInstance);
		createMasks(classFrame);
		createButtons(classFrame);
	}

	public abstract void createLabelsAndFields(JFrame frame, Class classInstance);
	
	public abstract void createMasks(JFrame frame);
	
	public abstract void createButtons(JFrame frame);	
}
