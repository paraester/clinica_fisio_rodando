package View;

import DAO.LoginUsuarioDAO;
//import Model.ClientCadastro;
//import Model.UsuarioLogado;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
//import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import Control.Register;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUsuario extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	//private Register register;
//	private LoginUsuarioDAO usuarioLogado = null;

	public LoginUsuario() {
		setTitle("LOGIN ALEGRA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 10, 400, 250);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		setLocationRelativeTo(null); // para exibir centralizado na tela

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(20, 50, 68, 14);
		contentPane.add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 100, 68, 14);
		contentPane.add(lblSenha);

		textField = new JTextField();
		textField.setBounds(83, 45, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(83, 95, 86, 20);
		contentPane.add(passwordField);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/images/icon.jpg")));
		 			  
		label.setBounds(200, 5, 190, 190);
		contentPane.add(label);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(20, 150, 89, 23);
		contentPane.add(btnOk);
		btnOk.setName("btnOk");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Component component = (Component)e.getSource();
		if(component.getName().equals("btnOk")) {

			String nome = textField.getText();
			String senha = new String(passwordField.getPassword());
			System.out.println("nome "+ nome);
			LoginUsuarioDAO usuarioLogado = new LoginUsuarioDAO();
			
			//usuarioLogado = register.getLogin();
			
			
			if(usuarioLogado.validar(nome,senha)) {
				 System.out.println("verdadeiro");
                 new MainFrame().setVisible(true);
                 this.dispose();//para fechar esta janela
			}else{
				System.out.println("falso");
                JOptionPane.showMessageDialog(null, "usuario ou senha invalido");
			}
		}
	}
	
}