package Model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClientCadastro {
	private int id = 0;
	private int idAvali = 0;
	private String nome = "";
	private int idade = 0;
	private String endereco = "";
	private String sexo = "";
	private String planosaude = "";
	private String telefone = "";
	private String responsavel = "";
	private String especialidade = "";
	private String datainiciotratamento = "";
	private String datadaavaliacao = "";
	private String medicoresponsavel = "";
	private int qtdesessoesrealizadas = 0;
	private String observacaodehoje = "";
	private String medicamento = "";

	private String datanascimento = "";
	private String datanascimentomysql = null;
	private String datainiciotratamentomysql = null;
	private String datadaavaliacaomysql = null;
	private String objetivos;
	private String condutas;
	private String diagnostico;
	@SuppressWarnings("unused")
	private String dataavaliacaoAutomatica;

	public ClientCadastro() {
		System.out.println("construindo novo cliente vazio");
	}
	public String concatenados() {
		return "Nome [" + nome + "] idade[" + Integer.toString(idade) + "] sexo[" + sexo + "]";

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdAvali() {
		return idAvali;
	}

	public void setIdAvali(int idAvali) {
		this.idAvali = idAvali;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	//Criado  setDataNascimentoMysql e getDataNascimentoMysql em ClienteCadastro
	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
		this.setDataNascimentoMysql(datanascimento);
	}
	
	public void setDataNascimentoMysql(String datanascimento) {
		if (datanascimento.equals("  /  /    ")) {
			this.datanascimentomysql = null;
			return;
		}
		// formata do padrao dd/MM/yyyy o formulario para o padrao yyyy-MM-dd para MySql  
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Date dataDeNascimento = null;
		try {
			dataDeNascimento = format.parse(datanascimento);
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Nao foi possivel pegar sua data de nascimento para mysql [" + datanascimento +"]");
			System.exit(-1);		
		}
		// convertendo para o padrao do Mysql
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datanascimentoParaMysql = sdf.format(dataDeNascimento);
		
		this.datanascimentomysql = datanascimentoParaMysql;
	}

	public String getDatanascimentoMysql() {
		return this.datanascimentomysql;
	}

	

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPlanosaude() {
		return planosaude;
	}

	public void setPlanosaude(String planosaude) {
		this.planosaude = planosaude;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
//inicio datainiciotratamento
	public String getDatainiciotratamento() {
		return datainiciotratamento;
	}

	public void setDatainiciotratamento(String datainiciotratamento) {
		this.datainiciotratamento = datainiciotratamento;
		this.setDatainiciotratamentoMysql(datainiciotratamento);
	}
	public void setDatainiciotratamentoMysql(String datainiciotratamento) {
		System.out.println("setData inicio tratamento mysql " + datainiciotratamento);
		if (datainiciotratamento.equals("  /  /    ")) {
			this.datainiciotratamentomysql = null;
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Date dataInicioDoTratamento = null;
		try {
			dataInicioDoTratamento = format.parse(datainiciotratamento);
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Nao foi possivel pegar a datainiciotratamento " + datainiciotratamento);
			try {
				dataInicioDoTratamento = format.parse("01/01/1970");
			} catch (ParseException f) {
				//f.printStackTrace();
				System.out.println("NAO Nao foi possivel pegar sua datainiciotratamento " + datainiciotratamento);		
			}		
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datainiciotratamentoParaMysql = sdf.format(dataInicioDoTratamento);
		System.out.println("Calculada a idade de " + datainiciotratamentoParaMysql);
		this.datainiciotratamentomysql = datainiciotratamentoParaMysql;
	}
	public String getDatainiciotratamentoMysql() {
		return this.datainiciotratamentomysql;
	}
//fim datainiciotratamento

	public String getDatadaavaliacao() {
		return datadaavaliacao;
	}

//inicio

	public void setDatadaavaliacao(String datadaavaliacao) {
		this.datadaavaliacao = datadaavaliacao;
		this.setDatadaavaliacaoMysql(datadaavaliacao);
	}
	public void setDatadaavaliacaoMysql(String datadaavaliacao) {
		if (datadaavaliacao.equals("  /  /    ")) {
			this.datadaavaliacao = null;
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Date dataDaAvaliacao = null;
		try {
			dataDaAvaliacao = format.parse(datadaavaliacao);
		} catch (ParseException e) {
			System.out.println("Nao foi possivel pegar a datadaavaliacao " + datadaavaliacao);
			try {
				dataDaAvaliacao = format.parse("01/01/1970");
			} catch (ParseException f) {
				System.out.println("NAO Nao foi possivel pegar sua datadaavaliacao " + datadaavaliacao);		
			}		
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datadaavaliacaoParaMysql = sdf.format(dataDaAvaliacao);
		this.datadaavaliacaomysql = datadaavaliacaoParaMysql;
	}
	public String getDatadaavaliacaoMysql() {
		return this.datadaavaliacaomysql;
	}	
//fim datadaavaliacao
	public String getMedicoresponsavel() {
		return medicoresponsavel;
	}

	public void setMedicoresponsavel(String medicoresponsavel) {
		this.medicoresponsavel = medicoresponsavel;
	}

	public int getQtdesessoesrealizadas() {
		return qtdesessoesrealizadas;
	}

	public void setQtdesessoesrealizadas(int qtdesessoesrealizadas) {
		this.qtdesessoesrealizadas = qtdesessoesrealizadas;
	}

	public String getObservacaodehoje() {
		return observacaodehoje;
	}

	public void setDataAvaliacao(String dataavaliacaoAutomatica) {
		this.dataavaliacaoAutomatica = dataavaliacaoAutomatica;
		
	}
	
	public void setObservacaodehoje(String observacaodehoje) {
		this.observacaodehoje = observacaodehoje;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}
	public String getObjetivosCampo() {
		return objetivos;
	}
	public void setObjetivosCampo(String objetivos) {
		this.objetivos = objetivos;
	}
	public String getCondutasCampo() {
		return condutas;
	}

	public void setCondutasCampo(String condutas) {
		this.condutas=condutas;
	}
	public String getDiagnosticoCampo() {
		return diagnostico;
	}
	public void setDiagnosticoCampo(String diagnostico) {
		this.diagnostico=diagnostico;
	}

}
