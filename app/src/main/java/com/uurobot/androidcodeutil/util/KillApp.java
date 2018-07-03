package com.uurobot.androidcodeutil.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;

import com.uurobot.androidcodeutil.MainActivity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao.liu on 2018/7/3.
 * 指定包名杀死进程
 */
public class KillApp {
	/*
	android:sharedUserId="android.uid.system"
	<uses-permission android:name="android.permission.FORCE_STOP_PACKAGES"></uses-permission>
	 */
	@SuppressLint("PrivateApi")
	private void exitApp(Context context){
		ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		Method forceStopPackage = null;
		try {
			assert am != null;
			forceStopPackage = am.getClass().getDeclaredMethod("forceStopPackage", String.class);
			forceStopPackage.setAccessible(true);
			forceStopPackage.invoke(am, "你指定的包名");
		}
		catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}
	//MGRC200
	//doExec("am force-stop 你的包名");
	private void doExec(String cmd) {
		List<String> cmds = new ArrayList<String>();
		cmds.add( "sh");
		cmds.add( "-c");
		cmds.add(cmd);
		ProcessBuilder pb = new ProcessBuilder(cmds);
		try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
