package com.example.sakurarpg;
import android.graphics.*;

public class actionButton extends uiBase {
	
	private charaPlayer _player_obj;
	public actionButton(charaPlayer player_obj,int x1, int y1, int x2, int y2) {
		this._touch_aria_x1 = x1;
		this._touch_aria_y1 = y1;
		this._touch_aria_x2 = x2;
		this._touch_aria_y2 = y2;
		
		this._player_obj = player_obj;
	}
	public void setAction(){
		
		this._player_obj._action_status = 2;
	}
	public void doDrow(Canvas canvas){ //, gameField game_field_obj)	{

		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));
		Rect rect = new Rect(
			(int)(this._touch_aria_x1 - 2),
			(int)(this._touch_aria_y1 - 2),
			(int)(this._touch_aria_x2 + 2),
			(int)(this._touch_aria_y2 + 2)
		);
		canvas.drawRect(rect, paint);
	}
}
