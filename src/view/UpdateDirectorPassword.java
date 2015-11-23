package view;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import controller.AuthenticationController;
import exception.AuthenticationException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateDirectorPassword extends View {
	
	private JPasswordField currentPasswordField;
	private JPasswordField newPasswordField;
	
	public UpdateDirectorPassword(){
		super();
		
		getContentPane().setLayout(null);
		
		JLabel titleLbl = new JLabel("Atualizar senha do diretor");
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 18));
		titleLbl.setBounds(361, 23, 296, 69);
		getContentPane().add(titleLbl);
		
		currentPasswordField = new JPasswordField();
		currentPasswordField.setBounds(396, 131, 204, 33);
		getContentPane().add(currentPasswordField);
		currentPasswordField.setColumns(10);
		
		JLabel currentPasswordLbl = new JLabel("Senha atual");
		currentPasswordLbl.setBounds(400, 104, 116, 25);
		getContentPane().add(currentPasswordLbl);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setColumns(10);
		newPasswordField.setBounds(396, 223, 204, 33);
		getContentPane().add(newPasswordField);
		
		JLabel lblNovaSenha = new JLabel("Nova senha");
		lblNovaSenha.setBounds(400, 196, 116, 25);
		getContentPane().add(lblNovaSenha);
		
		JButton btnNewButton = new JButton("Trocar senha");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				String currentPassword = currentPasswordField.getText();
				String newPassword = newPasswordField.getText();
				
				AuthenticationController auth = new AuthenticationController();
				
				try{
					auth.updateDirectorPassword(currentPassword, newPassword);
					showInfoMessage("Senha atualizada com sucesso!");
					dispose();
					new View().setVisible(true);
				}
				catch(AuthenticationException e1){
					showInfoMessage(e1.getMessage());
				}
				
			}
		});
		btnNewButton.setBounds(424, 280, 157, 25);
		getContentPane().add(btnNewButton);
	}
}
