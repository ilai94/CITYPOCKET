/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 19/06/2015
 * Nome File: CITYPOCKET/RegioneActivity.java
 * Versione : 2.0
 */


package com.citypocket.activity;

import com.citypocket.R;
import com.citypocket.interazione.DataRecoveryRegione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;




public class RegioneActivity  extends Activity{
	
	public static final String username = "b63af9d70c3f90";
	public static final String password = "e242e43a";
	public static ListView listview1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regione);
		listview1 = (ListView)findViewById(R.id.ListaRegioni);
		DataRecoveryRegione.Data(this).execute();
		listview1.setClickable(true);
		
		listview1.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				startActivity(new Intent("android.intent.action.ComuneActivity"));
			  }
			}); 

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
	public void saltaACategoria(View view){
		startActivity(new Intent("android.intent.action.CategoriaActivity"));
	}
	
	
}
