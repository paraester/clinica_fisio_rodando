package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import Control.Register;
import Model.FuncionarioCadastro;
import Model.JPasswordFieldLimitado;
import Model.JtextFieldLetrasNumeros;
import Model.JtextFieldSomenteLetras;
import Model.JtextFieldSomenteNumeros;
import Model.UsuarioLogado;

import java.awt.Graphics;
import java.awt.Image;

public class ClientFormCadastroFuncionarios extends JPanel implements VisualWindow {

	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(
			getClass().getResource("/images/alegra2.jpg"));
	private JButton btenviar, btvoltar, btlimpar;
	private JLabel nomedoformulario, nome, idade, sexo, planosaude, telefone, responsavel, endereco, datanascimento, dataadmissao, crefito;
	private JLabel especialidade,linha4, linha5, linhacadastrosenha, usuario, senha;
	private JPasswordField senhaCampo; 
	// define items in a String array:
	String[] especialidades = new String[] { "Selecione uma especialidade", "Ortopedia", "Neurologia", "Pilates", "Outros" };
	private JComboBox<String> comboEspecialidades;
	String[] sexxo = new String[] { " ", "Feminino", "Masculino" };
	private JComboBox<String> comboSexo;

	private JTextField nomeCampo, idadeCampo, enderecoCampo, planosaudeCampo, responsavelCampo, usuarioCampo, crefitoCampo;
	private JFormattedTextField jFormattedtelefoneCampo, jFormatteddatanascimento, jFormatteddataadmissao;
	private MainFrame telainit;
	private Register register;
	private int idChave = 0;
	private UsuarioLogado usuarioLogado = null;
	private FuncionarioCadastro funcionariocadastro;
	private JPasswordField senhaCampoDois;
	private JLabel senhaDois;
	private JLabel usuarioDica;
	private JLabel usuarioCampoDica;

	public ClientFormCadastroFuncionarios(MainFrame telainit, Register register) {
		this.register = register;
		this.telainit = telainit;
		// clientecadastro = register.getClient(1); //pegar cliente de id 1
		setupLayout();
		setupComponentes();
		setupEvents();
	}

	@Override
	public void setupLayout() {
		setLayout(null); // fixo
		// background=new JLabel("../View/image/middle.jpg");

		// ImageIcon icon = (new
		// ImageIcon(MainFrame.class.getResource("../View/image/middle.gif")));
		setBackground(Color.white);
		setVisible(true);
		setSize(800, 550);
	}

