package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteJDBC {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/series",
					"series","series");
			
			String id = "1";
			
			String sql = "select id,nome from dados";
			
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.printf("Id: %d  ", rs.getInt(1));
				System.out.printf("Nome: %s \n", rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
