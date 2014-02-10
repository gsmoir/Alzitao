package com.alzitao.AlzitaoHome;

import java.util.ArrayList;

import alzitao_home.helper.UtilsForPhoto;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.alzitao.homescreen.R;


public class ImageViewer extends MainActivity{
	
	private String LOG_TAG = "ImageViewer";
	Activity _activity;
	int _position;
	
	private UtilsForPhoto utils;
	private ArrayList<String> imagePaths = new ArrayList<String>();
	private ImageGalleryAdapter imageAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_viewer_layout);
		
		Log.i(LOG_TAG, "ImageV 1");
		//Set the common Actionbar
		setActionbarAsCloseAll();
		
		utils = new UtilsForPhoto(this);
		imagePaths = utils.getFilePaths();
		Log.i(LOG_TAG, "ImageV 2");
		imageAdapter = new ImageGalleryAdapter(_activity, imagePaths, this);
		
		Log.i(LOG_TAG, "ImageV 3");
		//showImages();
	
		//Test
		ViewPager view = (ViewPager) findViewById(R.id.photo_pager);
		Log.i(LOG_TAG, "ImageV 4");
		view.setAdapter(imageAdapter);
		
		
	}
	

	/*/Call the ImageGalleryAdapter for enabling image display and swipe
	public void showImages(){

		Log.i(LOG_TAG, "ImageV 3");
		Intent intent = new Intent(ImageViewer.this, ImageGalleryAdapter.class);
		
		intent.putExtra("position", _position);
		startActivity(intent);
		
	}*/

}
