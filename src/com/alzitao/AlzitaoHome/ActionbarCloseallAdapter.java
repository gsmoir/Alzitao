package com.alzitao.AlzitaoHome;

//Not used

import com.alzitao.homescreen.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ActionbarCloseallAdapter extends MainActivity {

	String LOG_CALL = "LOGGING 123";
	public void setActionbarAsCloseAll(){
		//Customize action bar to get as a button to close all
				Log.i(LOG_CALL, "1");
				final ActionBar actionbar = getActionBar();
				Log.i(LOG_CALL, "2");
				actionbar.setDisplayShowHomeEnabled(false);
				Log.i(LOG_CALL, "3");
				actionbar.setDisplayShowTitleEnabled(false);
				Log.i(LOG_CALL, "4");
				final LayoutInflater lf = (LayoutInflater) getSystemService("layout_inflater");
				View view = lf.inflate(R.layout.actionbar_custom_layout, null);
				actionbar.setCustomView(view, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
				actionbar.setDisplayShowCustomEnabled(true);
		
	}
	
}
