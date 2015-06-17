/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/RegioneAdapter.java
 * Versione : 2.0
 */

package com.citypocket.interazione;
//classe scheletro per la row della regione

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citypocket.R;




public class RegioneAdapter extends ArrayAdapter<Regione>{

	Context context; //variabile che rappresenta l'activity dell'oggetto
	int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
	Regione data[] = null; //data array di bottoni

	public RegioneAdapter(Context context,int layoutResourceId,Regione data[])
	{
		super(context,layoutResourceId,data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}


	@Override
	public View getView(int position,View convertView,ViewGroup parent)//convert view riga attuale da creare
	{
		View row = convertView;//riga corrente
		RegioneHolder holder = null; //variabili bottoni

		if(row == null) //righe inesistenti
		{
			//crea lo scheletro della riga
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId,null);
			
			holder = new RegioneHolder();
			holder.NomeRegione = (TextView)row.findViewById(R.id.nome_regione);
			
			holder.n_comuni = (TextView)row.findViewById(R.id.n_comuni);
			
			row.setTag(holder);
			
		}
		else
		{
			//riga già esistente..carica lo scheletro già inizializzato
			holder = (RegioneHolder)row.getTag();//
		}
		Regione User = data[position];
		//associa alle variabile le stringhe dei bottoni
		holder.NomeRegione.setText(User.Regione);
		holder.n_comuni.setText(User.n_comuni);
		return row;

	}

	static class RegioneHolder{
	
		TextView NomeRegione;
		TextView n_comuni;
	}
}