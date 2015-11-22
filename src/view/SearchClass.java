package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
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
	
	
	/**
	 * Contructor's method of SearchClass.
	 */
	public SearchClass() {

		super();
		
		getContentPane().setLayout(null);

		initialize();
		rbtnCode.setSelected(true);
		txtCodigo.requestFocus();

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

	public void createLabelsAndFields() {
		JLabel lblConsultarTurma = new JLabel("Consultar Turma");
		lblConsultarTurma.setBounds(268, 12, 124, 15);
		panel_0.add(lblConsultarTurma);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(90, 10, 114, 23);
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
	
	public void createComboBoxes(){
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
				JOptionPane.showMessageDialog(frame, "Teste1");
				if (rbtnCode.isSelected()) {
					JOptionPane.showMessageDialog(frame, "Teste2");
				} else if (rbtnFilter.isSelected()) {
					JOptionPane.showMessageDialog(frame, "Teste3");
				}

			}
		});

	}

	public void createStructOfResultTable() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 217, 707, 317);
		panel_0.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);

		String[] columns = { "Código", "Curso", "Professor", "Turno", "Início",
				"Término", "Ações" };

		tableModel = new DefaultTableModel(null, columns);
		final JTable tableOfPackages = new JTable(tableModel);
		tableOfPackages.setBackground(Color.WHITE);

		scrollPane.setViewportView(tableOfPackages);

		jTable = tableOfPackages;
	}
}
