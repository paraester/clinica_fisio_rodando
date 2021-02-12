package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
//import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Graphics;
import java.awt.Image;

import Control.Register;
import Model.ClientCadastro;
import Model.JtextFieldLetrasNumeros;
import Model.JtextFieldSomenteLetras;

public class ClientFormCadastroAvaliacao extends JPanel implements VisualWindow {
	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(
			getClass().getResource("/images/alegra2.jpg"));
	private JButton btvoltar;
	private JButton btcadastro;
	private JButton btenviar;
	private JButton btlimpar;
	private JLabel nome; // que veio do banco de dados
	private JTextField nomeCampo;
	private JLabel idade; // que veio do banco de dados
	private JTextField idadeCampo;
	private JLabel sexo; // que veio do banco de dados
	private JTextField sexoCampo;
	private JLabel medicoresponsavel; // que veio do banco de dados
	private JLabel observacaodehoje;
	private JLabel medicamento;
	private JLabel nomedoformulario;
	private JTextField medicoresponsavelCampo;
	private JTextArea observacaodehojeCampo;
		
	private JScrollPane scrollPane;
	// private Border border = BorderFactory.createLineBorder(Color.BLACK);
	private JTextField medicamentoCampo;
	private MainFrame telainit;
	private Register register;
	private ClientCadastro clientecadastro;
	private JLabel especialidade; // ( ortopedia, neurologia ,pilates)
	private JTextField especialidadeCampo;

	private JPopupMenu popupOpcoes;
	private ActionListener menuListener;
	private String nomeBuscado = "";
	private int editandoIdNovo = 0;
	private JLabel linha4;
	


	//provavelmente não mais necessario
/*
 * 	public void setClienteCadastro(ClientCadastro clientecadastro){
		System.out.println("adicionado cliente no formAvali " + clientecadastro.getNome());
		this.clientecadastro = clientecadastro;
	}

 * 
	public ClientFormCadastroAvaliacao(MainFrame telainit, Register register) {
		this.register = register;
		this.telainit = telainit;
		clientecadastro = register.getClient(0); // antigamente pegar cliente de id 1
		setupLayout();
		setupComponentes();
		setupEvents();
	}
*/
	public ClientFormCadastroAvaliacao(MainFrame telainit, Register register) {
		this.register = register;
		this.telainit = telainit;
		clientecadastro = register.getClient(0); // antigamente pegar cliente de id 1
		System.out.println("foi pego get: " + clientecadastro.getId());
		setupLayout();
		setupComponentes();
		setupEvents();
	}


	@Override
	public void setupLayout() {
		setLayout(null); // fixo
		setBackground(Color.white);
		setVisible(true);
		// setSize(900, 550);
	}

