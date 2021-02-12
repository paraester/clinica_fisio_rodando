package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import javax.swing.table.TableColumn;

import Control.Register;
import Model.FuncionarioCadastro;
import Model.TabelaFuncionarios;

public class ClientFormConsultarFuncionarios extends JPanel implements VisualWindow {
	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(
			getClass().getResource("/images/alegrainferioresquerdoBusca.jpg"));

	private JButton btvoltar;
	private JButton btconsultarClientes;
	private JButton btenviar;
	private MainFrame telainit;
	private JLabel nome; 
	private JPanel gridListagem; //grid
	private JTable tabelaJTable;//grid
	private JScrollPane tableContainer;//grid
	private TabelaFuncionarios tabelaFuncionarios;//grid
	
	private JPopupMenu popupOpcoes;// DigitandoEaparecendo
	private ActionListener menuListener;// DigitandoEaparecendo
	private String nomeBuscado = "";// DigitandoEaparecendo
	private FuncionarioCadastro funcionariocadastro; //digitando e aparecendo

	
	private int ultimacolunaclicada = -1; //para pegar duplo click
	private int ultimalinhaclicada = -1; //para pegar duplo click
	
	
	private JLabel nomedoformulario;
	private JTextField nomeCampo;
	private Register register;

	public ClientFormConsultarFuncionarios(MainFrame telainit, Register register) {
		this.register = register;
		this.telainit = telainit;
		funcionariocadastro = register.getFuncionario(1); // pegar cliente de id 1
		setupLayout();
		setupComponentes();
		setupEvents();
	}

	@Override
	public void setupLayout() {
		setLayout(null); // fixo
		setBackground(Color.white);
		setVisible(true);
		setSize(800, 550);
	}

	@Override
	public void setupComponentes() {
		nomedoformulario = new JLabel("FORMULÁRIO PARA CONSULTAR FUNCIONÁRIOS CADASTRADOS");
		nomedoformulario.setHorizontalAlignment(SwingConstants.CENTER);
		nomedoformulario.setBounds(250, 50, 570, 20);
		nomedoformulario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 150), 2));
		nomedoformulario.setBorder(
				javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.lightGray));
		nomedoformulario.setForeground(Color.black);
		nomedoformulario.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));

		nome = new JLabel("Nome do Funcionário: ");
		nome.setBounds(250, 90, 200, 20);// x y w h
		nomeCampo = new JTextField(250);
		nomeCampo.setBounds(250, 120, 570, 20);
		
		btenviar = new JButton("PROCURAR");
		btenviar.setBounds(410, 150, 120, 20);

		btvoltar = new JButton("HOME");
		btvoltar.setBounds(10, 50, 200, 30);
		btvoltar.setBackground(Color.white);
		btvoltar.setOpaque(true);
		btvoltar.setForeground(new Color(0, 0, 0));
		btvoltar.setRequestFocusEnabled(false);
		// btvoltar.setBorderPainted(false);
		btvoltar.setContentAreaFilled(false);
		btvoltar.setFocusPainted(false);
		btvoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));

		btconsultarClientes = new JButton("CLIENTES CADASTRADOS");
		btconsultarClientes.setBounds(10, 100, 200, 30);
		btconsultarClientes.setBackground(Color.white);
		btconsultarClientes.setOpaque(true);
		btconsultarClientes.setForeground(new Color(0, 0, 0));
		btconsultarClientes.setRequestFocusEnabled(false);
		btconsultarClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(224, 224, 224), 2));

		add(nomedoformulario);
		add(nome);
		add(nomeCampo);

		add(btvoltar);
		add(btconsultarClientes);
		add(btenviar);

	//grid
			// cria o modelo de Produto
			//antes era assim:
			//tabelaClientes = new TabelaClientes();
			tabelaFuncionarios = register.getTabelaFuncionarios();
			
			// atribui o modelo à tabela
			tabelaJTable = new JTable(tabelaFuncionarios);
			tableContainer = new JScrollPane(tabelaJTable);
			gridListagem = new JPanel();
			// gridListagem.setBackground(Color.blue);
			// gridListagem.setVisible(true);
			gridListagem.setLayout(new BorderLayout());
			gridListagem.setBounds(10, 250, 830, 290);

			// ate somar 780 de largura dada acima
			for (int i = 0; i < tabelaFuncionarios.getColumnCount(); i++) {
				TableColumn column = tabelaJTable.getColumnModel().getColumn(i);
				if (i == 0)
					column.setPreferredWidth(190); // nome
				if (i == 1)
					column.setPreferredWidth(40); // idade
				if (i == 2)
					column.setPreferredWidth(110); // endereco
				if (i == 3)
					column.setPreferredWidth(80); // nascimento
				if (i == 4)
					column.setPreferredWidth(100); // sexo
				if (i == 5)
					column.setPreferredWidth(100); // planosaude
				if (i == 6)
					column.setPreferredWidth(160); // telefone
			}
			gridListagem.add(tableContainer, BorderLayout.CENTER);
			gridListagem.setVisible(false);
			add(gridListagem);
	//fim grid
		}
	//grid
		public void exibirListaEncontrada() {
			ArrayList<ArrayList<String>> matrizFuncionario;
			// limpar tabela antes de nova busca??
			tabelaFuncionarios.zerar();
			matrizFuncionario = register.buscaListaFuncionarios(nomeCampo.getText());
			gridListagem.setVisible(true);
			tabelaFuncionarios.renovar(matrizFuncionario);
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
				atualizaSeDuploClick(row, col);
			}
		});
