package view.decorator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.Teacher;
import view.SearchTeacher;
import view.TeacherView;

public class ShowTeacherDecorator extends TeacherDecorator {

	private JButton searchTeacherBtn;
	private JButton backBtn;

	public ShowTeacherDecorator(TeacherView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, int fieldStatus, Teacher teacher) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, fieldStatus, teacher);
		createButtons(frame);
	}
	
	@Override
	public void createButtons(JFrame frame) {
		searchTeacherBtn = new JButton("Editar");
		frame.getContentPane().add(searchTeacherBtn);
		searchTeacherBtn.setBounds(599, 26, 117, 25);
		searchTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			

			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(799, 26, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				TeacherView teacherFrame = new SearchTeacher();
				teacherFrame.buildScreen(teacherFrame, NON_EDITABLE_FIELDS, null);
				teacherFrame.setVisible(true);
			}
		});
	}

	@Override
	public void createMasks(JFrame frame, int fieldStatus) {
		// TODO Auto-generated method stub
		
	}
}
