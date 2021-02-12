
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Image;

public class ClientFormHome extends JPanel implements VisualWindow {

	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(getClass().getResource("/images/alegra.jpg"));

	private JButton btcadastro = new JButton("CADASTRO PACIENTE");
	private JButton btavaliacao = new JButton("FAZER AVALIAÇÃO");
	private JButton btconsultar = new JButton("BUSCAR PACIENTE");
	private JButton btnSair = new JButton("SAIR");
	private MainFrame telainit;

	public ClientFormHome(MainFrame telainit) {
		this.telainit = telainit; // referencia para o objeto criado
		setupLayout();
		setupComponentes();
		setupEvents();

	}

	@Override
	public void setupLayout() {
		setLayout(null); // fixo
		setVisible(true);
		setBackground(Color.white);
		setSize(800, 600);

	}

	@Override
	public void setupComponentes() {

		btcadastro.setBackground(Color.white);
		btcadastro.setOpaque(true);
		btcadastro.setForeground(new Color(0, 0, 0));
		btcadastro.setRequestFocusEnabled(false);
		// btcadastro.setBorderPainted(false);
		btcadastro.setContentAreaFilled(false);
		btcadastro.setFocusPainted(false);
		btcadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btavaliacao.setBackground(Color.white);
		btavaliacao.setOpaque(true);
		btavaliacao.setForeground(new Color(0, 0, 0));
		btavaliacao.setRequestFocusEnabled(false);
		// btavaliacao.setBorderPainted(false);
		btavaliacao.setContentAreaFilled(false);
		btavaliacao.setFocusPainted(false);
		btavaliacao.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btconsultar.setBackground(Color.white);
		btconsultar.setOpaque(true);
		btconsultar.setForeground(new Color(0, 0, 0));
		btconsultar.setRequestFocusEnabled(false);
		// btconsultar.setBorderPainted(false);
		btconsultar.setContentAreaFilled(false);
		btconsultar.setFocusPainted(false);
		btconsultar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btcadastro.setBounds(10, 50, 200, 30);
		btavaliacao.setBounds(10, 100, 200, 30);
		btconsultar.setBounds(10, 150, 200, 30);
		
		
		btnSair.setBackground(Color.white);
		btnSair.setOpaque(true);
		btnSair.setForeground(new Color(0, 0, 0));
		btnSair.setRequestFocusEnabled(false);
		btnSair.setContentAreaFilled(false);
		btnSair.setFocusPainted(false);
		btnSair.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));
		btnSair.setBounds(10, 300, 200, 30);
		// Defining Panel

		add(btcadastro);
		add(btavaliacao);
		add(btconsultar);
		add(btnSair);
		

		// fundoTela();
	}
	@Override
	public void setupEvents() {
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btcadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formCadastroClientes", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		btavaliacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formAvali", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		btconsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formConsultarClientes", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});

	}
	public void fundoTela() {
		JLabel fundo;
		ImageIcon icon = new ImageIcon("../src/images/alegra.jpg");
		fundo = new JLabel(icon);
		fundo.setBounds(250, 70, 400, 400);
		fundo.setVisible(true);
		add(fundo);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = IMAGE_BACKGROUND.getImage();
		g.drawImage(img, 0, 0, this);
	}
}