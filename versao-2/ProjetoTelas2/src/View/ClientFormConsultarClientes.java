package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
//import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Control.Register;
//import Control.RegisterTabelas;
import Model.ClientCadastro;
import Model.TabelaClientes;

public class ClientFormConsultarClientes extends JPanel implements VisualWindow {
	private JButton btvoltar;
	private JButton btcadastro;
	private JButton btavaliacao;
	private JButton btenviar;
	private MainFrame telainit;
	private Register register;
//	private RegisterTabelas registertabelas;
	private JLabel nome; // que veio do banco de dados
	private JLabel idade; // que veio do banco de dados
	private JLabel sexo; // que veio do banco de dados
	private JLabel medicoresponsavel; // que veio do banco de dados
	private JLabel especialidade;
	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(
			getClass().getResource("/images/alegrainferioresquerdoBusca.jpg"));
	private JLabel nomedoformulario;
	private JTextField nomeCampo;
	private JTextField idadeCampo;
	private JTextField sexoCampo;
	private JTextField medicoresponsavelCampo;
	private JTextField especialidadeCampo;

	private JPanel gridListagem; //grid
	private JTable tabelaJTable;//grid
	private JScrollPane tableContainer;//grid
	private TabelaClientes tabelaClientes;//grid

	private JPopupMenu popupOpcoes;// DigitandoEaparecendo
	private ActionListener menuListener;// DigitandoEaparecendo
	private String nomeBuscado = "";// DigitandoEaparecendo
	private ClientCadastro clientecadastro; // DigitandoEaparecendot 
	
	private int ultimacolunaclicada = -1; //para pegar duplo click
	private int ultimalinhaclicada = -1; //para pegar duplo click
	private JLabel linha4;
	

	public ClientFormConsultarClientes(MainFrame telainit, Register register) {
		this.telainit = telainit;
		this.register = register;
		//clientecadastro = register.getClient(1); // pegar cliente de id 1
		setupLayout();
		setupComponentes();
		setupEvents();
	}

	@Override
	public void setupLayout() {
		setLayout(null); // fixo
		// ImageIcon icon = (new
		// ImageIcon(MainFrame.class.getResource("../View/image/middle.gif")));
		setBackground(Color.white);
		setVisible(true);
		setSize(800, 550);
	}

