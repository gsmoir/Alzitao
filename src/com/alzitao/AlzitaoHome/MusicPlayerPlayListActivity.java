package com.alzitao.AlzitaoHome;

import java.util.ArrayList;
import java.util.HashMap;

import com.alzitao.homescreen.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MusicPlayerPlayListActivity extends ListActivity {
	
	public ArrayList<HashMap<String, String>> songList = new ArrayList<HashMap<String,String>>();
	

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_player_playlist_layout);
		//ArrayList = dynamic sizeable Array according to list
		ArrayList<HashMap<String, String>> songListData = new ArrayList<HashMap<String,String>>();
		MusicPlayerSongManager plm = new MusicPlayerSongManager();
		
		//Get songs from SD CARD
		this.songList = plm.getPlayList();//Get song fxn in SongsManager
		
		//Looping through Playlist
		for(int i=0; i < songList.size(); i++){
			//New Hashmap
			HashMap<String, String> song = songList.get(i);
			//Add hashlist to arrayList
			songListData.add(song);
			
		}
		
		//Adding MenuItems to ListView--- SimpleAdapter=map static data to views defined in an XML file
		ListAdapter adapter = new SimpleAdapter(this, songListData, R.layout.music_player_playlist_item_layout,  new String[] {"songTitle"}, new int[] { R.id.songTitle});
		setListAdapter(adapter);//provide Cursor(random access) for the ListView
		
		//Selecting single ListView item Listening to listView Click
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				/* 
				 * TODO - - -Postion of song chooses here
				 * FOCUS here i fto remove playlist
				 * */
				
				//Getting listitem index
				int songIndex = position;
				
				//Starting new Intent
				Intent in = new Intent(getApplicationContext(), MusicPlayerMainActivity.class);
				
				//Sending songIndex to PlayerActivity
				in.putExtra("songIndex", songIndex);
				setResult(100, in);
				//Closing PlayLidtView
				finish();
				
			}
			
		});
		
		
	}
}
