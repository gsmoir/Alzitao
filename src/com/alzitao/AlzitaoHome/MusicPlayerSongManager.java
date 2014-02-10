package com.alzitao.AlzitaoHome;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class MusicPlayerSongManager {
	
	final String MEDIA_PATH = new String("/sdcard/");
	private ArrayList<HashMap<String, String>> songList = new ArrayList<HashMap<String,String>>();
	
	//Constructor
	public MusicPlayerSongManager(){
		
	}
	
	/*Function to read all mp3 files and
	 * And store in ArrayList
	 *  */
	
	public ArrayList<HashMap<String , String>> getPlayList(){
		File home = new File(MEDIA_PATH);
		//List the files from directory filtered for mp3 by FileExtensionfilter
		if( home.listFiles(new FileExtensionFilter()).length > 0  ){
			for(File file : home.listFiles(new FileExtensionFilter())){ // For Each loop
				HashMap<String, String> song = new HashMap<String, String>();
				song.put("songTitle", file.getName().substring(0, file.getName().length() - 4));
				song.put("songPath", file.getPath());
				
				//Add to songList
				songList.add(song);
				
				
				
			}
		}
		return songList;
		
	}
	
	
	//Class for filtering mp3 files
	class FileExtensionFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String filename) {
			
			return (filename.endsWith(".mp3") || filename.endsWith(".MP3") );
		}
		
		
	}
	
}
