/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 21/05/2015
 * Nome File: CITYPOCKET/RisultatoActivity.java
 * Versione : 1.0
 */




package com.citypocket.activity;

import java.util.ArrayList;

import com.example.provacondatabase.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RisultatoActivity extends ActionBarActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_risultato);
		String[] Regioni= new String[] {"Nome_app","Nome_app", "Nome_app","Nome_app","Nome_app","Nome_app","Nome_app","Nome_app","Nome_app","...",};
		ArrayList <String> listp = new ArrayList<String>();  
        for (int i = 0; i < Regioni.length; ++i) {  
             listp.add(Regioni[i]);  
		
		ListAdapter adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Regioni);	
		final ListView lv= (ListView) findViewById(R.id.ListaRisultato);
		lv.setAdapter(adapter);
        }
	}

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

}
