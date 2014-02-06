package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class gameMain {
	private Resources _resources;
	
	
	private gameField _field_obj;
	float _touch_x = 0;
	float _touch_y = 0;
	public gameMain (Resources resources) {
		this._resources = resources;
		
		this._field_obj = new gameField(this._resources); 
		
		
		
	}
	public boolean autoUpdate() {
		this._field_obj.doUpdate();
		return true;
	}
	public void manualUpdate(float touch_x, float touch_y){
		this._field_obj.touchCheck(touch_x, touch_y);
		this._field_obj.doUpdate();
		
		this._touch_x = touch_x;
		this._touch_y = touch_y;
	}
	public void doDraw(Canvas canvas) {
		this._field_obj.doDrow(canvas);
		
		// debug
		/*
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_x="+_touch_x, 0, 40*j, paint); j++;
		canvas.drawText("touch_y="+_touch_y, 0, 40*j, paint); j++;
		*/
		
	}
}
