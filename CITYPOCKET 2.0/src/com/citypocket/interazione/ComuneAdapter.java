/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/CategoriaAdapter.java
 * Versione : 2.0
 */

package com.citypocket.interazione;
//classe scheletro per la row della categoria

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.citypocket.R;




public class ComuneAdapter extends ArrayAdapter<Comune>{

	Context context; //variabile che rappresenta l'activity dell'oggetto
	int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
	Comune data[] = null; //data array di bottoni
	
	public ComuneAdapter(Context context,int layoutResourceId,Comune data[]){
		super(context,layoutResourceId,data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView(int position,View convertView,ViewGroup parent){//convert view riga attuale da creare
		View row = convertView;//riga corrente
		ComuneHolder holder = null; //variabili bottoni
		if(row == null){ //righe inesistenti
			//crea lo scheletro della riga
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId,null);
			holder = new ComuneHolder();
			holder.NomeComune = (TextView)row.findViewById(R.id.nome_comune);
			row.setTag(holder);
			}
		else{
			//riga già esistente..carica lo scheletro già inizializzato
			holder = (ComuneHolder)row.getTag();//
			}
		Comune User = data[position];
		//associa alle variabile le stringhe dei bottoni
		holder.NomeComune.setText(User.Comune);
		return row;
		}
	static class ComuneHolder{
		TextView NomeComune;
		}
	}
