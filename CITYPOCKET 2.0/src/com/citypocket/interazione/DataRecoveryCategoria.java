/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/DataRecoveryCategoria.java
 * Versione : 2.0
 */
package com.citypocket.interazione;

//Classe che si occupa di recuperare in modo asincrono i dati relativi alle categorie

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.citypocket.R;
import com.citypocket.activity.CategoriaActivity;
import com.citypocket.collegamento_database.DatabaseConnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;




public class DataRecoveryCategoria extends AsyncTask<String,Void,String> {
	
	
	private int Error;
	private ProgressDialog myProgress;
	public Context context;
	private Connection connection;
	private ResultSet Categorie;
	public static Categoria Categoria_data[];
	public static CategoriaAdapter adapter;
	
	public DataRecoveryCategoria(Context context)
	{
		super();
		this.context = context;
		myProgress = new ProgressDialog(context);
	}
	
	public static DataRecoveryCategoria Data(Context context) //per modificare solo il nome
	{
		DataRecoveryCategoria e = new DataRecoveryCategoria(context);
		return e;
	}
	
	@Override
    protected void onPreExecute()
    {
		super.onPreExecute();//chiama la funzione della classe base AsyncTask
		myProgress.setCanceledOnTouchOutside(false);
		myProgress.setTitle("Carico i dati..");    
		myProgress.setMessage("Please Wait..");
	 	myProgress.show();
    
    }

	
	 @Override
     protected String doInBackground(String... urls) {//In questa fase avvengono tutte le operazioni di JDBC
		 Error=0;
		 connection=null;System.out.println("connection");
		 connection = DatabaseConnect.estabilishConnection(connection,CategoriaActivity.username,CategoriaActivity.password);
		 if(connection==null) 
		 {
			 System.out.println("non connesso!");
			 Error=1;
			 }
     	 else setCategoriaLista();
     	 DatabaseConnect.closeConnection(connection);//chiude la connessione una volta terminate le operazioni richieste
         return null;
         }




	 @Override
	 protected void onPostExecute(String result) //istruzioni da eseguire dopo doInBackground
	 {   
		 adapter = new CategoriaAdapter(context,R.layout.my_row_categoria,Categoria_data);
		 CategoriaActivity.listview4.setAdapter(adapter);
		 
		 myProgress.dismiss(); //toglie il progressDialog poichè la task è stata eseguita
	     
		 //Messagio di Android in caso di errore o meno
		 if(Error!=0){switch (Error) {
		 case 1 : Toast.makeText(context,"DBConnection_Error (EDIT COMMUNITY, IP_Server);",Toast.LENGTH_SHORT).show();break;
		 case 7 : Toast.makeText(context,"ERRORE: NON sono presenti Categorie!",Toast.LENGTH_SHORT).show();break;
		 }}
		 else Toast.makeText(context,"Caricamento Categorie avvenuto con SUCCESSO!",Toast.LENGTH_SHORT).show();
	        	 
	  }
	 
	 //funzione che preleva i dati dalle query 
	 private void setCategoriaLista()
	 {
	 try{
	 Categorie = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select Nome_categoria from (categoria)";
	 Categorie = stmt.executeQuery(sql);
	 Categoria_data= new Categoria[numCategorie()];
	 int i=0;
	 while(Categorie.next()){
			 Categoria_data[i]=new Categoria(Categorie.getString("CATEGORIA"));
			 i++;
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 Categorie.close();}
	 catch(SQLException e)
	 {
		 System.out.println("Error retrieving Categorie!");
		 DatabaseConnect.closeConnection(connection);}
	 }
	 
	 
	 //serve per stabilire quante categorie sono presenti per poter sapere la lunghezza dell'array dinamico
	 private int numCategorie()
	 {
	 try{
		 ResultSet rs = null;
		 Statement stmt = connection.createStatement();
	     String sql = "select count(distinct Nome_categoria) AS CONTEGGIO from(categoria)"; 
	     System.out.println("1");
	     int nUsers=0;
	     rs = stmt.executeQuery(sql);  
	     while(rs.next())
	     {
	    	 nUsers= rs.getInt("CONTEGGIO");
	     };
	     System.out.println("2");
	     stmt.close();
	     rs.close();
	     return nUsers;
	     }
	 catch(SQLException e)
	 {
		 System.out.println("Error retrieving numero di Categorie!");
		 DatabaseConnect.closeConnection(connection);}
	 	return Error=7;
	 }

}