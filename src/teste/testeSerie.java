package teste;

import java.sql.Date;

import modelo.Serie;

public class testeSerie {

	public static void main(String[] args) {
		
		Serie s = new Serie();
		
		s.setNome("Teste");
		s.setTemporadas(2);
		s.setEpisodios(10);
		s.setGenero("Novo");
		s.setEncerrada(true);
		
		String str = "2020-03-31";
		Date date = Date.valueOf(str);
		s.setLancamento(date);
		
		System.out.println(s.toString());

	}

}
