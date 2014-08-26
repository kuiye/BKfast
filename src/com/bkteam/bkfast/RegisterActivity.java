package com.bkteam.bkfast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bkteam.activylist.SlideMenu;
import com.bkteam.base.Extraurl;
import com.example.bkfast.R;

import com.bkteam.make.MD5;
import com.bkteam.make.checktype;
/**
 * Created by Administrator on 2014/7/9 0009.
 */
public class RegisterActivity extends Activity implements OnClickListener{
	public String uname;
	public String upwd;
	public String urpwd;
	public String umail;
	public String uphone;
	public EditText name;
	public EditText pwd;
	public EditText rpwd;
	public EditText mail;
	public EditText phone;
	public String md5;
	SharedPreferences sp = null;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	public String ct_type;
	
	/*
	 *监听器声明
	 */
	private OnClickListener listener0 = null;
	private OnClickListener listener1 = null;
	private OnClickListener listener2 = null;
	private OnClickListener listener3 = null;
	private OnClickListener listener4 = null;
	private OnClickListener listener5 = null;
	/*
	 *菜单栏列名声明
	 */
	private TextView menu_0;
	private TextView menu_1;
	private TextView menu_2;
	private TextView menu_3;
	private TextView menu_4;
	/*
		注册按钮Button
	 */
	private Button button_register;
	/*
		侧边栏SlideMenu变量声明
	 */
	private SlideMenu slideMenu;
	/*
		获取的TextView的String类型变量声明
	 */
	private String text_get_title;

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
		setContentView(R.layout.register);
        /*
    		侧边栏-调用SlideMenu实现滑动侧边栏
        */
		slideMenu = (SlideMenu)findViewById(R.id.slide_menu);
		ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
		menuImg.setOnClickListener(this);
		
		/*
			注册按钮监听事件
		 */
		listener0 = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name=(EditText)findViewById(R.id.register_username_edit);
				uname=name.getText().toString();
				pwd=(EditText)findViewById(R.id.register_password_edit);
				upwd=pwd.getText().toString();
				rpwd=(EditText)findViewById(R.id.register_password_edit_again);
				urpwd=rpwd.getText().toString();
				mail=(EditText)findViewById(R.id.register_email_edit);
				umail=mail.getText().toString();
				phone=(EditText)findViewById(R.id.register_phoneNumber_edit);
				uphone=phone.getText().toString();
				if(uname.equals("")){
					Toast.makeText(getApplicationContext(), "请输入用户名",
							Toast.LENGTH_SHORT).show();
				}else{
					
					if(upwd.equals("")||urpwd.equals("")){
						Toast.makeText(getApplicationContext(), "请输入密码",
								Toast.LENGTH_SHORT).show();
						}else{
							if(uphone.equals("")||umail.equals("")){
								Toast.makeText(getApplicationContext(), "请输入邮箱或手机号",
										Toast.LENGTH_SHORT).show();
							}else{
								if(!upwd.equals(urpwd)){
									Toast.makeText(getApplicationContext(), "两次密码输入不一致",
											Toast.LENGTH_SHORT).show();
								}else{
									
									Toast.makeText(getApplicationContext(), "输入正确，正在注册",
											Toast.LENGTH_SHORT).show();
									init();
								}
							}
		
						}
					
					
				}
				
				
			}
		};
		
		
		/*
			菜单列表
		 */		
		listener1 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(RegisterActivity.this,MainActivity.class);
