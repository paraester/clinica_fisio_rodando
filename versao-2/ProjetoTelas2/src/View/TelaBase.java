package View;

import java.awt.CardLayout;
import javax.swing.JPanel;
import Control.Register;

public class TelaBase extends JPanel {
	// private Register register;
	//retirado para variaveis globais (método privado) 
	private ClientFormCadastroFuncionarios t1;
	//private ClientFormBuscaPaciente cfbp; // provavel nunca sera usado
	private ClientFormConsultarFuncionarios cfcf;
	private ClientFormConsultarClientes fcc;
	private ClientFormConsultarAvaliacoes fcoes;
	private ClientFormHome cfh; // ClientFormHome
	private ClientFormCadastroAvaliacao cfa; // ClientFormAvalia

	//private ClientFormCadastroAvaliacao cfasa;// ClientFormAvalia SalvarEavaliar que veio do cadastro de clientes
//	private ClientFormConsultar cfc; // ClientFormConsultar
	private ClientFormCadastroClientes cf; // ClientForm
		

	public TelaBase(MainFrame telainit, Register register) {
		// this.register = register;
		System.out.println("inicio telabase");


		setLayout(new CardLayout());
		t1 = new ClientFormCadastroFuncionarios(telainit, register);
		cf = new ClientFormCadastroClientes(telainit, register);
		cfa = new ClientFormCadastroAvaliacao(telainit, register);
		cfh = new ClientFormHome(telainit);
		//cfc = new ClientFormConsultar(telainit);
		fcc = new ClientFormConsultarClientes(telainit, register);
		fcoes = new ClientFormConsultarAvaliacoes(telainit, register);
	//	cfbp = new ClientFormBuscaPaciente(telainit, register);
		cfcf = new ClientFormConsultarFuncionarios(telainit, register);

		add(cfh, "formHome");
		add(cfa, "formAvali");
		//add(cfasa, "formAvaliSalvarEavaliar");
		add(cf, "formCadastroClientes");
	//	add(cfc, "formConsultar");
		add(fcc, "formConsultarClientes");
		add(fcoes, "formConsultarAvaliacoes");
		add(t1, "formCadastroFuncionarios");
	//	add(cfbp, "formClientBuscaPaciente");
		add(cfcf, "ClientFormConsultarFuncionarios");
	}
	/**
	 * Dá acesso ao formulário para atualizações no transcorrer da execução
	 * @return the FormCadastroAvali
	 */
	public ClientFormCadastroAvaliacao getFormCadastroAvali() {
		return cfa;
	}

	/**
	 * @param cfa the FormCadastroAvali to set
	 */
	public void setFormCadastroAvali(ClientFormCadastroAvaliacao cfa) {
		this.cfa = cfa;
	}

	/**
	 * @return the cf
	 */
	public ClientFormCadastroClientes getFormCadastroClientes() {
		return cf;
	}
	/**
	 * @param cf the cf to set
	 */
	public void setFormCadastroClientes(ClientFormCadastroClientes cf) {
		this.cf = cf;
	}
	
	/**
	 * @return the t1
	 */
	public ClientFormCadastroFuncionarios getFormCadastroFuncionarios() {
		return t1;
	}
	/**
	 * @param t1 the t1 to set
	 */
	public void setFormCadastroFuncionarios(ClientFormCadastroFuncionarios t1) {
		this.t1 = t1;
	}
	
}