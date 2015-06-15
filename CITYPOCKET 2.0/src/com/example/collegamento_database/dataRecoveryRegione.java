package com.example.collegamento_database;

//Classe che si occupa di recuperare in modo asincrono i dati relativi agli utenti della community

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.activity.RegioneActivity;
import com.example.provacondatabase.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;




public class dataRecoveryRegione extends AsyncTask<String,Void,String> {
	
	
	private int Error;
	private ProgressDialog myProgress;
	public Context context;
	private Connection connection;
	private ResultSet Regioni;
	
	public static Regione Regione_data[];
	public static RegioneAdapter adapter;
	
	
	public dataRecoveryRegione(Context context)
	{super();
	
	this.context = context;
	myProgress = new ProgressDialog(context);
	}
	
	public static dataRecoveryRegione Data(Context context) //per modificare solo il nome
	{
		dataRecoveryRegione e = new dataRecoveryRegione(context);
		return e;}
	
	
	
	
	
	@Override
    protected void onPreExecute()
    {super.onPreExecute();//chiama la funzione della classe base AsyncTask
	 myProgress.setCanceledOnTouchOutside(false);
     myProgress.setTitle("Carico i dati..");    
	 myProgress.setMessage("Please Wait..");
     myProgress.show();
    
    }

	
	 @Override
     protected String doInBackground(String... urls) {//In questa fase avvengono tutte le operazioni di JDBC
       Error=0;
		connection=null;System.out.println("connection");
     	connection = DatabaseConnect.estabilishConnection(connection,RegioneActivity.username,RegioneActivity.password);
     	if(connection==null) {System.out.println("non connesso!");Error=1;}
     	else setRegioneLista();
     	
        DatabaseConnect.closeConnection(connection);//chiude la connessione una volta terminate le operazioni richieste
              
        return null;}




	 @Override
	 protected void onPostExecute(String result) //istruzioni da eseguire dopo doInBackground
	 {   
		 adapter = new RegioneAdapter(context,R.layout.my_row_regione,Regione_data);
		 RegioneActivity.listview1.setAdapter(adapter);
		 
		 myProgress.dismiss(); //toglie il progressDialog poich� la task � stata eseguita
	     
		 //Messagio di Android in caso di errore o meno
		 if(Error!=0){switch (Error) {
		 case 1 : Toast.makeText(context,"DBConnection_Error (EDIT COMMUNITY, IP_Server);",Toast.LENGTH_SHORT).show();break;
		 case 2 : Toast.makeText(context,"ERRORE: COMMUNITY INESISTENTE!",Toast.LENGTH_SHORT).show();break;
		 case 7 : Toast.makeText(context,"ERRORE: NON sono presenti Utenti nella Community corrente!",Toast.LENGTH_SHORT).show();break;
		 }}
		 else Toast.makeText(context,"Caricamento Utenti avvenuto con SUCCESSO!",Toast.LENGTH_SHORT).show();
	        	 
	  }
	 


	 


	 //funzione che preleva i dati dalle query 
	 private void setRegioneLista()
	 {
	 try{
	 Regioni = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select distinct Regione from (localita)";
	 Regioni = stmt.executeQuery(sql);
	 Regione_data= new Regione[numRegioni()];
	 int i=0;
	 while(Regioni.next()){
			 Regione_data[i]=new Regione(Regioni.getString("REGIONE"), String.valueOf(numComuni()));
			 i++;
	 };
	 	
	 System.out.println("2");
	 stmt.close();
	 Regioni.close();}
	 catch(SQLException e)
	 {System.out.println("Error retrieving Users!");
	 DatabaseConnect.closeConnection(connection);}
	 }
	 
	 
	 //serve per stabilire quante regioni sono presenti  per poter sapere la lunghezza dell'array dinamico
	 private int numRegioni()
	 {
	 try{
	 ResultSet rs = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select count(distinct Regione) AS CONTEGGIO from(localita)"; 

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
	 {System.out.println("Error retrieving Number of Users!");
	 DatabaseConnect.closeConnection(connection);}
	 return Error=7;
	 }

	 //serve per prelevare il numero di comuni per Regioni
	 private int numComuni()
	 {
	 try{
	 ResultSet rs = null;
	 Statement stmt = connection.createStatement();
	 String sql = "select count(l.Nome_localita) as CONTEGGIO from localita l join applicazione ap on l.Nome_localita = ap.Nome_localita where l.Nome_localita ='"+Regioni.getString("REGIONE")+"' "; 

	 System.out.println("1");
	 int nComuni=0;
	 rs = stmt.executeQuery(sql);  
	 while(rs.next()){
		 nComuni= rs.getInt("CONTEGGIO");
	 };
	 
	 stmt.close();
	 rs.close();
	 return nComuni;}
	 catch(SQLException e)
	 {System.out.println("Error retrieving Number of Comuni!");
	 DatabaseConnect.closeConnection(connection);}
	 return Error=7;
	 }
}