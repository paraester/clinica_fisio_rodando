package DAO;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;

import com.mysql.fabric.xmlrpc.base.Data;

import Model.FuncionarioCadastro;
import Model.UsuarioLogado;

public class LoginUsuarioDAO  extends SQLException{
	private Statement stmt = null;
	private String sql, sql2;
	private Connection conexao = new FazendoConexao().getConexao();
	public String nomeConsulta;
	private String senhaConsulta;
	private String loginusuario;
	private String senhausuario;
	private String data;
	
	public LoginUsuarioDAO() {
		try {
			stmt = conexao.createStatement();		
			
		} catch (Exception e) {
			System.out.println("Erro");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}	
	}
	public boolean validar(String nome, String senha) {
		boolean result = false;
		System.out.println("entrando no bloco try do Validar");
		try {
			System.out.println("antes do sql");
			sql = "SELECT * FROM usuarios WHERE loginusuario like '" + nome + "';";
			System.out.println("depois do sql" + sql);
			PreparedStatement stmt = conexao.prepareStatement(sql);
			System.out.println(nome + "<--nomequeveio antes consulta ");
			
			ResultSet consulta = stmt.executeQuery();
			System.out.println(nome + "<--nomequeveio depois consulta ");
			while (consulta.next()) {
				nomeConsulta = consulta.getString("loginusuario");
				senhaConsulta = consulta.getString("senhausuario");
				if (nome.equals(nomeConsulta) && senha.equals(senhaConsulta)){
					result = true;
				}
			}
			System.out.println(nome + "<--nomequeveio  " + nomeConsulta + "<---nomeConsulta");
			System.out.println(senha + "<--senhaqueveio  " + senhaConsulta + "<---senhaConsulta");					
		}	
			
		catch (SQLException e) {   
			e.getMessage();
			System.out.println(result);
			result = false;
		}   
		System.out.println(result);   
		return result;  
	}
	public void setUsuario(UsuarioLogado usuario) {
		int novoId = 0;
		try {
			sql = "SELECT max(id) as ID FROM usuarios";
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

		usuario.setId(novoId);
		try {
			System.out.println("antes do sql");
			sql = "INSERT INTO usuarios (id, loginusuario, senhausuario, ultimologin) VALUES (?,?,?,?)";
			System.out.println("depois do sql" + sql);
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, novoId);
			stmt.setString(2, usuario.getLoginUsuario());
			stmt.setString(3, usuario.getSenhaUsuario());
			stmt.setString(4, "-1");
			
		}	
			
		catch (SQLException e) {   
			e.getMessage();
		} 

		
	}
	public void setSenha(String text) {
		
	}
	public void setUsuario(String text) {
		
	}
	// update para funcionários Senha
	public void atualizeCadastroSenhaFuncionario(UsuarioLogado usuario) {
		int idFuncionario = usuario.getId();
		sql = "UPDATE usuarios SET loginusuario = ?, senhausuario = ?, ultimologin = ? WHERE id = ?";
		System.out.println("sql update" + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getLoginUsuario());
			stmt.setString(2, usuario.getSenhaUsuario());
			stmt.setString(3, "-10");
			stmt.executeUpdate();
			System.out.println("Update da tabela funcionarios do(a) " + usuario.getLoginUsuario()
					+ " foi salvo na base de dados, com identificador: " + idFuncionario);
		} catch (Exception e) {
			System.err.println("Erro ao atualizar BD func: " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
	
	// salveSenhaFuncionario
	public boolean salveUsuarioSenhaFuncionario(UsuarioLogado usuarioLogado) {
		System.out.println("entrei em salveUsuarioSenhaFuncionario ");
		int novoId = 0;
		try {
			sql = "SELECT max(id) as ID FROM usuarios";
			ResultSet consulta = stmt.executeQuery(sql);
			if (consulta.next()) {
				novoId = consulta.getInt("ID") + 1;
				System.out.println("novo id: " + novoId);
			} else {
				novoId = 1;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		usuarioLogado.setId(novoId);

		System.out.println("data que foi pega: ");
		sql2 = "SELECT NOW() AS data ";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql2);
			ResultSet consulta = stmt.executeQuery();
			while (consulta.next()) {
				data = consulta.getString("data");
			}
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		
		sql = "INSERT INTO usuarios (id, loginusuario, senhausuario, ultimologin) VALUES (?,?,?,?)";
		System.out.println("Tentando rodar SQL insert user e senha: " + sql);
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, novoId);
			stmt.setString(2, usuarioLogado.getLoginUsuario());
			stmt.setString(3, usuarioLogado.getSenhaUsuario());
			stmt.setString(4, data);
			stmt.executeUpdate();
			System.out.println("Funcionário cadastrado senha: " + usuarioLogado.getLoginUsuario()
					+ " Cadastrado na base de dados, com identificador: " + usuarioLogado.getId());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// salvou user e senha do funcionario inicial deste cliente do tipo funcionario
		return true;
	}
	// fim 

}