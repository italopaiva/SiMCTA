package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Teacher;

public abstract class TeacherView extends View {
	
	protected JPanel contentPane;
	protected JTextField nameField = new JTextField();
    protected JTextField cpfField = new JTextField();
    protected JTextField rgField = new JTextField();
    protected JTextField cellField = new JTextField();
    protected JTextField phoneField = new JTextField();
    protected JTextField addressField = new JTextField();
    protected JTextField cepField = new JTextField();
    protected JTextField cityField = new JTextField();
    protected JTextField emailField = new JTextField();
    protected JTextField birthdateField = new JTextField();
    protected JTextField motherField = new JTextField();
    protected JTextField fatherField = new JTextField();
    protected JTextField dddCellField = new JTextField();
    protected JTextField dddPhoneField = new JTextField();
    protected JTextField issuingInstitutionField = new JTextField();
    protected JTextField ufField = new JTextField();
    protected JTextField numberField = new JTextField();
    protected JTextField complementField = new JTextField();
    protected JTextField qualificationField = new JTextField();
	protected JLabel registerTeacherLbl = new JLabel();
	
	protected JFrame frame = new JFrame();

	public void buildScreen(TeacherView teacherFrame, Teacher teacher){
		createLabelsAndFields(teacherFrame, teacher);
		createMasks(teacherFrame);
		createButtons(teacherFrame);
	}

	public abstract void createLabelsAndFields(JFrame frame, Teacher teacher);
	
	public abstract void createMasks(JFrame frame);
	
	public abstract void createButtons(JFrame frame);

}
