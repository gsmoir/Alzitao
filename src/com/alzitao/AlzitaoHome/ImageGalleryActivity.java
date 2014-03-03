package com.alzitao.AlzitaoHome;

import com.alzitao.homescreen.R;

import alzitao_home.helper.UtilsForImageGallery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ImageGalleryActivity extends MainActivity {

	
	private String LOG_TAG = "Image Gallery";
	
	private UtilsForImageGallery utilsForImageGallery;
	private ImageGalleryAdapter adapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Log.i(LOG_TAG, "Activity 1");
		
		super.onCreate(savedInstanceState);
		//Pager layout
		Log.i(LOG_TAG, "Activity 2");
		setContentView(R.layout.image_gallery_activity);
		

		Log.i(LOG_TAG, "Activity 3");
		viewPager = (ViewPager) findViewById(R.id.image_pager);
		Log.i(LOG_TAG, "Activity 4");
		utilsForImageGallery = new UtilsForImageGallery(getApplicationContext());
		Log.i(LOG_TAG, "Activity 5");
		Intent i = getIntent();
		//int position = i.getIntExtra("position", 0);

		Log.i(LOG_TAG, "Activity 6");
		int position = 1;
		Log.i(LOG_TAG, "Activity 7");
		adapter = new ImageGalleryAdapter(ImageGalleryActivity.this, utilsForImageGallery.getFilePaths());
		Log.i(LOG_TAG, "Activity 8");
		viewPager.setAdapter(adapter);
		Log.i(LOG_TAG, "Activity 9");
		// displaying selected image first
		viewPager.setCurrentItem(position);
		Log.i(LOG_TAG, "Activity 10");
	}
	
}
