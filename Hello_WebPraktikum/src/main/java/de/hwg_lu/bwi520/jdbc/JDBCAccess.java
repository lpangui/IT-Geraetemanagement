package de.hwg_lu.bwi520.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBCAccess {

	Connection dbConn;
	String dbDrivername;
	String dbURL;
	String dbUser;
	String dbPassword;
	String dbSchema;
	public JDBCAccess() throws NoConnectionException{
		this.setDBParms();
		this.createConnection();
		this.setSchema();
	}
	public abstract void setDBParms();
//	public void setDBParms(){
//		dbDrivername = "com.ibm.db2.jcc.DB2Driver";
//		dbURL = "jdbc:db2://143.93.202.3:50000/OUEBDB";
//		dbUser = "USER50";
//		dbPassword = "db2users";
//		dbSchema = "S654321";
//	}
	public void createConnection() throws NoConnectionException{
		try{
			Class.forName(dbDrivername);
			System.out.println("JDBC-Treiber erfolgreich geladen");
		
			dbConn = DriverManager.getConnection(
												dbURL,
	
												dbUser,
												dbPassword
												);
			System.out.println("Datenbankverbindung erfolgreich angelegt");
		}catch(Exception e){
			e.printStackTrace();
			throw new NoConnectionException();
		}
	}
	public abstract void setSchema() throws NoConnectionException;
//	public void setSchema() throws NoConnectionException {
//		try{
//			String sql = "SET SCHEMA ?";
//			PreparedStatement prepStat = dbConn.prepareStatement(sql);
//			prepStat.setString(1, dbSchema);
//			prepStat.executeUpdate();
//			String sql = "SET SCHEMA '" + dbSchema + "'";
//			System.out.println(sql);
//			dbConn.createStatement().executeUpdate(sql);
//			System.out.println("Schema " + dbSchema + " erfolgreich gesetzt");
//		}catch(SQLException se){
//			se.printStackTrace();
//			throw new NoConnectionException();
//		}
//	}
	public Connection getConnection() throws NoConnectionException {
		return dbConn;
	}
}