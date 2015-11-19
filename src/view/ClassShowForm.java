package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Class;

// Default form
public class ClassShowForm extends ClassView {

	private JLabel endDateLbl;

	@Override
	public void createLabelsAndFields(JFrame frame, Class classInstance){
		
		frame.getContentPane().setLayout(null);
		
		titleLbl = new JLabel("Turma " + classInstance.getClassId());
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		titleLbl.setBounds(410, 0, 300, 30);
		frame.getContentPane().add(titleLbl);
		
		//startDateLbl = new JLabel("Data de início " + classInstance.getStartDate().getSlashFormattedDate());
		startDateLbl = new JLabel("Data de início ");
		startDateLbl.setBounds(86, 32, 300, 30);
		frame.getContentPane().add(startDateLbl);
		
		//endDateLbl = new JLabel("Data de fim " + classInstance.getEndDate().getSlashFormattedDate());
		endDateLbl = new JLabel("Data de fim ");
		endDateLbl.setBounds(326, 32, 300, 30);
		frame.getContentPane().add(endDateLbl);
		
		
		classIdLbl = new JLabel("Alunos");
		classIdLbl.setBounds(86, 72, 122, 15);
		frame.getContentPane().add(classIdLbl);
	}

	@Override
	public void createMasks(JFrame frame){
		
	}

	@Override
	public void createButtons(JFrame frame){
		
	}

}
