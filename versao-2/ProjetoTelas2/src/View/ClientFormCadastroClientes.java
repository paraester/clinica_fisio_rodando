package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

import Control.Register;
import Model.ClientCadastro;
import Model.JtextFieldLetrasNumeros;
import Model.JtextFieldSomenteNumeros;

public class ClientFormCadastroClientes extends JPanel implements VisualWindow {
	private final ImageIcon IMAGE_BACKGROUND = new ImageIcon(getClass().getResource("/images/fundo-cadastro-clientes.jpg"));
	private MainFrame telainit;
	private JButton btvoltar;
	private JButton btavaliacao;
	private JButton btenviar;
	private JButton btapagar;
	private JButton btenviareavaliar;
	private JButton btlimpar;
	private int idChave = 0;// atua
	private JLabel nomedoformulario;
	private JLabel linha;
	private JLabel linha1, linha3;
	private JLabel nome;
	private JLabel idade;
	private JLabel endereco;
	private JLabel datanascimento;
	private JLabel sexo;
	String[] sexxo = new String[] { " ", "Feminino", "Masculino" };
	private JLabel planosaude;
	private JLabel telefone;
	private JLabel responsavel;
	private JLabel especialidade; // ( ortopedia, neurologia ,pilates)
	// define items in a String array:
	String[] especialidades = new String[] { "Selecione uma", "Ortopedia", "Neurologia", "Pilates", "Outros" };
	private JLabel datainiciotratamento;
	private JLabel datadaavaliacao;
	private JLabel medicoresponsavel;
	private JLabel qtdesessoesrealizadas;
	// private JLabel name;
	
	private JLabel diagnostico;
	private JTextField diagnosticoCampo;
	private JLabel condutas;
	private JTextField condutasCampo;
	private JLabel objetivos;
	private JTextField objetivosCampo;
	private JTextField nomeCampo;
	private JTextField idadeCampo;
	private JTextField enderecoCampo;
	private JTextField planosaudeCampo;
	private JTextField responsavelCampo;
	private JTextField medicoresponsavelCampo;
	private JTextField qtdesessoesrealizadasCampo;
	private JComboBox<String> comboSexo;
	private JComboBox<String> comboEspecialidades;
	private JFormattedTextField jFormattedtelefoneCampo;
	private JFormattedTextField jFormatteddatanascimento;
	private JFormattedTextField jFormatteddatainiciotratamentoCampo;
	private JFormattedTextField jFormatteddatadaavaliacaoCampo;
	// private final ImageIcon IMAGE_BACKGROUND = new
	// ImageIcon(getClass().getResource("/images/alegrainferioresquerdo.jpg"));

	private Register register;
	private ClientCadastro clientecadastro = null;
	private JLabel linha4;

