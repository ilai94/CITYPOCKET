package com.example.collegamento_database;
//classe scheletro per la row dell'utente

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.provacondatabase.R;




public class RegioneAdapter extends ArrayAdapter<Regione>{

Context context; //variabile che rappresenta l'activity dell'oggetto
int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
Regione data[] = null; //data array di bottoni

public RegioneAdapter(Context context,int layoutResourceId,Regione data[])
{super(context,layoutResourceId,data);
this.layoutResourceId = layoutResourceId;
this.context = context;
this.data = data;
}


@Override
public View getView(int position,View convertView,ViewGroup parent)//convert view riga attuale da creare
{
	

View row = convertView;//riga corrente
UserHolder holder = null; //variabili bottoni

if(row == null) //righe inesistenti
{
	//crea lo scheletro della riga
	LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	row = inflater.inflate(layoutResourceId,null);
	
	holder = new UserHolder();
	holder.NomeRegione = (TextView)row.findViewById(R.id.nome_regione);
	
	holder.n_comuni = (TextView)row.findViewById(R.id.n_comuni);
	
	row.setTag(holder);
	
}


else
{
	//riga gi� esistente..carica lo scheletro gi� inizializzato
	holder = (UserHolder)row.getTag();//
}


Regione User = data[position];
//associa alle variabile le stringhe dei bottoni
holder.NomeRegione.setText(User.Regione);
holder.n_comuni.setText(User.n_comuni);


return row;














}

static class UserHolder{
	
	TextView NomeRegione;
	
	
	TextView n_comuni;
	
	
	
	
}








}
