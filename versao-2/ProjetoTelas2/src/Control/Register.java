package Control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.ClienteCadastroDAO;
import DAO.LoginUsuarioDAO;
//import DAO.TabelasBdCriadas;
import Model.ClientCadastro;
import Model.FuncionarioCadastro;
import Model.TabelaAvaliacoes;
import Model.TabelaClientes;
import Model.TabelaFuncionarios;
import Model.UsuarioLogado;
import View.TelaBase;

public class Register {
	// private BD bd;
	public ClienteCadastroDAO bd;
	public LoginUsuarioDAO lbd;
	//public TabelasBdCriadas tbd;
	public LoginUsuarioDAO login;
	public TelaBase base;

	public Register() {
		// este objeto eh criado na classe MainFrame
		// bd = new BD();
		bd = new ClienteCadastroDAO();
		lbd = new LoginUsuarioDAO();
	}
	
/*	//cria a tabela usuarios
	public boolean criarTabela() {
		return tbd.criarTabela();
	}
	*/
	public TabelaClientes getTabelaClientes() {
		TabelaClientes tabelaClientes = new TabelaClientes();
		return tabelaClientes;
	}
	public TabelaAvaliacoes getTabelaAvaliacoes() {
		TabelaAvaliacoes tabelaAvaliacoes = new TabelaAvaliacoes();
		return tabelaAvaliacoes;
	}
	public TabelaFuncionarios getTabelaFuncionarios() {
		TabelaFuncionarios tabelaFuncionarios = new TabelaFuncionarios();
		return tabelaFuncionarios;
	}

//este é diferente do outro pq devolve um cliente que existe na BD utilizo pra trocar tela e carregar os dados do atualiza inclusive
	public ClientCadastro getClient(int id) {
		System.out.println("Em register e getCliente do id " + id);
		ClientCadastro cliente;
		cliente = new ClientCadastro();
		if (id > 0) {
			System.out.println("criando novo cliente");
			cliente = bd.getClient(cliente, id);
		}
		
		System.out.println("Pego o cliennte " + cliente.getNome());
		return cliente;
	}
	
//este getNovoClient cria um novo cliente em qualquer classe da View (Pq até 17/10 eu estava criando os objetos na View e enviando pro Register)
	public ClientCadastro getNovoClient() {
		ClientCadastro cliente = new ClientCadastro();
		return cliente;
	}

	//utilizado para consultas
	public FuncionarioCadastro getFuncionario(int id) {
		FuncionarioCadastro funcionario;
		funcionario = new FuncionarioCadastro();//idChave
		if (id > 0) {
			funcionario = bd.getFuncionarioById(id);
		}
		return funcionario;
	}
	public FuncionarioCadastro getNovoFuncionario() {
		FuncionarioCadastro funcionario = new FuncionarioCadastro();
		return funcionario;
	}
	
	public void salveAvaliacao(ClientCadastro client) {
		System.out.println("salveAvaliacao pront pra enviar ao banco");
		bd.salveAvaliacao(client);
	}
	
	public void atualizeAvaliacao(ClientCadastro client) {
		System.out.println("atualizeAvaliacao pront pra enviar ao banco");
		bd.atualizeAvaliacao(client);
	}
	
	public void atualizeCadastroCliente(ClientCadastro client) {
		System.out.println("atualize cadastro Cliente pronto pra enviar ao banco");
		bd.atualizeCadastroCliente(client);
	}
	public void atualizeCadastroFuncionario(FuncionarioCadastro funcionariocadastro) {
		System.out.println("atualize cadastro funcionario pronto pra enviar ao banco- Estou no REgister");
		bd.atualizeCadastroFuncionario(funcionariocadastro);
	}
	public void atualizeCadastroSenhaFuncionario(UsuarioLogado usuarioLogado) {
		System.out.println("atualize senha cadastro funcionario pronto pra enviar ao banco- Estou no REgister");
		lbd.atualizeCadastroSenhaFuncionario(usuarioLogado);
	}
	public boolean salveUsuarioSenhaFuncionario(UsuarioLogado usuarioLogado) {
		System.out.println("enviando para salvar a senha e usuario no banco - Estou no REgister");
		return lbd.salveUsuarioSenhaFuncionario(usuarioLogado);
	}
	public UsuarioLogado getLogin() { //nome = loginusuario e senha = senhausuario
		UsuarioLogado login = new UsuarioLogado();
		return login;
	}
	
