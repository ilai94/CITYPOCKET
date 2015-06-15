/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 21/05/2015
 * Nome File: CITYPOCKET/ComuneActivity.java
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


public class ComuneActivity  extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comune);
		String[] Regioni= new String[] {"Nome_comune1","Nome_comune2", "Nome_comune3","Nome_comune4","Nome_comune5","Nome_comune6","Nome_comune7","Nome_comune8","Nome_comune9","...",};
		ArrayList <String> listp = new ArrayList<String>();  
        for (int i = 0; i < Regioni.length; ++i) {  
             listp.add(Regioni[i]);  
		
		ListAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Regioni);	
		final ListView lv= (ListView) findViewById(R.id.ListaComuni);
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
             case "Nome_comune1":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune2":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune3":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune4":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune5":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune6":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "Nome_comune7":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));            	 
            	 break;
             case "Nome_comune8":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));            	 
            	 break;
             case "Nome_comune9":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
            	 break;
             case "...":
            	 startActivity(new Intent("android.intent.action.TuristaActivity"));
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
	public void saltaATurista(View view){
		startActivity(new Intent("android.intent.action.TuristaActivity"));
	}
}
