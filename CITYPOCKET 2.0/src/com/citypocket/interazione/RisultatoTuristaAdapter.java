/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/RegioneAdapter.java
 * Versione : 2.0
 */

package com.citypocket.interazione;
//classe per la row del risultato

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citypocket.R;




public class RisultatoTuristaAdapter extends ArrayAdapter<RisultatoTurista>{

	Context context; //variabile che rappresenta l'activity dell'oggetto
	int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
	RisultatoTurista data[] = null; //data array di bottoni

	public RisultatoTuristaAdapter(Context context,int layoutResourceId,RisultatoTurista data[])
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
		RisultatoTuristaHolder holder = null; //variabili bottoni

		if(row == null) //righe inesistenti
		{
			//crea lo scheletro della riga
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId,null);
			holder = new RisultatoTuristaHolder();
			holder.RisTur = (TextView)row.findViewById(R.id.nome_app_turista_risultato);
			row.setTag(holder);
			
		}
		else
		{
			//riga già esistente..carica lo scheletro già inizializzato
			holder = (RisultatoTuristaHolder)row.getTag();//
		}
		RisultatoTurista User = data[position];
		//associa alle variabile le stringhe 
		holder.RisTur.setText(User.RisultatoTurista);
		
		return row;

	}

	static class RisultatoTuristaHolder{
	
		TextView RisTur;
		
	}
}