	@Override
	public void setupComponentes() {
		nomedoformulario = new JLabel("FORMULÁRIO PARA CONSULTAR PACIENTES CADASTRADOS");
		nomedoformulario.setHorizontalAlignment(SwingConstants.CENTER);
		nomedoformulario.setBounds(250, 50, 570, 20);
		nomedoformulario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 150), 2));
		nomedoformulario.setBorder(
				javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.lightGray));
		nomedoformulario.setForeground(Color.black);
		nomedoformulario.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));

		nome = new JLabel("Paciente: ");
		nome.setBounds(250, 90, 200, 20);// x y w h
		nomeCampo = new JTextField(250);
		nomeCampo.setBounds(320, 90, 500, 20);

		// idade = new JLabel("Idade: " + clientecadastro.getIdade());
		idade = new JLabel("Idade: ");
		idade.setBounds(250, 130, 170, 20);
		idadeCampo = new JTextField(100);
		idadeCampo.setBounds(300, 130, 50, 20);

		sexo = new JLabel("Sexo: ");
		sexo.setBounds(360, 130, 80, 20);
		sexoCampo = new JTextField(100);
		sexoCampo.setBounds(400, 130, 80, 20);

		especialidade = new JLabel("Especialidade: ");
		especialidade.setBounds(490, 130, 150, 20);
		especialidadeCampo = new JTextField(100);
		especialidadeCampo.setBounds(600, 130, 220, 20);

		medicoresponsavel = new JLabel("Médico responsável: ");
		medicoresponsavel.setBounds(250, 170, 200, 20);// x y w h
		medicoresponsavelCampo = new JTextField(100);
		medicoresponsavelCampo.setBounds(400, 170, 420, 20);
		
		
		linha4 = new JLabel("<html><body>&nbsp;&nbsp; <CENTER>INSTRUÇÕES E DICAS: </CENTER><BR>"
				+ "&nbsp; |PROCURAR| - Mostra todos os clientes cadastrados. <br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Atualiza tabela.<br><br><br>"
				+ "&nbsp; |PACIENTE| - Digite um nome e clique em PROCURAR.<br><br><br>"
				+ "&nbsp; |TABELA| - Clicar duas vezes em um campo te levará a edição do registro.<br><br>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - Sempre clicar em |PROCURAR| após atualizar um registro."
				+ "<br> <br></body></html>");
		linha4.setBounds(850, 50, 500, 180);
		linha4.setOpaque(true);
		linha4.setBackground(Color.WHITE);
		linha4.setForeground(Color.darkGray);
		linha4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		linha4.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 10));
		

		btvoltar = new JButton("HOME");
		btcadastro = new JButton("CADASTRAR CLIENTE");
		btavaliacao = new JButton("FAZER AVALIAÇÃO");
		btenviar = new JButton("PROCURAR");
		btenviar.setBounds(450, 200, 150, 30);

		btvoltar.setBounds(10, 50, 200, 30);
		btavaliacao.setBounds(10, 100, 200, 30);
		btcadastro.setBounds(10, 150, 200, 30);

		btvoltar.setBackground(Color.white);
		btvoltar.setOpaque(true);
		btvoltar.setForeground(new Color(0, 0, 0));
		btvoltar.setRequestFocusEnabled(false);
		// btvoltar.setBorderPainted(false);
		btvoltar.setContentAreaFilled(false);
		btvoltar.setFocusPainted(false);
		btvoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btavaliacao.setBackground(Color.white);
		btavaliacao.setOpaque(true);
		btavaliacao.setForeground(new Color(0, 0, 0));
		btavaliacao.setRequestFocusEnabled(false);
		// btavaliacao.setBorderPainted(false);
		btavaliacao.setContentAreaFilled(false);
		btavaliacao.setFocusPainted(false);
		btavaliacao.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btcadastro.setBackground(Color.white);
		btcadastro.setOpaque(true);
		btcadastro.setForeground(new Color(0, 0, 0));
		btcadastro.setRequestFocusEnabled(false);
		// btvoltar.setBorderPainted(false);
		btcadastro.setContentAreaFilled(false);
		btcadastro.setFocusPainted(false);
		btcadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		add(linha4);
		add(nomedoformulario);
		add(nome);
		add(idade);
		add(sexo);
		add(especialidade);
		add(especialidadeCampo);
		add(medicoresponsavel);
		add(nomeCampo);
		add(idadeCampo);
		add(sexoCampo);
		add(medicoresponsavelCampo);

		add(btvoltar);
		add(btavaliacao);
		add(btcadastro);
		add(btenviar);
//grid
		// cria o modelo de Produto
		
		//antes era assim:
		//tabelaClientes = new TabelaClientes();
		tabelaClientes = register.getTabelaClientes();
		
		// atribui o modelo à tabela
		tabelaJTable = new JTable(tabelaClientes);
		tableContainer = new JScrollPane(tabelaJTable);
		gridListagem = new JPanel();
		// gridListagem.setBackground(Color.blue);
		// gridListagem.setVisible(true);
		gridListagem.setLayout(new BorderLayout());
		gridListagem.setBounds(10, 250, 1345, 420);

		// ate somar 780 de largura dada acima
		for (int i = 0; i < tabelaClientes.getColumnCount(); i++) {
			TableColumn column = tabelaJTable.getColumnModel().getColumn(i);
			if (i == 0)
				column.setPreferredWidth(260); // nome
			if (i == 1)
				column.setPreferredWidth(40); // idade
			if (i == 2)
				column.setPreferredWidth(110); // telefone
			if (i == 3)
				column.setPreferredWidth(80); // nascimento
			if (i == 4)
				column.setPreferredWidth(100); // especialidade
			if (i == 5)
				column.setPreferredWidth(210); // medico
		}
		gridListagem.add(tableContainer, BorderLayout.CENTER);
		gridListagem.setVisible(false);
		add(gridListagem);
