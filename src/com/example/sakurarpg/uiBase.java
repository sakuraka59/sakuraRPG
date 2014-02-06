package com.example.sakurarpg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class uiBase {
	private boolean _touch_event_flag = true;
	protected float _touch_aria_x1 = 0;
	protected float _touch_aria_y1 = 0;
	protected float _touch_aria_x2 = 0;
	protected float _touch_aria_y2 = 0;

	private boolean hoge = false;
	public uiBase() {
	}
	public void printDebug(Canvas canvas){
		Paint paint = new Paint();
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_x="+this._touch_aria_x1, 0, 200+40*j, paint); j++;
		canvas.drawText("touch_y="+this._touch_aria_y1, 0, 200+40*j, paint); j++;
		
		if(this.hoge == true) {
			canvas.drawText("touch_x="+this._touch_aria_x2, 0, 200+40*j, paint); j++;
		}
		canvas.drawText("touch_y="+_touch_aria_y2, 0, 200+40*j, paint); j++;
	}
	
	public void changeTouchEvent(boolean flag) {
		this._touch_event_flag = flag;
	}
	// タッチ時、指定範囲内チェック
	public boolean touchCheck(float touch_x, float touch_y) {
		if (this._touch_event_flag == true) {
			if (this._touch_aria_x1 <= touch_x && this._touch_aria_x2 >= touch_x &&
				this._touch_aria_y1 <= touch_y && this._touch_aria_y2 >= touch_y ) {
				this.hoge = true;
				return this.touchEvent(touch_x, touch_y);
			}
		}
		return false;
		
	}
	// タッチ時のイベント
	protected boolean touchEvent(float touch_x, float touch_y) {
		return false;
	}
}
