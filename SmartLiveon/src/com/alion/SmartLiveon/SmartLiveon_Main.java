package com.alion.SmartLiveon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SmartLiveon_Main extends Activity implements OnClickListener {

	static private String TAG = "SmartLiveon_MainActivity";
	
	//FTP Setting 
	public static String InstallPath = "/mnt/sdcard/allion";
	public static String LocalStoragePath = "/mnt/sdcard";
	public static String LocalExternalStoragePath = "/mnt/sdcard/external_sd";
	
	//Register Setting
	
	//SMS 
    public static final String SMS_LITE_PREFS_KEY = "sms_lite_prefs";
    public static final String PREF_KEY_NOTIFICATION_ID = "notification_id";
    
    static SmartLiveon_Main gBSmartLiveon_Main;

    public static SmartLiveon_Main getSmartLiveon_Main() {
        return gBSmartLiveon_Main;
    }
    
    // Each incoming sms gets its own notification. We have to use a new unique notification id
    // for each one.
    public int getNextNotificationId() {
        SharedPreferences prefs = getSharedPreferences(SMS_LITE_PREFS_KEY,
                Activity.MODE_PRIVATE);
        int notificationId = prefs.getInt(PREF_KEY_NOTIFICATION_ID, 0);
        ++notificationId;
        if (notificationId > 32765) {
            notificationId = 1;     // wrap around before it gets dangerous
        }
        // Save the updated notificationId in SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PREF_KEY_NOTIFICATION_ID, notificationId);
        editor.apply();

        Log.d(TAG, "getNextNotificationId: " + notificationId);

        return notificationId;
    }
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ClickListener_init(); // Listener 등록
		TopLayer_Init(); // top layer 설정
		Register_Init();
		gBSmartLiveon_Main = this;
	}

	public void ClickListener_init() {
		findViewById(R.id.btn_Settings).setOnClickListener(this);
		findViewById(R.id.btn_LiveView).setOnClickListener(this);
		findViewById(R.id.btn_LatestShockView).setOnClickListener(this);
		findViewById(R.id.btn_ShockViewList).setOnClickListener(this);
		findViewById(R.id.btn_Help).setOnClickListener(this);
	}

	public void TopLayer_Init() {
		LinearLayout contentsLayout = (LinearLayout) findViewById(R.id.ToptitleLayout);

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.inflection_top, contentsLayout, true);
		ImageView Iv_back = (ImageView) contentsLayout
				.findViewById(R.id.Back_btn);
		ImageView Iv_home = (ImageView) contentsLayout
				.findViewById(R.id.Home_btn);
		TextView Tv_title = (TextView) contentsLayout
				.findViewById(R.id.top_title_tv);
		Iv_back.setVisibility(View.INVISIBLE); // 버튼 감추기
		Iv_home.setVisibility(View.INVISIBLE); // 버튼 감추기
		Tv_title.setText(R.string.Main_title); // 타이틀 입력
	}
	
	public void Register_Init() {
		
 	  //TODO 나중에 확인할것.
	  /*	
	  String state = Environment.getExternalStorageState();
	  File externalstoragepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	  
	  try {

			File file = new File(InstallPath); 
			  
			if ( state.equals(Environment.MEDIA_MOUNTED)) {
			   
			    // TODO   1.프로그램 처음에 부팅후 First Register Setting
			if( !file.exists() )  // 원하는 경로에 폴더가 있는지 확인
			   file.mkdirs();
			    
			}else  if  (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) { 
			  //mExternalStorageAvailable = true; 
			  //mExternalStorageWriteable = false; 
			} else { 
			  Toast.makeText(SmartLiveon_Main.this, "NO SD Card ", Toast.LENGTH_SHORT).show();
			  //return  STATUS_NO_STORAGE; 
			} 
      
	  } catch (Exception e) {
		  
		   e.printStackTrace();
	  }
	  */
		
	}
      

	/*
	 * // 하단 세팅키 제거
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
 
		
		switch (v.getId()) {

		case R.id.btn_Settings:
			Intent intent = new Intent(SmartLiveon_Main.this,
					SmartLiveon_Settings.class);
			startActivity(intent);
			break;
		case R.id.btn_LiveView:
			intent = new Intent(SmartLiveon_Main.this, SmartLiveon_LiveView.class);
			startActivity(intent);
			break;
		case R.id.btn_LatestShockView:
			intent = new Intent(SmartLiveon_Main.this,
					SmartLiveon_LatestShockView.class);
			startActivity(intent);
			break;
		case R.id.btn_ShockViewList:
			intent = new Intent(SmartLiveon_Main.this, SmartLiveon_ShockViewList.class);
			startActivity(intent);
			break;
		case R.id.btn_Help:
			intent = new Intent(SmartLiveon_Main.this, SmartLiveon_Help.class);
			startActivity(intent);
			break;
		default:
			Log.i(TAG, "click event error");
			break;
		}

	}

}
