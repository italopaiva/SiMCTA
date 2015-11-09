package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import util.ButtonColumn;
import controller.PackageController;
import dao.PackageDAO;
import model.Package;
import exception.CourseException;
import exception.PackageException;
import exception.StudentException;

@SuppressWarnings("serial")
public class SearchPackage extends View {

	private static final int ACTIVE = 1;

	private static final int INACTIVE = 0;

	private JPanel contentPane;
	final DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JTable jTable;
	
	private JTextField jTxtPackageID;
	private JLabel jLblPackageName;
	private JLabel jLblValor;
	private JLabel jLblDuracao;
	private JList<String> jLstCourses;

	private JInternalFrame internalFrame;
	private int packageId;
	private JButton activeOrDeactivePackageBtn;
	private int status;
	private String action;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPackage frame = new SearchPackage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws PackageException 
	 */
	public SearchPackage() throws SQLException, PackageException{
		
		super();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextField searchedPackageField = new JTextField();
		searchedPackageField.setBounds(140, 56, 446, 19);
		contentPane.add(searchedPackageField);
		searchedPackageField.setColumns(10);
		
		final JButton btnPesquisar = new JButton("Listar Pacotes");
		btnPesquisar.setBounds(598, 53, 152, 25);
		contentPane.add(btnPesquisar);
		
		internalFrame = new JInternalFrame();
		internalFrame.getContentPane().setLayout(null);
		
		JButton editPackageBtn = new JButton("Editar");
		editPackageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean permissionToAccess = false;
				
				permissionToAccess = getPermissionToAccess();
				if(permissionToAccess){
					
					dispose();
					
					try {
						dispose();
						EditPackage editPackageFrame;
						editPackageFrame = new EditPackage(packageId);
						editPackageFrame.setVisible(true);
					} catch (SQLException e) {
						showInfoMessage("Ocorreu um erro ao carregar os cursos. Tente novamente.");
					} catch (CourseException e) {
						
					} catch (ParseException e) {

					}

				}
				else{
					View frame = new View();
					frame.setVisible(true);
				}				
			}
		});
		editPackageBtn.setBounds(124, 248, 107, 25);
		editPackageBtn.setVisible(true);
		internalFrame.getContentPane().add(editPackageBtn);
		
		activeOrDeactivePackageBtn = new JButton("Desativar");
		activeOrDeactivePackageBtn.setBounds(279, 248, 107, 25);
		activeOrDeactivePackageBtn.setVisible(false);
		internalFrame.getContentPane().add(activeOrDeactivePackageBtn);
		
		JLabel lblInfo = new JLabel("Informações sobre Pacote");
		lblInfo.setBounds(12, 14, 219, 15);
		internalFrame.getContentPane().add(lblInfo);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(12, 60, 70, 14);
		internalFrame.getContentPane().add(lblNome);
		
		jLblPackageName = new JLabel("");
		jLblPackageName.setBounds(43, 79, 273, 15);
		internalFrame.getContentPane().add(jLblPackageName);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 33, 70, 15);
		internalFrame.getContentPane().add(lblId);
		
		jTxtPackageID = new JTextField();
		jTxtPackageID.setEditable(false);
		jTxtPackageID.setEnabled(false);
		jTxtPackageID.setBounds(43, 30, 114, 19);
		internalFrame.getContentPane().add(jTxtPackageID);
		jTxtPackageID.setColumns(10);
		
		JLabel lblValorTitulo = new JLabel("Valor: ");
		lblValorTitulo.setBounds(13, 106, 70, 15);
		internalFrame.getContentPane().add(lblValorTitulo);
		
		JLabel lblDuracaoTitulo = new JLabel("Duração:");
		lblDuracaoTitulo.setBounds(12, 144, 70, 15);
		internalFrame.getContentPane().add(lblDuracaoTitulo);
		
		jLblDuracao = new JLabel("");
		jLblDuracao.setBackground(Color.LIGHT_GRAY);
		jLblDuracao.setBounds(54, 167, 137, 15);
		internalFrame.getContentPane().add(jLblDuracao);
		
		JLabel lblCursosAssociados = new JLabel("Cursos Associados");
		lblCursosAssociados.setBounds(368, 33, 148, 15);
		internalFrame.getContentPane().add(lblCursosAssociados);
		
		jLstCourses = new JList<String>();
		jLstCourses.setBackground(Color.LIGHT_GRAY);
		jLstCourses.setBounds(366, 59, 156, 153);
		internalFrame.getContentPane().add(jLstCourses);
		
		jLblValor = new JLabel("");
		jLblValor.setBackground(Color.LIGHT_GRAY);
		jLblValor.setBounds(70, 117, 70, 15);
		internalFrame.getContentPane().add(jLblValor);
		
		JLabel lblDuracao = new JLabel("");
		lblDuracao.setBackground(Color.LIGHT_GRAY);
		lblDuracao.setBounds(70, 169, 161, 30);
		internalFrame.getContentPane().add(lblDuracao);
		internalFrame.setEnabled(false);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setBounds(227, 141, 557, 317);
		contentPane.add(internalFrame);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 141, 557, 317);
		contentPane.add(scrollPane);
		scrollPane.setBackground(Color.WHITE);
		
		final JButton backButton = new JButton("Voltar");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchedPackageField.setText("");
				internalFrame.dispose();
				backButton.setVisible(false);
			}
		});
		
		String [] columns = { "Nome", "Valor", "Duração", "Status", "Id", "Ações" };
			
		tableModel = new DefaultTableModel(null, columns);
		final JTable tableOfPackages = new JTable(tableModel);
		tableOfPackages.setBackground(Color.WHITE);
			
		backButton.setBounds(762, 53, 117, 25);
		contentPane.add(backButton);
		backButton.setVisible(false);
		scrollPane.setViewportView(tableOfPackages);
		
		jTable = tableOfPackages;
		
		tableOfPackages.removeColumn(tableOfPackages.getColumnModel().getColumn(4));
		
		getSearchedPackage(searchedPackageField.getText());
		
		Action showPackage = new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					
					internalFrame.validate();

					JTable table = (JTable)e.getSource();
					int idPackage = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());

					try {
						getPackageById(idPackage);
					} catch (PackageException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					internalFrame.setVisible(true);
					internalFrame.updateUI();
					backButton.setVisible(true);
					tableOfPackages.setVisible(false);

				}
		};
		
		ButtonColumn buttonColumn2 = new ButtonColumn(tableOfPackages, showPackage, 4);
		
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				internalFrame.dispose();
				backButton.setVisible(false);
				String searchedPackage = searchedPackageField.getText();
				
				boolean enteredPackage = !searchedPackage.isEmpty();
				
				if(enteredPackage == true){
					try {
						getSearchedPackage(searchedPackage);
					} catch (SQLException | PackageException e1) {
						e1.printStackTrace();
					}	
				}
				else{
					//JOptionPane.showMessageDialog(null, "Digite algum texto para pesquisar por pacotes.");
					try {
						getSearchedPackage("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (PackageException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				jTable.updateUI();
			}
			
		});
		
		searchedPackageField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
				
			}
			
			public void warn(){
				if (searchedPackageField.getText().isEmpty()){
					btnPesquisar.setText("Listar Pacotes");
				} else {
					btnPesquisar.setText("Pesquisar");
				}
			}
		});
		
		searchedPackageField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getSearchedPackage(searchedPackageField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PackageException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	/**
	 * Method used to pass the course value to monetary form
	 * @param value - Receives the course value on entire form
	 * @return - Return the course value on monetary form (R$)
	 */
	private String passValueToMonetaryForm(Integer value) {
		
		String valueText= "";
		String entireValue = "";
		String decimalValue = "";
		int lengthOfValue = 0;
		
		valueText = value.toString();
		lengthOfValue = valueText.length();
		entireValue = valueText.substring(0, (lengthOfValue - 2));
		decimalValue = valueText.substring((lengthOfValue - 2), lengthOfValue);
		valueText = entireValue + "," + decimalValue;
		
		return valueText;
	}
	
	public void getSearchedPackage(String name) throws SQLException, PackageException{
		
		DefaultTableModel model = (DefaultTableModel) jTable.getModel();
	    model.setRowCount(0);
		
		PackageController packageController = new PackageController();
		ArrayList<model.Package> arrayListPackage = packageController.searchPackageByName(name);		
		
		if (arrayListPackage == null){
			JOptionPane.showMessageDialog(jTable, "Não foi possível encontrar resultados para a pesquisa.");
		} else {
		
			int lengthArray = 0;
			int i = 0;
		
			lengthArray = arrayListPackage.size();
		
			while (lengthArray > i) {
				String[] packages = new String[6];
				packages[0] = arrayListPackage.get(i).getName();
				packages[1] = "R$ " + passValueToMonetaryForm(arrayListPackage.get(i).getValue());
				packages[2] = arrayListPackage.get(i).getDuration().toString() + " semana(s)";
				packages[3] = showActiveOrInactive(arrayListPackage.get(i).getStatus());
				packages[4] = arrayListPackage.get(i).getId().toString();
				packages[5] = "Ver";
				tableModel.addRow(packages);
				i++;
			}
		}
	}

	public void getPackageById(final int idPackage) throws PackageException{
		this.packageId = idPackage;
		
		final PackageController packageController = new PackageController();
		final Package packageToShow;
		
		DefaultListModel<String> courseListModel = new DefaultListModel<String>();
		final ArrayList<String> coursesName;
		packageToShow = packageController.showPackage(idPackage);

		jTxtPackageID.setText(packageToShow.getId().toString());
		jLblPackageName.setText(packageToShow.getName());
		jLblDuracao.setText(packageToShow.getDuration().toString() + " semanas");
		jLblValor.setText(passValueToMonetaryForm(packageToShow.getValue()));
		
		coursesName = packageToShow.getCourses();
		
		int i = 0;
		
		while (i < coursesName.size()){
			courseListModel.addElement(coursesName.get(i));
			i++;
		}
		
		jLstCourses.setModel(courseListModel);
		
		
		status = packageToShow.getStatus();
		action = setTextToTheDeactiveOrActiveButton(status);
		activeOrDeactivePackageBtn.setVisible(true);
		activeOrDeactivePackageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				
				int confirm = 0;				
				
				confirm = JOptionPane.showConfirmDialog(internalFrame, "Tem certeza que deseja que o pacote seja " + action + "?", action, JOptionPane.YES_NO_OPTION);
				
				if (confirm == JOptionPane.YES_OPTION) {
					
					boolean wasAltered = false;
					try {
						changeStatus();
						wasAltered = packageController.alterStatusOfThePackage(packageToShow, status);
					} 
					catch(PackageException e1) {

					}	
					if(wasAltered){
						showInfoMessage("O pacote está " + action + "!");	
						action = setTextToTheDeactiveOrActiveButton(status);
					}
					else{
						changeStatus();
						showInfoMessage("Um erro ocorreu, o pacote não foi " + action);
					}
				}
				else{
					// Nothing to do
				}
			
			}

			private void changeStatus() {
				
				if(status == ACTIVE){
					status = INACTIVE;
				}
				else{
					status = ACTIVE;
				}
				
			}
		});
		
	}
	private String setTextToTheDeactiveOrActiveButton(int status) {
		
		String packageStatus = "";
		if(status == ACTIVE){
			activeOrDeactivePackageBtn.setText("Desativar");
			packageStatus = "desativado";
		}
		else{
			activeOrDeactivePackageBtn.setText("Ativar");
			packageStatus = "ativado";
		}
		
		return packageStatus;
		
	}
	
	private String showActiveOrInactive(int status){
		
		String statusToShow = null;
		
		switch(status){
			case INACTIVE:
				statusToShow = "Desativado";
				break;
			case ACTIVE:
				statusToShow = "Ativado";
		}
		
		return statusToShow;
	}	
}
