package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class charaPlayer extends charaBase{
	
	public charaPlayer(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
		this._drow_w = this._img_value.getWidth();
		this._drow_h = this._img_value.getHeight();

		this._move_speed_base = 1.0f;
	}

	public void doUpdate(float touch_x, float touch_y) {
		
		this._drow_x = touch_x;
		this._drow_y = touch_y;

	}
}
