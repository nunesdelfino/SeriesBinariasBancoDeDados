package modelo;

import java.sql.Date;

import utils.DateSql;

public class Serie implements IModelo {

	private Integer id;
	private String Nome;
	private Date Lancamento;
	private Boolean Encerrada;
	private Integer Temporadas;
	private Integer Episodios;
	private String Genero;
	
	public Serie(String Nome, Date Lancamento, Boolean Encerrada, Integer Temporadas, Integer Episodios) {
		super();
		this.Nome = Nome;
		this.Lancamento = Lancamento;
		this.Encerrada = Encerrada;
		this.Temporadas = Temporadas;
		this.Episodios = Episodios;
		
	}
	
	public Serie(String Nome,  Date Lancamento, Boolean Encerrada, Integer Episodios) {
		super();
		this.Nome = Nome;
		this.Episodios = Episodios;
		this.Lancamento = Lancamento;
		this.Encerrada = Encerrada;
	}
	
	public Serie() {
		super();
	}
	
	public String toString() {
		return "ID: " + id + "\n" +
				"Nome: " + Nome + "\n" +
				"Data de Lançamento: " + Lancamento + "\n" +
				"Encerrada: " + Encerrada + "\n" +
				"Número de Temporadas: " + Temporadas + "\n" +
				"Número de Episódios: " + Episodios + "\n" +
				"Genero: " + Genero + "\n\n";
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public Date getLancamento() {
		return Lancamento;
	}

	public void setLancamento(java.sql.Date date) {
		this.Lancamento = date;
	}

	public Boolean isEncerrada() {
		return Encerrada;
	}

	public void setEncerrada(Boolean encerrada) {
		this.Encerrada = encerrada;
	}

	public Integer getTemporadas() {
		return Temporadas;
	}

	public void setTemporadas(Integer temporadas) {
		this.Temporadas = temporadas;
	}

	public Integer getEpisodios() {
		return Episodios;
	}

	public void setEpisodios(Integer episodios) {
		this.Episodios = episodios;
	}
	
	public String getAtributoNome(int Coluna) {
		
		String NomeColuna = "";
		
		switch(Coluna) {
			case 0:
				NomeColuna = "Nome";
				break;
			case 1:
				NomeColuna = "Data de Lancamento";
				break;
			case 2:
				NomeColuna = "Encerrada";
				break;
			case 3:
				NomeColuna = "Número de Teporadas";
				break;
			case 4:
				NomeColuna = "Número de Episódios";
				break;
		}
		
		return NomeColuna;
		
	}
	
	public Object getAtributoValor(int Coluna) {
		
		Object ValorColuna = null;
		
		switch(Coluna) {
			case 0:
				ValorColuna = this.getNome();
				break;
			case 1:
				ValorColuna = DateSql.converteDataParaSql(getLancamento());
				break;
			case 2:
				ValorColuna = this.isEncerrada()?"Sim":"Não";
				break;
			case 3:
				ValorColuna = this.getTemporadas();
				break;
			case 4:
				ValorColuna = this.getEpisodios();
				break;
		}
		
		return ValorColuna;
	}
	
	public int getNumeroAtributos() {
		return 5;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