	//salva um cliente
	public boolean salveCliente(ClientCadastro client) {
		return bd.salveCliente(client);
	}
	
	//remove cliente
	public boolean removeCliente(ClientCadastro clienteremove) {
		return bd.deleteCadastroCliente(clienteremove);
		
	}
	
	public void getAvaliacaoDoCliente(ClientCadastro client, int idChaveAvaliacao){
		System.out.println("getAvaliacaoDoCliente indo ao bd pegar dados");
		bd.getAvaliacaoDoCliente(client, idChaveAvaliacao);		
	}
	
	public boolean salveFuncionario(FuncionarioCadastro client) {
		return bd.salveFuncionarios(client);
	}

		// metodo que pega no campo nome string por string
	public ArrayList<String> getFewClients(String nomeParte) {
		return bd.getFewClients(nomeParte);
	}
	
	//DigitandoEaparecendo-  metodo que pega no campo nome string por string
	public ArrayList<String> getFewFuncionarios(String nomeParte) throws SQLException {
		return bd.getFewFuncionarios(nomeParte);
	}
	

	// pegar cliente pelo texto inicial do Nome
	public ClientCadastro getClientByName(String inicioNome) {
		ClientCadastro cliente = new ClientCadastro();
		return bd.getClientByName(cliente, inicioNome);
	}
	
	// pegar nome do funcionario pelo texto inicial do Nome
	public FuncionarioCadastro getFuncionarioByName(String inicioNome) throws SQLException {
		return bd.getFuncionarioByName(inicioNome);
	}
	
	// pegar dados dos clientes via buscas
	public ArrayList<ArrayList<String>> buscaListaClientes(int chave, String nome, String idade, String sexo,
		String espec, String medico) {
		return bd.buscaListaClientes(chave, nome, idade, sexo, espec, medico);
	}
	// pegar dados dos clientes via buscas
	public ArrayList<ArrayList<String>> buscaListaFuncionarios(String nome) {
		return bd.buscaListaFuncionarios(nome);
	}
	//REGISTER->TB 
	public void setTelaBase(TelaBase base) {
		this.base = base;
	}
	//criar método para contarAnos
	public int getIdadePelaDataNascimento(String datanascimento) {
		System.out.println("Pegar idade em getIdadePelaDataNascimento dado " + datanascimento);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.parse(datanascimento, formatter);

		Period period = Period.between(birthday, today);
		
		System.out.print("Idade de " + period.getYears() + " anos");
		//System.out.print(period.getMonths() + " months, ");

		return period.getYears();
	}
	//criar método para validar datas
	public boolean isDateValid(String data) {
		System.out.println("Pegar idade em isDateValid dado " + data);
		int dia, mes, ano;
		if (data.equals("")){
			return false;
		}
		String[] partes = data.split("/");
		System.out.println("vetor com 3 partes da data");
		
		try {
			dia = Integer.parseInt(partes[0]);
			mes = Integer.parseInt(partes[1]);
			ano = Integer.parseInt(partes[2]);
			
		} catch (Exception e){
			return false;
		}
		
		if (dia <= 0 || dia >31 || mes <=0 || mes >12 || ano <=1890 || ano >=2020){
			if (ano >=2020){
				System.out.println("Chamar paraester@gmail.com - eenviar a mensagem Chegamos em 2020 ver Register");
				JOptionPane.showMessageDialog(null, "Chamar paraester@gmail.com - eenviar a mensagem--> Chegamos em 2020 ver Register <--", "2020", JOptionPane.WARNING_MESSAGE);
			}
			return false;
		}
		
		return true;
	}

}