	@Override
	public void setupComponentes() {

		nomedoformulario = new JLabel(" Cadastrar Nova Avaliação Clínica");
		nomedoformulario.setBounds(250, 50, 570, 30);
		nomedoformulario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 150), 2));
		nomedoformulario.setBorder(
				javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.lightGray));
		nomedoformulario.setForeground(Color.black);
		nomedoformulario.setFont(new Font("Serifa", Font.CENTER_BASELINE, 15));

		nome = new JLabel("Paciente: ");
		nome.setBounds(250, 110, 200, 20);// x y w h
		nomeCampo = new JtextFieldLetrasNumeros(250);
		nomeCampo.setBounds(320, 110, 500, 20);

		// idade = new JLabel("Idade: " + clientecadastro.getIdade());
		idade = new JLabel("Idade: ");
		idade.setBounds(250, 150, 170, 20);
		idadeCampo = new JTextField(100);
		idadeCampo.setBounds(300, 150, 50, 20);

		sexo = new JLabel("Sexo: ");
		sexo.setBounds(360, 150, 80, 20);
		sexoCampo = new JTextField(100);
		sexoCampo.setBounds(400, 150, 80, 20);

		especialidade = new JLabel("Especialidade: ");
		especialidade.setBounds(490, 150, 150, 20);
		especialidadeCampo = new JTextField(100);
		especialidadeCampo.setBounds(600, 150, 220, 20);

		medicoresponsavel = new JLabel("Médico desta avaliação: ");
		medicoresponsavel.setBounds(250, 190, 200, 20);// x y w h
		medicoresponsavelCampo = new JtextFieldSomenteLetras(100);
		medicoresponsavelCampo.setBounds(430, 190, 390, 20);

		medicamento = new JLabel("Medicamento em uso: ");
		medicamento.setBounds(250, 230, 240, 20);
		medicamentoCampo = new JtextFieldLetrasNumeros(200);
		medicamentoCampo.setBounds(410, 230, 410, 20);
		
		
		observacaodehoje = new JLabel("Observação: ");
		observacaodehoje.setBounds(250, 250, 170, 40);
		observacaodehojeCampo = new JTextArea("", 50, 100);
		observacaodehojeCampo.setBounds(250, 260, 1100, 370);
		observacaodehojeCampo.append(" ");
		observacaodehojeCampo.append("  \n");
		observacaodehojeCampo.setWrapStyleWord(true);
		observacaodehojeCampo.setLineWrap(true);
		// observacaodehojeCampo.setBackground(Color.gray);
		observacaodehojeCampo.setFont(new Font("Serifa", Font.ITALIC, 12));
		observacaodehojeCampo.setLineWrap(true);
		observacaodehojeCampo.setWrapStyleWord(true);
		observacaodehojeCampo.setBorder(BorderFactory.createTitledBorder(" Observações "));
		
		scrollPane = new JScrollPane(observacaodehojeCampo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(1100, 370));
		scrollPane.setBounds(250, 260, 1100, 370);		
		linha4 = new JLabel("<html><body>&nbsp;&nbsp; <CENTER>INSTRUÇÕES E DICAS: </CENTER><BR>"
				+ "&nbsp; |PACIENTE| - Comece digitando um nome. <br><br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "&nbsp; - |IDADE|, |SEXO| e |ESPECIALIDADE| são preenchidos automáticamente.<br><br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ " - Cadastre os demais campos.<br><br>"
				+ "&nbsp; |SALVAR| - Salva a avaliação na base de dados.<br><br><br><br>"
				+ "&nbsp; Para consultar o registro salvo use o atalho F6."
				+ "<br> <br></body></html>");
		linha4.setBounds(850, 50, 500, 180);
		linha4.setOpaque(true);
		linha4.setBackground(Color.WHITE);
		linha4.setForeground(Color.darkGray);
		linha4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		linha4.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 10));
		
		btenviar = new JButton("SALVAR");
		btenviar.setBounds(250, 642, 100, 30);
		btenviar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btlimpar = new JButton("LIMPAR");
		btlimpar.setBounds(360, 642, 100, 30);
		btlimpar.setContentAreaFilled(false);
		btlimpar.setFocusPainted(false);
		btlimpar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btcadastro = new JButton("CADASTRAR CLIENTE");
		btcadastro.setBackground(Color.white);
		btcadastro.setOpaque(true);
		btcadastro.setForeground(new Color(0, 0, 0));
		btcadastro.setRequestFocusEnabled(false);
		btcadastro.setBounds(10, 100, 200, 30);

		// btvoltar.setBorderPainted(false);

		btcadastro.setContentAreaFilled(false);
		btcadastro.setFocusPainted(false);
		btcadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btvoltar = new JButton("HOME");
		btvoltar.setBackground(Color.white);
		btvoltar.setOpaque(true);
		btvoltar.setForeground(new Color(0, 0, 0));
		btvoltar.setRequestFocusEnabled(false);
		// btvoltar.setBorderPainted(false);
		btvoltar.setContentAreaFilled(false);
		btvoltar.setFocusPainted(false);
		btvoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));
		btvoltar.setBounds(10, 50, 200, 30);

		add(scrollPane);
		add(linha4);
		add(nomedoformulario);
		add(nome);
		add(nomeCampo);
		add(idade);
		add(idadeCampo);
		add(sexo);
		add(sexoCampo);
		add(especialidade);
		add(especialidadeCampo);
		add(medicoresponsavel);
		add(medicamento);
		add(medicoresponsavelCampo);
		add(scrollPane);
		add(medicamentoCampo);
	//	add(observacaodehojeCampo);

		add(btvoltar);
		add(btcadastro);
		add(btenviar);
		add(btlimpar);

	}

	public void preencherFormComCliente(String clienteClicado) {
		System.out.println("Tratar de preencher com ["+ clienteClicado + "] q foi escolhido.");
		clientecadastro = register.getClientByName(clienteClicado); // pegar
																	// cliente
		refazerFormularioGenerico();

	}

	public void refazerFormularioPorID(int idChave, int idChaveAvaliacao){
		System.out.println("Tratar de preencher com ["+ idChave + " " + idChaveAvaliacao + "] q foi enviado.");
		clientecadastro = register.getClient(idChave); // pegar cliente de id que veio de alguem cadastrando
		register.getAvaliacaoDoCliente(clientecadastro, idChaveAvaliacao);
		refazerFormularioGenerico();
	}
	
	private void refazerFormularioGenerico(){
		limparFormulario();
		System.out.println("Preencher no form com ::" + clientecadastro.concatenados());
		nomeBuscado = clientecadastro.getNome(); // para evitar nova busca
		nomeCampo.setText(clientecadastro.getNome());
		idadeCampo.setText(Integer.toString(clientecadastro.getIdade()));
		idadeCampo.setEditable(false);
		sexoCampo.setText(clientecadastro.getSexo());
		sexoCampo.setEditable(false);
		especialidadeCampo.setText(clientecadastro.getEspecialidade());
		especialidadeCampo.setEditable(false);
		medicoresponsavelCampo.setText(clientecadastro.getMedicoresponsavel());
		medicamentoCampo.setText(clientecadastro.getMedicamento());
		observacaodehojeCampo.setText(clientecadastro.getObservacaodehoje());
		btenviar.setText("Atualizar");
		nomedoformulario.setText("<html>&nbsp;&nbsp;&nbsp; Atualizar Avaliação Clínica<html>");
		
		System.out.println("Agora preenchido com " + nomeCampo.getText());
	}
	public int getEditandoIdNovo() {
		return editandoIdNovo;
	}
	public void setEditandoIdNovo(int editandoIdNovo) {
		this.editandoIdNovo = editandoIdNovo;
	}
	public void mostrarOpcoesNomes(String nomeParte, String op) {
		//ao criar novo id pode ter vindo nesta pagina, dai ao preencher ja foi por busca no bd e nao precisamos do PopUpMenu
		int editandoNovo = this.getEditandoIdNovo();
		if (editandoNovo > 0) {
			System.out.println("temos editando bom de tamanho, dai apagar para nao ter JPopMenu neste caso" + editandoNovo);
			this.setEditandoIdNovo(0);
			return;
		}		
		String cliente;
		popupOpcoes = new JPopupMenu();
		if (nomeParte.equals(nomeBuscado)) {
			return;
		}
		if (nomeParte.equals("")) {
			return;
		}
		idadeCampo.setText("");
		sexoCampo.setText("");
		especialidadeCampo.setText("");
		System.out.println(" Parte eh [" + nomeParte + "] e antigo buscado eh [" + nomeBuscado + "]");
		if (!(nomeParte.equals(""))) {
			// Buscar na base de dados esse cliente nomeParte
			ArrayList<String> algunsClientes = register.getFewClients(nomeParte);
			for (int i = 0; i < algunsClientes.size(); i++) {
				cliente = algunsClientes.get(i);
				JMenuItem item = new JMenuItem(cliente);
				item.addActionListener(menuListener);
				popupOpcoes.add(item);
			}
			// para nao buscar duas vezes o mesmo texto seguidamente
			nomeBuscado = nomeParte;
		}
		popupOpcoes.setVisible(true);
		popupOpcoes.setFocusable(false);
		try {
			popupOpcoes.show(nomeCampo, 10, 20);
		} catch (Exception e1) {
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
			System.out.println("eerro em popupOpcoes ao tentar método show CFCA -> mostrarOpcoesNomes");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = IMAGE_BACKGROUND.getImage();
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void setupEvents() {
		
		// para escutar escolha de nomes nas opcoes
		menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String clienteClicado = event.getActionCommand();
				// System.out.println("Popup menu item ["+ clienteClicado + "]
				// was pressed.");
				preencherFormComCliente(clienteClicado);
			}
		};
		
		// monitorar se foi escrito algo no campo nomecampo
		nomeCampo.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				mostrarOpcoesNomes(nomeCampo.getText().trim(), "changedUpdate");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				mostrarOpcoesNomes(nomeCampo.getText().trim(), "removeUpdate");
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				mostrarOpcoesNomes(nomeCampo.getText().trim(), "insertUpdate");
			}
		});

		btcadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formCadastroClientes", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		btvoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formHome", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
				limparFormulario();
			}
		});
		btenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Aqui vamos armazenar no objeto da classe ClientAvali que está
				// em model
				String medico, medicammento, obs;
				medico = medicoresponsavelCampo.getText();
				medicammento = medicamentoCampo.getText();
				obs = observacaodehojeCampo.getText();
				
				if (clientecadastro == null || clientecadastro.getId() == 0 || medico.equals("") || medicammento.equals("") || obs.equals("")) {
					System.out.println("Alertar pois campo(s) necessario(s) esta(o) vazio(s) ClientFormCadastroAvaliacao");
					telainit.trocarTela("formHome", 0);
					return;
				}
				clientecadastro.setMedicoresponsavel(medico);
				clientecadastro.setObservacaodehoje(obs);
				clientecadastro.setMedicamento(medicammento);
				
				if (clientecadastro.getIdAvali() > 0) {
					register.atualizeAvaliacao(clientecadastro);
					telainit.trocarTela("formHome", 0);
				} else {
					register.salveAvaliacao(clientecadastro);
					telainit.trocarTela("formHome", 0);
				}
				// System.out.println("Texto do campo medico eh " +
				// medicoresponsavelCampo.getText());

				limparFormulario();
			}
		});
		

		btlimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou em limpar");
				limparFormulario();			}
		});
	}

	public void limparFormulario() {
		nomeCampo.setText("");
		idadeCampo.setText("");
		sexoCampo.setText("");
		especialidadeCampo.setText("");
		medicoresponsavelCampo.setText("");
		medicamentoCampo.setText("");
		observacaodehojeCampo.setText("");
	}
}