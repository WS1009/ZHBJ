package com.wangshun.slidingmenudemo.anim;

import android.graphics.Canvas;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.wangshun.slidingmenudemo.R;

public class CustomScaleAnimation extends CustomAnimation {

	public CustomScaleAnimation() {
		super(R.string.anim_scale, new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}			
		});
	}

}
