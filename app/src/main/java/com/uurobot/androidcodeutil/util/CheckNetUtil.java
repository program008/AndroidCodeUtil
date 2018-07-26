package com.uurobot.androidcodeutil.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tao.liu on 2018/7/25.
 * description this is description
 */
public class CheckNetUtil {

	private static final String TAG = "CheckNetUtil";

	public static String Ping(String str) {
		String resault = "";
		Process p;
		try {
			//ping -c 3 -w 100  中  ，-c 是指ping的次数 3是指ping 3次 ，-w 100  以秒为单位指定超时间隔，是指超时时间为100秒
			p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + str);
			int status = p.waitFor();

			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			Log.e(TAG, buffer.toString());

			if (status == 0) {
				resault = "success";
			}
			else {
				resault = "faild";
			}
		}
		catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}


		return resault;
	}

}
