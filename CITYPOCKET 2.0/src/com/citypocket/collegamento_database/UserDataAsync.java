/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/UserDataAsync.java
 * Versione : 2.0
 */

package com.citypocket.collegamento_database;

import java.sql.Connection;

import com.citypocket.activity.CategoriaActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


public class UserDataAsync extends AsyncTask<Void,Void,Void>{

private Context context;
private Connection connection;
private ProgressDialog myProgress;

public UserDataAsync(Context context)//costruttore di classe,si occupa di ricevere in input il context della mainActivity,necessario per inizializzare il progressDialog
{
	super();
	this.context = context;
	myProgress = new ProgressDialog(this.context);

}

	

@Override
	protected void onPreExecute()//funzione che si occupa di inizializzare correttamente il progressDialog nell'interfaccia
{
		
		super.onPreExecute();
		myProgress.setCanceledOnTouchOutside(false);
	    myProgress.setTitle("Carico i dati..");    
		myProgress.setMessage("Please Wait..");
	    myProgress.show();
		
		
	}
	
    //funzione che si occupa dell'effettiva interazione con il database.
    //essa si connette al database utilizzando i metodi definiti nella classe DatabaseConnect,e utilizzando i parametri di accesso definiti nella mainactivity.
//dopodich� si occupa tramite opportune funzioni di recuperare i dati dell'utente dal database,settare con tali dati le variabili di classe,e poi chiudere la connessione.
	@Override
	protected Void doInBackground(Void... params) {
		connection = DatabaseConnect.estabilishConnection(connection, CategoriaActivity.username, CategoriaActivity.password);
		DatabaseConnect.closeConnection(connection);
		return null;
	}

    @Override
    	protected void onPostExecute(Void result) 
    	//funzione che si occupa di gestire i risultati recuperati dal database.
    	//questi dati,opportunamente settati come variabili di classe,verranno poi passati agli appositi campi dell'interfaccia principale.
    {
    		
    		super.onPostExecute(result);
    		myProgress.dismiss();//toglie il progressDialog poich� la task � stata ormai completata
    		
    }

}