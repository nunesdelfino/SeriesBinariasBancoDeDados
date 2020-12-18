package visao.Jtable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.IModelo;

@SuppressWarnings("serial")
public class ModeloJTableModel extends AbstractTableModel {
	
	private List<IModelo> TabelaLista;
	private IModelo Tabela;
	int NumeroColunas = 0;
	int NumeroLinhas = 0;
	ArrayList<String> NomeColunas = null;
	
	public ModeloJTableModel(List<IModelo> tabList, IModelo tab) {
		this.setTabelaLista(tabList);
		this.setTabela(tab);
	}	
	
	@Override
	public int getColumnCount() {
		if (NumeroColunas == 0) {
			NumeroColunas = this.getTabela().getNumeroAtributos();
		}
		return NumeroColunas;
	}
	@Override
	public int getRowCount() {
		if (NumeroLinhas == 0) {
			NumeroLinhas = this.getTabelaLista().size();
		}
		return NumeroLinhas;
	}
	@Override
	public Object getValueAt(int Linha, int Coluna) {
		Object Valor = this.getTabelaLista().get(Linha).getAtributoValor(Coluna);
		return Valor;
	}
	
	public String getColumnName(int Coluna) {
		return this.getTabela().getAtributoNome(Coluna);
	}
	public List<IModelo> getTabelaLista() {
		return TabelaLista;
	}
	public void setTabelaLista(List<IModelo> tabelaLista) {
		TabelaLista = tabelaLista;
	}
	public IModelo getTabela() {
		return Tabela;
	}
	public void setTabela(IModelo tabela) {
		Tabela = tabela;
	}
	public int getNumeroColunas() {
		return NumeroColunas;
	}
	public void setNumeroColunas(int numeroColunas) {
		NumeroColunas = numeroColunas;
	}
	public int getNumeroLinhas() {
		return NumeroLinhas;
	}
	public void setNumeroLinhas(int numeroLinhas) {
		NumeroLinhas = numeroLinhas;
	}
	public ArrayList<String> getNomeColunas() {
		return NomeColunas;
	}
	public void setNomeColunas(ArrayList<String> nomeColunas) {
		NomeColunas = nomeColunas;
	}
	
	public IModelo getModeloLinha(int linha) {
		return this.getTabelaLista().get(linha);
	}

}
