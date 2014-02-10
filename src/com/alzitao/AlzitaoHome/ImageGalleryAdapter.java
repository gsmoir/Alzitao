package com.alzitao.AlzitaoHome;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alzitao.homescreen.R;


public class ImageGalleryAdapter extends PagerAdapter {
	
	public String LOG_TAG = "Image test" ;
	
	private Activity _activity;
	private ArrayList<String> _imagePathsArray;
	private LayoutInflater _inflater;
	
	Context mContext;
	
	/*public ImageGalleryAdapter(Activity activity, ArrayList<String> imagePathsArray, Context c){

		Log.i(LOG_TAG, "ImageVA 4");
		this._activity = activity;
		this._imagePathsArray = imagePathsArray;
		mContext = c;
		
	}*/

	public ImageGalleryAdapter(Activity _activity2,
			ArrayList<String> imagePaths, ImageViewer c) {
		Log.i(LOG_TAG, "ImageVA 4");
		this._activity = _activity2;
		this._imagePathsArray = imagePaths;
		mContext = c;
	}

	@Override
	public int getCount() {

		Log.i(LOG_TAG, "ImageVA 5");
		return this._imagePathsArray.size();
		
	}
	
	@Override
	public boolean isViewFromObject(View view, Object object) {

		Log.i(LOG_TAG, "ImageVA 6");
		return view == ((RelativeLayout) object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position){

		/*
		
		ImageView imgDisplay;
		
		Log.i(LOG_TAG, "ImageVA 6");
		//test by giving default position as 1---future planning to randomize it
		position = 1;
		
		//Get the inflater service
		_inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		//get the view by inflating the layout
		View viewLayout  = _inflater.inflate(R.layout.image_gallery_fragment, container , false);
		Log.i(LOG_TAG, "5");
		
		imgDisplay = (ImageView) viewLayout.findViewById(R.id.photo_pager);
		Log.i(LOG_TAG, "6");
		
		//To use in converting any image of large size for screen for efficiency
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
		
		Bitmap bitmap = BitmapFactory.decodeFile(_imagePathsArray.get(position), bmOptions);
		
		imgDisplay.setImageBitmap(bitmap);
		Log.i(LOG_TAG, "7");
		((ViewPager) container ).addView(viewLayout);
		Log.i(LOG_TAG, "8");
		
		*/
		
		
		ImageView iv = new ImageView(mContext);
		Bitmap BM = BitmapFactory.decodeFile (_imagePathsArray.get (position));
		iv.setImageBitmap(BM);
		((ViewPager)container).addView(iv,0);
		return iv;
		
		
		
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}

}
