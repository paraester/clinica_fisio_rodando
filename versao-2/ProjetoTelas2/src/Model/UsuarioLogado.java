package Model;

public class UsuarioLogado {
	private String loginusuario = "";
	private String senhausuario = "";
	private String ultimologin = "";
	private int id = 0;
	

	public UsuarioLogado() {
		System.out.println("construindo novo usuario - tudo vazio - nao logado");
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	public String getLoginUsuario() {
		return loginusuario;
	}
	public void setLoginUsuario(String loginusuario) {
		this.loginusuario = loginusuario;
	}
	public String getSenhaUsuario() {
		return senhausuario;
	}
	public void setSenhaUsuario(String senhausuario) {
		this.senhausuario = senhausuario;
	}
	public String getUltimoLogin() {
		return loginusuario;
	}
	public void setUltimoLogin(String ultimologin) {
		this.ultimologin = ultimologin;
	}
}
