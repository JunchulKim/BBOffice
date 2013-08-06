package com.skt.omp.m2mserviceplatform;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.skt.omp.m2mserviceplatform.M2mServicePlatform_Base64.InputStream;
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

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;



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
	
	 FTPClient ftpClient = null;

	 File file = null;
	 FTPFile[] files = null;
	 
	 FileInputStream    fin        = null;
	 FileOutputStream   fos        = null;        // File Output Stream
	 
	 File   localfile  = new File("/mnt/sdcard/watermark.jpg");
	 String remoteFile = "/watermark.jpg";
	 
	 public static String directoryName = "/mnt/sdcard";
	 public static String mPath = "Client";
	 
	 
	 
     String server = "114.31.34.220";
     //int port = 21;
     int port = 10021;
     
     String user = "allion";
     String pass = "aadmin0";
     
    // ftpClient.connect("114.31.34.220",10021);   
    // ftpClient.setControlEncoding("UTF-8");  // KOR 
     
   //  ftpClient.login("allion", "!aadmin0");
     
	 
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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fullscreen);

		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
		
        try {
        	
        	APP_IDKEY_BASE64 = M2mServicePlatform_Base64.encodeBytes ((APP_ID + ":" + APP_KEY).getBytes());
        	Log.d(APP_TAG, "-APP_IDKEY         " + APP_ID + ":" + APP_KEY);
        	Log.d(APP_TAG, "-APP_IDKEY_BASE64: " + APP_IDKEY_BASE64);
        	
          System.out.println("----------------------------------------");
		   System.out.println("*****   FTP TEST   ****");
	      System.out.println("----------------------------------------");
	          
        	 
 		   ftpClient = new FTPClient();
 		       
 		   FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
  		   ftpClient.configure(conf);
  	      
          ftpClient.connect("114.31.34.220",10021);   
          ftpClient.setControlEncoding("UTF-8");  // KOR 
          
          int reply = ftpClient.getReplyCode();
      	   if ( !FTPReply.isPositiveCompletion(reply) ) {
      	       System.out.println("FTP Fail");
      	   }else{
      	 	   System.out.println("FTP OK");
              ftpClient.login("allion", "!aadmin0");
      	   }
                  
          ftpClient.changeWorkingDirectory("/");      // 접속하는 서버 작업 디렉토리 변경시
        	// iterates over the files and prints details for each
        	DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
    //      ftpClient.enterRemotePassiveMode(); //<-- 이 메소드를 해준다음에
    //      ftpClient.enterLocalPassiveMode(); //<-- 이 메소드를 해주면 가능하네용..
          
          files = ftpClient.listFiles();
          
       	for (FTPFile file : files) {
        		
        	    String details = file.getName();
        	    if (file.isDirectory()) {
        	        details = "[" + details + "]";
        	    }
        	    details += "\t\t" + file.getSize();
        	    details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
        	    System.out.println(details);
        	}
  
    	try
    	{
    		   System.out.println("---8-------------------------------------");
    		   
    	//		  ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    			  
    		   
    		 File   localfile  = new File("/mnt/sdcard/watermark.jpg");
    		 String remoteFile = "/watermark.jpg";
    		 
    		fos    = new FileOutputStream(localfile);        //  다운로드할 File 생성
    		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    		 
    	    try
    	    {
    	        ftpClient.retrieveFile(remoteFile, fos); // (Permission denied)
    	    }
    	    catch (Exception ex)
    	    {
    	       // return new FTPFile[]{};
    	    	 fos.close();        // Stream 닫기
    	    	 ftpClient.logout();
    	    	 ftpClient.disconnect();
    	    }
           
    	}
    	catch(Exception ex)
    	{
    		System.out.println("IO Exception : " + ex.getMessage());
    	} 
      finally 
        {
          if (fos != null)
            {
             try
                {
                   fos.close();        // Stream 닫기
                }
              catch(Exception ex)
                {
                   System.out.println("IO Exception : " + ex.getMessage());
                }
            }
        }
         	
   	   ftpClient.logout();
       if (ftpClient != null && ftpClient.isConnected()){ ftpClient.disconnect(); }
           
	   System.out.println("*********************************************");
	   System.out.println("*********************************************");
        	
            
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
					   System.out.println("-Press----------------------");
				} else {
					mSystemUiHider.show();
					   System.out.println("----------------------------------------");
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
	
	 public void download(String dir, String downloadFileName, String path) {

	        FileOutputStream out = null;
	        java.io.InputStream in = null;
	        dir += downloadFileName;
	        try {
	            in = ftpClient.retrieveFileStream(dir);
	            out = new FileOutputStream(new File(path));
	            int i;
	            while ((i = in.read()) != -1) {
	                out.write(i);
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            try {
	                in.close();
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	    };

	    public void upload(String dir, File file) {

	        FileInputStream in = null;

	        try {
	            in = new FileInputStream(file);
	            ftpClient.storeFile(dir + file.getName(), in);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    
	
	public boolean isNetworkAvailable() {
		
	//	 System properties;
		//String hostname = properties.getProperty("FTP_SERVER");
	   //     String user = properties.getProperty("FTP_USER");
	   //     String passwd = properties.getProperty("FTP_PASSWD");
		
		/*
	        FTPClient client = new FTPClient();
	       // client.connect("114.31.34.220");
	        client.connect("114.31.34.220",10021);   
	        
	        client.login("allion","!addmin0");
	        
	        String reply = client.getStatus();
	        System.out.println(reply);
	        client.enterRemotePassiveMode();
	        client.changeWorkingDirectory("/uploads");
	        FTPFile[] files = client.listFiles();
	        System.out.println(files.length);
	        for (FTPFile file : files) {
	            System.out.println(file.getName());
	        }

	        String[] fileNames = client.listNames();
	        if (fileNames != null) {
	            for (String file : fileNames) {
	                System.out.println(file);
	            }
	        }
	        client.disconnect();
	    */
		
	    return false;
	} 
	
}






