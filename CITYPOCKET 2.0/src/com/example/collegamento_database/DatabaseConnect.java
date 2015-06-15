package com.example.collegamento_database;
//Classe che gestisce la connesione al DataBase di MySql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnect {

	
public static Connection estabilishConnection(Connection conn,String user,String password)
{  
	System.out.println("connection-entrato");
	String url = "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net:3306/ad_d314fb7c8084eac";
 	
	
	System.out.println("user: "+user+" password: "+password+" url: "+url+"");
	
	try {System.out.println("connection 1");
		Class.forName("com.mysql.jdbc.Driver");System.out.println("connection 2");
		conn = DriverManager.getConnection(url,user,password);System.out.println("connection 3");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("Driver non caricato");
		return null;
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Errore con url e credenziali!");
		return null;
	}


    return conn;


}



public static void closeConnection(Connection conn)
{
	try { System.out.println("connection chiusa");
		conn.close();
	} catch (SQLException e) {
		System.out.println("Impossibile chiudere la connessione!");
		e.printStackTrace();
	}
	
	
	
	
	
	
}














}