	@Override
	public void setupComponentes() {
		nomedoformulario = new JLabel(" FORMULÁRIO PARA CADASTRO DE FUNCIONÁRIOS");
		nomedoformulario.setBounds(250, 50, 570, 30);
		nomedoformulario.setOpaque(true);
		nomedoformulario.setBackground(Color.WHITE);
		nomedoformulario.setForeground(Color.black);
		nomedoformulario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 1)); //createLineBorder(new Color(95, 159, 159), 2));
		nomedoformulario.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 15));
		
		nome = new JLabel("Nome: ");
		nome.setBounds(250, 110, 100, 20);// x y w h
		nomeCampo = new JtextFieldLetrasNumeros(250);
		nomeCampo.setBounds(310, 110, 510, 20);

		endereco = new JLabel("Endereço: ");
		enderecoCampo = new JtextFieldLetrasNumeros(200);
		endereco.setBounds(250, 140, 170, 40);
		enderecoCampo.setBounds(330, 150, 490, 20);

		sexo = new JLabel("Sexo: ");
		sexo.setBounds(250, 190, 200, 40);
		comboSexo = new JComboBox<String>(sexxo);
		comboSexo.setBounds(290, 200, 120, 20);

		idade = new JLabel("Idade: ");
		idade.setBounds(470, 190, 100, 40);
		idadeCampo = new JTextField(100);
		idadeCampo.setBounds(520, 200, 60, 20);

		telefone = new JLabel("Telefone: ");
		telefone.setBounds(630, 190, 110, 40);

		MaskFormatter telefoneCampo = null;
		try {
			telefoneCampo = new MaskFormatter("(##)####-####*");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormattedtelefoneCampo = new JFormattedTextField(telefoneCampo);
		jFormattedtelefoneCampo.setBounds(700, 200, 120, 20);

		datanascimento = new JLabel("Data de nascimento: ");
		datanascimento.setBounds(250, 240, 210, 20);
		MaskFormatter mascaradatanascimento = null;
		try {
			mascaradatanascimento = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormatteddatanascimento = new JFormattedTextField(mascaradatanascimento);
		jFormatteddatanascimento.setBounds(400, 240, 90, 20);

		planosaude = new JLabel("Plano de Saúde: ");
		planosaudeCampo = new JtextFieldLetrasNumeros(40);
		planosaude.setBounds(510, 240, 130, 20);
		planosaudeCampo.setBounds(630, 240, 190, 20);

		especialidade = new JLabel("Especialidade clínica: ");
		comboEspecialidades = new JComboBox<String>(especialidades);
		especialidade.setBounds(250, 280, 210, 20);
		// especialidadeCampo.setBounds(160, 290, 190, 20);
		comboEspecialidades.setBounds(420, 280, 240, 20);// x y w h
		
		dataadmissao = new JLabel("Data Admissão: ");
		dataadmissao.setBounds(250, 320, 210, 20);
		MaskFormatter mascaradataadmissao = null;
		try {
			mascaradataadmissao = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormatteddataadmissao = new JFormattedTextField(mascaradataadmissao);
		jFormatteddataadmissao.setBounds(370, 320, 100, 20);
		
		crefito = new JLabel("CREFITO: ");
		crefito.setBounds(550, 320, 100, 20);// x y w h
		//crefitoCampo = new JTextField(250);
		crefitoCampo = new JtextFieldSomenteNumeros(12);
		crefitoCampo.setBounds(620, 320, 200, 20);
		

		responsavel = new JLabel("Responsável: ");
		//responsavelCampo = new JTextField(40);
		responsavelCampo = new JtextFieldSomenteLetras(40);		
		responsavel.setBounds(250, 250, 150, 20);
		responsavelCampo.setBounds(350, 260, 450, 20);

		linhacadastrosenha = new JLabel(" Cadastre um usuário e uma senha para este funcionário");
		linhacadastrosenha.setBounds(250, 400, 570, 20);
		linhacadastrosenha.setOpaque(true);
		linhacadastrosenha.setBackground(Color.WHITE);//setBackground(Color.lightGray);
		linhacadastrosenha.setForeground(Color.BLACK);
		linhacadastrosenha.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 1));//BLACK
		linhacadastrosenha.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 10)); //setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));
		
		
		linha5 = new JLabel("");
		linha5.setBounds(250, 430, 500, 120);
		linha5.setOpaque(true);
		linha5.setBackground(Color.WHITE);
		linha5.setForeground(Color.darkGray);
		linha5.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		linha5.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 10));
		
		usuario = new JLabel("<html><body>Nome de usuário: </body></html>");
		usuario.setBounds(280, 440, 200, 20);// x y w h
		//usuarioCampo = new JTextField(8);
		usuarioCampo = new JtextFieldSomenteLetras(10);
		usuarioCampo.setColumns(10);
		usuarioCampo.isMaximumSizeSet();
		usuarioCampo.setBounds(420, 440, 100, 20);
		
		usuarioDica = new JLabel("<html><body><h5><font color=\"gray\">  somente letras e números</font></h5> </body></html>");
		usuarioDica.setBounds(540, 440, 250, 20);
		
		senha = new JLabel("<html><body>Senha: </body></html>");
		senha.setBounds(280, 480, 100, 15);// x y w h
		//senhaCampo =  new JPasswordField(15);
		senhaCampo =  new JPasswordFieldLimitado(6);
		senhaCampo.setEchoChar('#');
		senhaCampo.setBounds(280, 500, 90, 20);
		
		senhaDois = new JLabel("<html><body>Senha Novamente: </body></html>");
		senhaDois.setBounds(420, 480, 200, 20);// x y w h		
		senhaCampoDois =  new JPasswordFieldLimitado(6);
		senhaCampoDois.setEchoChar('#');
		senhaCampoDois.setBounds(420, 500, 90, 20);
		
		usuarioCampoDica = new JLabel("<html><body><h5><font color=\"gray\">  somente letras e números</font></h5> </body></html>");
		usuarioCampoDica.setBounds(540, 500, 250, 20);

		
	
		btenviar = new JButton("SALVAR CADASTRO");
		btenviar.setBounds(250, 560, 200, 30);
		btenviar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		// btvoltar.setBorderPainted(false);

		
		btlimpar = new JButton("LIMPAR FORMULÁRIO");
		btlimpar.setBounds(480, 560, 250, 30);
		btlimpar.setContentAreaFilled(false);
		btlimpar.setFocusPainted(false);
		btlimpar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btvoltar = new JButton("HOME");
		btvoltar.setBounds(10, 50, 200, 30);
		btvoltar.setBackground(Color.white);
		btvoltar.setOpaque(true);
		btvoltar.setForeground(new Color(0, 0, 0));
		btvoltar.setRequestFocusEnabled(false);
		btvoltar.setContentAreaFilled(false);
		btvoltar.setFocusPainted(false);
		btvoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 255, 225), 2));
		linha4 = new JLabel("<html><body>&nbsp;&nbsp; <CENTER>INSTRUÇÕES E DICAS: </CENTER><BR>"
				+ "&nbsp; |FUNCIONÁRIO| - Insira o nome do funcionário."
				+ " - Cadastre os demais campos.<br>"
				+ "&nbsp; |NOME| e |ENDEREÇO| - Somente letras e números <br><br>"
				+ "&nbsp; |CREFITO| - Somente números<br><br>"
				+ "&nbsp; |NOME DE USUÁRIO| - Somente letras sem \"ç\" e sem acentos. Limite 10 letras<br><br>"
				+ "&nbsp; |SENHA| e |SENHA NOVAMENTE|- Sem \"ç\" e sem acentos. Limite 10 letras<br><br>"
				+ "&nbsp; |SALVAR| - Salva o cadastro na base de dados. F7 para consultar<br><br>"
				+ "<br></body></html>");
		linha4.setBounds(845, 50, 500, 200);
		linha4.setOpaque(true);
		linha4.setBackground(Color.WHITE);
		linha4.setForeground(Color.darkGray);
		linha4.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		linha4.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 10));
		
		add(linhacadastrosenha);
		add(usuario);
		add(usuarioCampo);
		add(usuarioDica);
		add(senha);
		add(senhaDois);
		add(senhaCampo);
		add(senhaCampoDois);
		add(usuarioCampoDica);
		add(linha4);
		add(linha5);
		add(nomedoformulario);
		add(nome);
		add(nomeCampo);
		add(sexo);
		add(comboSexo);
		add(idade);
		add(idadeCampo);
		add(idadeCampo);
		add(planosaude);
		add(endereco);
		add(enderecoCampo);
		add(telefone);
		add(jFormattedtelefoneCampo);
		add(datanascimento);
		add(jFormatteddatanascimento);
		add(jFormatteddataadmissao);
		add(dataadmissao);
		add(planosaude);
		add(planosaudeCampo);
		add(especialidade);
		add(comboEspecialidades);
		add(crefito);
		add(crefitoCampo);
		add(btvoltar);
		add(btenviar);
		add(btlimpar);
	}

	@Override
	public void setupEvents() {
		nomeCampo.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("changedUpdate recebido com texto " + nomeCampo.getText().trim());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("removeUpdate recebido com texto " + nomeCampo.getText().trim());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("insertUpdate recebido com texto " + nomeCampo.getText().trim());
			}
		});

		btvoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formHome", 0); // isso aqui por conta do
													// repasse de id overflow na
													// classe cadastro avali
			}
		});

		
		btenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//if (senhaCampo.equals(senhaCampoDois)){
					int idChave = addFuncionario();
					if (idChave >= 0) {
						System.out.println("Iniciar salvamento do funcionário " + nomeCampo.getText());
						setIdChave(idChave);// atua
						limparFormulario();
						System.out.println("Funcionario atualizado ");
						JOptionPane.showMessageDialog(null, "As informações do funcionario foram atualizadas ",
								"Informações atualizadas", JOptionPane.INFORMATION_MESSAGE);
						telainit.trocarTela("ClientFormConsultarFuncionarios", 0);
				//	}else{
				//		JOptionPane.showMessageDialog(null, "Senhas não conferem - As senhas digitadas são diferentes ",
				//				"Digite novamente as senhas", JOptionPane.INFORMATION_MESSAGE);
				//	}
					
				}
			}
		});
		btlimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou em limpar");
				limparFormulario();			}
		});
	}

	public void setIdChave(int idChave) {
		this.idChave = idChave;
	}

	public int getIdChave() {
		return this.idChave;
	}

	private int addFuncionario() {
		if (funcionariocadastro == null || funcionariocadastro.getId() == 0) {
			System.out.println("vamos pegar chave do novo funcionariocadastro ");
			funcionariocadastro = register.getNovoFuncionario();
			usuarioLogado = register.getLogin();
		}
		funcionariocadastro.setNome(nomeCampo.getText());
		if (idadeCampo.getText().equals("")) {
			funcionariocadastro.setIdade(0);
		} else {
			funcionariocadastro.setIdade(Integer.parseInt(idadeCampo.getText()));
		}

		int anosDeVida = -1;
		try {
			anosDeVida = register.getIdadePelaDataNascimento(jFormatteddatanascimento.getText());
		} catch (Exception e) {
			System.out.println("aqui parou a data nascimento errada");
			JOptionPane.showMessageDialog(null, "Data nascimento errada, por favor corrigir", "Data nascimento errada",
					JOptionPane.WARNING_MESSAGE);
			// System.exit(-1);
			return -1;
		}

		if (anosDeVida != funcionariocadastro.getIdade()) {
			System.out.println("Idade " + funcionariocadastro.getIdade() + " nao confere " + anosDeVida);

			// Alertar usuario pois idade pode realmente ser diferente nos
			// documentos
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Idade diferente da data de nascimento! \n Tem certeza que a idade é diferente?", "Atenção",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult != JOptionPane.YES_OPTION) {
				// Erro padrão a pessoa irá corrigir a idade e não salvaremos
				// agora
				return -1;
			}
		}

		funcionariocadastro.setEndereco(enderecoCampo.getText());
		funcionariocadastro.setDatanascimento(jFormatteddatanascimento.getText());
		String tmpSexo = sexxo[comboSexo.getSelectedIndex()];
		funcionariocadastro.setSexo(tmpSexo);
		funcionariocadastro.setPlanosaude(planosaudeCampo.getText());
		funcionariocadastro.setTelefone(jFormattedtelefoneCampo.getText());

		String tmpEspecialidade = especialidades[comboEspecialidades.getSelectedIndex()];
		if (tmpEspecialidade.equals("Selecione uma")) {
			tmpEspecialidade = "";
		}
		funcionariocadastro.setEspecialidade(tmpEspecialidade);
		
		funcionariocadastro.setDataAdmissao(jFormatteddataadmissao.getText());
		
		funcionariocadastro.setCrefito(crefito.getText());
		
		String usuario = usuarioCampo.getText();
		
		System.out.println(" O usuário deste cadastro eh " + usuario);
	
		usuarioLogado.setLoginUsuario(usuario);	
		
		System.out.println("usuário logado: " + usuarioLogado.getLoginUsuario());
		
		String senha = new String(senhaCampo.getPassword());
	//	String senha = senhaCampo.getText();
		System.out.println(" O senha eh " + senha);
		usuarioLogado.setSenhaUsuario(senha);
		
			
		if (funcionariocadastro.getId() > 0) {
			System.out.println("Agora atualizar " + funcionariocadastro.getId());
			register.atualizeCadastroFuncionario(funcionariocadastro);
			register.atualizeCadastroSenhaFuncionario(usuarioLogado);

			funcionariocadastro.setId(0);

		} else {
			System.out.println("Agora vamos cadastrar " + funcionariocadastro.getId());
			register.salveFuncionario(funcionariocadastro);
			System.out.println("na tabela usuários:");
			register.salveUsuarioSenhaFuncionario(usuarioLogado);
			limparFormulario();
		}

		int idChave = funcionariocadastro.getId();

		limparFormulario();
		return idChave;
	}

	public void refazerFormularioGenerico(int idChave) {
		System.out.println("o id que chegou é " + idChave);
		funcionariocadastro = register.getFuncionario(idChave);
		System.out.println("Preencher no form com ::" + funcionariocadastro.concatenados());
		nomeCampo.setText(funcionariocadastro.getNome());
		idadeCampo.setText(Integer.toString(funcionariocadastro.getIdade()));
		enderecoCampo.setText(funcionariocadastro.getEndereco());
		jFormatteddatanascimento.setText(funcionariocadastro.getDatanascimento());
		// comboSexo.setText(funcionariocadastro.getSexo());
		for (int i = 0; i < sexxo.length; i++) {
			if (sexxo[i].equals(funcionariocadastro.getSexo())) {
				comboSexo.setSelectedIndex(i);
			}
		}
		planosaudeCampo.setText(funcionariocadastro.getPlanosaude());
		jFormattedtelefoneCampo.setText(funcionariocadastro.getTelefone());
		System.out.println("Agora preenchido com " + nomeCampo.getText() + " Com id " + funcionariocadastro.getId());
	}

	public void limparFormulario() {
		nomeCampo.setText("");
		idadeCampo.setText("");
		enderecoCampo.setText("");
		jFormatteddatanascimento.setText("");
		comboSexo.setSelectedIndex(0);
		planosaudeCampo.setText("");
		jFormattedtelefoneCampo.setText("");
		responsavelCampo.setText("");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = IMAGE_BACKGROUND.getImage();
		g.drawImage(img, 0, 0, this);
	}

}