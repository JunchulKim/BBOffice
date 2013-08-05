package com.alion.SmartLiveon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SmartLiveon_Main extends Activity implements OnClickListener {

	static private String TAG = "BB_MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ClickListener_init(); // Listener 등록
		TopLayer_Init(); // top layer 설정
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
