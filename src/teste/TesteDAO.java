package teste;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import controle.ControleSerie;
import modelo.Serie;
import persistencia.DAOSerie;

public class TesteDAO {
	
	public static void main(String[] args) {
		
		DAOSerie dao = DAOSerie.getInstance();
		
		ControleSerie ctr = new ControleSerie();
		
		List<Serie> lista = new ArrayList<>();
		
		Serie s = new Serie();
		s.setId(9);
		s = ctr.removerSerie(s);
		if(s == null) {
			System.out.println("Série não existe no banco de dados!");
		}
		

//		s.setNome("Teste 3");
//		s.setEncerrada(true);
//		s.setEpisodios(10);
//		
//		
//		String str = "2020-03-31";
//		Date date = Date.valueOf(str);
//		s.setLancamento(date);
//		
//		s.setGenero("Terror");
//		s.setTemporadas(2);
//		s = ctr.incluirSerie(s);
		
		
		lista = dao.listarTodas();
		
		for(Serie se: lista) {
			System.out.println(se.toString());
		}
		
	}
		

}
