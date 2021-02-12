package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TabelaClientes extends AbstractTableModel {

	private ArrayList<String> colunas;
	private ArrayList<ArrayList<String>> matrizClientes;
	private ArrayList<String> linhaClientes;

	public TabelaClientes() {
		colunas = new ArrayList<String>();
		colunas.add("Nome");
		colunas.add("Idade");
		colunas.add("Telefone");
		colunas.add("Nascimento");
		colunas.add("Especialidade");
		colunas.add("Médico");

		matrizClientes = new ArrayList<ArrayList<String>>();
		linhaClientes = new ArrayList<String>();
	}

	public void inserir(ArrayList<String> linha) {
		matrizClientes.add(linha);
		fireTableDataChanged();
	}

	public void renovar(ArrayList<ArrayList<String>> novaMatriz) {
		matrizClientes = novaMatriz;
		fireTableDataChanged();
	}

	public void zerar() {
		matrizClientes = new ArrayList<ArrayList<String>>();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return matrizClientes.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.size();
	}

	@Override
	public String getColumnName(int column) {
		// System.out.println("Pegando o nome da coluna " + column);
		// qual o nome da coluna
		return colunas.get(column);
	}

	@Override
	public Class<String> getColumnClass(int columnIndex) {
		// retorna a classe que representa a coluna
		/*
		 * if (columnIndex == COL_NOME) { return String.class; } else if
		 * (columnIndex == COL_QUANT) { return Integer.class; }
		 */
		return String.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// roda muitas vezes para pegar o produto da linha, coluna
		// System.out.println("Pegando item linha " + rowIndex + " e coluna " +
		// columnIndex);
		// verifica qual valor deve ser retornado
		/*
		 * if (columnIndex == COL_NOME) { //return p.getNome(); return "maria";
		 * } else if (columnIndex == COL_QUANT) { //return p.getQuant(); return
		 * 2; }
		 */
		linhaClientes = matrizClientes.get(rowIndex);
		// temos armazenado o campo id na primeira coluna de cada linha
		return linhaClientes.get(columnIndex + 1);
	}

	public int getId(int numLinha) {
		linhaClientes = matrizClientes.get(numLinha);

		return Integer.parseInt(linhaClientes.get(0));

	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// no nosso caso todas nao vão ser editáveis, entao retorna true pra
		// todas
		return false;
	}

}
