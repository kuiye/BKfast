package com.bkteam.make;


/**
 * Created by Kui_Ye on 14-7-8.
 */
public class Dtime {
	/*
	 * uninx时间戳转换为字符串类型
	 */

	public String TimeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(new java.util.Date(timestamp));
		return date;
	}

	/*
	 * 字符串转换为uninx时间戳类型
	 */
	public void Timrchange(String timechange) {

	}

	public static void main(String[] args) {
		Dtime dt = new Dtime();

		// System.out.println( new
		// java.text.SimpleDateFormat("11/09/2009 11:31:26").format(new
		// java.util.Date(UnixTimestamp * 1000)));

	}
}
