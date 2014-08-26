package com.bkteam.activylist;

import java.io.InputStream;

import org.json.JSONArray;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bkfast.R;

public class DeailInfoActivity extends Activity {
	/*
	 * 监听器声明
	 */
	private OnClickListener listener_0=null;
	private ImageView imageView;
//	private Button button;
	
	SharedPreferences sp = null;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		listener_0 = new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(DeailInfoActivity.this,MainActivity.class);
//				startActivity(intent);
//				DeailInfoActivity.this.finish();
				onBackPressed();
			}
		};
		
		imageView = (ImageView) findViewById(R.id.menu_title_back_btn);
		imageView.setOnClickListener(listener_0);
//		button = (Button) findViewById(R.id.menu_title_back_btn);
//		button.setOnClickListener(listener_0);
//		initView();
	}
	
	
//	private void initView(){
//		final InfoEntity infoEntity= (InfoEntity) getIntent().getSerializableExtra("info");

//	
//	}
}
