package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TabelasBdCriadas  extends SQLException{
	private Statement stmt = null;
	private String sql, sql2;
	private Connection conexao = new FazendoConexao().getConexao();
	public String nomeConsulta;

	public TabelasBdCriadas() {
		try {
			stmt = conexao.createStatement();

		} catch (Exception e) {
			System.out.println("Erro ao criar Tabela arrumado necessária");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public boolean criarTabela() {
		try {
			sql = "CREATE TABLE IF NOT EXISTS usuarios " + "(id INT PRIMARY KEY     NOT NULL,"
					+ " loginusuario           varchar(16)    NOT NULL, "
					+ " senhausuario           varchar(15)     NOT NULL, "
					+ " ultimologin           varchar(15)     NOT NULL";
			stmt.executeUpdate(sql);

			sql2 = "INSERT INTO usuarios (id, loginusuario, senhausuario, ultimologin) VALUES (?,?,?,?)";
			System.out.println("depois do sql" + sql);
			PreparedStatement stmt = conexao.prepareStatement(sql2);
			stmt.setInt(1, 1);
			stmt.setString(2, "adm");
			stmt.setString(3, "adm");
			stmt.setString(4, "-1");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("tabela usuarios criada");
		return true;
	}
}
