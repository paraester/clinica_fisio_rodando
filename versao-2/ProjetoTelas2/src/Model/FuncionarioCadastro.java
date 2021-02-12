package Model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FuncionarioCadastro {
	private int id;
	private String nome = "";
	private int idade = 0;
	private String endereco = "";
	private String datanascimento = "";
	private String datanascimentomysql = null;
	private String sexo = "";
	private String planosaude = "";
	private String telefone = "";
	private String especialidade;
	private String dataadmissao;
	@SuppressWarnings("unused")
	private String crefito;
	
	public String concatenados() {
		return "Nome [" + nome + "] idade[" + Integer.toString(idade) + "] sexo[" + sexo + "]";

	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}
	/**
	 * @param idade the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}
	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return the datanascimento
	 */
	public String getDatanascimento() {
		return datanascimento;
	}
	/**
	 * @param datanascimento the datanascimento to set
	 */

	//Criado  setDataNascimentoMysql e getDataNascimentoMysql em ClienteCadastro
	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
		this.setDataNascimentoMysql(datanascimento);
	}
	
	public void setDataNascimentoMysql(String datanascimento) {
		System.out.println("Chamado setDataNascimentoMysql com datanascimento [" + datanascimento + "]");
		if (datanascimento.equals("  /  /    ")) {
			this.datanascimentomysql = null;
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Date dataDeNascimento = null;
		try {
			dataDeNascimento = format.parse(datanascimento);
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Nao foi possivel pegar sua data de nascimento func para mysql [" + datanascimento + "]");
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
	
	
	
	
	
	
	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the planosaude
	 */
	public String getPlanosaude() {
		return planosaude;
	}
	/**
	 * @param planosaude the planosaude to set
	 */
	public void setPlanosaude(String planosaude) {
		this.planosaude = planosaude;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setDataAdmissao(String dataadmissao) {
		this.dataadmissao = dataadmissao;
		this.setDataAdmissaoMysql(dataadmissao);		
	}
	
	public void setDataAdmissaoMysql(String dataadmissao) {
		System.out.println("setData inicio tratamento mysql " + dataadmissao);
		if (dataadmissao.equals("  /  /    ")) {
			this.dataadmissao = null;
			return;
		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Date DtAdmissao = null;
		try {
			DtAdmissao = format.parse(dataadmissao);
		} catch (ParseException e) {
			//e.printStackTrace();
			System.out.println("Nao foi possivel pegar a datainiciotratamento " + dataadmissao);
			try {
				DtAdmissao = format.parse("01/01/1970");
			} catch (ParseException f) {
				//f.printStackTrace();
				System.out.println("NAO Nao foi possivel pegar sua datainiciotratamento " + dataadmissao);		
			}		
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataAdmissaoParaMysql = sdf.format(DtAdmissao);
		System.out.println("Calculada a idade de " + dataAdmissaoParaMysql);
		this.dataadmissao = dataAdmissaoParaMysql;
	}
	public String getDataAdmissao() {
		return dataadmissao;
	}

	public void setCrefito(String crefito) {
		this.crefito = crefito;
	}

}
