package com.skt.omp.m2mserviceplatform;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.skt.omp.m2mserviceplatform.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.util.Base64;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;
	
	/**
	 * The TAG for this activity.
	 */
	private static final String APP_TAG = "m2mserviceplatform";
	
	private static final String APP_URL = "211.115.15.157:10005";
	private static final String sclsRetrieve_url = "http://211.115.15.157:10005/smartlive/scls";
	
	private static final String APP_NAME = "smartliveon";
	private static final String APP_SCL_BASE = "smartlive";
	
	private static final String APP_ID = "AP10001255";
	private static final String APP_KEY = "AP10001255";
	
	public static String APP_IDKEY_BASE64 = "";
	
    
   /**
    * Base64 인코딩
    */
   public static String getBase64encode(String content){
       return Base64.encodeToString(content.getBytes(), 0);
   }
    
   /**
    * Base64 디코딩
    */
   public static String getBase64decode(String content){
       return new String(Base64.decode(content, 0));
   }
   
   
	

	//		1 	SC10004489 	AIRJET#01 	01031962223 	GMMP 	allion 	01031962223
	// 	http://211.115.15.157:10004/openapi/apitest/testAction

	 /*
	public static void downloadFileWithAuth(String urlStr, String user, String pass, String outFilePath) {
	    try {
	        // URL url = new URL ("http://ip:port/download_url");
	        URL url = new URL(urlStr);
	        String authStr = user + ":" + pass;
	        String authEncoded = Base64.encodeBytes(authStr.getBytes());

	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Authorization", "Basic " + authEncoded);

	        File file = new File(outFilePath);
	        InputStream in = (InputStream) connection.getInputStream();
	        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
	        for (int b; (b = in.read()) != -1;) {
	            out.write(b);
	        }
	        out.close();
	        in.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	*/
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);

		/*
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		*/
		
        try {
        	
        	APP_IDKEY_BASE64 = M2mServicePlatform_Base64.encodeBytes ((APP_ID + ":" + APP_KEY).getBytes());
        	Log.d(APP_TAG, "-APP_IDKEY         " + APP_ID + ":" + APP_KEY);
        	Log.d(APP_TAG, "-APP_IDKEY_BASE64: " + APP_IDKEY_BASE64);
           System.out.println("----------------------------------------");
          // System.out.println(response.getStatusLine());
        	
           
        	HttpClient client = new DefaultHttpClient();  
        	String getURL = sclsRetrieve_url;
        	
        	HttpGet httpget = new HttpGet(getURL);
        	httpget.addHeader("Authorization", "Basic " + APP_IDKEY_BASE64);
       	//httpget.setHeader("Authorization", APP_IDKEY_BASE64);
        	//httpget.setHeader("Content-Type", "application/xml");
        
        	
        	HttpResponse responseGet = client.execute(httpget);
        	int code = responseGet.getStatusLine().getStatusCode();
        	Log.d(APP_TAG, "-Result code: " + code);
        	
        	HttpEntity resEntityGet = responseGet.getEntity();
        	        
        	if (resEntityGet != null)
        	{  
        	    Log.i("RESPONSE", EntityUtils.toString(resEntityGet));
        	    
        	    //reader = new BufferedReader( new InputStreamReader( resEntityGet.getContent()));
        	    //int read;
              // char[] buff = new char[1024];
              // while( (read = reader.read(buff) ) != -1){
              //    sb.append(buff, 0, read);
                // }
             	//Log.d(APP_TAG, sb.toString());
             	
        	}
        	
        	
       // 	HttpClient httpClient = new DefaultHttpClient(); 

          //HttpGet httpGet = new HttpGet("http://www.naver.com"); -> ok
          //HttpGet httpGet = new HttpGet("http://211.115.15.157:10005"); -> ok
       //   HttpGet httpGet = new HttpGet("http://211.115.15.157:10005/smartlive/scls");
          
        //  HttpResponse httpResponse = httpClient.execute(httpGet); 
        //  System.out.println(EntityUtils.toString(httpResponse.getEntity()));
              
         
          /*
          String encoding = Base64Encoder.encode ("test1:test1");
          HttpPost httppost = new HttpPost("http://host:post/test/login");
          httppost.setHeader("Authorization", "Basic " + encoding);

          System.out.println("executing request " + httppost.getRequestLine());
          HttpResponse response = httpclient.execute(httppost);
          HttpEntity entity = response.getEntity();
          */
        	
        	
        	
            
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(APP_TAG, "Error: " + e.getMessage());
        }
        
		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					// Cached values.
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							// If the ViewPropertyAnimator API is available
							// (Honeycomb MR2 and later), use it to animate the
							// in-layout UI controls at the bottom of the
							// screen.
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							// If the ViewPropertyAnimator APIs aren't
							// available, simply show or hide the in-layout UI
							// controls.
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							// Schedule a hide().
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});

		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});

		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		findViewById(R.id.dummy_button).setOnTouchListener(
				mDelayHideTouchListener);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	
	
	public boolean isNetworkAvailable() {
		
		//    ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		//    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		    
		  //  if (networkInfo != null && networkInfo.isConnected()) {
		//    return true;
		//}
	    
	    return false;
	} 
	
	
}






