package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FazendoConexao{
	private Connection con = null;
	private String stringCon;
	
	public Connection getConexao(){
		try {
			stringCon = "jdbc:mysql://127.0.0.1/looproject?autoReconnect=true&useSSL=false";//pra não dar erro de ssl blablabla que estava dando: WARN: Establishing SSL connection without server's identity verification is not recommended. 
			con = DriverManager.getConnection(stringCon,"userjava","senhajava");
			System.out.println("Conectado.");
		} catch(SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		return con;
	}
}