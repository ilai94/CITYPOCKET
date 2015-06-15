/**
 * Autori : Merola Sabrina, Pafundi Vincenzo, Russo Debora, Tecchia Elisabetta
 * Esame : Programmazione I
 * Facoltà : Ingegneria Informatica Federico II Napoli
 * Data : 21/05/2015
 * Nome File: CITYPOCKET/TuristaActivity.java
 * Versione : 1.0
 */




package com.example.activity;



import com.example.provacondatabase.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TuristaActivity extends ActionBarActivity {

	/*private DbAdapter dbHelper;
	private Cursor cursor;
	List listaCategoria = new ArrayList();*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_turista);
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
	public void si(View view){
		startActivity(new Intent("android.intent.action.RisultatoActivity"));
	}
	public void no(View view){
		startActivity(new Intent("android.intent.action.CategoriaActivity"));
	}
/*
	public void onResume(){
		listaCategoria = caricaCategoria();
	}
	private List caricaCategoria(){
		dbHelper = new DbAdapter(this);
		dbHelper.open();
		cursor = dbHelper.fetchAllCategoria();
		while ( cursor.moveToNext() ) {
			listaCategoria.add(cursor.getString( cursor. getColumnIndex(DbAdapter.KEY_LOCALITA) ));
			}
			cursor.close(); 
			dbHelper.close(); 
			return listaCategoria; 
			}*/

}
