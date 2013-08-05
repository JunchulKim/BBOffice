package com.alion.SmartLiveon.Utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alion.SmartLiveon.R;

public class CustomDialog extends Dialog {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
		lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		lpWindow.dimAmount = 0.8f;
		getWindow().setAttributes(lpWindow);

		setContentView(R.layout.custom_dialog);

		setLayout();
		setTitle(mTitle);
		setSub1Title(mSub1Title);
		setSub12itle(mSub2Title);
		setEt1_hint(Et1_hint);
		setEt2_hint(Et2_hint);
		setLeftBtn_text(LeftBtn_text);

		setClickListener(mLeftClickListener, mRightClickListener);
	}

	public CustomDialog(Context context) {
		// Dialog 배경을 투명 처리 해준다.
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
	}

	public CustomDialog(Context context, String title,
			View.OnClickListener singleListener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		this.mTitle = title;
		this.mLeftClickListener = singleListener;
	}
	
	public CustomDialog(Context context, String _Title, String _Sub1Title,
			String _Sub2Title, String _LeftBtnText, View.OnClickListener leftListener,
			View.OnClickListener rightListener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		this.mTitle = _Title;
		this.mSub1Title = _Sub1Title;
		this.mSub2Title = _Sub2Title;
		this.LeftBtn_text = _LeftBtnText;
		this.mLeftClickListener = leftListener;
		this.mRightClickListener = rightListener;	
	}

	public CustomDialog(Context context, String _Title, String _Sub1Title,
			String _Sub2Title, String _Et1Hint, String _Et2Hint,
			String _LeftBtnText, View.OnClickListener leftListener,
			View.OnClickListener rightListener) {
		super(context, android.R.style.Theme_Translucent_NoTitleBar);
		this.mTitle = _Title;
		this.mSub1Title = _Sub1Title;
		this.mSub2Title = _Sub2Title;
		this.Et1_hint = _Et1Hint;
		this.Et2_hint = _Et2Hint;
		this.LeftBtn_text = _LeftBtnText;
		this.mLeftClickListener = leftListener;
		this.mRightClickListener = rightListener;
	}

	private void setTitle(String title) {
		mTitleView.setText(title);
	}

	private void setSub1Title(String _Sub1Title) {
		mSub1View.setText(_Sub1Title);
	}

	private void setSub12itle(String _Sub2Title) {
		mSub2View.setText(_Sub2Title);
	}

	private void setEt1_hint(String _Et1Hint) {
		mEditText1.setHint(_Et1Hint);
	}

	private void setEt2_hint(String _Et2Hint) {
		mEditText2.setHint(_Et2Hint);
	}

	private void setLeftBtn_text(String _LeftBtnText) {
		mLeftButton.setText(_LeftBtnText);
	}

	private void setClickListener(View.OnClickListener left,
			View.OnClickListener right) {
		if (left != null && right != null) {
			mLeftButton.setOnClickListener(left);
			mRightButton.setOnClickListener(right);
		} else if (left != null && right == null) {
			mLeftButton.setOnClickListener(left);
		} else {

		}
	}
	
	public void setXmlcomponent (Boolean _value) {
		if(_value.equals(false))
		{
			mEditText1.setVisibility(View.VISIBLE);
			mEditText2.setVisibility(View.VISIBLE);
			mSMS.setVisibility(View.INVISIBLE);
			mImageFrame.setVisibility(View.INVISIBLE);
		}
		else
		{
			mEditText1.setVisibility(View.INVISIBLE);
			mEditText2.setVisibility(View.INVISIBLE);
			mSMS.setVisibility(View.VISIBLE);
			mImageFrame.setVisibility(View.VISIBLE);
		}
	}
	

	/*
	 * Layout
	 */
	private TextView mTitleView;
	private TextView mSub1View;
	private TextView mSub2View;
	private Button mLeftButton;
	private Button mRightButton;
	private EditText mEditText1;
	private EditText mEditText2;
	private RadioGroup mSMS;
	private RadioGroup mImageFrame;
	private String mTitle;
	private String mSub1Title;
	private String mSub2Title;
	private String Et1_hint;
	private String Et2_hint;
	private String LeftBtn_text;

	private View.OnClickListener mLeftClickListener;
	private View.OnClickListener mRightClickListener;

	/*
	 * Layout
	 */

	private void setLayout() {
		mTitleView = (TextView) findViewById(R.id.tv_title);
		mSub1View = (TextView) findViewById(R.id.Sub1_title);
		mSub2View = (TextView) findViewById(R.id.Sub2_title);
		mEditText1 = (EditText) findViewById(R.id.Et_Sub1);
		mEditText2 = (EditText) findViewById(R.id.Et_Sub2);
		mLeftButton = (Button) findViewById(R.id.bt_left);
		mRightButton = (Button) findViewById(R.id.bt_right);
		mSMS = (RadioGroup) findViewById(R.id.rg_NotiSMS);
		mImageFrame = (RadioGroup) findViewById(R.id.rg_ImageFrame);
		// mRightButton.setVisibility(View.GONE);

	}

}
