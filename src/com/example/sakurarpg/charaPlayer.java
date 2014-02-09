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
		
		// start point
		this._drow_x = 600;
		this._drow_y = 900;

		this._move_speed_base = 2.0f;
	}
	
	public void setMovePoint(float touch_x, float touch_y) {
		this._move_point_x = touch_x;
		this._move_point_y = touch_y;
	//	this._drow_x = touch_x;
	//	this._drow_y = touch_y;

		
		if (touch_x > this._drow_x) {
			this._move_speed_x = this._move_speed_base;
			
		} else if (touch_x < this._drow_x) {
			this._move_speed_x = this._move_speed_base * (-1);
		}
		
		if (touch_y > this._drow_y) {
			this._move_speed_y = this._move_speed_base;

		} else if (touch_y < this._drow_y) {
			this._move_speed_y = this._move_speed_base * (-1);
		}
		
		
		this._move_angle = angle();
//		this._move_angle = 180;
		this._move_speed_x = (float)(Math.cos(this._move_angle * Math.PI / 180 ) * this._move_speed_base);

		this._move_speed_y = (float)(Math.sin(this._move_angle * Math.PI / 180 ) * this._move_speed_base);
//		this._move_speed_y = this._move_speed_base;
//		this._drow_x = touch_x;
//		this._drow_y = touch_y;

	}
}
