/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/RegioneAdapter.java
 * Versione : 2.0
 */

package com.citypocket.interazione;
//classe scheletro per la row dell'utente

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citypocket.R;




public class RisultatoAdapter extends ArrayAdapter<Risultato>{

	Context context; //variabile che rappresenta l'activity dell'oggetto
	int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
	Risultato data[] = null; //data array di bottoni

	public RisultatoAdapter(Context context,int layoutResourceId,Risultato data[])
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
		RisultatoHolder holder = null; //variabili bottoni

		if(row == null) //righe inesistenti
		{
			//crea lo scheletro della riga
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId,null);
			holder = new RisultatoHolder();
			holder.Ris = (TextView)row.findViewById(R.id.nome_app_risultato);
			row.setTag(holder);
			
		}
		else
		{
			//riga gi� esistente..carica lo scheletro gi� inizializzato
			holder = (RisultatoHolder)row.getTag();//
		}
		Risultato User = data[position];
		//associa alle variabile le stringhe dei bottoni
		holder.Ris.setText(User.Risultato);
		
		return row;

	}

	static class RisultatoHolder{
	
		TextView Ris;
		
	}
}