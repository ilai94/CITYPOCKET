/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/DataRecoveryRegione.java
 * Versione : 2.0
 */

package com.citypocket.interazione;

//Classe che si occupa di recuperare in modo asincrono i dati relativi ai risultati

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import com.citypocket.R;
import com.citypocket.activity.RisultatoActivity;
import com.citypocket.collegamento_database.DatabaseConnect;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;




public class DataRecoveryRisultato extends AsyncTask<String,Void,String> {
	
	
	private int Error;
	private ProgressDialog myProgress;
	public Context context;
	private Connection connection;
	private ResultSet Risultati;
	public static Risultato Risultati_data[];
	public static RisultatoAdapter adapter;
	
	
	public DataRecoveryRisultato(Context context)
	{
		super();
		this.context = context;
		myProgress = new ProgressDialog(context);
	}
	
	public static DataRecoveryRisultato Data(Context context) //per modificare solo il nome
	{
		DataRecoveryRisultato e = new DataRecoveryRisultato(context);
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
     protected String doInBackground(String... urls) //In questa fase avvengono tutte le operazioni di JDBC
     {  
		 Error=0;
		 connection=null;System.out.println("connection");
     	 connection = DatabaseConnect.estabilishConnection(connection,RisultatoActivity.username,RisultatoActivity.password);
     	 if(connection==null) 
     	 {
     		 System.out.println("non connesso!");
     		 Error=1;
     		 }
     	else setRisultatoLista();
     	
        DatabaseConnect.closeConnection(connection);//chiude la connessione una volta terminate le operazioni richieste
              
        return null;
      }




	 @Override
	 protected void onPostExecute(String result) //istruzioni da eseguire dopo doInBackground
	 {   
		 adapter = new RisultatoAdapter(context,R.layout.my_row_risultato,Risultati_data);
		 RisultatoActivity.listview1.setAdapter(adapter);
		 
		 myProgress.dismiss(); //toglie il progressDialog poichè la task è stata eseguita
	     
		 //Messagio di Android in caso di errore o meno
		 if(Error!=0){
			 switch (Error) {
			 case 1 : Toast.makeText(context,"DBConnection_Error (EDIT COMMUNITY, IP_Server);",Toast.LENGTH_SHORT).show();break;
			 case 7 : Toast.makeText(context,"ERRORE: NON sono presenti Risultati",Toast.LENGTH_SHORT).show();break;
			 }
			 }
		 else Toast.makeText(context,"Caricamento Risultati avvenuto con SUCCESSO!",Toast.LENGTH_SHORT).show();
	        	 
	  }
	 


	 


	 //funzione che preleva i dati dalle query 
	 private void setRisultatoLista()
	 {
	 try{
	 Risultati = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select distinct Nome_app from (applicazione) where Nome_localita ='"+Risultati.getString("COMUNE")+"' and Categoria='"+Risultati.getString("CATEGORIA")+"' ";
	 Risultati = stmt.executeQuery(sql);
	 Risultati_data= new Risultato[numRisultato()];
	 int i=0;
	 while(Risultati.next()){
			 Risultati_data[i]=new Risultato(Risultati.getString("REGIONE"));
			 i++;
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 Risultati.close();}
	 catch(SQLException e)
	 {System.out.println("Error retrieving Risultati!");
	 DatabaseConnect.closeConnection(connection);}
	 }
	 
	 
	 //serve per stabilire quante app sono presenti per poter sapere la lunghezza dell'array dinamico
	 private int numRisultato()
	 {
	 try{
	 ResultSet rs = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select count(distinct Nome_app) from (applicazione) where Nome_localita ='"+Risultati.getString("COMUNE")+"' and Categoria='"+Risultati.getString("CATEGORIA")+"' ";
	
	 System.out.println("1");
	 int nUsers=0;
	 rs = stmt.executeQuery(sql);  
	 while(rs.next()){
		 nUsers= rs.getInt("CONTEGGIO");
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 rs.close();
	 return nUsers;}
	 catch(SQLException e)
	 {System.out.println("Error retrieving Number of appRisultato!");
	 DatabaseConnect.closeConnection(connection);}
	 return Error=7;
	 }

	
}