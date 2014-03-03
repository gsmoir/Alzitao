/*Uses/ extends ViewPager
 * Layout manager that allows the user to flip left and right through pages of data. 
 * You supply an implementation of a PagerAdapter to generate the pages that the view shows.
 * */

package com.alzitao.AlzitaoHome;

import java.util.ArrayList;

import com.alzitao.homescreen.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;



public class ImageGalleryAdapter extends PagerAdapter {
	
	public String LOG_TAG = "ImageGalleryAdapter" ;
	
	private Activity _activity;
	private ArrayList<String> _imagePathsArray;
	private LayoutInflater _inflater;
	
	//Context mContext;
	
	/*public ImageGalleryAdapter(Activity activity, ArrayList<String> imagePathsArray, Context c){

		Log.i(LOG_TAG, "ImageVA 4");
		this._activity = activity;
		this._imagePathsArray = imagePathsArray;
		mContext = c;
		
	}*/

	public ImageGalleryAdapter(Activity _activity2,
			ArrayList<String> imagePaths) {
		Log.i(LOG_TAG, "Adapter 1");
		this._activity = _activity2;
		this._imagePathsArray = imagePaths;
		//mContext = c;
	}

	
	@Override
	public int getCount() {

		Log.i(LOG_TAG, "Adapter 2");
		return this._imagePathsArray.size();
		
	}
	
	
	/*The method isViewFromObject(View, Object) identifies whether a page View is associated with a given key object.
	 * */
	@Override
	public boolean isViewFromObject(View view, Object object) {

		Log.i(LOG_TAG, "Adapter 3");
		return view == ((RelativeLayout) object);
	}
	
	
	
	/*InstantiateItem
	 * Create the page for the given position.
	 * The adapter is responsible for adding the view to the container given here, 
	 * although it only must ensure this is done by the time it returns from finishUpdate(ViewGroup).
	 * 
	 * Parameters
	 * container	The containing View in which the page will be shown.
	 * position	The page position to be instantiated.
	 * 
	 * Returns an Object representing the new page.
	 * */
	@Override
	public Object instantiateItem(ViewGroup container, int position){
		
		ImageView imgDisplay;
		
		Log.i(LOG_TAG, "Adapter 4");
		
		//test by giving default position as 1---future planning to randomize it
		//position = 1;
		
		//Get the inflater service for activity who called this adapter
		_inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		Log.i(LOG_TAG, "Adapter 5");
		//get the view by inflating the layout
		View viewLayout  = _inflater.inflate(R.layout.image_gallery_layout, container , false);
		Log.i(LOG_TAG, "adapter 6");
		
		//Get the Image layout for Full view
		imgDisplay = (ImageView) viewLayout.findViewById(R.id.full_layout);
		Log.i(LOG_TAG, "adapter 7");
		
		//To use in converting any image of large size for screen for efficiency
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		
		//Render image in small resolution to remove out of memory error
		bmOptions.inSampleSize = 1;
		
		bmOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Log.i(LOG_TAG, "adapter 8");
		Bitmap bitmap = BitmapFactory.decodeFile(_imagePathsArray.get(position), bmOptions);
		Log.i(LOG_TAG, "adapter 9");
		imgDisplay.setImageBitmap(bitmap);
		Log.i(LOG_TAG, "adapter 10");

		((ViewPager) container ).addView(viewLayout);
		Log.i(LOG_TAG, "adapter 11");
		
		/*
		ImageView iv = new ImageView(mContext);
		Bitmap BM = BitmapFactory.decodeFile (_imagePathsArray.get (position));
		iv.setImageBitmap(BM);
		((ViewPager)container).addView(iv,0);
		return iv;
		*/
	
		
		/*
		@Override
	    public void onPageSelected(int position) {
	        Log.d("Testing", TAG + " onPageSelected");
	    }

	        @Override
	        public boolean onInterceptTouchEvent(MotionEvent event) {
	            Log.d("Testing", "intercept");
	                return false;
	        }
		}
		*/
		
		
		
		
		return viewLayout;
		
		
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}

	
	
	
}
