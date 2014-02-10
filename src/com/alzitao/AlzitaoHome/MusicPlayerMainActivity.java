package com.alzitao.AlzitaoHome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.alzitao.homescreen.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayerMainActivity extends MainActivity implements OnCompletionListener{

	
	private String LOG_TAG = "mp";
	//Init Random var
	private Random rand = new Random();
	
	private ImageButton btnPlay;
	//private Button btnFwd ;
	//private Button btnBwd ;
	private ImageButton btnNxt;
	private ImageButton btnPrev;
	private ImageButton btnRpt ;
	private ImageButton btnMainNext;
	//private Button btnSfl ;
	//private Button btnPlaylist ;
	
	private TextView songTitleLabel;
	
	//Media Player
	public MediaPlayer mp;
	
	private MusicPlayerSongManager musicPlayerSongManager;
	private int currentSongIndex = 0;
	
	
	private boolean isShuffle = true;// Default song in shuffle mode
	private boolean isRepeat = false;
	
	private ArrayList <HashMap <String , String> > songsList = new ArrayList <HashMap < String, String> > ();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(LOG_TAG, "mp 1");
		
		setContentView(R.layout.music_player_layout);

		Log.i(LOG_TAG, "mp 2");
		
		//Actionbar custom to close all== Inherited from main activity
		setActionbarAsCloseAll();
		
		
		//Get buttons
		btnPlay = (ImageButton) findViewById(R.id.btnPlay) ;
		//btnFwd = (Button) findViewById(R.id.btnFwd) ;
		//btnBwd =(Button) findViewById(R.id.btnBwd) ;
		btnNxt = (ImageButton) findViewById(R.id.btnNext) ;
		btnPrev = (ImageButton) findViewById(R.id.btnPrevious) ;
		btnRpt = (ImageButton) findViewById(R.id.btRrpt) ;
		btnMainNext = (ImageButton) findViewById(R.id.btn_main_next) ;
		//btnSfl = (Button) findViewById(R.id.btSfl) ;
		//btnPlaylist = (Button) findViewById(R.id.btnPlaylist) ;
		
		//Get songtitle from layout
		songTitleLabel = (TextView) findViewById(R.id.songTitle);
		
		
		
		//MediaPlayer
		mp = new MediaPlayer();
		musicPlayerSongManager = new MusicPlayerSongManager();
		
		//Listeners
		mp.setOnCompletionListener(this);
		
		//Getting the songs list
		songsList = musicPlayerSongManager.getPlayList();
		
		//By Default play a Random song
		currentSongIndex = rand.nextInt( ( songsList.size() - 1 ) - 0 + 1 ) + 0;
		playSong(currentSongIndex);//PLAY
		
		
		
		/* 
		 * Play Button click Event
		 * Pauses or plays accordingly
		 * */
		
		btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Check already playing
				if (mp.isPlaying()){
					if(mp!=null){
						
						mp.pause();
						//Changing button Image to play button
						btnPlay.setImageResource(R.drawable.btn_play);
					}
					
				}
				else{
					//Resume song
					if(mp!= null){
						
						mp.start();
						//Change button image to pause
						btnPlay.setImageResource(R.drawable.btn_pause);
					}
				}
				
			}
		});
		
		
		/*On touch of the Main image button
		 * Set Random on and play next
		 * */
		btnMainNext.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//Set Shuffle anyway
				if(!isShuffle){
					isShuffle = true;
				}
				
				currentSongIndex = rand.nextInt( ( songsList.size() - 1 ) - 0 + 1 ) + 0;
				playSong(currentSongIndex);//PLAY
				
				
				
			}
			
			
		});
		
	}
	
	
	
	
	

	/* On a song complete
	 * If repeat ON, play same song
	 * else play next
	 * */	
	@Override
	public void onCompletion(MediaPlayer mp) {
		
		//Check Repeat ON
		if(isRepeat){
			
			playSong(currentSongIndex);
		}
		//Check If shuffle on
		else if(isShuffle){
			//Play Random song
			currentSongIndex = rand.nextInt( ( songsList.size() - 1 ) - 0 + 1 ) + 0;
			playSong(currentSongIndex);
		}
		//No repeat and shuffle
		else{
			//Not last song
			if(currentSongIndex< (songsList.size() - 1) ){
				playSong(currentSongIndex + 1);
				currentSongIndex = currentSongIndex +1 ; 
				
			}
			//Last Song
			else{
				//play first song
				playSong(0);
				currentSongIndex = 0;
			}
			
		}
		
	}
	
	
	//On Play click/invoke
	
	public void playSong(int songIndex){
		
		try{
			
			mp.reset();
			mp.setDataSource(songsList.get(songIndex).get("songPath"));
			mp.prepare();
			mp.start();
			
			//Displaying Song title
			String songTitle = songsList.get(songIndex).get("songTitle");
			songTitleLabel.setText(songTitle);
			
			btnPlay.setImageResource(R.drawable.btn_pause);
			
			
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
		
		/* Next button click event
		 * Plays next song by taking currentSongIndex + 1
		 * */
		
		btnNxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//If clicks here, consider wants just next, so set shuffle false
				if(isShuffle){					
					isShuffle = false;
				}
				
				//Check next song exists				
				if(currentSongIndex < (songsList.size() - 1 ) ){
					playSong(currentSongIndex +1);
					currentSongIndex = currentSongIndex +1 ;
					
				}
				
				else
				{
					playSong(0);
					currentSongIndex = 0; 
					
				}
				
			}
		});
		
		/* Back button click event
		* Plays previous song by currentSongIndex - 1
		* */
		btnPrev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				//If clicks here, consider wants just prev, so set shuffle false
				if(isShuffle){					
					isShuffle = false;
				}
				
				//Check song not the first				
				if(currentSongIndex > 0){
					playSong(currentSongIndex - 1);
					currentSongIndex = currentSongIndex - 1;
				}
				else{
					// play last song
					playSong(songsList.size() - 1);
					currentSongIndex = songsList.size() - 1;
				}
			}
		});
		
		/* Repeat button Click event
		 * Enables repeat flag true
		 * */
		
		btnRpt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(isRepeat){
					isRepeat = false;
					Toast.makeText(getApplicationContext(), "Repeat is off", Toast.LENGTH_SHORT).show();
					btnRpt.setImageResource(R.drawable.btn_repeat);
					
				}
				else{
					isRepeat = true;
					Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
					
					btnRpt.setImageResource(R.drawable.btn_repeat_focused);
					
				}
			}
		});
		
	}
	
	
	//Define what to do on Destroy life-cycle
	@Override
	public void onDestroy(){
		super.onDestroy();
		mp.release();
	}
	

}
