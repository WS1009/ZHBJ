package com.wangshun.zhbj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wangshun.zhbj.base.BasePager;
/**
 * 设置
 * @author Kevin
 *
 */
public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		Log.d(TAG, "设置加载数据了");
		tvTitle.setText("设置");
		btnMenu.setVisibility(View.GONE);

		TextView tvContent = new TextView(mActivity);
		tvContent.setText("设置");
		tvContent.setTextColor(Color.RED);
		tvContent.setTextSize(25);
		tvContent.setGravity(Gravity.CENTER);

		flContent.addView(tvContent);
	}

}
