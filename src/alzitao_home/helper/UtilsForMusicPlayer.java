package alzitao_home.helper;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import alzitao_home.helper.AppConstant;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UtilsForMusicPlayer {
	
	
	private ArrayList<HashMap<String, String>> songList = new ArrayList<HashMap<String,String>>();
	
	private AppConstant appConstant;
	
	private String LOG_TAG = new String("MPMan");
	
	private Context _context;
	
	final String MEDIA_PATH = new String(appConstant.SONG_DIRECTORY);
	
	//Constructor
	public UtilsForMusicPlayer(Context context){
		this._context = context;
	}
	
	
	/*/Constructor
	public MusicPlayerSongManager(){
		
	}*/
	
	/*Function to read all mp3 files and
	 * And store in ArrayList
	 *  */
	
	public ArrayList<HashMap<String , String>> getPlayList(){
	
		
		Log.i(LOG_TAG, "mpman 1");
		
		File home = new File( android.os.Environment.getExternalStorageDirectory() + File.separator + MEDIA_PATH);
		
		Log.i(LOG_TAG, "mpman 2");
		
		if(home.isDirectory()){
			Log.i(LOG_TAG, "mpman 3");
			//List the files from directory filtered for mp3 by FileExtensionfilter
			if( home.listFiles(new FileExtensionFilter()).length > 0  ){
				for(File file : home.listFiles(new FileExtensionFilter())){ // For Each loop
					HashMap<String, String> song = new HashMap<String, String>();
					song.put("songTitle", file.getName().substring(0, file.getName().length() - 4));
					song.put("songPath", file.getPath());
					
					//Add to songList
					songList.add(song);
	
				}
				
			}else{
				Log.i(LOG_TAG, "mpman 4");
					
				Toast.makeText(_context, AppConstant.SONG_DIRECTORY +"is Empty. Add Songs.",Toast.LENGTH_SHORT).show();		
				}
			
		}else{
			Log.i(LOG_TAG, "mpman 5");
			Toast.makeText(_context, AppConstant.PHOTO_ALBUM +"Does not exists. Please create the folder and add imges.",Toast.LENGTH_LONG).show();
						
			Log.i(LOG_TAG, "mpman6");
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
