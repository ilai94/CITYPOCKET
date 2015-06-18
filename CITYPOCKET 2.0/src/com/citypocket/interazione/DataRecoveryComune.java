/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/DataRecoveryCategoria.java
 * Versione : 2.0
 */
package com.citypocket.interazione;

//Classe che si occupa di recuperare in modo asincrono i dati relativi ai comuni

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.citypocket.R;
import com.citypocket.activity.ComuneActivity;
import com.citypocket.collegamento_database.DatabaseConnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;




public class DataRecoveryComune extends AsyncTask<String,Void,String> {
	
	
	private int Error;
	private ProgressDialog myProgress;
	public Context context;
	private Connection connection;
	private ResultSet Comuni;
	public static Comune Comune_data[];
	public static ComuneAdapter adapter;
	
	public DataRecoveryComune(Context context)
	{
		super();
		this.context = context;
		myProgress = new ProgressDialog(context);
	}
	
	public static DataRecoveryComune Data(Context context) //per modificare solo il nome
	{
		DataRecoveryComune e = new DataRecoveryComune(context);
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
		 connection = DatabaseConnect.estabilishConnection(connection,ComuneActivity.username,ComuneActivity.password);
		 if(connection==null) 
		 {
			 System.out.println("non connesso!");
			 Error=1;
			 }
     	 else setComuneLista();
     	 DatabaseConnect.closeConnection(connection);//chiude la connessione una volta terminate le operazioni richieste
         return null;
         }




	 @Override
	 protected void onPostExecute(String result) //istruzioni da eseguire dopo doInBackground
	 {   
		 adapter = new ComuneAdapter(context,R.layout.my_row_comune,Comune_data);
		 ComuneActivity.listview2.setAdapter(adapter);
		 
		 myProgress.dismiss(); //toglie il progressDialog poichè la task è stata eseguita
	     
		 //Messagio di Android in caso di errore o meno
		 if(Error!=0){switch (Error) {
		 case 1 : Toast.makeText(context,"DBConnection_Error (EDIT COMMUNITY, IP_Server);",Toast.LENGTH_SHORT).show();break;
		 case 7 : Toast.makeText(context,"ERRORE: NON sono presenti Comuni!",Toast.LENGTH_SHORT).show();break;
		 }}
		 else Toast.makeText(context,"Caricamento Comuni avvenuto con SUCCESSO!",Toast.LENGTH_SHORT).show();
	        	 
	  }
	 
	 //funzione che preleva i dati dalle query 
	 private void setComuneLista()
	 {
	 try{
	 Comuni = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select (l.Nome_localita) from localita l join applicazione ap on l.Nome_localita = ap.Nome_localita where l.Nome_localita ='"+Comuni.getString("REGIONE")+"' ";
	 Comuni = stmt.executeQuery(sql);
	 Comune_data= new Comune[numComuni()];
	 int i=0;
	 while(Comuni.next()){
			 Comune_data[i]=new Comune(Comuni.getString("COMUNE"));
			 i++;
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 Comuni.close();}
	 catch(SQLException e)
	 {
		 System.out.println("Error retrieving Comuni!");
		 DatabaseConnect.closeConnection(connection);}
	 }
	 
	 
	 //serve per stabilire quanti comuni sono presenti per poter sapere la lunghezza dell'array dinamico
	 private int numComuni()
	 {
	 try{
		 ResultSet rs = null;
		 Statement stmt = connection.createStatement();
	     String sql = "select count(l.Nome_localita) as CONTEGGIO from localita l join applicazione ap on l.Nome_localita = ap.Nome_localita where l.Nome_localita ='"+Comuni.getString("REGIONE")+"' ";
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
		 System.out.println("Error retrieving numero di Comuni!");
		 DatabaseConnect.closeConnection(connection);}
	 	return Error=7;
	 }

}