package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TabelaFuncionarios extends AbstractTableModel {

	private ArrayList<String> colunas;
	private ArrayList<ArrayList<String>> matrizFuncionario;
	private ArrayList<String> linhaFuncionario = null;

	public TabelaFuncionarios() {
		//nome, idade, endereco, datanascimento, sexo, planosaude, telefone
		colunas = new ArrayList<String>();
		colunas.add("Nome");
		colunas.add("Idade");
		colunas.add("Endereco");
		colunas.add("Nascimento");
		colunas.add("Sexo");
		colunas.add("Plano Saúde");
		colunas.add("Telefone");

		matrizFuncionario = new ArrayList<ArrayList<String>>();
		linhaFuncionario = new ArrayList<String>();
	}
	public int getId(int numLinha) {
		linhaFuncionario = matrizFuncionario.get(numLinha);
		return Integer.parseInt(linhaFuncionario.get(0));
	}
	public void inserir(ArrayList<String> linha) {
		matrizFuncionario.add(linha);
		fireTableDataChanged();
	}

	public void renovar(ArrayList<ArrayList<String>> novaMatriz) {
		matrizFuncionario = novaMatriz;
		fireTableDataChanged();
	}

	public void zerar() {
		matrizFuncionario = new ArrayList<ArrayList<String>>();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return matrizFuncionario.size();
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
		//System.out.println("Pegando item linha " + rowIndex + " e coluna " +
		// columnIndex);
		// verifica qual valor deve ser retornado
		/*
		 * if (columnIndex == COL_NOME) { //return p.getNome(); return "maria";
		 * } else if (columnIndex == COL_QUANT) { //return p.getQuant(); return
		 * 2; }
		 */
		linhaFuncionario = matrizFuncionario.get(rowIndex);
		// temos armazenado o campo id na primeira coluna de cada linha
		return linhaFuncionario.get(columnIndex + 1);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		System.out.println("AAAA setValueAt item linha " + rowIndex + " e coluna " + columnIndex);
		// pega o produto da linha
		// Produto p = produtos.get(rowIndex);

		// verifica qual valor vai ser alterado
		/*
		 * if (columnIndex == COL_NOME) { p.setNome(aValue.toString()); } else
		 * if (columnIndex == COL_QUANT) {
		 * p.setQuant(Integer.parseInt(aValue.toString())); }
		 */

		// avisa que os dados mudaram
		fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// no nosso caso todas nao vão ser editáveis, entao retorna true pra
		// todas
		return false;
	}

}
