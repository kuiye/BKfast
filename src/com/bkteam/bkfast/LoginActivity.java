package com.bkteam.bkfast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.bkteam.base.Extraurl;
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
import com.bkteam.make.MD5;
import com.example.bkfast.R;

public class LoginActivity extends Activity implements OnClickListener {
	/*
	 * 监听器声明
	 */
	private OnClickListener listener0 = null;
	private OnClickListener listener1 = null;
	private OnClickListener listener2 = null;
	private OnClickListener listener3 = null;
	private OnClickListener listener4 = null;
	private OnClickListener listener5 = null;
	private OnClickListener listener6 = null;	
	/*
	 * 菜单栏列名声明
	 */
	private TextView menu_0;
	private TextView menu_1;
	private TextView menu_2;
	private TextView menu_3;
	private TextView menu_4;
	/*
	 * 侧边栏SlideMenu变量声明
	 */
	private SlideMenu slideMenu;
	/*
	 * 获取的TextView的String类型变量声明
	 */
	private String text_get_title;
	/*
	 * 注册链接
	 */
	private TextView textView;
	
	
	
	public EditText name;
	public EditText pwd;
	public String uname;
	public String upwd;
	private Button button0;
	SharedPreferences sp = null;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	public String ct_pwd;
	public String md5;

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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
        /*
    		侧边栏-调用SlideMenu实现滑动侧边栏
        */
		slideMenu = (SlideMenu)findViewById(R.id.slide_menu);
		ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
		menuImg.setOnClickListener(this);
		/*
		 * 登陆按钮监听事件
		 */
		listener0 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				name = (EditText) findViewById(R.id.login_username_edit);
				uname = name.getText().toString();
				pwd = (EditText) findViewById(R.id.login_password_edit);
				upwd = pwd.getText().toString();
				MD5 m5 = new MD5();
				md5 = MD5.GetMD5Code(upwd);
				ArrayList nameValuePairs = new ArrayList();
				nameValuePairs.add(new BasicNameValuePair("uname", uname));
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
							exa.url+"login.php");

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

							ct_pwd = json_data.getString("user_pwd");

							if (ct_pwd.equals(md5)) {

								Intent intent0 = new Intent(LoginActivity.this,
										MainActivity.class);
								startActivity(intent0);
								LoginActivity.this.finish();
							} else {
								Toast.makeText(getApplicationContext(),
										"用户名或密码错误", Toast.LENGTH_SHORT).show();
							}

							// overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
						}
					} else {
						Toast.makeText(getApplicationContext(), "mark",
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e1) {
					Toast.makeText(getApplicationContext(), "用户名或密码错误",
							Toast.LENGTH_SHORT).show();
				} catch (ParseException e1) {
					Toast.makeText(getApplicationContext(), "数据异常",
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
	
		/*
		 * 登陆链接监听事件
		 */
		listener6 = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent6 = new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(intent6);
				LoginActivity.this.finish();
			}
		};
		
		/*
			菜单列表
		 */
		listener1 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
//				menu_0=(TextView)findViewById(R.id.menu_dingwaimai);
//				text_get_title=String.valueOf(menu_0.getText().toString());
//				Bundle bl=new Bundle();
//				bl.putString("title",text_get_title);
//				intent0.putExtras(bl);
				startActivity(intent1);
				LoginActivity.this.finish();
			}
		};
		
		listener2 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent2 = new Intent(LoginActivity.this,LoginActivity.class);
				menu_1=(TextView)findViewById(R.id.menu_gerenzhongxin);
				text_get_title=String.valueOf(menu_1.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent2.putExtras(bl);
				startActivity(intent2);
				LoginActivity.this.finish();
			}
		};
		
		listener3 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent3 = new Intent(LoginActivity.this,MenuActivity_guanyu.class);
				menu_2=(TextView)findViewById(R.id.menu_guanyu);
				text_get_title=String.valueOf(menu_2.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent3.putExtras(bl);
				startActivity(intent3);
				LoginActivity.this.finish();
			}
		};

		listener4 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent4 = new Intent(LoginActivity.this,MenuActivity_wodedingdan.class);
				menu_3=(TextView)findViewById(R.id.menu_wodedingdan);
				text_get_title=String.valueOf(menu_3.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent4.putExtras(bl);
				startActivity(intent4);
				LoginActivity.this.finish();
			}
		};

		listener5 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent5 = new Intent(LoginActivity.this,MenuActivity_yijianfakui.class);
				menu_4=(TextView)findViewById(R.id.menu_yijianfankui);
				text_get_title=String.valueOf(menu_4.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent5.putExtras(bl);
				startActivity(intent5);
				LoginActivity.this.finish();
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
		 * 登陆按钮
		 */
		button0 = (Button) findViewById(R.id.login_button);
		button0.setOnClickListener(listener0);
		/*
		 * 注册链接
		 */
        textView=(TextView)findViewById(R.id.register_link);
        textView.setOnClickListener(listener6);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

}
