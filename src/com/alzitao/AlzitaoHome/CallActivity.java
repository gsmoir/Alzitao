package com.alzitao.AlzitaoHome;

import com.alzitao.homescreen.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class CallActivity extends MainActivity {
	
	String LOG_TAG = "123";
	String telNo = "123";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.calling_layout);
		
		
		//Actionbar custom to close all== Inherited from main activity
		setActionbarAsCloseAll();
		
		//Set the images of contacts to grid view
		setContactMenu();
		
		
	}
	
	
	public void setContactMenu(){
		
		//Contacts menu designing
				
		GridView contact_gridview = (GridView) findViewById(R.id.contact_grid);	
		contact_gridview.setAdapter(new MenuImageAdapter(this));
				
		// Call PhoneStateListener to listen to calling activities
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		tm.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		
		
		
		contact_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				
				getNumber(pos);
				
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+telNo));
				Log.i(LOG_TAG, "Test call1");
				startActivity(callIntent);
				
			}
		});
				
		//Following code is added to Close this app if call is finished
		if (getIntent().getBooleanExtra("EXIT", false)) {
	         finish();
	    }
				
	}
	

	//Get Telephone number for each grid
	public String getNumber( int pos){
		
		if(pos == 0){
			
			telNo = "09551850800";
		}
		
		else if(pos == 1){
			
			telNo = "07200121928";
		}
		else if(pos == 2){
			
			telNo = "11111111111";
			
		}
		else if(pos == 3){
			
			telNo = "1111122222";
		}
		else if(pos == 4){
			
			telNo = "22222222222";
		}
		else if(pos == 5){
			
			telNo = "33333333333";
		}
		
		return telNo;
		
	}
	
	
	//monitor phone call activities
		private class PhoneCallListener extends PhoneStateListener{
			
			private Boolean isCalling = false;
			String LOG_CALL = "LOGGING 123";
			
			@Override 
			public void onCallStateChanged(int state, String incomingNumber){
				
				
				if(TelephonyManager.CALL_STATE_RINGING == state){
					// phone ringing
					Log.i(LOG_CALL, "Ringing No:" +incomingNumber);
					
				}
				if(TelephonyManager.CALL_STATE_OFFHOOK == state){
					// active
					Log.i(LOG_CALL, "Offhook");
					isCalling = true;
					
				}
				
				if(TelephonyManager.CALL_STATE_IDLE == state){
					Log.i(LOG_CALL, "IDLE");
					// run when class initial and phone call ended,
					// need detect flag from CALL_STATE_OFFHOOK
					
					if(isCalling){
						
						Log.i(LOG_CALL, "Restart App");
						
						Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						i.putExtra("EXIT", true);
						startActivity(i);
						
						isCalling = false;
						
					}
					
				}
				
			}
		}
	
	
	
	

}


