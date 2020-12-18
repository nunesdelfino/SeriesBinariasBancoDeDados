package controle;

import modelo.Serie;
import persistencia.DAOSerie;

public class ControleSerie {
	
	DAOSerie dao;
	
	public ControleSerie() {
		super();
		dao = DAOSerie.getInstance();
		
	}
	
	public Serie incluirSerie(Serie serie) {
		return dao.incluir(serie);
	}
	
	public Serie removerSerie(Serie serie) {
		return dao.remover(serie);
	}
	
	

}