//				menu_0=(TextView)findViewById(R.id.menu_dingwaimai);
//				text_get_title=String.valueOf(menu_0.getText().toString());
//				Bundle bl=new Bundle();
//				bl.putString("title",text_get_title);
//				intent0.putExtras(bl);
				startActivity(intent1);
				RegisterActivity.this.finish();
			}
		};

		listener2 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent2 = new Intent(RegisterActivity.this,LoginActivity.class);
				menu_1=(TextView)findViewById(R.id.menu_gerenzhongxin);
				text_get_title=String.valueOf(menu_1.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent2.putExtras(bl);
				startActivity(intent2);
				RegisterActivity.this.finish();
			}
		};

		listener3 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent3 = new Intent(RegisterActivity.this,MenuActivity_guanyu.class);
				menu_2=(TextView)findViewById(R.id.menu_guanyu);
				text_get_title=String.valueOf(menu_2.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent3.putExtras(bl);
				startActivity(intent3);
				RegisterActivity.this.finish();
			}
		};

		listener4 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent4 = new Intent(RegisterActivity.this,MenuActivity_wodedingdan.class);
				menu_3=(TextView)findViewById(R.id.menu_wodedingdan);
				text_get_title=String.valueOf(menu_3.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent4.putExtras(bl);
				startActivity(intent4);
				RegisterActivity.this.finish();
			}
		};
		
		listener5 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent5 = new Intent(RegisterActivity.this,MenuActivity_yijianfakui.class);
				menu_4=(TextView)findViewById(R.id.menu_yijianfankui);
				text_get_title=String.valueOf(menu_4.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent5.putExtras(bl);
				startActivity(intent5);
				RegisterActivity.this.finish();
			}
		};
		
		/*
		 * 菜单列-定外卖 注册监听
		 */
		menu_0 = (TextView) findViewById(R.id.menu_dingwaimai);
		menu_0.setOnClickListener(listener1);
		/*
		 * 菜单列-个人中心 注册监听
		 */
		menu_1 = (TextView) findViewById(R.id.menu_gerenzhongxin);
		menu_1.setOnClickListener(listener2);
		/*
		 * 菜单列-关于 注册监听
		 */
		menu_2 = (TextView) findViewById(R.id.menu_guanyu);
		menu_2.setOnClickListener(listener3);
		/*
		 * 菜单列-我的订单  注册监听
		 */
		menu_3 = (TextView) findViewById(R.id.menu_wodedingdan);
		menu_3.setOnClickListener(listener4);
		/*
		 * 菜单列-意见反馈  注册监听
		 */
		menu_4 = (TextView) findViewById(R.id.menu_yijianfankui);
		menu_4.setOnClickListener(listener5);
		/*
			注册按钮
		 */
		button_register = (Button) findViewById(R.id.register_button);
		button_register.setOnClickListener(listener0);


	}
	
	public void init(){
		boolean ynum=checktype.isMobile(uphone);
		boolean ymail=checktype.isEmail(umail);
		if(!ynum){
			Toast.makeText(getApplicationContext(), "手机号格式不正确",
					Toast.LENGTH_SHORT).show();    
			}else{
    		  if(!ymail){
    			  Toast.makeText(getApplicationContext(), "邮箱格式格式不正确",
    						Toast.LENGTH_SHORT).show();    
    		  }else{
    			  MD5 md =new MD5();
    			  md5 = md.GetMD5Code(upwd);
    			  ArrayList nameValuePairs = new ArrayList();
  				nameValuePairs.add(new BasicNameValuePair("uname", uname));
  				nameValuePairs.add(new BasicNameValuePair("upwd", md5));
  				nameValuePairs.add(new BasicNameValuePair("umail", umail));
  				nameValuePairs.add(new BasicNameValuePair("uphone", uphone));

  				StrictMode
  						.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
  								.detectDiskReads().detectDiskWrites()
  								.detectNetwork() // or
  								// .detectAll()
  								// for
  								// all
  								// detectable
  								// problems
  								.penaltyLog().build());
  				StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
  						.detectLeakedSqlLiteObjects()
  						.detectLeakedClosableObjects().penaltyLog()
  						.penaltyDeath().build());

  				try {
  					Extraurl exa =new Extraurl();
  					HttpClient httpclient = new DefaultHttpClient();
  					// HttpPost httppost = new
  					// HttpPost("http://10.1.13.107/www/app/app/android/login.php");
  					HttpPost httppost = new HttpPost(
  							exa.url+"reg.php");

  					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
  							HTTP.UTF_8));
  					HttpResponse response = httpclient.execute(httppost);
  					HttpEntity entity = response.getEntity();
  					is = entity.getContent();
  				} catch (Exception e) {
  					Toast.makeText(getApplicationContext(), "4",
  							Toast.LENGTH_SHORT).show();
  				}
  				// convert response to string
  				try {
  					BufferedReader reader = new BufferedReader(
  							new InputStreamReader(is, "utf8"), 8);
  					sb = new StringBuilder();
  					sb.append(reader.readLine() + "\n");

  					String line = null;
  					while ((line = reader.readLine()) != null) {
  						sb.append(line + "\n");
  					}
  					is.close();
  					result = sb.toString();
  				} catch (Exception e) {
  					Toast.makeText(getApplicationContext(), "3",
  							Toast.LENGTH_SHORT).show();
  				}
  				// paring data

  				try {
  					jArray = new JSONArray(result);
  					JSONObject json_data = null;
  					if (JSONObject.NULL != null) {
  						for (int i = 0; i < jArray.length(); i++) {
  							json_data = jArray.getJSONObject(i);

  							ct_type= json_data.getString("type");

  							if( ct_type.equals("1")) {
  								Toast.makeText(getApplicationContext(),
  										"注册成功", Toast.LENGTH_SHORT).show();

  								Intent intent0 = new Intent(RegisterActivity.this,MainActivity.class);
  								startActivity(intent0);
  								RegisterActivity.this.finish();
  								
  							} else {
  								Toast.makeText(getApplicationContext(),
  										"注册失败", Toast.LENGTH_SHORT).show();
  								
  							}

  							// overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
  						}
  					} else {
  						Toast.makeText(getApplicationContext(), "mark",
  								Toast.LENGTH_SHORT).show();
  					}
  				} catch (JSONException e1) {
  					Toast.makeText(getApplicationContext(), "数据异常.",
  							Toast.LENGTH_SHORT).show();
  				} catch (ParseException e1) {
  					Toast.makeText(getApplicationContext(), "数据异常",
  							Toast.LENGTH_SHORT).show();
  				} catch (Exception e) {
  					e.printStackTrace();
  				}

  			}
  		};
    			  
    			  
    			  
   
    		  
    
		
		
		
		
	}
}
