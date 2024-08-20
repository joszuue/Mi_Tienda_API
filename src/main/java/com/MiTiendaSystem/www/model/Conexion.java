package com.MiTiendaSystem.www.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver ";
	 static final String DB_URL = "jdbc:mysql://localhost:3306/mi_tienda_system";

	 static final String USER = "root";
	 static final String PASS = "";

	 Connection conex = null;

	 Statement stmt = null;

	 PreparedStatement consulta = null;

	 ResultSet resultSet = null;

	 public Conexion(){
		 try {
			 Class.forName(JDBC_DRIVER);
		 } catch (ClassNotFoundException ex) {
			Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,
			null, ex);
		 }
	 }

	 public void connect() throws SQLException{
		 conex = DriverManager.getConnection(DB_URL,USER,PASS);
	 }

	 public void close() throws SQLException{
		 if(conex != null){
			 conex.close();
			 conex = null;
		 }
	 }
}
