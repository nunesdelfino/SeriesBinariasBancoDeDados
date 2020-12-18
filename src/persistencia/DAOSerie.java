package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.PersitenciaException;
import modelo.Serie;

public class DAOSerie {
	
	private static final String SQL_SELECT = "select id,nome,numero_episodios,numero_temporadas,genero,encerrada,data_lancamento from dados";
	private static final String JDBC_PASSWORD = "series";
	private static final String JDBC_USER = "series";
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/series";
	private static final String JDBC_DRIVER = "org.postgresql.Driver";

	private static DAOSerie dao;

	private Connection con;
	
	private DAOSerie() {
		this.initDriverJDBC();
		this.initConection();
	}
	
	private void initConection() {
		try {
			this.con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		} catch (SQLException e) {
			throw new PersitenciaException("Erro na conexão com o Banco: Iniciar Conexão", e);
		}
	}
	
	private void initDriverJDBC() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (Exception e) {
			throw new PersitenciaException("Erro na conexão com o Banco: Iniciar Driver", e);
		}
	}
	
	public static DAOSerie getInstance() {
		if(dao == null) {
			dao = new DAOSerie();
		}
		return dao;
	}
	
	public Serie getSerieById(Integer id) {
		Serie serie = null;
		
		String sql = SQL_SELECT + "where id=?";
		
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				serie = getSerieByResultSet(rs);
			}
		} catch (SQLException e) {
			throw new PersitenciaException("Erro ao consultar o Banco: Id", e);
		}
		
		return serie;
	}
	
	public List<Serie> listarTodas(){
		return this.listarOuProcurar(null);
	}
	
	public List<Serie> localizarByNome(String nome){
		return this.listarOuProcurar(nome);
	}
	
	private List<Serie> listarOuProcurar(String nome){
		List<Serie> lista = new ArrayList<>();
		
		Serie serie = null;
		String sql = SQL_SELECT;
		
		if(nome != null && !nome.equals("")) {
			sql = sql + " where nome like ?";
		}
		
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(sql);
			
			if(nome != null && !nome.equals("")) {
				pst.setString(1, nome+"%");
			}
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				serie = getSerieByResultSet(rs);
				lista.add(serie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersitenciaException("Erro ao consultar o Banco: listarOuProcurar", e);
		}
		
		return lista;
	}
	
	private Serie getSerieByResultSet(ResultSet rs) throws SQLException {
		Serie serie = new Serie();
		serie.setId(rs.getInt("id"));
		serie.setNome(rs.getString("nome"));
		serie.setEpisodios(rs.getInt("numero_episodios"));
		serie.setTemporadas(rs.getInt("numero_temporadas"));
		serie.setGenero(rs.getString("genero"));
		serie.setEncerrada(rs.getBoolean("encerrada"));
		serie.setLancamento(rs.getDate("data_lancamento"));
		
		return serie;
	}
	
	public Serie incluir(Serie serie) {
		String sql = "insert into dados(nome,numero_episodios,"
				+ "numero_temporadas,genero,encerrada, data_lancamento)"
				+ " values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pst = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, serie.getNome());
			pst.setInt(2, serie.getEpisodios());
			pst.setInt(3, serie.getTemporadas());
			pst.setString(4, serie.getGenero());
			pst.setBoolean(5, serie.isEncerrada());
			pst.setDate(6, serie.getLancamento());
			
			pst.executeUpdate();
			
			ResultSet generateKeys = pst.getGeneratedKeys();
			
			if(generateKeys.next()) {
				serie.setId(generateKeys.getInt(1));
			} else {
				// TODO
				throw new SQLException("Erro ao incluir série, ID não retornado.");
			}
		} catch (SQLException e) {
			if(e.getMessage().contains("idx_nome")) {
				throw new PersitenciaException("Erro ao incluir, serie: " + serie.getNome()
						+ "já existe!", e);
			}
			throw new PersitenciaException("Erro ao incluir: " + e.getMessage(), e);
		}
		return serie;
	}
	
	// TODO é uma boa pratica atualizar todo o objeto?
	public Serie atualizar(Serie serie) {
		String sql = "update dados set nome=? where id=?";
		
		PreparedStatement pst;
		int afetadas = 0;
		
		try {
			pst = this.con.prepareStatement(sql);
			pst.setString(1, serie.getNome());
			pst.setInt(2, serie.getId());
			
			afetadas = pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersitenciaException("Erro ao atualizar: " + e.getMessage(), e);
		}
		
		switch (afetadas) {
		case 0:
			return null;
		case 1:
			return serie;
		default:
			throw new PersitenciaException("Multiplos registros atualizados!", null);
		}
	}
	
	public Serie remover(Serie serie) {
		String sql = "delete from dados where id=?";
		
		PreparedStatement pst;
		int afetadas;
		try {
			pst = this.con.prepareStatement(sql);
			pst.setInt(1, serie.getId());
			afetadas = pst.executeUpdate();
		} catch (SQLException e) {
			throw new PersitenciaException("Erro ao remover: " + e.getMessage(), e);
		}
		
		switch (afetadas) {
		case 0:
			return null;
		case 1:
			return serie;
		default:
			throw new PersitenciaException("Multiplos registros removidos!", null);
		}
	}
	
}
