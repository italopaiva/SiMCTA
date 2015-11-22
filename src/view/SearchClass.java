package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import util.ButtonColumn;
import view.decorator.ShowTeacherDecorator;
import view.decorator.class_decorator.ClassDecorator;
import view.decorator.class_decorator.EditClassDecorator;
import view.decorator.class_decorator.NewClassDecorator;
import view.decorator.class_decorator.ShowStudentsClassDecorator;
import view.forms.ClassForm;
import view.forms.ClassShowForm;
import view.forms.TeacherForm;

import com.sun.javafx.collections.MappingChange.Map;

import model.Class;
import model.Course;
import model.Teacher;
import datatype.CPF;
import controller.ClassController;
import controller.CourseController;
import controller.TeacherController;
import exception.ClassException;
import exception.CourseException;
import exception.TeacherException;

public class SearchClass extends View {

	private JPanel panel_0;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtCodigo;
	private JRadioButton rbtnCode;
	private JRadioButton rbtnFilter;
	private JComboBox<String> cmbCourse;
	private JComboBox<String> cmbTeacher;
	private JComboBox<String> cmbShift;
	private JScrollPane scrollPane;
	private TableModel tableModel;
	private JTable jTable;
	private JButton btnSearch;
	
	private ClassController classController;
	private ClassView classFrame;


	/**
	 * Contructor's method of SearchClass.
	 */
	public SearchClass() {

		super();

		getContentPane().setLayout(null);

		initialize();
		rbtnCode.setSelected(true);
		txtCodigo.requestFocus();
		rbtnFilter.setEnabled(false);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		createStructOfWindow();

		createLabelsAndFields();

		createComboBoxes();

		createButtonsAndListeners();

		createStructOfResultTable();

	}

	/**
	 * Initialize the struct of the frame.
	 */
	public void createStructOfWindow() {
		panel_0 = new JPanel();
		panel_0.setBounds(100, 100, 788, 617);
		setContentPane(panel_0);
		panel_0.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(134, 39, 525, 160);
		panel_0.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(90, 41, 296, 102);
		panel.add(panel_1);
		panel_1.setLayout(null);

	}

	/**
	 * Create labels and fields.
	 */
	public void createLabelsAndFields() {
		JLabel lblConsultarTurma = new JLabel("Consultar Turma");
		lblConsultarTurma.setBounds(268, 12, 124, 15);
		panel_0.add(lblConsultarTurma);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(90, 10, 154, 23);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(12, 12, 48, 15);
		panel_1.add(lblCurso);

		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setBounds(12, 43, 70, 15);
		panel_1.add(lblProfessor);

		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setBounds(12, 74, 70, 15);
		panel_1.add(lblTurno);

	}

	/**
	 * Create Comboxes.
	 */
	public void createComboBoxes() {
		cmbCourse = new JComboBox<String>();
		cmbCourse.setBounds(67, 7, 186, 24);
		cmbCourse.addItem("Teste");
		cmbCourse.setSelectedItem(0);
		panel_1.add(cmbCourse);

		cmbTeacher = new JComboBox();
		cmbTeacher.setBounds(114, 38, 139, 24);
		panel_1.add(cmbTeacher);

		cmbShift = new JComboBox();
		cmbShift.setBounds(150, 69, 103, 24);
		panel_1.add(cmbShift);

	}

	/**
	 * Create buttons and listeners.
	 */
	public void createButtonsAndListeners() {

		rbtnCode = new JRadioButton("Código");
		rbtnCode.setBounds(8, 8, 74, 23);
		panel.add(rbtnCode);

		rbtnFilter = new JRadioButton("Filtros");
		rbtnFilter.setBounds(8, 35, 81, 23);
		panel.add(rbtnFilter);

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnCode);
		group.add(rbtnFilter);

		JButton btnSearch = new JButton("Pesquisar");
		btnSearch.setBounds(398, 34, 117, 25);
		panel.add(btnSearch);

		rbtnCode.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					cmbCourse.setEnabled(false);

					cmbTeacher.setEnabled(false);

					cmbShift.setEnabled(false);

					txtCodigo.setEnabled(true);
					txtCodigo.requestFocus();
				}

			}
		});

		rbtnFilter.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbCourse.setEnabled(true);

				cmbTeacher.setEnabled(true);

				cmbShift.setEnabled(true);

				txtCodigo.setEnabled(false);

			}
		});

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				resetTable();

				if (rbtnCode.isSelected()) {
					classController = new ClassController();
					try {
						ArrayList<Class> cls = classController
								.searchClass(txtCodigo.getText().toString());

						if (cls == null) {
							JOptionPane.showMessageDialog(rbtnCode,
									"Turma não encontrada.");
						} else {
							int arrayListSize = cls.size();
							int i = 0;
							while (i < arrayListSize) {
								String[] classeString = getLineOfStringByClass(cls
										.get(i));
								((DefaultTableModel) tableModel)
										.addRow(classeString);
								i++;
							}

						}

					} catch (ClassException e1) {
						JOptionPane.showMessageDialog(frame, "Ocorreu um erro:"
								+ e1.getMessage());
					}
				} else if (rbtnFilter.isSelected()) {
					JOptionPane.showMessageDialog(frame, "Teste3");
				}

			}
		});

	}
	
	/** 
	 * Create a struct of result table
	 */
	public void createStructOfResultTable() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 217, 901, 317);
		panel_0.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);

		String[] columns = { "Código", "Curso", "Professor", "Turno", "Início",
				"Término", "Editar", "Visualizar alunos" };

		tableModel = new DefaultTableModel(null, columns);
		final JTable tableOfClasses = new JTable(tableModel);
		tableOfClasses.setBackground(Color.WHITE);

		scrollPane.setViewportView(tableOfClasses);

		jTable = tableOfClasses;
		
		Action editClass = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				String code  = table.getModel().getValueAt(selectedRow,1).toString();
				
				Class cls = classController.getClass(code);
				dispose();
				classFrame = new NewClassDecorator(new ClassForm());
				classFrame.buildScreen(classFrame, null);
				classFrame.setVisible(true);
				

				
			}
			
		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfClasses, editClass, 6);
		
		Action showStudentsClass = new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTable table = (JTable)e.getSource();
				int selectedRow = table.getSelectedRow();
				
				String code  = table.getModel().getValueAt(selectedRow,0).toString();
				
				Class enrolledClass = classController.getClass(code);
				dispose();
				classFrame = new ShowStudentsClassDecorator(new ClassShowForm());
				classFrame.buildScreen(classFrame, enrolledClass);
				classFrame.setVisible(true);
				
			}
			
		};
		
		ButtonColumn buttonColumn3 = new ButtonColumn(tableOfClasses, showStudentsClass, 7);

		((JScrollPane) scrollPane).setViewportView(tableOfClasses);
		
	}

	/**
	 * Used to return a String[] with the values of Class
	 * 
	 * @param cls
	 * @return classeString - String[]
	 */
	public String[] getLineOfStringByClass(Class cls) {

		String[] classeString = new String[8];
		classeString[0] = cls.getClassId();
		classeString[1] = cls.getCourse().getName();
		classeString[2] = cls.getTeacher().getName();
		classeString[3] = cls.getShift();
		classeString[4] = cls.getStartDate().getHyphenFormattedDate();
		classeString[5] = cls.getEndDate().getHyphenFormattedDate();
		classeString[6] = "Editar";
		classeString[7] = "Visualizar alunos";
		return classeString;
	}

	/**
	 * Used to reset the table of result.
	 */
	public void resetTable() {
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
		model.setRowCount(0);
	}
	
}
