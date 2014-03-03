package com.alzitao.AlzitaoHome;

import alzitao_home.helper.UtilsForImageGallery;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.alzitao.homescreen.R;


//Main Activity
public class MainActivity extends Activity {
	String LOG_TAG = "Sam Main";
	
	private UtilsForImageGallery utilsForImageGallery;
	private int gridWidth;
	private int padding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get the layout from layout XML, and display the view
		setContentView(R.layout.activity_main);
		
		//Calls function to get the red closeAll bar
		setActionbarAsCloseAll();
		
		//Set the menu layout
		setHomeMenu();
		
	}

	//Customize action bar to get as a button to close all
	public void setActionbarAsCloseAll(){
		
		final ActionBar actionbar = getActionBar();
		actionbar.setDisplayShowHomeEnabled(true);
		actionbar.setDisplayShowTitleEnabled(false);
				
		final LayoutInflater lf = (LayoutInflater) getSystemService("layout_inflater");
		View view = lf.inflate(R.layout.actionbar_custom_layout, null);
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
	
	
	
	/*Set layout(Menu & calling ) and adapter
	 * 
	 */
	public void setHomeMenu(){
		//Main menu designing
		GridView menu_gridview = (GridView) findViewById(R.id.menu_grid);
		
		
		InitializeGridLayout(menu_gridview);
		
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
						
						
				//For Image Gallery
				if(pos == 1){	
					Log.i(LOG_TAG, "pos 2");
					Intent imgIntent = new Intent(MainActivity.this, ImageGalleryActivity.class);
					Log.i(LOG_TAG, "pos 3");
					imgIntent.putExtra("id", pos);
					Log.i(LOG_TAG, "pos 4");
					startActivity(imgIntent);
				}
				
				
				//For Radio Player
				if(pos == 2){	
					Log.i(LOG_TAG, "rp1");
					Intent radioPlayer = new Intent();
					radioPlayer.setClassName("com.mediatek.FMRadio", "com.mediatek.FMRadio.FMRadioActivity");
					Log.i(LOG_TAG, "rp2");
					
					startActivity(radioPlayer);
					Log.i(LOG_TAG, "rp3");
				}
						
				
						
						
				//For Music Player
				if(pos == 3){	
					Log.i(LOG_TAG, "pos 4");
					Intent musicPlayerIntent = new Intent(MainActivity.this, MusicPlayerMainActivity.class);
					startActivity(musicPlayerIntent);
				}
				
				//For Piano
				if(pos == 4){	
					Log.i(LOG_TAG, "piano2");
					Intent piano = new Intent();
					piano.setClassName("com.htlvnapps.pianokid", "com.htlvnapps.pianokid.MenuActivity");
					Log.i(LOG_TAG, "piano1");
					
					startActivity(piano);
				}
				
				//For Puzzle
				if(pos == 5){	
					Log.i(LOG_TAG, "pzl1");
					Intent puzzle = new Intent();
					puzzle.setClassName("pl.heryan.sudoku", "pl.heryan.sudoku.Main");
					Log.i(LOG_TAG, "pzl2");
					
					startActivity(puzzle);
				}
				
						
			}
					
					
		});
	}
	
	
	//Set Grid Layout parameters, ie. basic column and padding sizes
	private void InitializeGridLayout(GridView menu_gridview){
		
		
		//Get gridview width
				utilsForImageGallery = new UtilsForImageGallery(this);
				
				//Dynamic defining columnwidth by getting screenwidth, minus padding , and divided into no. of columns
				gridWidth=  (int) ( (utilsForImageGallery.getScreenWidth() *2  ) / 9 );
				//columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_COL + 1) * padding ) ) / AppConstant.NUM_COL );
				
				padding = gridWidth/20;
		
		
		//Set GridView Layout Dynamically
				//Get the gridView
				menu_gridview.setColumnWidth(gridWidth);
				menu_gridview.setPadding((int) padding,(int) padding,(int) padding,(int) padding);
				//menu_gridview.setHorizontalSpacing((int) padding);
				//menu_gridview.setVerticalSpacing((int) padding);
		
	}
	
	
	
}
