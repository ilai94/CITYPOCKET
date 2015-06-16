package com.citypocket.interazione;
//classe scheletro per la row della categoria

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.provacondatabase.R;




public class CategoriaAdapter extends ArrayAdapter<Categoria>{

	Context context; //variabile che rappresenta l'activity dell'oggetto
	int layoutResourceId;//serve per adattare la dimensione della riga ala suo contenuto
	Categoria data[] = null; //data array di bottoni
	
	public CategoriaAdapter(Context context,int layoutResourceId,Categoria data[]){
		super(context,layoutResourceId,data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView(int position,View convertView,ViewGroup parent){//convert view riga attuale da creare
		View row = convertView;//riga corrente
		CategoriaHolder holder = null; //variabili bottoni
		if(row == null){ //righe inesistenti
			//crea lo scheletro della riga
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId,null);
			holder = new CategoriaHolder();
			holder.NomeCategoria = (TextView)row.findViewById(R.id.nome_categoria);
			row.setTag(holder);
			}
		else{
			//riga già esistente..carica lo scheletro già inizializzato
			holder = (CategoriaHolder)row.getTag();//
			}
		Categoria User = data[position];
		//associa alle variabile le stringhe dei bottoni
		holder.NomeCategoria.setText(User.Categoria);
		return row;
		}
	static class CategoriaHolder{
		TextView NomeCategoria;
		}
	}