//fim grid
	}
//grid
	public void exibirListaEncontrada() {
		ArrayList<ArrayList<String>> matrizClientes;
		// limpar tabela antes de nova busca??
		tabelaClientes.zerar();
		matrizClientes = register.buscaListaClientes(0, nomeCampo.getText(), idadeCampo.getText(), sexoCampo.getText(),
				especialidadeCampo.getText(), medicoresponsavelCampo.getText());
		gridListagem.setVisible(true);
		tabelaClientes.renovar(matrizClientes);
	}
//grid
	@Override
	public void setupEvents() {
//grid
		tabelaJTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = tabelaJTable.rowAtPoint(e.getPoint());
				int col = tabelaJTable.columnAtPoint(e.getPoint());
				// para etapa 3 pegar o campo e jogar para outra tela e editar
				// ou só mostrar
				System.out.println("CLICADO Value in the cell (" + row + ", " + col + ") clicked :"
						+ tabelaJTable.getValueAt(row, col).toString());
				//método para editar cliente ie Levar para atualizar um cliente
				atualizaSeDuploClick(row, col);
			}
		});
//grid
		btenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				exibirListaEncontrada();
				limpeCamposPoisSalvou();
			}
		});

		btcadastro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridListagem.setVisible(false); //pra esconder a tabela
				telainit.trocarTela("formCadastroClientes", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});

		btavaliacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridListagem.setVisible(false); //pra esconder a tabela
				telainit.trocarTela("formAvali", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});

		btvoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridListagem.setVisible(false); //pra esconder a tabela
				telainit.trocarTela("formHome", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		// DigitandoEaparecendo
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
		//DigitandoEaparecendo
		// para escutar escolha de nomes nas opcoes
		menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String clienteClicado = event.getActionCommand();
				System.out.println("Popup menu item ["+ clienteClicado + "] was pressed.");
				preencherFormComCliente(clienteClicado);
			}
		};
		
		
	}
	//atualiza como o número da última coluna e linha clicada
	public void atualizaSeDuploClick(int row, int col){
		
		if (ultimacolunaclicada == col &&  ultimalinhaclicada == row) {
			//System.out.println("duplo click ativado linha: " + row +" coluna "+ col);
			int idclient = tabelaClientes.getId(row);
			//System.out.println("id localizado deste cliente é: "+ idclient);
			//agora é só chamar a tela com o id do cliente
			telainit.trocarTela("formCadastroClientes", idclient);
			gridListagem.setVisible(false); //pra esconder a tabela
		}
		ultimacolunaclicada = col;
		ultimalinhaclicada = row;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = IMAGE_BACKGROUND.getImage();
		g.drawImage(img, 0, 0, this);
	}

	// DigitandoEaparecendo
	public void preencherFormComCliente(String clienteClicado) {
		System.out.println("atualiza nome do campo para nome " + clienteClicado);
		clientecadastro = register.getClientByName(clienteClicado); // pegar // cliente
		nomeBuscado = clientecadastro.getNome(); // para evitar nova busca
		nomeCampo.setText(clientecadastro.getNome());
	}

	// DigitandoEaparecendo
	public void mostrarOpcoesNomes(String nomeParte, String op) {
		String cliente;
		popupOpcoes = new JPopupMenu();

		if (nomeParte.equals(nomeBuscado)) {
			return;
		}
		if (!(nomeParte.equals(""))) {
			// Buscar na base de dados esse cliente nomeParte
			ArrayList<String> algunsClientes = register.getFewClients(nomeParte); // getFewClients - método que pega no campo nome string por string
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
			System.out.println("erro em popupOpcoes ao tentar método show");
		}
	}
	public void limpeCamposPoisSalvou() {
		nomeCampo.setText("");
		idadeCampo.setText("");
		sexoCampo.setText("");
		especialidadeCampo.setText("");
		medicoresponsavelCampo.setText("");
	}
	
}