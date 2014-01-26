/*This class is intended for designing HomeScreen menu image settings */
package com.alzitao.AlzitaoHome;

import com.alzitao.homescreen.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MenuImageAdapter extends BaseAdapter{

	String LOG_TAG = "123";
	
	private Context mContext;
	
	
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
		
		if(convertView == null){ //that is not recycled/new, initialize some attributes
			imageView =  new ImageView(mContext);
			imageView.setLayoutParams( new GridView.LayoutParams(210, 210) );
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(7, 7, 7, 7);
			//imageView.setBackgroundResource(R.drawable.stroke);//Was trying to cutomize menu bg
			
			
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
