package com.bkteam.activylist;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AsyncCallBack;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.bkteam.base.Extraurl;
public class TaskConsel {
	private Context myContext;
	private Handler myhaHandler;
	private FinalHttp finalHttp;
	private List<InfoEntity> infoList;
	private String pageSize;
	public TaskConsel() {
		// TODO Auto-generated constructor stub
	}
	public TaskConsel(Context context,Handler handler,String pagesize){
		this.myContext=context;
		this.myhaHandler=handler;
		this.finalHttp=new FinalHttp();
		this.pageSize=pagesize;
		init();
	}
	private void init(){
		Extraurl exa =new Extraurl();
		System.out.println(exa.url);
		
		finalHttp.get(exa.url+"ctname.php", new AsyncCallBack<Object>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
//				Toast.makeText(myContext, "姝ｅ湪鑾峰彇鏈嶅姟鍣ㄤ俊鎭�, 2000).show();
			}
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				super.onLoading(count, current);
			}
			@Override
			public void onSuccess(Object result) {
				// TODO Auto-generated method stub
				String infos=String.valueOf(result);
				if (infos!=null && !infos.equals("[]")) {
					infoList=JSONUtils.fromJson(infos,
							new TypeToken<List<InfoEntity>>() {
							});
					Message message=new Message();
					message.what=1;
					message.obj=infoList;
					myhaHandler.sendMessage(message);
				}else{
					Message message=new Message();
					message.what=2;
					myhaHandler.sendMessage(message);
				}
			}
			@Override
			public void onFailure(Throwable error, String msg) {
				// TODO Auto-generated method stub
				Toast.makeText(myContext, "鑾峰彇缃戠粶杩炴帴澶辫触", 2000).show();
				if (Integer.parseInt(pageSize)<=3) {
					String infos=SDtxtInfo.readsdtxt("test", "info.txt");
					if (infos!=null && !infos.equals("[]")) {
						infoList=JSONUtils.fromJson(infos,
								new TypeToken<List<InfoEntity>>() {
								});
						Message message=new Message();
						message.what=1;
						message.obj=infoList;
						myhaHandler.sendMessage(message);
					}
				}else{
					Message message=new Message();
					message.what=2;
					myhaHandler.sendMessage(message);
				}
			}
		});
	}
}
