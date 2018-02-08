package com.wangshun.zhbj.utils;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * 获取BitmapUtils对象, 保证多个模块共用一个BitmapUtils对象,避免内存溢出
 * 
 * @author Kevin
 * 
 */
public class BitmapHelper {

	private static BitmapUtils mBitmapUtils = null;

	public static BitmapUtils getBitmapUtils(Context ctx) {
		if (mBitmapUtils == null) {
			mBitmapUtils = new BitmapUtils(ctx);
		}
		
		return mBitmapUtils;
	}
}
