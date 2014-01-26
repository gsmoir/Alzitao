package com.alzitao.AlzitaoHome;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.alzitao.homescreen.R;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setActionbarAsCloseAll();
		setHomeMenu();
	}

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		
		//getMenuInflater().inflate(R.menu.main, menu);
		//return true;
		
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_actions, menu);
		
		return super.onCreateOptionsMenu(menu);
		
	}  */

	
	
	public void setActionbarAsCloseAll(){
		//Customize action bar to get as a button to close all
				final ActionBar actionbar = getActionBar();
				actionbar.setDisplayShowHomeEnabled(true);
				actionbar.setDisplayShowTitleEnabled(false);
				
				final LayoutInflater lf = (LayoutInflater) getSystemService("layout_inflater");
				View view = lf.inflate(R.layout.custom_actionbar_layout, null);
				actionbar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				actionbar.setDisplayShowCustomEnabled(true);
				
				//Set to close all and comes to home
				Button bt = (Button) findViewById(R.id.antionbutton_closeAll);
				bt.setOnClickListener(new OnClickListener() {					
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
				});
				
		
	}
	
	
	
	public void setHomeMenu(){
		
		//Main menu designing
				GridView menu_gridview = (GridView) findViewById(R.id.menu_grid);
				menu_gridview.setAdapter(new MenuImageAdapter(this));
				
				
				//set onclick on Menu for different Activities
				menu_gridview.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View view, int pos,
							long id) {
						//Position 0 is for calling
						if(pos == 0){						
							Intent callIntent = new Intent(MainActivity.this, CallActivity.class);
							callIntent.putExtra("id", pos);
							startActivity(callIntent);
						}
						
					}
					
					
				});
	}
	
	
	
}
