package DAO;

import java.sql.*;
/**
 * o CRUD vai ser feito aqui 
 * Create, Read, Update, Delete
 */

import java.util.ArrayList;

import Model.ClientCadastro;
import Model.FuncionarioCadastro;
import Model.UsuarioLogado;

import java.util.Date;

import Control.Register;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ClienteCadastroDAO extends SQLException {
	private Statement stmt = null;
	private String sql, sql2;
	private Connection conexao = new FazendoConexao().getConexao();
	private int idAvali;
	private int idChave;
	private Register register;
	private ClientCadastro cliente;

	public ClienteCadastroDAO() {
		// Tentar criar statements (declarações) para esta conexao
		try {
			stmt = conexao.createStatement();
			// conexao.close();
		} catch (Exception e) {
			System.out.println("Erro ao criar Tabela arrumado necessária");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// Verificar se existem as tabelas necessárias nesta base de dados
		// organizar separado depois//para sql lite int IDENTITY(1,1) //
	}

	// inicio arrumando as datas
	// converter data vinda do Mysql para string com / / /
	private String converteDataDoMysqlParaForm(String datanascimento) {
		// formata do padrao yyyy-MM-dd do MySql para o padrao dd/MM/yyyy o
		// formulario
		// SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		format.setLenient(false);
		Date dataDeNascimento = null;
		try {
			dataDeNascimento = format.parse(datanascimento);
		} catch (ParseException e) {
			// e.printStackTrace();
			System.out.println("Nao foi possivel pegar sua data de nascimento [" + datanascimento + "]");
			return "";
		}
		// convertendo para o padrao do Mysql
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String datanascimentoParaMysql = sdf.format(dataDeNascimento);

		// convertendo para o formulario
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datanascimentoParaForm = sdf.format(dataDeNascimento);

		return datanascimentoParaForm;
	}
	// fim arrumando as datas

	// salve cliente paciente
	public boolean salveCliente(ClientCadastro cliente) {
		int novoId = 0;
		Date diaAtual = new Date();
		// Time horaAtual = new Time(diaAtual.getTime()); // parte com apenas
		// horario
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String diaAtualParaMysql = sdf.format(diaAtual);
		try {
			sql = "SELECT max(id) as ID FROM clientes";
			ResultSet consulta = stmt.executeQuery(sql);
			if (consulta.next()) {
				novoId = consulta.getInt("ID") + 1;
			} else {
				novoId = 1;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		sql = "INSERT INTO clientes (id, nome, idade, endereco, datanascimento, sexo, planosaude, telefone, responsavel, especialidade, datainiciotratamento, datadaavaliacao, medicoresponsavel, qtdesessoesrealizadas, objetivos, condutas, diagnostico, atualizadoem) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, novoId);
			stmt.setString(2, cliente.getNome());
			stmt.setInt(3, cliente.getIdade());
			stmt.setString(4, cliente.getEndereco());
			// stmt.setString(5, cliente.getDatanascimento());
			stmt.setString(5, cliente.getDatanascimentoMysql());
			stmt.setString(6, cliente.getSexo());
			stmt.setString(7, cliente.getPlanosaude());
			stmt.setString(8, cliente.getTelefone());
			stmt.setString(9, cliente.getResponsavel());
			stmt.setString(10, cliente.getEspecialidade());

			// stmt.setString(11, cliente.getDatainiciotratamento());
			stmt.setString(11, cliente.getDatainiciotratamentoMysql());

			// stmt.setString(12, cliente.getDatadaavaliacao());
			stmt.setString(12, cliente.getDatadaavaliacaoMysql());

			stmt.setString(13, cliente.getMedicoresponsavel());
			stmt.setInt(14, cliente.getQtdesessoesrealizadas());
			
			stmt.setString(15, cliente.getObjetivosCampo());
			stmt.setString(16, cliente.getCondutasCampo());
			stmt.setString(17, cliente.getDiagnosticoCampo());
			
			stmt.setString(18, diaAtualParaMysql);
			stmt.executeUpdate();
			System.out.println("Cliente: " + cliente.getNome() + " Cadastrado na base de dados, com identificador: "
					+ cliente.getId());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		cliente.setId(novoId);
		setIdChave(novoId);

		// salvar avaliacao inicial deste cliente
		salveAvaliacao(cliente);
		return true;
	}

	public void salveAvaliacao(ClientCadastro cliente) {
		int novoId = 0;
		try {
			sql = "SELECT max(id) as ID FROM avaliacao";
			ResultSet consulta = stmt.executeQuery(sql);
			if (consulta.next()) {
				novoId = consulta.getInt("ID") + 1;
			} else {
				novoId = 1;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		sql = "INSERT INTO avaliacao (id, idcliente, medicoresponsavel, medicamento, observacaodehoje, dataavaliacao) VALUES (?,?,?,?,?,?)";
		Date diaAtualAvalia = new Date();
		// Time horaAtual = new Time(diaAtual.getTime()); // parte com apenas
		// horario
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String diaAtualAvaliaParaMysql = sdf.format(diaAtualAvalia);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, novoId);
			stmt.setInt(2, cliente.getId());
			stmt.setString(3, cliente.getMedicoresponsavel());
			stmt.setString(4, cliente.getMedicamento());
			stmt.setString(5, cliente.getObservacaodehoje());
			stmt.setString(6, diaAtualAvaliaParaMysql);
			stmt.executeUpdate();
			System.out.println("Avaliacao do(a) " + cliente.getNome()
					+ " foi salvo na base de dados, com identificador: " + novoId);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		setIdAvali(novoId);
	}

	// DELETE
	public boolean deleteCadastroCliente(ClientCadastro cliente) {
		int idCliente = cliente.getId();
		sql = "DELETE FROM clientes WHERE id = ?";
		sql2 = "DELETE FROM avaliacao WHERE idcliente = ?";
		System.out.println("sql update" + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, cliente.getId());
			stmt.executeUpdate();
			System.out.println("DELETE tabela clientes do(a) " + cliente.getNome()
					+ " foi salvo na base de dados, com identificador: " + idCliente);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql2);
			stmt.setInt(1, cliente.getId());
			stmt.executeUpdate();
			System.out.println("DELETE tabela avaliacao do(a) " + cliente.getNome()
					+ " foi salvo na base de dados, com identificador: " + idCliente);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return true;
	}

	public void deleteCadastroFuncionario(FuncionarioCadastro funcionariocadastro) {
		int idFuncionario = funcionariocadastro.getId();
		sql = "DELETE FROM funcionariocadastros WHERE id = ?";
		System.out.println("sql update" + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, funcionariocadastro.getId());
			stmt.executeUpdate();

			System.out.println("Update da tabela funcionarios do(a) " + funcionariocadastro.getNome()
					+ " foi salvo na base de dados, com identificador: " + idFuncionario);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	

	
	
	// update para funcionários
	public void atualizeCadastroFuncionario(FuncionarioCadastro funcionariocadastro) {
		int idFuncionario = funcionariocadastro.getId();
		sql = "UPDATE funcionarios SET nome = ?, idade = ?, endereco = ?, datanascimento = ?, sexo = ?, planosaude = ?, telefone = ? WHERE id = ?";
		System.out.println("sql update" + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionariocadastro.getNome());
			stmt.setInt(2, funcionariocadastro.getIdade());
			stmt.setString(3, funcionariocadastro.getEndereco());
			stmt.setString(4, funcionariocadastro.getDatanascimentoMysql());
			// stmt.setString(4, funcionariocadastro.getDatanascimento());
			stmt.setString(5, funcionariocadastro.getSexo());
			stmt.setString(6, funcionariocadastro.getPlanosaude());
			stmt.setString(7, funcionariocadastro.getTelefone());
			stmt.setInt(8, idFuncionario);
			stmt.executeUpdate();
			System.out.println("Update da tabela funcionarios do(a) " + funcionariocadastro.getNome()
					+ " foi salvo na base de dados, com identificador: " + idFuncionario);
		} catch (Exception e) {
			System.err.println("Erro ao atualizar BD func: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	// update para cliente
	public void atualizeCadastroCliente(ClientCadastro cliente) {
		System.out.println("Atualizar cliente " + cliente.getNome());
		int idClientes = cliente.getId(); // medicoresponsavel = + "'" +
											// cliente.getMedicoresponsavel() +
											// "',
		sql = "UPDATE clientes SET nome = ?, idade = ?, endereco = ?, datanascimento = ?, sexo = ?, planosaude = ?, telefone = ?, responsavel = ?, especialidade = ?, datainiciotratamento = ?, datadaavaliacao = ?, medicoresponsavel = ?, qtdesessoesrealizadas = ?, objetivos = ?, condutas = ?, diagnostico = ? WHERE id = ?";
		System.out.println("sql update" + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getIdade());
			stmt.setString(3, cliente.getEndereco());
			stmt.setString(4, cliente.getDatanascimentoMysql());
			// stmt.setString(4, cliente.getDatanascimento());
			stmt.setString(5, cliente.getSexo());
			stmt.setString(6, cliente.getPlanosaude());
			stmt.setString(7, cliente.getTelefone());
			stmt.setString(8, cliente.getResponsavel());
			stmt.setString(9, cliente.getEspecialidade());
			// stmt.setString(10, cliente.getDatainiciotratamento());
			stmt.setString(10, cliente.getDatainiciotratamentoMysql());

			// stmt.setString(11, cliente.getDatadaavaliacao());
			stmt.setString(11, cliente.getDatadaavaliacaoMysql());

			stmt.setString(12, cliente.getMedicoresponsavel());
			stmt.setInt(13, cliente.getQtdesessoesrealizadas());
			
			stmt.setString(14, cliente.getObjetivosCampo());
			stmt.setString(15, cliente.getCondutasCampo());
			stmt.setString(16, cliente.getDiagnosticoCampo());
			
			
			stmt.setInt(17, idClientes);
			stmt.executeUpdate();
			System.out.println("Update da tabela cliente do(a) " + cliente.getNome()
					+ " foi salvo na base de dados, com identificador: " + idClientes);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void atualizeAvaliacao(ClientCadastro cliente) {
		int idAvali = cliente.getIdAvali();
		sql = "UPDATE avaliacao SET medicoresponsavel = ?, medicamento = ?, observacaodehoje = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getMedicoresponsavel());
			stmt.setString(2, cliente.getMedicamento());
			stmt.setString(3, cliente.getObservacaodehoje());
			stmt.setInt(4, idAvali);
			stmt.executeUpdate();
			System.out.println("Update da  Avaliacao do(a) " + cliente.getNome()
					+ " foi salvo na base de dados, com identificador: " + idAvali);
		} catch (Exception e) {
			System.err.println(";;;;" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public ClientCadastro getClient(ClientCadastro cliente, int id) {
		System.out.println("fafds -- " + cliente.getNome() + " e id: " + id);
		this.cliente = cliente;
		// ClientCadastro cliente;
		// cliente = register.getNovoClient();
		System.out.println("Esterfafds" + cliente);
		// ClientCadastro cliente = new ClientCadastro();
		try {
			ResultSet consulta = stmt.executeQuery("SELECT * FROM clientes WHERE id = " + id + ";");
			if (consulta.next()) {
				// int id = consulta.getInt("id");
				System.out.println("pegando id");
				cliente.setId(consulta.getInt("id"));
				System.out.println("pegando nome");
				cliente.setNome(consulta.getString("nome"));
				System.out.println("pegando idade");
				cliente.setIdade(consulta.getInt("idade"));
				System.out.println("pegando endere");
				cliente.setEndereco(consulta.getString("endereco"));
				System.out.println("pegando data nascim");
				cliente.setDatanascimento(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
				// cliente.setDatanascimento(consulta.getString("datanascimento"));
				System.out.println("pegando sexo");
				cliente.setSexo(consulta.getString("sexo"));
				System.out.println("pegando plano");
				cliente.setPlanosaude(consulta.getString("planosaude"));
				System.out.println("pegando fone");
				cliente.setTelefone(consulta.getString("telefone"));
				System.out.println("pegando respons");
				cliente.setResponsavel(consulta.getString("responsavel"));
				System.out.println("pegando especialidade");
				cliente.setEspecialidade(consulta.getString("especialidade"));
				System.out.println("pegando inicio tra");
				cliente.setDatainiciotratamento(
						converteDataDoMysqlParaForm(consulta.getString("datainiciotratamento")));
				System.out.println("pegando data avali");
				cliente.setDatadaavaliacao(converteDataDoMysqlParaForm(consulta.getString("datadaavaliacao")));
				System.out.println("pegando quantidade");
				cliente.setQtdesessoesrealizadas(consulta.getInt("qtdesessoesrealizadas"));
				
				System.out.println("pegando objetivos");
				cliente.setObjetivosCampo(consulta.getString("objetivos"));
				System.out.println("pegando condutas");
				cliente.setCondutasCampo(consulta.getString("condutas"));
				System.out.println("pegando diagnostico");
				cliente.setDiagnosticoCampo(consulta.getString("diagnostico"));
			}
		} catch (Exception e) {
			System.out.println("Erro em getclient ao tentar pegar cliente de id " + id);
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return cliente;
	}

	public ClientCadastro getClientByName(ClientCadastro cliente, String inicioNome) {
		// cliente = register.getNovoClient();
		// ClientCadastro cliente = new ClientCadastro();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM clientes WHERE nome like ? ;");
			stmt.setString(1, "%" + inicioNome + "%");
			ResultSet consulta = stmt.executeQuery();
			if (consulta.next()) {
				// int id = consulta.getInt("id");
				cliente.setId(consulta.getInt("id"));
				cliente.setNome(consulta.getString("nome"));
				cliente.setIdade(consulta.getInt("idade"));
				cliente.setEndereco(consulta.getString("endereco"));

				cliente.setDatanascimento(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
				cliente.setSexo(consulta.getString("sexo"));
				cliente.setPlanosaude(consulta.getString("planosaude"));
				cliente.setTelefone(consulta.getString("telefone"));
				cliente.setResponsavel(consulta.getString("responsavel"));
				cliente.setEspecialidade(consulta.getString("especialidade"));

				cliente.setDatainiciotratamento(
						converteDataDoMysqlParaForm(consulta.getString("datainiciotratamento")));
				cliente.setDatadaavaliacao(converteDataDoMysqlParaForm(consulta.getString("datadaavaliacao")));

				cliente.setQtdesessoesrealizadas(consulta.getInt("qtdesessoesrealizadas"));
				
				cliente.setObjetivosCampo(consulta.getString("objetivos"));
				cliente.setCondutasCampo(consulta.getString("condutas"));
				cliente.setDiagnosticoCampo(consulta.getString("diagnostico"));
				

				
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return cliente;
	}

	public void getAvaliacaoDoCliente(ClientCadastro cliente, int idChaveAvaliacao) {
		System.out.println("getAvaliacaoDoCliente em idAvali " + idChaveAvaliacao);
		try {
			// Livrando-se um pouco de um SqlInjection
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM avaliacao WHERE id = ? ;");
			stmt.setInt(1, idChaveAvaliacao);
			ResultSet consulta = stmt.executeQuery();

			if (consulta.next()) {
				cliente.setIdAvali(idChaveAvaliacao);
				cliente.setMedicoresponsavel(consulta.getString("medicoresponsavel"));
				cliente.setMedicamento(consulta.getString("medicamento"));
				cliente.setObservacaodehoje(consulta.getString("observacaodehoje"));
				cliente.setDataAvaliacao(consulta.getString("dataavaliacao"));//data automatica
				
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public void encerrar() throws SQLException {
		if (conexao.isClosed()) {
			conexao.close();
		}
	}

	public ArrayList<String> getFewClients(String nomeParte) {
		ArrayList<String> algunsClientes = new ArrayList<String>();
		String cliente;
		int limitTexto = 38; // limite de caracteres do nome par ser exibido em
								// menu de opcoes

		try {
			// Inseguro
			// sql = "SELECT nome FROM clientes WHERE nome like '" + nomeParte +
			// "%' ORDER BY nome LIMIT 10;";
			// ResultSet consulta = stmt.executeQuery( sql );
			// Livrando-se um pouco de um SqlInjection
			sql = "SELECT nome FROM clientes WHERE nome LIKE ? ORDER BY nome LIMIT 10;";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + nomeParte + "%");
			ResultSet consulta = stmt.executeQuery();

			while (consulta.next()) {
				cliente = consulta.getString("nome");
				if (cliente.length() > limitTexto) {
					cliente = cliente.substring(0, limitTexto);
				}
				algunsClientes.add(cliente);
			}

		} catch (Exception e) {
			System.err.println("getFew" + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return algunsClientes;
	}

	public ArrayList<ArrayList<String>> buscaListaClientes(int chave, String nome, String idade, String sexo,
			String espec, String medico) {
		ArrayList<ArrayList<String>> matrizClientes = new ArrayList<ArrayList<String>>();
		PreparedStatement stmt = null;
		ArrayList<String> tmpLinha;
		String sql;
		if (chave == 1) {
			sql = "SELECT *, avaliacao.id, avaliacao.medicoresponsavel as mmedicoresponsavel FROM clientes  LEFT JOIN avaliacao ON clientes.id = avaliacao.idcliente WHERE ";
		} else {
			sql = "SELECT *, t2.id, t2.medicoresponsavel as mmedicoresponsavel FROM clientes LEFT JOIN (SELECT idcliente, MAX(id) AS maxid FROM avaliacao GROUP BY idcliente) AS t1 ON clientes.id = t1.idcliente LEFT JOIN avaliacao AS t2 ON t2.id = t1.maxid WHERE ";
			// este sql irá devolver a lista de clientes com seu médico da
			// última avaliação
		}

		int inome = 0, iidade = 0, isexo = 0, iespec = 0, imedico = 0;
		int qtCampos = 0;

		if (!(nome.equals(""))) {
			if (qtCampos == 0) {
				sql += "nome LIKE ? ";
			} else {
				sql += "AND nome LIKE ? ";
			}
			qtCampos++;
			inome = qtCampos;
		}
		if (!(idade.equals(""))) {
			if (qtCampos == 0) {
				sql += "idade = ? ";
			} else {
				sql += "AND idade = ? ";
			}
			qtCampos++;
			iidade = qtCampos;
		}
		if (!(sexo.equals(""))) {
			if ("Masculino".toLowerCase().contains(sexo.toLowerCase())) {
				sexo = "Masculino";
			} else {
				sexo = "Feminino";
			}
			if (qtCampos == 0) {
				sql += "sexo = ? ";
			} else {
				sql += "AND sexo = ? ";
			}
			qtCampos++;
			isexo = qtCampos;
		}
		if (!(espec.equals(""))) {
			if (qtCampos == 0) {
				sql += "especialidade LIKE ? ";
			} else {
				sql += "AND especialidade LIKE ? ";
			}
			qtCampos++;
			iespec = qtCampos;
		}
		if (!(medico.equals(""))) {
			if (qtCampos == 0) {
				sql += "mmedicoresponsavel LIKE ? ";
			} else {
				sql += "AND mmedicoresponsavel LIKE ? ";
			}
			qtCampos++;
			imedico = qtCampos;
		}

		if (inome == 0 && iidade == 0 && isexo == 0 && iespec == 0 && imedico == 0) {
			// buscar 10 primeiro clientes em orde alfabetica
			if (chave == 1) {
				sql = "SELECT *, avaliacao.id, avaliacao.medicoresponsavel as mmedicoresponsavel FROM clientes  LEFT JOIN avaliacao ON clientes.id = avaliacao.idcliente ORDER BY nome, avaliacao.id ASC ; ";
			} else {
				// sql = "SELECT * FROM clientes ORDER BY nome ; ";
				sql = "SELECT *, t2.id, t2.medicoresponsavel as mmedicoresponsavel FROM clientes LEFT JOIN (SELECT idcliente, MAX(id) AS maxid FROM avaliacao GROUP BY idcliente) AS t1 ON clientes.id = t1.idcliente LEFT JOIN avaliacao AS t2 ON t2.id = t1.maxid ORDER BY clientes.nome ASC;";
				// este sql irá devolver a lista de clientes com seu médico da
				// última avaliação
			}

			// sql = "SELECT * FROM clientes ORDER BY nome ;";
		} else {
			// completar sql para buscar pelos campos nao nulos preenchidos pelo
			// usuario
			sql += " ORDER BY nome ASC  ;";
		}
		try {
			stmt = conexao.prepareStatement(sql);
		} catch (Exception e) {
			System.err.println("stmt:: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		try {
			if (inome > 0) {
				stmt.setString(inome, "%" + nome + "%");
			}
			if (iidade > 0) {
				stmt.setString(iidade, idade);
			}
			if (isexo > 0) {
				stmt.setString(isexo, sexo);
			}
			if (iespec > 0) {
				stmt.setString(iespec, "%" + espec + "%");
			}
			if (imedico > 0) {
				stmt.setString(imedico, "%" + medico + "%");
			}
		} catch (Exception e) {
			System.err.println("setString:: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("sql foi " + sql);
		try {
			ResultSet consulta = stmt.executeQuery();
			while (consulta.next()) {
				// na ordem
				// campo id vai primeiro pra uso futuro
				/*
				 * colunas.add("Nome"); colunas.add("Idade");
				 * colunas.add("Telefone"); colunas.add("Nascimento");
				 * colunas.add("Especialidade"); colunas.add("Médico");
				 */
				tmpLinha = new ArrayList<String>();
				tmpLinha.add(consulta.getString("id"));

				if (chave == 0) {
					// clientes
					System.out.println("cahve igual zero");
					tmpLinha.add(consulta.getString("nome"));
					tmpLinha.add(String.valueOf(consulta.getString("idade")));
					tmpLinha.add(consulta.getString("telefone"));
					tmpLinha.add(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
					tmpLinha.add(consulta.getString("especialidade"));
					tmpLinha.add(consulta.getString("mmedicoresponsavel"));
				} else {
					// dados de clientes e avaliações
					System.out.println("cahve diferente de zero" + consulta.getString("id"));
					tmpLinha.add(converteDataDoMysqlParaForm(consulta.getString("dataavaliacao")));
					System.out.println(" dataavaliacao");
					tmpLinha.add(consulta.getString("nome"));
					System.out.println(" nome");
					tmpLinha.add(String.valueOf(consulta.getString("idade")));
					System.out.println(" idade");
					tmpLinha.add(consulta.getString("mmedicoresponsavel"));
					System.out.println(" mmedicoresponsavel");
					tmpLinha.add(consulta.getString("medicamento"));
					System.out.println(" medicamento");
					tmpLinha.add(consulta.getString("observacaodehoje"));

				}
				// System.out.println(" achado nome " +
				// consulta.getString("nome"));

				matrizClientes.add(tmpLinha);

				// idcliente , medicoresponsavel, medicamento , observacaodehoje
				// id, nome, idade, endereco, datanascimento, sexo, planosaude,
				// telefone, responsavel, especialidade, datainiciotratamento,
				// datadaavaliacao, medicoresponsavel, qtdesessoesrealizadas
			}
		} catch (Exception e) {
			System.err.println("tmpLinha:::: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return matrizClientes;
	}
	// salveFuncionario
	public boolean salveFuncionarios(FuncionarioCadastro funcionario) {
		int novoId = 0;
		try {
			sql = "SELECT max(id) as ID FROM funcionarios";
			ResultSet consulta = stmt.executeQuery(sql);
			if (consulta.next()) {
				novoId = consulta.getInt("ID") + 1;
			} else {
				novoId = 1;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		funcionario.setId(novoId);

		sql = "INSERT INTO funcionarios (id, nome, idade, endereco, datanascimento, sexo, planosaude, telefone) VALUES (?,?,?,?,?,?,?,?)";
		System.out.println("Tentando rodar SQL: " + sql);
		try {

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, novoId);
			stmt.setString(2, funcionario.getNome());
			stmt.setInt(3, funcionario.getIdade());
			stmt.setString(4, funcionario.getEndereco());

			// stmt.setString(5, funcionario.getDatanascimento());
			stmt.setString(5, funcionario.getDatanascimentoMysql());
			stmt.setString(6, funcionario.getSexo());
			stmt.setString(7, funcionario.getPlanosaude());
			stmt.setString(8, funcionario.getTelefone());
			stmt.executeUpdate();
			System.out.println("Funcionário: " + funcionario.getNome()
					+ " Cadastrado na base de dados, com identificador: " + funcionario.getId());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// salvou funcionario inicial deste cliente do tipo funcionario
		return true;
	}
	// fim salveFuncionario

	public FuncionarioCadastro getFuncionarioById(int id) {
		FuncionarioCadastro funcionario = new FuncionarioCadastro();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM funcionarios WHERE id = ? ;");
			stmt.setInt(1, id);
			ResultSet consulta = stmt.executeQuery();
			if (consulta.next()) {
				funcionario.setId(consulta.getInt("id"));
				funcionario.setNome(consulta.getString("nome"));
				funcionario.setIdade(consulta.getInt("idade"));
				funcionario.setEndereco(consulta.getString("endereco"));
				funcionario.setDatanascimento(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
				funcionario.setSexo(consulta.getString("sexo"));
				funcionario.setPlanosaude(consulta.getString("planosaude"));
				funcionario.setTelefone(consulta.getString("telefone"));
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return funcionario;
	}

	// buscaListaFuncionarios
	public ArrayList<ArrayList<String>> buscaListaFuncionarios(String nome) {
		ArrayList<ArrayList<String>> matrizFuncionario = new ArrayList<ArrayList<String>>();
		PreparedStatement stmt = null;
		ArrayList<String> tmpLinha;
		String sql;
		sql = "SELECT * FROM funcionarios WHERE ";

		int inome = 0;
		int qtCampos = 0;

		if (!(nome.equals(""))) {
			if (qtCampos == 0) {
				sql += "nome LIKE ? ";
			} else {
				sql += "AND nome LIKE ? ";
			}
			qtCampos++;
			inome = qtCampos;
		}
		if (inome == 0) {
			sql = "SELECT * FROM funcionarios ORDER BY nome ;";
			// sql = "SELECT * FROM clientes ORDER BY nome ;";
		} else {
			// completar sql para buscar pelos campos nao nulos preenchidos pelo
			// usuario
			sql += " ORDER BY nome ;";
		}
		try {
			// System.out.println(sql);
			stmt = conexao.prepareStatement(sql);
		} catch (Exception e) {
			System.err.println("stmt:: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		try { // nome, idade, endereco, datanascimento, sexo, planosaude,
				// telefone
			if (inome > 0) {
				stmt.setString(inome, "%" + nome + "%");
			}
		} catch (Exception e) {
			System.err.println("setString:: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("sql foi " + sql);
		try {
			ResultSet consulta = stmt.executeQuery();
			while (consulta.next()) {
				// na ordem
				// campo id vai primeiro pra uso futuro
				/*
				 * colunas.add("Nome"); colunas.add("Idade");
				 * colunas.add("Telefone"); colunas.add("Nascimento");
				 * colunas.add("Especialidade"); colunas.add("Médico");
				 */
				// nome, idade, endereco, datanascimento, sexo, planosaude,
				// telefone
				tmpLinha = new ArrayList<String>();
				tmpLinha.add(consulta.getString("id"));
				tmpLinha.add(consulta.getString("nome"));
				tmpLinha.add(String.valueOf(consulta.getString("idade")));
				tmpLinha.add(consulta.getString("endereco"));
				tmpLinha.add(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
				tmpLinha.add(consulta.getString("sexo"));
				tmpLinha.add(consulta.getString("planosaude"));
				tmpLinha.add(consulta.getString("telefone"));
				matrizFuncionario.add(tmpLinha);

				// idcliente , medicoresponsavel, medicamento , observacaodehoje
				// id, nome, idade, endereco, datanascimento, sexo, planosaude,
				// telefone, responsavel, especialidade, datainiciotratamento,
				// datadaavaliacao, medicoresponsavel, qtdesessoesrealizadas
			}
		} catch (Exception e) {
			System.err.println("tmpLinha:: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return matrizFuncionario;
	}

	// getFuncionarioByName - DigitandoEaparecendo
	public FuncionarioCadastro getFuncionarioByName(String inicioNome) throws SQLException {
		FuncionarioCadastro funcionario = new FuncionarioCadastro();

		try {
			// Inseguro
			// ResultSet consulta = stmt.executeQuery( "SELECT * FROM clientes
			// WHERE nome like '" + inicioNome + "%';");
			// Livrando-se um pouco de um SqlInjection
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM funcionarios WHERE nome like ? ;");
			stmt.setString(1, "%" + inicioNome + "%");
			ResultSet consulta = stmt.executeQuery();
			if (consulta.next()) {
				// int id = consulta.getInt("id");
				funcionario.setId(consulta.getInt("id"));
				funcionario.setNome(consulta.getString("nome"));
				funcionario.setIdade(consulta.getInt("idade"));
				funcionario.setEndereco(consulta.getString("endereco"));
				funcionario.setDatanascimento(converteDataDoMysqlParaForm(consulta.getString("datanascimento")));
				funcionario.setSexo(consulta.getString("sexo"));
				funcionario.setPlanosaude(consulta.getString("planosaude"));
				funcionario.setTelefone(consulta.getString("telefone"));
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			stmt.close();
			System.exit(0);
		}

		return funcionario;
	}

	// DigitandoEaparecendo - getFewFuncionarios
	public ArrayList<String> getFewFuncionarios(String nomeParte) throws SQLException {
		ArrayList<String> algunsFuncionarios = new ArrayList<String>();
		String funcionario;
		int limitTexto = 38; // limite de caracteres do nome par ser exibido em
								// menu de opcoes
		try {
			sql = "SELECT nome FROM funcionarios WHERE nome LIKE ? ORDER BY nome LIMIT 10;";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeParte + "%");
			ResultSet consulta = stmt.executeQuery();

			while (consulta.next()) {
				funcionario = consulta.getString("nome");
				if (funcionario.length() > limitTexto) {
					funcionario = funcionario.substring(0, limitTexto);
				}
				algunsFuncionarios.add(funcionario);
			}

		} catch (Exception e) {
			System.err.println("getFew" + e.getClass().getName() + ": " + e.getMessage());
			stmt.close();
			System.exit(0);
		}
		return algunsFuncionarios;
	}

	public int getIdAvali() {
		return idAvali;
	}

	public void setIdAvali(int idAvali) {
		this.idAvali = idAvali;
	}

	public int getIdChave() {
		return idChave;
	}

	public void setIdChave(int idChave) {
		this.idAvali = idChave;
	}

}
