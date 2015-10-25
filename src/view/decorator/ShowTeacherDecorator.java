package view.decorator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import exception.TeacherException;
import view.TeacherView;

public class ShowTeacherDecorator extends TeacherDecorator {

	private JButton searchTeacherBtn;

	public ShowTeacherDecorator(TeacherView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, int fieldStatus) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, fieldStatus);
		changeNames(frame);
		createButtons(frame);
	}
	

	private void changeNames(JFrame frame) {
		registerTeacherLbl.setText("professor");
		registerTeacherLbl.updateUI();		
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
	}

	@Override
	public void createMasks(JFrame frame, int fieldStatus) {
		// TODO Auto-generated method stub
		
	}
}
