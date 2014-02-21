package com.example.sakurarpg;
import android.graphics.*;

public class actionUi extends uiBase {

	private charaPlayer _player_obj;
	private actionButton _attackButtonObj;
	public actionUi(charaPlayer player_obj) {
		this._touch_aria_x1 = 0;
		this._touch_aria_y1 = 1500;
		this._touch_aria_x2 = 1200;
		this._touch_aria_y2 = 1700;
		
		this._player_obj = player_obj;
		this._attackButtonObj = new actionButton(this._player_obj, 50, 1550, 150, 1650);
	}
	protected boolean touchEvent(float touch_x, float touch_y, int touch_action) {
		return false;
	}
	public void doDrow(Canvas canvas) {

		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		//*
		Rect rect = new Rect(
			(int)(this._touch_aria_x1),
			(int)(this._touch_aria_y1),
			(int)(this._touch_aria_x2),
			(int)(this._touch_aria_y2)
		);
		/*/
		Rect rect = new Rect(
			(int)(0),
			(int)(1500),
			(int)(1200),
			(int)(1700)
		);
		//*/
		canvas.drawRect(rect, paint);
		
		this._attackButtonObj.doDrow(canvas);
		
	}
	
}
