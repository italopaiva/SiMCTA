package view.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Course;
import model.Package;
import model.ServiceItem;
import view.SearchPackage;
import view.ServiceItemView;
import view.forms.ServiceItemForm;
import controller.CourseController;
import controller.PackageController;
import exception.CourseException;
import exception.PackageException;

public class ShowPackageDecorator extends ServiceItemDecorator{

	protected static final Integer PACKAGE_ACTIVE = 1;
	
	private JButton editTeacherBtn;
	private JButton backBtn;
	private JTextField durationField;
	private JTextField valueField;
	Package packageInstance;
	private JButton btnActiveOrDeactive;
	private DefaultTableModel tableModel;
	private JTable tableOfCourses;
	
	public ShowPackageDecorator(ServiceItemView viewToDecorate) {
		super(viewToDecorate);
	}

	@Override
	public void createLabelsAndFields(JFrame viewToDecorate, ServiceItem packageInstance) {
		this.frame = viewToDecorate;
		super.createLabelsAndFields(viewToDecorate, packageInstance);
		this.packageInstance = (Package) packageInstance;

		nameField.setBounds(276, 74, 346, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblC = new JLabel(packageInstance.getName());
		lblC.setFont(new Font("Dialog", Font.BOLD, 20));
		lblC.setBounds(326, 12, 344, 25);
		frame.getContentPane().add(lblC);
		
		durationField =  new JTextField();
		durationField.setBounds(276, 143, 132, 25);
		frame.getContentPane().add(durationField);
			
		valueField = new JTextField();
		valueField.setBounds(284, 224,124, 28);
		frame.getContentPane().add(valueField);
			
		JLabel lblCursosAssociados = new JLabel("Cursos Associados");
		lblCursosAssociados.setBounds(276, 252, 144, 15);
		frame.getContentPane().add(lblCursosAssociados);
						
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 281, 353, 169);
		frame.getContentPane().add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		String [] columns = {"Cursos", "ID", "Duração"};
		
		tableModel = new DefaultTableModel(null, columns);			

		tableOfCourses = new JTable(tableModel);
		
		disposeColumns(tableOfCourses);
		tableOfCourses.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableOfCourses);
				
		fillTheFields(this.packageInstance);

	}
	
	private void fillTheFields(Package packageInstance) {
		
		nameField.setText(packageInstance.getName());
		durationField.setText(packageInstance.getDuration().toString() + " semanas");
		
		Integer value = packageInstance.getValue();
		valueField.setText("R$" + packageInstance.getFormattedValue(value));
		
		ArrayList<String> coursesId;

		coursesId = packageInstance.getCourses();
		Integer courseId = 0;
		CourseController courseController = new CourseController();
		Course dataOfCourse = null;
		
		int i = 0;
		
		while (i < coursesId.size()){
			courseId = new Integer(coursesId.get(i));
			try {
				dataOfCourse = courseController.showCourse(courseId);
				
				String [] courses = new String [3];
				courses[0] = dataOfCourse.getName();
				courses[1] = coursesId.get(i);
				courses[2] = dataOfCourse.getDuration().toString();
				tableModel.addRow(courses);
			} 
			catch(CourseException e){
			
			}

			i++;
		}		
		
		setNonEditableAllFields();
		
	}
	
	private void setNonEditableAllFields() {
		
		nameField.setEditable(false);
		durationField.setEditable(false);
		valueField.setEditable(false);
		
	}

	@Override
	public void createButtons(JFrame frame) {
		editTeacherBtn = new JButton("Editar");
		frame.getContentPane().add(editTeacherBtn);
		editTeacherBtn.setBounds(379, 455, 117, 25);
		editTeacherBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				ServiceItemView editPackageFrame = new EditPackageDecorator(new ServiceItemForm());
				dispose();
				editPackageFrame.buildScreen(editPackageFrame, packageInstance);
				editPackageFrame.setVisible(true);
			}
		});
		
		backBtn = new JButton("Voltar");
		frame.getContentPane().add(backBtn);
		backBtn.setBounds(479, 535, 117, 25);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){			
				dispose();
				ServiceItemView packageFrame;
				packageFrame = new SearchPackage();
				packageFrame.buildScreen(packageFrame,null);
				packageFrame.setVisible(true);
			}
		});
		
		btnActiveOrDeactive = new JButton("New Button");
		btnActiveOrDeactive.setBounds(579, 455, 117, 25);
		frame.getContentPane().add(btnActiveOrDeactive);
		btnActiveOrDeactive.setText(showsActiveOrDeactive(packageInstance.getStatus()));
		
		btnActiveOrDeactive.addActionListener(new ActionListener() {
			
			private int packageStatus = packageInstance.getStatus();

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = 0;				
				String action =  showsActiveOrDeactive(packageStatus);
				confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja " + action + " este pacote?", "Atenção!!!", JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					/*
					PackageController packageController = new PackageController();
					int packageID = packageInstance.getId();
					
					boolean wasChanged = packageController.alterStatusPackage(packageID);
					if(wasChanged){
						changeStatus();
						String currentStatus = showActiveOrInactive(packageStatus);
						showInfoMessage("Pacote " + currentStatus + " com sucesso!");
						btnActiveOrDeactive.setText(showsActiveOrDeactive(packageStatus));
					}
					else{
						showInfoMessage("Não foi possível" + action + "o pacote!");	
					}*/
				}
				
			}

			private void changeStatus() {
				if(packageStatus == PACKAGE_ACTIVE){
					packageStatus = 0;
				}
				else{
					packageStatus = 1;
				}
				
			}

		});
	}

	private String showsActiveOrDeactive(int status){
		String statusToShow = null;
		
		switch(status){
			case 0:
				statusToShow = "Ativar";
				break;
			case 1:
				statusToShow = "Desativar";
		}
		
		return statusToShow;
	}
	
	private String showActiveOrInactive(int status){
		
		String statusToShow = null;
		
		switch(status){
			case 0:
				statusToShow = "Desativado";
				break;
			case 1:
				statusToShow = "Ativado";
		}
		
		return statusToShow;
	}
	
	/**
	 * Dispose the id and duration columns
	 * @param table - Receives the table to dispose columns
	 */
	private void disposeColumns(JTable table) {
		
		TableColumnModel tableModel = table.getColumnModel();
		
		tableModel.getColumn(1).setMinWidth(0);     
		tableModel.getColumn(1).setPreferredWidth(0);  
		tableModel.getColumn(1).setMaxWidth(0);    
	
		tableModel.getColumn(2).setMinWidth(0);     
		tableModel.getColumn(2).setPreferredWidth(0);  
		tableModel.getColumn(2).setMaxWidth(0);
					
	}
}
