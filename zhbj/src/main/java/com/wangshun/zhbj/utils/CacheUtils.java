package com.wangshun.zhbj.utils;

import android.content.Context;

/**
 * 处理网络缓存
 * 
 * @author Kevin
 * 
 */
public class CacheUtils {

	/**
	 * 设置缓存
	 * 
	 * @param key
	 *            缓存表示: 可以使用url来标示一段json数据
	 * @param value
	 *            缓存内容是json数据
	 */
	public static void setCache(Context ctx, String key, String value) {
		//也可以使用文件缓存，以MD5（URL）为文件名，以json为文件内容
		//也可以使用数据库存储缓存
		SharePreferenceUtils.putString(ctx, key, value);
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	public static String getCache(Context ctx, String key) {
		//文件缓存：查找一个有没有一个叫做MD5（url）的文件，有的话说明有缓存
		return SharePreferenceUtils.getString(ctx, key, null);
	}

}
