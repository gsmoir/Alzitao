package alzitao_home.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

//Utilities class for re usability for Photo gallery  
public class UtilsForPhoto {
	
	private Context _context;
	
	//Constructor
	public UtilsForPhoto(Context context){
		this._context = context;
		
	}

	//Reading file paths from SD card
	public ArrayList<String> getFilePaths(){
		//var declaration
		ArrayList<String> filePathArray = new ArrayList<String>();
		
		//declare file getting path from sd card
		File im_directory = new File(android.os.Environment.getExternalStorageDirectory(), "SpecialMoments" );// + File.separator + AppConstant.FILE_EXTN );
		
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
		
		//If directory not exists
		else {
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Error");
			alert.setMessage(AppConstant.PHOTO_ALBUM +" dir path is invalid! please set Image directory.");
			alert.setPositiveButton("OK", null);
			alert.show();
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
}
