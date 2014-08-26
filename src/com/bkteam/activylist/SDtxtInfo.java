package com.bkteam.activylist;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import android.os.Environment;
import android.util.Log;

/**
 * 读取SD卡上面的东西
 * 
 * @author Administrator
 * 
 */
public class SDtxtInfo {
	/**
	 * 读取kingoit目录下的某个文件夹
	 * @param mkname 目录名称
	 * @param txtname文件名称
	 * @return
	 */
	public static String readsdtxt(String mkname,String txtname) {
		String readtxt = null;
		File f = new File(Environment.getExternalStorageDirectory()
				+ "/" + mkname+"/"+txtname);// 这是对应文件名
		if (!f.exists()) {
			readtxt = null;
		} else {
			try {
				InputStream in = new BufferedInputStream(new FileInputStream(f));
				BufferedReader br = new BufferedReader(new InputStreamReader(
						in, "UTF-8"));
				String tmp;
				StringBuffer sb = new StringBuffer();
				while ((tmp = br.readLine()) != null) {
					// 在这对tmp操作
					sb.append(tmp);
				}
				br.close();
				in.close();
				readtxt = sb.toString();
			} catch (Exception e) {
			}
		}
		return readtxt;
	}
	/**
	 * 
	 * @param filedir 目录名称
	 * @param fileName 文件名称
	 * @param txtinfos 文件内容
	 * @throws IOException
	 */
	public static void writesdtxt(String filedir,String fileName,String txtinfos) throws IOException{
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/"+filedir);// 这是对应文件名
		if (!file.exists()) {
			file.mkdirs();
		}
		File file2=new File(file.getPath()+"/"+fileName);
		if (!file2.exists()) {
			file2.createNewFile();
		}
		FileOutputStream fileOutputStream=new FileOutputStream(file2);
		byte [] bytes=txtinfos.getBytes();
		fileOutputStream.write(bytes);
		fileOutputStream.close();
	}
}
