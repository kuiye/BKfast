package com.bkteam.bkfast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bkteam.activylist.SlideMenu;
import com.example.bkfast.R;

/**
 * Created by Administrator on 2014/7/9 0009.
 */
public class MenuActivity_yijianfakui extends Activity implements OnClickListener {
	/*
	 * 发送给另一个Activity的Bundle
	 */
	private Bundle bl_receive;
	/*
	 * 发送给另一个Activity的Intent
	 */
	private Intent intent_receive;
	
	private String title;
	/*
	 * 返回title的String变量声明
	 */
	private String text_get_title;
	/*
	 * 发送到title的TextView变量声明
	 */
	private TextView text_set_title;
	/*
	 * 侧边栏SlideMenu变量声明
	 */
	private SlideMenu slideMenu;
	/*
	 * 监听器声明
	 */
	private OnClickListener listener0 = null;
	private OnClickListener listener1 = null;
	private OnClickListener listener2 = null;
	private OnClickListener listener3 = null;
	private OnClickListener listener4 = null;
	/*
	 * 菜单栏列名声明
	 */
	private TextView menu_0;
	private TextView menu_1;
	private TextView menu_2;
	private TextView menu_3;
	private TextView menu_4;
	
		
    /*
		侧边栏点击事件
    */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_bar_menu_btn:
			if (slideMenu.isMainScreenShowing()) {
				slideMenu.openMenu();
			} else {
				slideMenu.closeMenu();
			}
			break;
		}
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.slidemenu_yijianfankui);
	    /*
			侧边栏声明
	    */
		slideMenu = (SlideMenu)findViewById(R.id.slide_menu);
		ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
		menuImg.setOnClickListener(this);
		
		//获取到上一个页面传过来的Intent
		intent_receive=this.getIntent();
		//获取Intent中的Bundle数据
		bl_receive=intent_receive.getExtras();
		title=bl_receive.getString("title");
		text_set_title=(TextView)findViewById(R.id.title_bar_name);
		text_set_title.setText(title);
		
		/*
		 * 菜单列-定外卖 点击监听事件
		 */
		listener0 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent0 = new Intent(MenuActivity_yijianfakui.this,MainActivity.class);
//				menu_0=(TextView)findViewById(R.id.menu_dingwaimai);
//				text_get_title=String.valueOf(menu_0.getText().toString());
//				Bundle bl=new Bundle();
//				bl.putString("title",text_get_title);
//				intent0.putExtras(bl);
				startActivity(intent0);
				MenuActivity_yijianfakui.this.finish();
			}
		};
		/*
		 * 菜单列-个人中心 点击监听事件
		 */
		listener1 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(MenuActivity_yijianfakui.this,LoginActivity.class);
				menu_1=(TextView)findViewById(R.id.menu_gerenzhongxin);
				text_get_title=String.valueOf(menu_1.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent1.putExtras(bl);
				startActivity(intent1);
				MenuActivity_yijianfakui.this.finish();
			}
		};
		/*
		 * 菜单列-关于 点击监听事件
		 */
		listener2 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent2 = new Intent(MenuActivity_yijianfakui.this,MenuActivity_guanyu.class);
				menu_2=(TextView)findViewById(R.id.menu_guanyu);
				text_get_title=String.valueOf(menu_2.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent2.putExtras(bl);
				startActivity(intent2);
				MenuActivity_yijianfakui.this.finish();
			}
		};
		/*
		 * 菜单列-我的订单 点击监听事件
		 */
		listener3 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent3 = new Intent(MenuActivity_yijianfakui.this,MenuActivity_wodedingdan.class);
				menu_3=(TextView)findViewById(R.id.menu_wodedingdan);
				text_get_title=String.valueOf(menu_3.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent3.putExtras(bl);
				startActivity(intent3);
				MenuActivity_yijianfakui.this.finish();
			}
		};
		/*
		 * 菜单列-意见反馈 点击监听事件
		 */
		listener4 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent4 = new Intent(MenuActivity_yijianfakui.this,MenuActivity_yijianfakui.class);
				menu_4=(TextView)findViewById(R.id.menu_yijianfankui);
				text_get_title=String.valueOf(menu_4.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent4.putExtras(bl);
				startActivity(intent4);
				MenuActivity_yijianfakui.this.finish();
			}
		};
		/*
		 * 菜单列-定外卖 注册监听
		 */
		menu_0 = (TextView) findViewById(R.id.menu_dingwaimai);
		menu_0.setOnClickListener(listener0);
		/*
		 * 菜单列-个人中心 注册监听
		 */
		menu_1 = (TextView) findViewById(R.id.menu_gerenzhongxin);
		menu_1.setOnClickListener(listener1);
		/*
		 * 菜单列-关于 注册监听
		 */
		menu_2 = (TextView) findViewById(R.id.menu_guanyu);
		menu_2.setOnClickListener(listener2);
		/*
		 * 菜单列-我的订单  注册监听
		 */
		menu_3 = (TextView) findViewById(R.id.menu_wodedingdan);
		menu_3.setOnClickListener(listener3);
		/*
		 * 菜单列-意见反馈  注册监听
		 */
		menu_4 = (TextView) findViewById(R.id.menu_yijianfankui);
		menu_4.setOnClickListener(listener4);
	
	}

}
