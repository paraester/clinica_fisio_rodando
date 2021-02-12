package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

//import javax.swing.Icon;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Control.Register;

public class MainFrame extends JFrame implements VisualWindow {
	 
	private Register register;
	private JMenuBar menuBar;
	private JMenu menuCliente;
	private JMenu menuConsulta;
	private JMenu menuFuncionarios;
	private JMenuItem cadastrarFuncionarios, cadastrarcliente, avaliacaocliente,
			consultarClientes, consultarAvaliacoes, consultarFuncionarios;
	private CardLayout cl;
	private TelaBase base;
	// private final ImageIcon icon = (new
	// ImageIcon("../View/image/middle.jpg"));
	// private Icon icon = new ImageIcon("/images/middle.gif");

	public MainFrame() {
		setupLayout();
		setupComponentes();
		setupEvents();
	}
	@Override
	public void setupLayout() {
		//System.out.println("que veio " + );
		//int largura = 1024;
		//int altura = 600;
		//int largura = 850;
		//int altura = 600;
		int largura = 1366;
		int altura = 768;
		setVisible(false); // configurar primeiro e depois exibir
		setTitle("Clínica de Fisioterapia");
		setLayout(null); // fixo
		setLayout(new BorderLayout());
	//	setResizable(false);// para desabilitar o botão maximizar
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(largura, altura);
		Dimension dimensoes = this.getSize();
		if (dimensoes.height != altura || dimensoes.width != largura) {
			System.out.println("Pegou dimensoes erradas!!");
			System.out.println(dimensoes);
			//System.out.println("tentando setar novamente");
			setSize(largura, altura);			
		}
		setLocationRelativeTo(null); // para exibir centralizado na tela
		setVisible(true);
	}

	@Override
	public void setupComponentes() {
		register = new Register(); // faz o acesso a base de dados
		menuBar = new JMenuBar(); // menu

		menuCliente = new JMenu("Paciente");
		menuCliente.setMnemonic(KeyEvent.VK_P);// ALT+P

		cadastrarcliente = new JMenuItem("Cadastrar", KeyEvent.VK_C);
		KeyStroke f2KeyStroke = KeyStroke.getKeyStroke("F2");
		cadastrarcliente.setAccelerator(f2KeyStroke);

		menuCliente.addSeparator();
		avaliacaocliente = new JMenuItem("Avaliar", KeyEvent.VK_A);
		KeyStroke f3KeyStroke = KeyStroke.getKeyStroke("F3");
		avaliacaocliente.setAccelerator(f3KeyStroke);

		menuCliente.addSeparator();
		menuCliente.add(cadastrarcliente);
		menuCliente.add(avaliacaocliente);
		menuBar.add(menuCliente);
		// inicio menuConsulta
		menuConsulta = new JMenu("Consultar");
		menuConsulta.setMnemonic(KeyEvent.VK_C);// ALT+C

		consultarClientes = new JMenuItem("Paciente", KeyEvent.VK_P);
		KeyStroke f5KeyStroke = KeyStroke.getKeyStroke("F5");
		consultarClientes.setAccelerator(f5KeyStroke);

		consultarAvaliacoes = new JMenuItem("Avaliações", KeyEvent.VK_A);
		KeyStroke f6KeyStroke = KeyStroke.getKeyStroke("F6");
		consultarAvaliacoes.setAccelerator(f6KeyStroke);

		consultarFuncionarios = new JMenuItem("Funcionários", KeyEvent.VK_F);
		KeyStroke f7KeyStroke = KeyStroke.getKeyStroke("F7");
		consultarFuncionarios.setAccelerator(f7KeyStroke);

		menuConsulta.add(consultarClientes); // ClientFormConsultarClientes
		menuConsulta.add(consultarAvaliacoes);// ClientFormConsultarAvaliacoes
		menuConsulta.add(consultarFuncionarios);// ClientFormConsultarAvaliacoes
		menuBar.add(menuConsulta);
		// fim menuConsulta
		// inicio menuFuncionarios
		menuFuncionarios = new JMenu("Funcionários");
		menuFuncionarios.setMnemonic(KeyEvent.VK_F);// ALT+F

		cadastrarFuncionarios = new JMenuItem("Cadastrar", KeyEvent.VK_C);
		KeyStroke f8KeyStroke = KeyStroke.getKeyStroke("F8");
		cadastrarFuncionarios.setAccelerator(f8KeyStroke);

		menuFuncionarios.add(cadastrarFuncionarios);
		menuBar.add(menuFuncionarios);
		// fim menufuncionarios

		/* menu sair
		menusair = new JMenu("sair");
		menusair.setMnemonic(KeyEvent.VK_S);// ALT+S
		menusair.add(saindoSistema);
		menuBar.add(menusair);		
		 */

		setJMenuBar(menuBar);
		revalidate();
		base = new TelaBase(this, register); // MAINFRAME->TB e TB -> MAINFRAME
												// e TB -> REGISTER
				
		cl = new CardLayout();
		cl = (CardLayout) base.getLayout();
		register.setTelaBase(base); // REGISTER->TB para o register ter acesso a
									// telaBase e atualizar o formAvali por
									// exemplo qdo vier com um id SalvarEavaliar
									// do fomrCadastroClient

		add(base, BorderLayout.CENTER);
		


		cadastrarFuncionarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("formCadastroFuncionarios", 0);
			}
		});

		cadastrarcliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("formCadastroClientes", 0);
			}
		});
		avaliacaocliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("formAvali", 0);
			}
		});
		consultarClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("formConsultarClientes", 0);
			}
		});
		consultarAvaliacoes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("formConsultarAvaliacoes", 0);
			}
		});// faz a busca pelo nome do paciente depois chama a classe

		consultarFuncionarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				trocarTela("ClientFormConsultarFuncionarios", 0);
			}
		});

	}

	@Override
	public void setupEvents() {

	}

	public void trocarTela(String tela, int idChave) {
		System.out.println("TrocarTela para a tela " + tela + " com idChave " + idChave);
		// para redesenhar a tela com os campos preenchidos
		if (tela.equals("formAvali")) {
			System.out.println("Clicado em formAvali");
			ClientFormCadastroAvaliacao cfa = register.base.getFormCadastroAvali();
			if (idChave == 0) {
				idChave = register.bd.getIdChave();
			}
			if (idChave > 0) {
				cfa.setEditandoIdNovo(idChave);
				int idChaveAvaliacao = register.bd.getIdAvali();
				System.out.println("Em Mainframe e pego idchave " + idChave + " e idChaveAvaliacao " + idChaveAvaliacao);
				System.out.println("e vamos atualizar o formAvali");
				cfa.refazerFormularioPorID(idChave, idChaveAvaliacao);
				System.out.println("pronto form com id recem salvo");
			} else {
				cfa.limparFormulario();
			}
		} else if (tela.equals("formCadastroClientes")) {
			ClientFormCadastroClientes cfc = register.base.getFormCadastroClientes();
			if (idChave > 0) {
				//duplo clique num cliente agora pegar os dados dele e exibir
				cfc.refazerFormularioGenerico(idChave);
			} else {
				cfc.limparFormulario();
			}			
		} else if (tela.equals("formCadastroFuncionarios")) {
			ClientFormCadastroFuncionarios t1 = register.base.getFormCadastroFuncionarios();
			if (idChave > 0) {
				System.out.println("o id que saiu de mainframe é " + idChave);		
				t1.refazerFormularioGenerico(idChave);
			} else {
				t1.limparFormulario();
			}
		}
		cl.show(base, tela);
	}
}