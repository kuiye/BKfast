package com.bkteam.bkfast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

import com.bkteam.activylist.DeailInfoActivity;
import com.bkteam.activylist.InfoEntity;
import com.bkteam.activylist.MyTaskAdapter;
import com.bkteam.activylist.SlideMenu;
import com.bkteam.activylist.TaskConsel;
import com.example.bkfast.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
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
	 * 获取的TextView的String类型变量声明
	 */
	private String text_get_title;

	
//	private Button button0;
//	private Button button1;
	/*
	 * 侧边栏SlideMenu变量声明
	 */
	private SlideMenu slideMenu;
    private ListView list;

    private TextView title,bott;
	private ListView listView;
	private List<InfoEntity> infoList,infos;
	private MyTaskAdapter taskAdapter;
	private boolean refreshable;
	private int count=0;
	public String name;
	public String uid;
	private Handler myHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what==2) {
				refreshable=false;
				bott.setVisibility(View.VISIBLE);
			}
			if (msg.what==1) {
				if (msg.obj!=null) {
					infos=(List<InfoEntity>) msg.obj;
					for (int i = 0; i <infos.size(); i++) {
						infoList.add(infos.get(i));
					}
					taskAdapter.notifyDataSetChanged();
					refreshable=true;
				}
			}
			if (msg.what==0) {
				TaskConsel taskConsel=new TaskConsel(MainActivity.this, myHandler,String.valueOf(msg.obj));
				refreshable=false;
			}
		}
	};
    
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
        setContentView(R.layout.index);
        
        /*
    	 *	侧边栏-调用SlideMenu实现滑动侧边栏
         */
		slideMenu = (SlideMenu)findViewById(R.id.slide_menu);
		ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
		menuImg.setOnClickListener(this);
		
        /*
    	 *	菜谱列表
         */
		initView();
		initListview();
		Message message=new Message();
		message.what=0;
		myHandler.sendMessage(message);
		
		/*
		 * 菜单列-定外卖 点击监听事件
		 */
		listener0 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent0 = new Intent(MainActivity.this,MainActivity.class);
//				menu_0=(TextView)findViewById(R.id.menu_dingwaimai);
//				text_get_title=String.valueOf(menu_0.getText().toString());
//				Bundle bl=new Bundle();
//				bl.putString("title",text_get_title);
//				intent0.putExtras(bl);
				startActivity(intent0);
				MainActivity.this.finish();
			}
		};
		/*
		 * 菜单列-个人中心 点击监听事件
		 */
		listener1 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
				menu_1=(TextView)findViewById(R.id.menu_gerenzhongxin);
				text_get_title=String.valueOf(menu_1.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent1.putExtras(bl);
				startActivity(intent1);
				MainActivity.this.finish();
			}
		};
		/*
		 * 菜单列-关于 点击监听事件
		 */
		listener2 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent2 = new Intent(MainActivity.this,MenuActivity_guanyu.class);
				menu_2=(TextView)findViewById(R.id.menu_guanyu);
				text_get_title=String.valueOf(menu_2.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent2.putExtras(bl);
				startActivity(intent2);
				MainActivity.this.finish();
			}
		};
		/*
		 * 菜单列-我的订单 点击监听事件
		 */
		listener3 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent3 = new Intent(MainActivity.this,MenuActivity_wodedingdan.class);
				menu_3=(TextView)findViewById(R.id.menu_wodedingdan);
				text_get_title=String.valueOf(menu_3.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent3.putExtras(bl);
				startActivity(intent3);
				MainActivity.this.finish();
			}
		};
		/*
		 * 菜单列-意见反馈 点击监听事件
		 */
		listener4 = new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent4 = new Intent(MainActivity.this,MenuActivity_yijianfakui.class);
				menu_4=(TextView)findViewById(R.id.menu_yijianfankui);
				text_get_title=String.valueOf(menu_4.getText().toString());
				Bundle bl=new Bundle();
				bl.putString("title",text_get_title);
				intent4.putExtras(bl);
				startActivity(intent4);
				MainActivity.this.finish();
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
        
//        /*
//    		登陆按钮监听事件
//        */
//        listener0=new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent0=new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent0);
//                MainActivity.this.finish();
//			}
//		};
//        /*
//    		注册按钮监听事件
//        */
//		listener1=new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent1=new Intent(MainActivity.this,RegisterActivity.class);
//                startActivity(intent1);
//                MainActivity.this.finish();
//			}
//		};
		
//        /*
//    		登陆按钮
//        */
//		button0=(Button)findViewById(R.id.index_title_login_button);
//		button0.setOnClickListener(listener0);
//        /*
//    		注册按钮
//        */
//		button1=(Button)findViewById(R.id.index_title_register_button);
//		button1.setOnClickListener(listener1);
	}
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void initView(){
		
		listView=(ListView) findViewById(R.id.list);
	}
	private void initListview(){
		infoList=new ArrayList<InfoEntity>();
		taskAdapter=new MyTaskAdapter(MainActivity.this, infoList);
		listView.setAdapter(taskAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,DeailInfoActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("uid","1");
				intent.putExtra("info", infoList.get(arg2));
				startActivity(intent);
			}
		});
	//	listView.setOnScrollListener(scrollListener);
	}
	/**
	 * 设置滚动条
	 */
	public OnScrollListener scrollListener=new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			if ((firstVisibleItem + visibleItemCount == totalItemCount) && (totalItemCount != 0)) {   
				if (refreshable) {   
				refreshable = false; 
					Message message=new Message();
					message.what=0;
					message.obj=count;  //count是记录你的等级当前滑动页数的  
					myHandler.sendMessage(message);
					count=count+1;
					}   
				}   
		}
	};
  
}