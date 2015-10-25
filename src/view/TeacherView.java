package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class TeacherView extends View {
	
	protected JPanel contentPane;
	protected JTextField nameField;
	protected JTextField cpfField;
	protected JTextField rgField;
	protected JTextField cellField;
	protected JTextField phoneField;
	protected JTextField addressField;
	protected JTextField cepField;
	protected JTextField cityField;
	protected JTextField emailField;
	protected JTextField birthdateField;
	protected JTextField motherField;
	protected JTextField fatherField;
	protected JTextField dddCellField;
	protected JTextField dddPhoneField;
	protected JButton registerTeacherBtn;
	protected JTextField issuingInstitutionField;
	protected JTextField ufField;
	protected JTextField numberField;
	protected JTextField complementField;
	protected JTextField qualificationField;
	protected JLabel registerTeacherLbl = new JLabel();
	
	protected JFrame frame = new JFrame();

	public void buildScreen(TeacherView teacherFrame, int fieldStatus){
		createLabelsAndFields(teacherFrame,fieldStatus);
		createMasks(teacherFrame,fieldStatus);
		createButtons(teacherFrame);
	}

	public abstract void createLabelsAndFields(JFrame frame, int fieldStatus);
	
	public abstract void createMasks(JFrame frame, int fieldStatus);
	
	public abstract void createButtons(JFrame frame);



}
