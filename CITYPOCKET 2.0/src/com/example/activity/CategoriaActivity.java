/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 21/05/2015
 * Nome File: CITYPOCKET/CategoriaActivity.java
 * Versione : 1.0
 */




package com.example.activity;

import java.util.ArrayList;

import com.example.provacondatabase.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class CategoriaActivity  extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoria);
		
		String[] categoria= new String[] {"Ambiente Ed Ecologia","Attrazioni, monumenti e musei","Benzina","Biblioteca","Business e Shopping","Car/Bike Sharing","Eventi","Free WI-FI","Geolocalizzazione","Itinerari","Lavoro","Meteo","Mobilità","Pagamenti","Parcheggio","Ristorazione","Salute","Segnalazioni Da Parte Del Cittadino","Servizio Al Cittadino","Strutture per il pernottamento"," "};
		ArrayList <String> listp = new ArrayList<String>();  
        for (int i = 0; i < categoria.length; ++i) {  
             listp.add(categoria[i]);  
		
		ListAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categoria);	
		final ListView lv= (ListView) findViewById(R.id.ListaCategoria);
		lv.setAdapter(adapter);
		lv.setClickable(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	
              // ListView Clicked item index
             int itemPosition= position;
              
             // ListView Clicked item value
             String  itemValue = (String) lv.getItemAtPosition(position);
             
             switch(itemValue)
             {
             case "Servizio Al Cittadino":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Ambiente Ed Ecologia":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Biblioteca":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Parcheggio":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Segnalazioni Da Parte Del Cittadino":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Mobilità":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Itinerari":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Car/Bike Sharing":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Attrazioni, monumenti e musei":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Eventi":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Meteo":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Strutture per il pernottamento":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Ristorazione":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Geolocalizzazione":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Pagamenti":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Business e Shopping":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Benzina":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Free WI-FI":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Salute":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
             case "Lavoro":
            	 startActivity(new Intent("android.intent.action.RisultatoActivity"));
            	 break;
            	 
             }
             /*   startActivity(new Intent("android.intent.action.RegioneActivity"));
               Show Alert 
              Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
             */
          	  
          	
            }

       }); 
        }
  }
	/*	
		lv.setOnItemClickListener (new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id){
				TextView txtId = (TextView) 
						v.findViewById(R.id.ListaRegioni);
				
			 }
		});
        }
	}
		*/


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void risultato(View view){
		startActivity(new Intent("android.intent.action.RisultatoActivity"));
	}
	
}
