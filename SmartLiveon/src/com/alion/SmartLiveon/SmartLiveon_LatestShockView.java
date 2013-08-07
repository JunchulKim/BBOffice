package com.alion.SmartLiveon;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alion.SmartLiveon.Utils.BitmapDrawable;

public class SmartLiveon_LatestShockView extends Activity implements
		OnClickListener {

	ImageView IV_frame;
	//"Test Array" ???�에 data class �?변경하??구현 ?�야 ?
	ArrayList<File> items = new ArrayList<File>();
	ArrayList<String> folderName = new ArrayList<String>();
	ArrayList<Drawable> BitmapDb = new ArrayList<Drawable>();
	AnimationDrawable activeAnimation = new AnimationDrawable();
	Button Btn_Play_Pause;
	LinearLayout toplayer;
	

	static int duration = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_latestshockview);
		
		Image_View();

		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			TopLayer_Init(); // top layer ?�정
			//Toast.makeText(this, "?�로 ?�정", Toast.LENGTH_SHORT).show();
		} else {
			toplayer = (LinearLayout) findViewById(R.id.ToptitleLayout);
			toplayer.setVisibility(View.GONE);
			//Toast.makeText(this, "가�??�정", Toast.LENGTH_SHORT).show();
		}
		
		Btn_Play_Pause = (Button)findViewById(R.id.Btn_Play_Pause);
		findViewById(R.id.Btn_Play_Pause).setOnClickListener(this);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		switch (newConfig.orientation) {
		case Configuration.ORIENTATION_LANDSCAPE:
			toplayer = (LinearLayout) findViewById(R.id.ToptitleLayout);
			toplayer.setVisibility(View.GONE);
			break;
		case Configuration.ORIENTATION_PORTRAIT:
			toplayer.setVisibility(View.VISIBLE);
			TopLayer_Init(); // top layer ?�정
			
			break;
		}
		
	}

	public void Items_Stored(String Path) {

		File fileList = new File(Path);
		
		if (fileList.exists()) {
			File[] files = fileList.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isHidden()) {
					if (files[i].isDirectory()) {
						folderName.add(files[i].getName());
						Items_Stored(files[i].getAbsolutePath());
					} else if (files[i].isFile()) {
						items.add(files[i]);
					}
				}
			}
		}
		
	}
	
	
	public void Image_View() {
		
		String SDpath;
		String externalState = Environment.getExternalStorageState();

		IV_frame = (ImageView) findViewById(R.id.IV_frame);

		if (externalState.equals(Environment.MEDIA_MOUNTED)) {
			SDpath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/test";
		} else {
			SDpath = Environment.MEDIA_UNMOUNTED;
		}

		Items_Stored(SDpath);
		/*
		Bitmap mPreviewImg = null;
		BitmapFactory.Options bfo = new BitmapFactory.Options();
		try {
			bfo.inSampleSize=1;
			mPreviewImg = BitmapFactory.decodeFile(items.get(0).getAbsolutePath(), bfo);
					
		} catch (OutOfMemoryError e) {
			// TODO: handle exception
			e.printStackTrace();
			System.gc();
			try {
				bfo.inSampleSize=4;
				mPreviewImg = BitmapFactory.decodeFile(items.get(0).getAbsolutePath(), bfo);
			} catch (OutOfMemoryError e2) {
				// TODO: handle exception
				e2.printStackTrace();
				System.gc();
				try {
					bfo.inSampleSize=8;
					mPreviewImg = BitmapFactory.decodeFile(items.get(0).getAbsolutePath(), bfo);
				} catch (Exception e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
		}
		IV_frame.setImageBitmap(mPreviewImg);
		*/
		/*
		Bitmap bm = BitmapFactory. decodeFile(items.get(0).getAbsolutePath());
        IV_frame.setImageBitmap(bm);
		*/
		
		Bitmap mPreviewImg = null;
		
		for (int i = 0; i < items.size(); i++) {
			
			BitmapFactory.Options bfo = new BitmapFactory.Options();
			try {
				bfo.inSampleSize=4;
				mPreviewImg = BitmapFactory.decodeFile(items.get(i).getAbsolutePath(), bfo);
						
			} catch (OutOfMemoryError e) {
				// TODO: handle exception
				e.printStackTrace();
				System.gc();
				try {
					bfo.inSampleSize=8;
					mPreviewImg = BitmapFactory.decodeFile(items.get(i).getAbsolutePath(), bfo);
				} catch (OutOfMemoryError e2) {
					// TODO: handle exception
					e2.printStackTrace();
					System.gc();
					try {
						bfo.inSampleSize=16;
						mPreviewImg = BitmapFactory.decodeFile(items.get(i).getAbsolutePath(), bfo);
					} catch (Exception e3) {
						// TODO: handle exception
						e3.printStackTrace();
					}
				}
			}
			
			/*
			Bitmap bm = BitmapFactory.decodeFile(items.get(i)
					.getAbsolutePath());
			*/
			Log.i("jspark",items.get(i).getAbsolutePath());
			Drawable frame = new BitmapDrawable(mPreviewImg);
			BitmapDb.add(frame);
			activeAnimation.addFrame(BitmapDb.get(i), duration);
		}
		
		IV_frame.setImageDrawable(activeAnimation);
		
		
		
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
		Iv_back.setVisibility(View.VISIBLE); // 버튼 감추�?
		Iv_home.setVisibility(View.INVISIBLE); // 버튼 감추�?
		Tv_title.setText("BB/Num: " + folderName.get(0) + "\n" + "Time: "
				+ folderName.get(2)); // 블랙박스 번호�??�겨 받아??값을
		// ?�어 줘야?
		
		contentsLayout.findViewById(R.id.Back_btn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Back_btn:
			onBackPressed();
			break;

		case R.id.Btn_Play_Pause:
			if(activeAnimation.isRunning()) {
				pause();
				//Toast.makeText(this, "?�시?��?", Toast.LENGTH_SHORT).show();
			}
				
			else {
				play();
				//Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
	private AnimationDrawable sphereAnimation = new AnimationDrawable();
	private AnimationDrawable sphereResume;
	//private AnimationDrawable activeAnimation;
	private Drawable currentFrame;
	private Drawable checkFrame;
	private int frameIndex;
	private void pause()
	{
	    sphereResume = new AnimationDrawable();
	    activeAnimation.stop();
	    currentFrame = activeAnimation.getCurrent();

	    frameLoop:
	    for(int i = 0; i < sphereAnimation.getNumberOfFrames(); i++)
	    {
	        checkFrame = activeAnimation.getFrame(i);

	        if(checkFrame == currentFrame)
	        {
	            frameIndex = i;
	            for(int k = frameIndex; k < activeAnimation.getNumberOfFrames(); k++)
	            {
	                Drawable frame = activeAnimation.getFrame(k);
	                sphereResume.addFrame(frame, 50);
	            }
	            for(int k = 0; k < frameIndex; k++)
	            {
	                Drawable frame = activeAnimation.getFrame(k);
	                sphereResume.addFrame(frame, 50);
	            }
	            activeAnimation = sphereResume;
	            IV_frame.setImageDrawable(activeAnimation);
	            IV_frame.invalidate();
	            break frameLoop;
	        }
	    }
	}
	private void play()
	{
	    
	   //activeAnimation.setOneShot(true);
	    //activeAnimation.start();
	    activeAnimation.run();
	}

	private void stop()
	{
	    
	    activeAnimation.stop();
	    activeAnimation = sphereAnimation;
	    IV_frame.setImageDrawable(activeAnimation);
	}

}
