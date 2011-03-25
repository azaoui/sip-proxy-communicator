package gov.nist.sip.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	//connection to the database, url,user and password given
	static Connection DbConnection(String url, String user, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Could not find the JDBC driver!");
			System.exit(1);
		}
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException sqle) {
			System.out.println("Could not connect");
			System.exit(1);
		}
		System.out.println("Connected successfully to Database");
		return conn;
	}

	//get the connection pointer for every use
	public static Statement getSql() throws SQLException {	
		Connection conn = DbConnection("jdbc:postgresql://127.0.0.1:5432/sip_db", "postgres", "root");
		Statement sql = conn.createStatement();
		return sql;
	}
	
	public static int findUserID(String Username) throws SQLException{
		Statement sql = getSql();
		ResultSet result = sql.executeQuery("select * from users where username ='" + Username + "'");
		
		if (result != null){
			result.next();
			return result.getInt("ID");
		} else {
			return 0;
		}
	}
	
}
