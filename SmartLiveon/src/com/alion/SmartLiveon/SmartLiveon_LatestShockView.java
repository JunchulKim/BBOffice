package com.alion.SmartLiveon;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SmartLiveon_LatestShockView extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_latestshockview);
		
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) // 세로 가로 방향 확인
		{
			TopLayer_Init(); // top layer 설정
		}
		else
		{
			LinearLayout toplayer = (LinearLayout) findViewById(R.id.ToptitleLayout);
			toplayer.setVisibility(View.GONE);
		}
		
		
		
	}
	
	public void Image_View() {
		
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
		Iv_back.setVisibility(View.VISIBLE); // 버튼 감추기
		Iv_home.setVisibility(View.INVISIBLE); // 버튼 감추기
		Tv_title.setText("B/B Num\n(Time of Occurrence)"); // 블랙박스 번호를 넘겨 받아서 값을 넣어 줘야함 
		
		contentsLayout.findViewById(R.id.Back_btn).setOnClickListener(this);//상단 back btn 이벤트 등록
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Back_btn:
			onBackPressed();
			break;

		default:
			break;
		}
	}
}
