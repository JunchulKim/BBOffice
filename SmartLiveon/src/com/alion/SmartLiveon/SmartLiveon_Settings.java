package com.alion.SmartLiveon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alion.SmartLiveon.Utils.CustomDialog;

public class SmartLiveon_Settings extends Activity implements OnClickListener {
	CustomDialog mCustomDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ClickListener_init(); // Listener 등록
		TopLayer_Init(); // top layer 설정
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

		Iv_back.setVisibility(View.VISIBLE);
		Iv_home.setVisibility(View.INVISIBLE);
		Tv_title.setText(R.string.Main_Settings);

		contentsLayout.findViewById(R.id.Back_btn).setOnClickListener(this);
	}

	public void ClickListener_init() {
		findViewById(R.id.btn_ProductRegistration).setOnClickListener(this);
		findViewById(R.id.btn_ChangePW).setOnClickListener(this);
		findViewById(R.id.btn_TransferSettings).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Back_btn:
			onBackPressed();
			break;
		case R.id.btn_ProductRegistration:
			mCustomDialog = new CustomDialog(this,
					getString(R.string.Settings_ProductRegistration),
					getString(R.string.BB_Phone),
					getString(R.string.BB_Password),getString(R.string.BB_Hint1),getString(R.string.BB_Hint2),getString(R.string.BB_LeftText),
					leftClickListener, rightClickListener);
			mCustomDialog.show();
			mCustomDialog.setXmlcomponent(false);
			break;
		case R.id.btn_ChangePW:
			mCustomDialog = new CustomDialog(this,
					getString(R.string.Change_Password),
					getString(R.string.Change_Confirm),
					getString(R.string.Change_Retry),getString(R.string.Change_Hint1),getString(R.string.Change_Hint2),getString(R.string.Change_LeftText),
					leftClickListener, rightClickListener);
			mCustomDialog.show();
			mCustomDialog.setXmlcomponent(false);
			break;
		case R.id.btn_TransferSettings:
			mCustomDialog = new CustomDialog(this,
					getString(R.string.Transfer_Settings),
					getString(R.string.Transfer_SMS),
					getString(R.string.Transfer_Frame),getString(R.string.Transfer_LeftText),
					leftClickListener, rightClickListener);
			mCustomDialog.show();
			mCustomDialog.setXmlcomponent(true);
			break;
		default:
			break;
		}
	}

	private View.OnClickListener leftClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			mCustomDialog.dismiss();

		}
	};

	private View.OnClickListener rightClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			mCustomDialog.dismiss();
		}
	};
}