//grid
		btconsultarClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formConsultarClientes", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		btvoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridListagem.setVisible(false); //pra esconder a tabela
				telainit.trocarTela("formHome", 0); //isso aqui por conta do repasse de id overflow na classe cadastro avali
			}
		});
		btenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exibirListaEncontrada();
			}
		});
		// DigitandoEaparecendo
		// monitorar se foi escrito algo no campo nomecampo
		nomeCampo.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					mostrarOpcoesNomes(nomeCampo.getText().trim(), "changedUpdate");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					mostrarOpcoesNomes(nomeCampo.getText().trim(), "removeUpdate");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					mostrarOpcoesNomes(nomeCampo.getText().trim(), "insertUpdate");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//DigitandoEaparecendo
		// para escutar escolha de nomes nas opcoes
		menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String clienteClicado = event.getActionCommand();
				System.out.println("Popup menu item ["+ clienteClicado + "] was pressed.");
				try {
					preencherFormComCliente(clienteClicado);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	public void atualizaSeDuploClick(int row, int col){
		if (ultimacolunaclicada == col &&  ultimalinhaclicada == row) {
			int idclient = tabelaFuncionarios.getId(row);
			//System.out.println("id localizdo do funcion é " + idclient);
			telainit.trocarTela("formCadastroFuncionarios", idclient);
			gridListagem.setVisible(false);
		}
			ultimacolunaclicada = col;
			ultimalinhaclicada = row;
		}
	// DigitandoEaparecendo
	public void preencherFormComCliente(String clienteClicado) throws SQLException {
		funcionariocadastro = register.getFuncionarioByName(clienteClicado); // pegar // cliente
		nomeBuscado = funcionariocadastro.getNome(); // para evitar nova busca
		nomeCampo.setText(funcionariocadastro.getNome());
	}

	// DigitandoEaparecendo
	public void mostrarOpcoesNomes(String nomeParte, String op) throws SQLException {
		String funcionario;
		popupOpcoes = new JPopupMenu();

		if (nomeParte.equals(nomeBuscado)) {
			return;
		}
		if (!(nomeParte.equals(""))) {
			// Buscar na base de dados esse cliente nomeParte
			ArrayList<String> algunsFuncionarios = register.getFewFuncionarios(nomeParte); // getFewFuncionarios - método que pega no campo nome string por string
			for (int i = 0; i < algunsFuncionarios.size(); i++) {
				funcionario = algunsFuncionarios.get(i);
				
				JMenuItem item = new JMenuItem(funcionario);
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
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = IMAGE_BACKGROUND.getImage();
		g.drawImage(img, 0, 0, this);
	}

}