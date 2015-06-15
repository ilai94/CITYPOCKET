/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 21/05/2015
 * Nome File: CITYPOCKET/RegioneActivity.java
 * Versione : 1.0
 */



package com.example.activity;

import com.example.collegamento_database.UserDataAsync;
import com.example.collegamento_database.dataRecoveryRegione;
import com.example.provacondatabase.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class RegioneActivity  extends Activity{
	
	public static final String username = "b63af9d70c3f90";
	public static final String password = "e242e43a";
	public static ListView listview1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regione);
		 getUserData(); //Recupero dei dati utente
			//Carica gli utenti della Community
			
			listview1 = (ListView)findViewById(R.id.ListaRegioni);
			
			dataRecoveryRegione.Data(this).execute();
		
		/*String[] Regioni= new String[] {"Abruzzo", "Basilicata","Calabria","Campania","Emilia-Romagna","Friuli-Venezia Giulia","Lazio","Liguria","Lombardia","Marche","Molise","Piemonte","Puglia","Sardegna","Sicilia","Toscana","Trentino-Alto Adige","Umbria","Valle d’Aosta","Veneto"," "};
		ArrayList <String> listp = new ArrayList<String>();  
        for (int i = 0; i < Regioni.length; ++i) {  
             listp.add(Regioni[i]);  
		
		ListAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Regioni);	
		final ListView lv= (ListView) findViewById(R.id.ListaRegioni);
		lv.setAdapter(adapter);
		lv.setClickable(true);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	
              // ListView Clicked item index
             int itemPosition     = position;
              
             // ListView Clicked item value
             String  itemValue = (String) lv.getItemAtPosition(position);
             
             switch(itemValue)
             {
             case "Basilicata":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Abruzzo":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Calabria":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Campania":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Emilia-Romagna":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Friuli-Venezia Giulia":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Lazio":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Liguria":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));            	 
            	 break;
             case "Lombardia":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));            	 
            	 break;
             case "Marche":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Molise":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Piemonte":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Puglia":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Sardegna":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Sicilia":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Toscana":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Trentino-Alto Adige":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Umbria":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Valle d’Aosta":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
             case "Veneto":
            	 startActivity(new Intent("android.intent.action.ComuneActivity"));
            	 break;
            	 
             }
            
          	  
          	
            }

       });
        }*/
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
	public void saltaACategoria(View view){
		startActivity(new Intent("android.intent.action.CategoriaActivity"));
	}
	private void getUserData()
	{
		UserDataAsync u = new UserDataAsync(this);
		u.execute();
		
	}
	
}
