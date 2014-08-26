package com.bkteam.activylist;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.kymjs.aframe.bitmap.KJBitmap;
import org.kymjs.aframe.bitmap.utils.BitmapCreate;
import org.kymjs.aframe.ui.BindView;
import org.kymjs.aframe.ui.fragment.BaseFragment;
import org.kymjs.aframe.ui.widget.KJListView;
import org.kymjs.aframe.ui.widget.KJListView.KJListViewListener;
import org.kymjs.aframe.ui.ViewInject;
import org.kymjs.aframe.ui.fragment.BaseFragment;
import com.bkteam.base.Extraurl;
import com.example.bkfast.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AsyncCallBack;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
 * 
 * @author Administrator
 * 
 */
public class MyTaskAdapter extends BaseAdapter {
	private Context context = null;
	private List<InfoEntity> datas = null;
	private LayoutInflater inflater = null;
	private ViewHolder viewHolder;
	
	
	/**
	 * 
	 * @param context
	 *            锟斤拷锟斤拷锟斤拷
	 * @param datas
	 *            锟斤拷锟�
	 */
	public MyTaskAdapter(Context context, List<InfoEntity> dataes) {
		this.context = context;
		this.datas = dataes;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return datas.size();
	}

	@Override
	public Object getItem(int position) {

		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}
    
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.index_shop, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.textview_home_listview_title);
			viewHolder.max = (TextView) convertView
					.findViewById(R.id.textview_home_listview_time);
			
			viewHolder.topimage=(ImageView) convertView.findViewById(R.id.imageview_home_listview_thumb);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String title = datas.get(position).getId();
		if (null != title && title.length() > 6) {
			title = title.substring(0, 6);
		}
		viewHolder.name.setText(datas.get(position).getName());
		viewHolder.max.setText(datas.get(position).getName());
		
		final Extraurl exa =new Extraurl();
		
		try {
//			Bitmap bitmap=getHttpBitmap("http://192.168.1.101/www/public/attachment/201303/05/00/5134c70277bcd.jpg");
//			viewHolder.topimage.setImageBitmap(bitmap);
			  KJBitmap kjb = KJBitmap.create();
	            // 载入本地图片
	            // kjb.display(imageView, "/storage/sdcard0/1.png");
	            // 载入网络图片
	            kjb.display(
	            		viewHolder.topimage,
	                    exa.imgurl+datas.get(position).getPreview().toString());
	            // kjb.display(
	            // imageView,
	            // "https://raw.githubusercontent.com/kymjs/KJFrameForAndroid/master/KJFrameExample/big_image2.jpg");
	            ViewInject.toast("图片较大，加载中");
		} catch (Exception e) {
				new Handler().post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						FinalHttp finalHttp=new FinalHttp();
						
						finalHttp.download(exa.url, Environment.getExternalStorageDirectory()+ "/bkteam/"+datas.get(position).getPreview().toString(), new AsyncCallBack<File>() {
						});
					}
				});
				

		}
		return convertView;
	}
	/**

			
				

		}
		return convertView;
	}
	private Bitmap I_ImageLoder(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	
	* 加载本地图片
	* @param url
	* @return
	*/
	public static Bitmap getLoacalBitmap(String url) throws Exception {
	          FileInputStream fis = new FileInputStream(url);
	          return BitmapFactory.decodeStream(fis);
	}
	/**
	* 从服务器取图片
	* @param url
	* @return
	*/
	public static Bitmap getHttpBitmap(String url) throws Exception {
	     URL myFileUrl = null;
	     Bitmap bitmap = null;
	          myFileUrl = new URL(url);
	          HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
	          conn.setConnectTimeout(0);
	          conn.setDoInput(true);
	          conn.connect();
	          InputStream is = conn.getInputStream();
	          bitmap = BitmapFactory.decodeStream(is);
	     return bitmap;
	}

	
	private static class ViewHolder {
		public TextView name;
		public TextView max;
		public TextView min;
		public TextView city;
		public ImageView topimage;
	}
}
