/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/DataRecoveryRegione.java
 * Versione : 2.0
 */

package com.citypocket.interazione;

//Classe che si occupa di recuperare in modo asincrono i dati relativi ai risultati turista

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.citypocket.R;
import com.citypocket.activity.RisultatoTuristaActivity;
import com.citypocket.collegamento_database.DatabaseConnect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;




public class DataRecoveryRisultatoTurista extends AsyncTask<String,Void,String> {
	
	
	private int Error;
	private ProgressDialog myProgress;
	public Context context;
	private Connection connection;
	private ResultSet RisultatiTuristi;
	public static RisultatoTurista RisultatiTuristi_data[];
	public static RisultatoTuristaAdapter adapter;
	
	
	public DataRecoveryRisultatoTurista(Context context)
	{
		super();
		this.context = context;
		myProgress = new ProgressDialog(context);
	}
	
	public static DataRecoveryRisultatoTurista Data(Context context) //per modificare solo il nome
	{
		DataRecoveryRisultatoTurista e = new DataRecoveryRisultatoTurista(context);
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
     	 connection = DatabaseConnect.estabilishConnection(connection,RisultatoTuristaActivity.username,RisultatoTuristaActivity.password);
     	 if(connection==null) 
     	 {
     		 System.out.println("non connesso!");
     		 Error=1;
     		 }
     	else setRisultatoTuristaLista();
     	
        DatabaseConnect.closeConnection(connection);//chiude la connessione una volta terminate le operazioni richieste
              
        return null;
      }




	 @Override
	 protected void onPostExecute(String result) //istruzioni da eseguire dopo doInBackground
	 {   
		 adapter = new RisultatoTuristaAdapter(context,R.layout.my_row_risultato_turista,RisultatiTuristi_data);
		 RisultatoTuristaActivity.listview1.setAdapter(adapter);
		 
		 myProgress.dismiss(); //toglie il progressDialog poichè la task è stata eseguita
	     
		 //Messagio di Android in caso di errore o meno
		 if(Error!=0){
			 switch (Error) {
			 case 1 : Toast.makeText(context,"DBConnection_Error (EDIT COMMUNITY, IP_Server);",Toast.LENGTH_SHORT).show();break;
			 case 7 : Toast.makeText(context,"ERRORE: NON sono presenti Risultati Turista",Toast.LENGTH_SHORT).show();break;
			 }
			 }
		 else Toast.makeText(context,"Caricamento Risultati Turista avvenuto con SUCCESSO!",Toast.LENGTH_SHORT).show();
	        	 
	  }
	 


	 


	 //funzione che preleva i dati dalle query 
	 private void setRisultatoTuristaLista()
	 {
	 try{
	 RisultatiTuristi = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select distinct Nome_app from (applicazione) where Nome_categoria ='Servizi al Cittadino'or Nome_categoria ='Parcheggio'or Nome_categoria ='Mobilità'or Nome_categoria =' Itinerari'or Nome_categoria =' Car/Bike Sharing'or Nome_categoria ='Attrazioni, Monumenti e Musei'or Nome_categoria ='Eventi'or Nome_categoria ='Meteo'or Nome_categoria ='Strutture per il Pernottamento'or Nome_categoria ='Ristorazione'or Nome_categoria ='Free Wi-Fi' and Nome_localita='"+RisultatiTuristi.getString("COMUNE")+"'";
	 RisultatiTuristi = stmt.executeQuery(sql);
	 RisultatiTuristi_data= new RisultatoTurista[numRisultatoTurista()];
	 int i=0;
	 while(RisultatiTuristi.next()){
			 RisultatiTuristi_data[i]=new RisultatoTurista(RisultatiTuristi.getString("REGIONE"));
			 i++;
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 RisultatiTuristi.close();}
	 catch(SQLException e)
	 {System.out.println("Error retrieving RisultatiTurista!");
	 DatabaseConnect.closeConnection(connection);}
	 }
	 
	 
	 //serve per stabilire quante app per turisti sono presenti  per poter sapere la lunghezza dell'array dinamico
	 private int numRisultatoTurista()
	 {
	 try{
	 ResultSet rs = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select count(distinct Nome_app) from (applicazione) where Nome_categoria ='Servizi al Cittadino'or Nome_categoria ='Parcheggio'or Nome_categoria ='Mobilità'or Nome_categoria =' Itinerari'or Nome_categoria =' Car/Bike Sharing'or Nome_categoria ='Attrazioni, Monumenti e Musei'or Nome_categoria ='Eventi'or Nome_categoria ='Meteo'or Nome_categoria ='Strutture per il Pernottamento'or Nome_categoria ='Ristorazione'or Nome_categoria ='Free Wi-Fi' and Nome_localita='"+RisultatiTuristi.getString("COMUNE")+"'";

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
	 {System.out.println("Error retrieving Number of appRisultatoTurista!");
	 DatabaseConnect.closeConnection(connection);}
	 return Error=7;
	 }

	
}