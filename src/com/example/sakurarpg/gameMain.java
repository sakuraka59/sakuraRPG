package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.*;

public class gameMain {
	private Resources _resources;
	private charaPlayer _player_obj;
	
	
	private gameField _field_obj;
	private float _touch_x = 0;
	private float _touch_y = 0;
//	private int _touch_action =0;
	
	private actionUi _action_ui_obj;
	
	public gameMain (Resources resources) {
		this._resources = resources;
		this._player_obj = new charaPlayer(resources);
		
		this._field_obj = new gameField(this._resources, this._player_obj); 
		this._action_ui_obj = new actionUi(this._player_obj);
		
		
		
	}
	public boolean autoUpdate() {
		this._field_obj.doUpdate();
		return true;
	}
	public void manualUpdate(float touch_x, float touch_y, MotionEvent touch_obj){
	//	float touch_x = touch_obj.getX() / view_scale;
	//	float touch_y = touch_obj.getY() / view_scale;
		this._field_obj.touchCheck(touch_x, touch_y, touch_obj.getAction());
		this._action_ui_obj.touchCheck(touch_x, touch_y, touch_obj.getAction());
//		this._field_obj.doUpdate();
		
		this._touch_x = touch_x;
		this._touch_y = touch_y;
		//this._touch_action = touch_obj.getAction();
		
	}
	public void doDraw(Canvas canvas) {
		this._field_obj.doDrow(canvas);
		this._action_ui_obj.doDrow(canvas);
		
		// debug
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
	//	canvas.drawText("touch_action="+_touch_action, 300, 40*j, paint); j++;
		
		//*
		canvas.drawText("touch_x="+_touch_x, 0, 40*j, paint); j++;
		canvas.drawText("touch_y="+_touch_y, 0, 40*j, paint); j++;
		//*/
		
	}
}
