package alzitao_home.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

//Utilities class for re usability for Photo gallery  
public class UtilsForImageGallery {
	
	private Context _context;
	private AppConstant appConstant;
	
	private String LOG_TAG = new String("image_utils");
	
	//Constructor
	public UtilsForImageGallery(Context context){
		this._context = context;
		
	}

	//Reading file paths from SD card
	public ArrayList<String> getFilePaths(){
		//var declaration
		ArrayList<String> filePathArray = new ArrayList<String>();
		
		Log.i(LOG_TAG, "1");
		
		//declare file getting path from sd card
		File im_directory = new File(android.os.Environment.getExternalStorageDirectory() + File.separator + appConstant.PHOTO_ALBUM); 
		

		//Check for directory
		if(im_directory.isDirectory() ){
			
			File[] listFiles = im_directory.listFiles();
			
			//Check for Count
			if(listFiles.length>0){

				//loop through the files
				for(int i=0; i<listFiles.length; i++){
					//get Filepath
					String filePath = listFiles[i].getAbsolutePath();
					
					//Check support of File extension, IsSupported function below
					if(IsSupportedFile(filePath)){
						//add image path to array
						filePathArray.add(filePath);
						
					}
					
				}
			}
			
			else{
				//Give instruction if directory is empty			
				Toast.makeText(_context, AppConstant.PHOTO_ALBUM +"is Empty. Add iamges.",Toast.LENGTH_SHORT).show();
			}
		}
		
		//If its not directory
		else {
			
			Toast.makeText(_context, AppConstant.PHOTO_ALBUM +"Does not exists. Please create the folder and add imges.",Toast.LENGTH_LONG).show();
			
			/*This part needs revision
			 * not working
			 * see if it can be used to close activity when error occurs
			 * so that we can avoid Exception handling
			 * 
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Error");
			alert.setMessage(AppConstant.PHOTO_ALBUM +" dir path is invalid! please set Image directory.");
			alert.setPositiveButton("OK", null);
			alert.show();
			*/
			
		}
		
		
		
		
		return filePathArray;		
		
		}
	
	//Function to check supported file extension
	private boolean IsSupportedFile(String filePath){
		//get the extention part
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1 ), filePath.length());
		//Locale.getDefault is used for local language support
		if(AppConstant.FILE_EXTN.contains(ext.toLowerCase(Locale.getDefault()) )){
					
			return true;
		}
		else{
			return false;				
		}
	}
	
	
	
	public int getScreenWidth(){
		
		int columnWidth;
		
		WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		final Point point = new Point();
		
		try{
			display.getSize(point);
			
			
		}
		catch (java.lang.NoSuchMethodError ignore){
			//older devices
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		

		//Get the width in landscape mode only for consistent grid size
		if(point.x > point.y){
			columnWidth = point.x;
		}
		else{
			columnWidth = point.y;
		}
		
		
		//columnWidth = display.getHeight();
		return columnWidth;
	}
	
}
