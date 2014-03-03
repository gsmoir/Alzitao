/*This class is intended for designing HomeScreen menu image settings */
package com.alzitao.AlzitaoHome;

import alzitao_home.helper.UtilsForImageGallery;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.alzitao.homescreen.R;

public class MenuImageAdapter extends BaseAdapter{

	String LOG_TAG = "123";
	
	private Context mContext;
	private UtilsForImageGallery utilsForImageGallery;
	private int gridWidth;
	private int padding;
	private GridView gridView;
	
	public MenuImageAdapter(Context c){
		
		mContext = c;
	}
	
	
	
	
	@Override
	public int getCount() {
		Log.i(LOG_TAG, "3");

		int iconsCount = 0;
		Log.i(LOG_TAG, "Test1");
		if(mContext.getClass().getSimpleName().equals("MainActivity")){ // If called for Menu
			
			iconsCount = mMenuThumbimages.length;
			
		}
		
		else if(mContext.getClass().getSimpleName().equals("CallActivity")){ // If called for Call Icons
			Log.i(LOG_TAG, "Test2");
			iconsCount = mContactThumbimages.length;
			
		}
		
		return iconsCount;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	//Create new imageview for each item referenced by the adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView imageView;
		Log.i(LOG_TAG, "4");
		
		
		//Get gridview width
		utilsForImageGallery = new UtilsForImageGallery( mContext );
		
		//Dynamic defining columnwidth by getting screenwidth, minus padding , and divided into no. of columns
		gridWidth=  (int) ( (utilsForImageGallery.getScreenWidth() ) / 5 );
		//columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_COL + 1) * padding ) ) / AppConstant.NUM_COL );
		
		padding = gridWidth/10;
		
			
		if(convertView == null){ //that is not recycled/new, initialize some attributes
			
			
			imageView =  new ImageView(mContext);
			
			imageView.setLayoutParams( new GridView.LayoutParams(gridWidth, gridWidth) );
			
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			
			imageView.setPadding(padding,padding,padding,padding);
			
		}
		else{
			Log.i(LOG_TAG, "5");
			
			imageView = (ImageView) convertView;
		}
		
		
		//Set  Image Resource for the icons from the Image Array 
		if(mContext.getClass().getSimpleName().equals("MainActivity")){ // If called for Menu
		
			imageView.setImageResource(mMenuThumbimages[position]);
			
		}
		
		else if(mContext.getClass().getSimpleName().equals("CallActivity")){ // If called for Call Icons
			Log.i(LOG_TAG, "6");
			imageView.setImageResource(mContactThumbimages[position]);
			
		}
		
		return imageView;
	}
	//Image array for HomeScreen Icons
	private Integer[] mMenuThumbimages = {
			R.drawable.ic_menu_phone,
			R.drawable.ic_menu_photo,
			R.drawable.ic_menu_radio,
			R.drawable.ic_menu_tape,
			R.drawable.ic_menu_piano,
			R.drawable.ic_menu_puzzle,
			
	};
	
	//Image Array for Contact Images
	private Integer[] mContactThumbimages = {
			R.drawable.ic_call_rajni,
			R.drawable.ic_call_sam,
			R.drawable.ic_call_lauren,
			R.drawable.ic_call_kalam,
			
			
								
	};
	
	

}