	public ClientFormCadastroClientes(MainFrame telainit, Register register) {

		this.telainit = telainit;
		this.register = register;

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

		nomedoformulario = new JLabel("      Formulário para Cadastro de Clientes");
		nomedoformulario.setBounds(10, 20, 1000, 30);
		nomedoformulario.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 150), 2));
		nomedoformulario.setBorder(
				javax.swing.BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.lightGray, Color.lightGray));
		nomedoformulario.setForeground(Color.black);
		nomedoformulario.setFont(new Font("Serifa", Font.CENTER_BASELINE, 15));

		linha1 = new JLabel(" Informações pessoais");
		linha1.setBounds(10, 80, 995, 20);
		linha1.setOpaque(true);
		linha1.setBackground(Color.lightGray);
		linha1.setForeground(Color.BLUE);
		linha1.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));

		nome = new JLabel("Nome: ");
		// nome.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 0,
		// 0, Color.pink));
		nomeCampo = new JtextFieldLetrasNumeros(250);
		nome.setBounds(10, 100, 100, 40);// x y w h
		nomeCampo.setBounds(60, 110, 760, 20);

		sexo = new JLabel("Sexo: ");
		comboSexo = new JComboBox<String>(sexxo);
		// sexoCampo.setBounds(90, 200, 260, 20);
		sexo.setBounds(850, 100, 250, 40);
		comboSexo.setBounds(890, 110, 105, 20);

		endereco = new JLabel("Endereço: ");
		enderecoCampo = new JTextField(200);
		endereco.setBounds(10, 140, 100, 40);
		enderecoCampo.setBounds(90, 150, 730, 20);

		idade = new JLabel("Idade: ");
		idade.setBounds(850, 140, 100, 40);
		// telefoneCampo = new JTextField(40);
		idadeCampo = new JTextField(100);
		idadeCampo.setBounds(895, 150, 100, 20);

		telefone = new JLabel("Telefone: ");
		telefone.setBounds(10, 180, 100, 40);
		MaskFormatter telefoneCampo = null;
		try {
			telefoneCampo = new MaskFormatter("(##)####-####*");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormattedtelefoneCampo = new JFormattedTextField(telefoneCampo);
		jFormattedtelefoneCampo.setBounds(85, 190, 120, 20);

		planosaude = new JLabel("Plano de Saúde: ");
		planosaudeCampo = new JtextFieldLetrasNumeros(40);
		planosaude.setBounds(210, 190, 130, 20);
		planosaudeCampo.setBounds(330, 190, 230, 20);

		datanascimento = new JLabel("Data de nascimento: ");
		datanascimento.setBounds(570, 190, 210, 20);
		MaskFormatter mascaradatanascimento = null;
		try {
			mascaradatanascimento = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormatteddatanascimento = new JFormattedTextField(mascaradatanascimento);
		jFormatteddatanascimento.setBounds(720, 190, 100, 20);
		responsavel = new JLabel("Responsável: ");
		responsavelCampo = new JtextFieldLetrasNumeros(40);
		responsavel.setBounds(10, 230, 150, 20);
		responsavelCampo.setBounds(110, 230, 530, 20);

		linha = new JLabel(" Informações específicas");
		linha.setBounds(10, 280, 995, 20);
		// linha.setBorder(javax.swing.BorderFactory.createLineBorder(new
		// Color(0, 0, 0), 1));
		linha.setOpaque(true);
		linha.setBackground(Color.lightGray);
		linha.setForeground(Color.BLUE);
		linha.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));

		especialidade = new JLabel("Especialidade: ");
		comboEspecialidades = new JComboBox<String>(especialidades);
		especialidade.setBounds(10, 330, 210, 20);
		// especialidadeCampo.setBounds(160, 290, 190, 20);
		comboEspecialidades.setBounds(120, 330, 240, 20);// x y w h

		qtdesessoesrealizadas = new JLabel("Quantidade de sessões necessárias: ");
		qtdesessoesrealizadasCampo = new JtextFieldSomenteNumeros(40);
		qtdesessoesrealizadas.setBounds(400, 330, 355, 20);
		qtdesessoesrealizadasCampo.setBounds(670, 330, 50, 20);

		datainiciotratamento = new JLabel("Data início tratamento: ");
		datainiciotratamento.setBounds(10, 380, 210, 20);
		MaskFormatter datainiciotratamentoCampo = null;
		try {
			datainiciotratamentoCampo = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormatteddatainiciotratamentoCampo = new JFormattedTextField(datainiciotratamentoCampo);
		jFormatteddatainiciotratamentoCampo.setBounds(180, 380, 180, 20);

		datadaavaliacao = new JLabel("Data Avaliação: ");
		datadaavaliacao.setBounds(400, 380, 340, 20);
		MaskFormatter datadaavaliacaoCampo = null;
		try {
			datadaavaliacaoCampo = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		jFormatteddatadaavaliacaoCampo = new JFormattedTextField(datadaavaliacaoCampo);
		jFormatteddatadaavaliacaoCampo.setBounds(520, 380, 200, 20);

		medicoresponsavelCampo = new JTextField(100);
		medicoresponsavel = new JLabel("Médico responsável: ");
		medicoresponsavel.setBounds(10, 430, 200, 20);
		medicoresponsavelCampo.setBounds(170, 430, 650, 20);

		
		linha3 = new JLabel(" Informações fisioterapêuticas");
		linha3.setBounds(10, 450, 995, 20);
		linha3.setOpaque(true);
		linha3.setBackground(Color.lightGray);
		linha3.setForeground(Color.BLUE);
		linha3.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));

		objetivos = new JLabel("Objetivos: ");
		objetivosCampo = new JtextFieldLetrasNumeros(250);
		objetivos.setBounds(10, 470, 100, 40);// x y w h
		objetivosCampo.setBounds(85, 480, 920, 30);

		condutas = new JLabel("Condutas: ");
		condutasCampo = new JtextFieldLetrasNumeros(250);
		condutas.setBounds(10, 520, 250, 40);
		condutasCampo.setBounds(85, 530, 920, 30);

		diagnostico = new JLabel("Diagnóstico: ");
		diagnosticoCampo = new JtextFieldLetrasNumeros(250);
		diagnostico.setBounds(10, 570, 100, 40);
		diagnosticoCampo.setBounds(100, 580, 905, 30);
		
		linha4 = new JLabel("");
		linha4.setBounds(1090, 480, 210, 80);
		linha4.setOpaque(true);
		linha4.setBackground(Color.white);
		linha4.setForeground(Color.BLUE);
		linha4.setFont(new Font("Serifa", Font.CENTER_BASELINE, 12));
		
		btenviar = new JButton("SALVAR");
		btenviar.setBounds(50, 640, 100, 30);
		btenviar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btenviareavaliar = new JButton("SALVAR e AVALIAR");
		btenviareavaliar.setBounds(160, 640, 200, 30);
		btenviareavaliar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));
		btenviareavaliar.setVisible(true);
		
		btapagar = new JButton("APAGAR");
		btapagar.setBounds(380, 640, 100, 30);
		btapagar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btapagar.setVisible(false);

		btlimpar = new JButton("LIMPAR");
		btlimpar.setBounds(1090, 200, 210, 30);
		btlimpar.setContentAreaFilled(false);
		btlimpar.setFocusPainted(false);
		btlimpar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btvoltar = new JButton("HOME");
		btvoltar.setBounds(1090, 80, 210, 30); // botao volta pro home
		btvoltar.setBackground(Color.white);
		btvoltar.setOpaque(true);
		btvoltar.setForeground(new Color(0, 0, 0));
		btvoltar.setRequestFocusEnabled(false);
		// btvoltar.setBorderPainted(false);
		btvoltar.setContentAreaFilled(false);
		btvoltar.setFocusPainted(false);
		btvoltar.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		btavaliacao = new JButton("AVALIAÇÃO");
		btavaliacao.setBounds(460, 640, 110, 30);
		btavaliacao.setBackground(Color.white);
		btavaliacao.setOpaque(true);
		btavaliacao.setForeground(new Color(0, 0, 0));
		btavaliacao.setRequestFocusEnabled(false);
		// btavaliacao.setBorderPainted(false);
		btavaliacao.setContentAreaFilled(false);
		btavaliacao.setFocusPainted(false);
		btavaliacao.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(95, 159, 159), 2));

		add(nomedoformulario, BorderLayout.PAGE_START);
		add(linha1);
	//	add(linha4);
		add(nome);
		add(endereco);
		add(telefone);
		add(idade);
		add(datanascimento);
		add(sexo);
		add(comboSexo);
		add(planosaude);
		add(responsavel);
		add(linha);
		add(especialidade);
		add(comboEspecialidades);
		add(datainiciotratamento);
		add(datadaavaliacao);
		//add(medicoresponsavel);
		add(qtdesessoesrealizadas);
		add(nomeCampo);
		add(enderecoCampo);
		add(jFormattedtelefoneCampo);
		add(idadeCampo);
		add(jFormatteddatanascimento);
		add(planosaudeCampo);
		add(responsavelCampo);
		add(jFormatteddatainiciotratamentoCampo);
		add(jFormatteddatadaavaliacaoCampo);
		//add(medicoresponsavelCampo);
		add(qtdesessoesrealizadasCampo);
		
		add(linha3);
		add(objetivos);
		add(objetivosCampo);
		add(condutas);
		add(condutasCampo);
		add(diagnostico);
		add(diagnosticoCampo);

		add(btenviar);
		add(btenviareavaliar);
		add(btapagar);
		add(btlimpar);
		add(btvoltar);
	}

	@Override
	public void setupEvents() {
		btenviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idChave = addClient();
				if (idChave >= 0 ){
					System.out.println("foi pego idChave: " + idChave);
					//setIdChave(idChave);// atua
					System.out.println("Paciente atualizado ");
					JOptionPane.showMessageDialog (null, "As informações do paciente foram atualizadas ", "Informações atualizadas", JOptionPane.INFORMATION_MESSAGE);
					telainit.trocarTela("formConsultarClientes", 0);
				}
			}
		});
		btenviareavaliar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idChave = addClient();
				System.out.println("foi pego idChave: " + idChave + " e vamos ir para avali");
				if (idChave >= 0 ){
					//setIdChave(idChave);
					telainit.trocarTela("formAvali", idChave); // em MainFrame se id
																// > 0 usamos
																// re-preencher form
				}
				

			}
		});
		btapagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeClient();
			}
		});
		btlimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparFormulario();
			}
		});
		btvoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telainit.trocarTela("formHome", 0); // isso aqui por conta do
													// repasse de id overflow na
													// classe cadastro avali
				limparFormulario();
			}
		});

	}

	public void setIdChave(int idChave) {//nao mais necessário
		this.idChave = idChave;
	}

	public int getIdChave() {
		return this.idChave;
	}
	private int removeClient() {
		if (clientecadastro == null) {
			System.out.println("não tem o que remover");
			telainit.trocarTela("formHome", 0);
		} else {
			System.out.println("Agora vamos cadastrar " + clientecadastro.getId());
			register.removeCliente(clientecadastro);
		}

		int idChave = clientecadastro.getId();
		limparFormulario();
		return idChave;
	}

	private int addClient() {
		if (nomeCampo.getText().trim().equals("")) {
			System.out.println("campo nome eh obrigatorio");
			JOptionPane.showMessageDialog (null, "Campo Nome está vazio, por favor corrigir", "Campo Nome está vazio!", JOptionPane.WARNING_MESSAGE);
			//System.exit(-1);
			return -1;
		}
		
		if (clientecadastro == null || clientecadastro.getId() == 0) {//quando foi carregado novo vem null. Quando foi limpado vem zero clientecadastro.getId() == 0
			System.out.println("vamos pegar chave do novo cliente ");
			clientecadastro = register.getNovoClient();
		}
		// Client c = register.getClient();
		clientecadastro.setNome(nomeCampo.getText());
		// se nao foi fazio o idadeCampo.getText() dai converter pra
		// inteiro

		
		try {
			clientecadastro.setIdade(Integer.parseInt(idadeCampo.getText()));
		} catch (Exception e) {
			System.out.println("aqui parou por erro ao calcular a idade para inteiro");
			JOptionPane.showMessageDialog (null, "Campo idade está incorreto, por favor corrigir", "Campo Idade está incorreto!", JOptionPane.WARNING_MESSAGE);
			// System.exit(-1);
			return -1;
		}
		
		int anosDeVida = -1;
		try {
			anosDeVida = register.getIdadePelaDataNascimento(jFormatteddatanascimento.getText());
		} catch (Exception e) {
			System.out.println("aqui parou a data nascimento errada");
			JOptionPane.showMessageDialog (null, "Data nascimento errada, por favor corrigir", "Data nascimento errada", JOptionPane.WARNING_MESSAGE);
			//System.exit(-1);
			return -1;
		}
		
		if (anosDeVida != clientecadastro.getIdade()) {
			System.out.println("Idade " + clientecadastro.getIdade() + " nao confere " + anosDeVida);
			
			// Alertar usuario pois idade pode realmente ser diferente nos documentos
			int dialogResult = JOptionPane.showConfirmDialog (null, "A idade '" + clientecadastro.getIdade() + "' é diferente da data de nascimento!\nPois pela data de nascimento calculamos '" + anosDeVida + "' anos de vida!\nO formulário está preenchido corretamente?","Atenção: idade não confere",JOptionPane.YES_NO_OPTION);
			//pergunta era: Tem certeza que a idade é diferente?
			if(dialogResult != JOptionPane.YES_OPTION){
			  // Erro padrão a pessoa irá corrigir a idade e não salvaremos agora
				return -1;
			}
		}
		if ( ! register.isDateValid(jFormatteddatainiciotratamentoCampo.getText())) {
			System.out.println("campo data inicio tratamento é obrigatorio");
			JOptionPane.showMessageDialog (null, "Campo data inicio tratamento está vazio ou incorreto, por favor corrigir", "Campo Data inicio está vazio!", JOptionPane.WARNING_MESSAGE);
			//System.exit(-1);
			return -1;
		}
		if ( ! register.isDateValid(jFormatteddatadaavaliacaoCampo.getText())) {
			System.out.println("campo data avaliação é obrigatorio");
			JOptionPane.showMessageDialog (null, "Campo data avaliação está vazio ou incorreto, por favor corrigir", "Campo Data inicio está vazio!", JOptionPane.WARNING_MESSAGE);
			//System.exit(-1);
			return -1;
		}
		
		clientecadastro.setEndereco(enderecoCampo.getText());
		clientecadastro.setDatanascimento(jFormatteddatanascimento.getText());
		String tmpSexo = sexxo[comboSexo.getSelectedIndex()];
		clientecadastro.setSexo(tmpSexo);
		clientecadastro.setPlanosaude(planosaudeCampo.getText());
		clientecadastro.setTelefone(jFormattedtelefoneCampo.getText());
		clientecadastro.setResponsavel(responsavelCampo.getText());
		String tmpEspecialidade = especialidades[comboEspecialidades.getSelectedIndex()];
		if (tmpEspecialidade.equals("Selecione uma")) {
			tmpEspecialidade = "";
		}
		clientecadastro.setEspecialidade(tmpEspecialidade);
		
		clientecadastro.setDatainiciotratamento(jFormatteddatainiciotratamentoCampo.getText());
		
		clientecadastro.setDatadaavaliacao(jFormatteddatadaavaliacaoCampo.getText());
		
		clientecadastro.setObjetivosCampo(objetivosCampo.getText());
		clientecadastro.setCondutasCampo(condutasCampo.getText());
		clientecadastro.setDiagnosticoCampo(diagnosticoCampo.getText());

	//	clientecadastro.setMedicoresponsavel(medicoresponsavelCampo.getText());
		if (qtdesessoesrealizadasCampo.getText().equals("")) {
			clientecadastro.setQtdesessoesrealizadas(0);
		} else {
			try {
				clientecadastro.setQtdesessoesrealizadas(Integer.parseInt(qtdesessoesrealizadasCampo.getText()));
			} catch (Exception e) {
				System.out.println("aqui parou por erro ao calcular Qtdesessoesrealizadas para inteiro");
				JOptionPane.showMessageDialog (null, "Campo Quantidade de Sessões está incorreto, por favor corrigir", "Campo Quantidade de Sessões está incorreto!", JOptionPane.WARNING_MESSAGE);
				//System.exit(-1);
				return -1;
			}		
		}
		// System.out.println("enviar para salvamento do cliente " +
		// nomeCampo.getText());
		if (clientecadastro.getId() > 0) {
			System.out.println("Agora atualizar " + clientecadastro.getId());
			register.atualizeCadastroCliente(clientecadastro);

		} else {
			System.out.println("Agora vamos cadastrar " + clientecadastro.getId());
			register.salveCliente(clientecadastro);

		}

		/*
		 * if (register.salveCliente(clientecadastro)) {
		 * limpeCamposPoisSalvou(); }
		 */
		int idChave = clientecadastro.getId();
		limparFormulario();
		return idChave;
	}
	// método para repreencher o formulario quando vier de uma tabela que foi
	// consultada e dado dois clickes.
	public void refazerFormularioGenerico(int idChave) {
		System.out.println("Em refazerFormularioGenerico com id " + idChave);
		clientecadastro = register.getClient(idChave);
		System.out.println("Preencher no form com ::" + clientecadastro.concatenados());
		nomeCampo.setText(clientecadastro.getNome());
		idadeCampo.setText(Integer.toString(clientecadastro.getIdade()));
		enderecoCampo.setText(clientecadastro.getEndereco());
		jFormatteddatanascimento.setText(clientecadastro.getDatanascimento());
		// comboSexo.setText(clientecadastro.getSexo());
		for (int i = 0; i < sexxo.length; i++){
			if (sexxo[i].equals(clientecadastro.getSexo())){
				comboSexo.setSelectedIndex(i);
			}
		}
		planosaudeCampo.setText(clientecadastro.getPlanosaude());

	
		
		jFormattedtelefoneCampo.setText(clientecadastro.getTelefone());
		responsavelCampo.setText(clientecadastro.getResponsavel());
		// especialidades.setText(clientecadastro.getEspecialidade());
		for (int i = 0; i < especialidades.length; i++){
			if (especialidades[i].equals(clientecadastro.getEspecialidade())){
				comboEspecialidades.setSelectedIndex(i);
			}
		}
		jFormatteddatainiciotratamentoCampo.setText(clientecadastro.getDatainiciotratamento());
		jFormatteddatadaavaliacaoCampo.setText(clientecadastro.getDatadaavaliacao());

		
		System.out.println("médico: "+clientecadastro.getDatadaavaliacao());
		
	//	medicoresponsavelCampo.setText(clientecadastro.getMedicoresponsavel());
		
		
		qtdesessoesrealizadasCampo.setText(Integer.toString(clientecadastro.getQtdesessoesrealizadas()));
		
		
		objetivosCampo.setText(clientecadastro.getObjetivosCampo());
		condutasCampo.setText(clientecadastro.getCondutasCampo());
		diagnosticoCampo.setText(clientecadastro.getDiagnosticoCampo());
		
		
		System.out.println("Agora preenchido com " + nomeCampo.getText() + " Com id " + clientecadastro.getId());
		nomedoformulario.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Atualizar Cadastro do Cliente<html>");
		btenviar.setText("Atualizar");
		//btenviareavaliar.setVisible(false);
		btenviareavaliar.setText("Atualizar e Avaliar");
		btapagar.setVisible(true);
	}



	public void salveCliente(ClientCadastro cc) {
		System.out.println("bla salvando ClientFormCadastroClientes" + cc.getNome());
	}

	
	public void limparFormulario() {
		if(clientecadastro == null ){
			nomedoformulario.setText("<html>&nbsp; Formulário para Cadastro de Cliente<html>");
			nomeCampo.setText("");
			idadeCampo.setText("");
			enderecoCampo.setText("");
			jFormatteddatanascimento.setText("");
			comboSexo.setSelectedIndex(0);
			planosaudeCampo.setText("");
			jFormattedtelefoneCampo.setText("");
			responsavelCampo.setText("");
			comboEspecialidades.setSelectedIndex(0);
			jFormatteddatainiciotratamentoCampo.setText("");
			jFormatteddatadaavaliacaoCampo.setText("");
			//medicoresponsavelCampo.setText("");
			qtdesessoesrealizadasCampo.setText("");
			
			objetivosCampo.setText("");
			condutasCampo.setText("");
			diagnosticoCampo.setText("");
			
			
			btapagar.setVisible(false);
		} else {
			nomedoformulario.setText("<html>&nbsp; Formulário para Cadastro de Cliente<html>");
			clientecadastro.setId(0); // apagar o id
			nomeCampo.setText("");
			idadeCampo.setText("");
			enderecoCampo.setText("");
			jFormatteddatanascimento.setText("");
			comboSexo.setSelectedIndex(0);
			planosaudeCampo.setText("");
			jFormattedtelefoneCampo.setText("");
			responsavelCampo.setText("");
			comboEspecialidades.setSelectedIndex(0);
			jFormatteddatainiciotratamentoCampo.setText("");
			jFormatteddatadaavaliacaoCampo.setText("");
		//	medicoresponsavelCampo.setText("");
			qtdesessoesrealizadasCampo.setText("");
			objetivosCampo.setText("");
			condutasCampo.setText("");
			diagnosticoCampo.setText("");
			
			btapagar.setVisible(false);
			btenviar.setText("Salvar");
			btenviareavaliar.setText("SALVAR e AVALIAR");
		}
	}
	
	public void fundoTela() {
		JLabel fundo;
		ImageIcon icon = new ImageIcon("/images/fundo-cadastro-clientes.jpg");
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