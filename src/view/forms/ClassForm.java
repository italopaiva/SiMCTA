package view.forms;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.ClassView;
import model.Class;

// Default form
public class ClassForm extends ClassView {
	public ClassForm() {
	}

	@Override
	public void createLabelsAndFields(JFrame frame, Class classInstance){
		
		frame.getContentPane().setLayout(null);
		
		startDateLbl = new JLabel("* Data de início");
		startDateLbl.setBounds(600, 170, 128, 15);
		frame.getContentPane().add(startDateLbl);
		
		classIdLbl = new JLabel("Código da turma");
		classIdLbl.setBounds(286, 42, 122, 15);
		frame.getContentPane().add(classIdLbl);
			
		classCourseLbl = new JLabel("* Curso da turma");
		classCourseLbl.setBounds(286, 101, 128, 15);
		frame.getContentPane().add(classCourseLbl);
		
		classTeacherLbl = new JLabel("* Professor da turma");
		classTeacherLbl.setBounds(286, 170, 180, 15);
		frame.getContentPane().add(classTeacherLbl);
		
		classShiftLbl = new JLabel("* Turno");
		classShiftLbl.setBounds(600, 101, 70, 15);
		frame.getContentPane().add(classShiftLbl);
	}

	@Override
	public void createMasks(JFrame frame){
		
	}

	@Override
	public void createButtons(JFrame frame){
		
	}

}
