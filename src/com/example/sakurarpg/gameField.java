package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class gameField extends uiBase {
	private charaPlayer _player_obj;
	/*
	public float _touch_aria_x1 = 0;
	public float _touch_aria_y1 = 300;
	public float _touch_aria_x2 = 1600;
	public float _touch_aria_y2 = 1500;
	*/
	public gameField(Resources resources) {
		super();
		this._player_obj = new charaPlayer(resources);
		this._touch_aria_x1 = 0;
		this._touch_aria_y1 = 300;
		this._touch_aria_x2 = 1600;
		this._touch_aria_y2 = 1500;
	}
	public boolean doUpdate() {
		boolean ret_flag = true;
		
		if (this._player_obj.doUpdate() == false) {
			ret_flag = false;
		}
		return ret_flag;
	}
	@Override
	protected boolean touchEvent(float touch_x, float touch_y) {
		this._player_obj.setMovePoint(touch_x, touch_y);
		return false;
	}
	
	// 描画関数 
	public void doDrow(Canvas canvas) {
		/*
		Paint paint = new Paint();
	    paint.setColor(Color.argb(255, 0, 128, 128));
	    Rect rect = new Rect((int)this._touch_aria_x1, (int)this._touch_aria_y1, (int)this._touch_aria_x2, (int)this._touch_aria_y2);
	    canvas.drawRect(rect, paint);
	    */
//	    this.printDebug(canvas);
		this._player_obj.doDrow(canvas);
	}
}
