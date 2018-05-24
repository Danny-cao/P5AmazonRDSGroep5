package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {
	
	// maak verbinding met de oracle db op de amazon rds cloud
	private static final String DB_URL = "jdbc:oracle:thin:@myoracledb.csos8plwzsrn.us-west-2.rds.amazonaws.com:1521:ORCL";
	private static final String USER = "dannycao1234";
	private static final String PASS = "Geheim01";
	
	
	protected Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
		
	}
	
	public void closeConnection(Connection conn) throws SQLException {
		
		conn.close();
		
	}

}
