package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class gameField extends uiBase {
	private charaPlayer _player_obj;
	private fieldMap _map_obj;
	/*
	public float _touch_aria_x1 = 0;
	public float _touch_aria_y1 = 300;
	public float _touch_aria_x2 = 1600;
	public float _touch_aria_y2 = 1500;
//	*/
	public float _camera_x = 0;
	public float _camera_y = 0;
	public gameField(Resources resources) {
		super();
		this._player_obj = new charaPlayer(resources);
		this._map_obj = new fieldMap(resources);
		
		this._touch_aria_x1 = 0;
		this._touch_aria_y1 = 300;
		this._touch_aria_x2 = 1600;
		this._touch_aria_y2 = 1500;
	}
	public boolean doUpdate() {
		boolean ret_flag = true;
		
		// move camera ues data
		float move_end_camera_x = this._player_obj._move_point_x - this._player_obj._drow_x;
		float move_end_camera_y = this._player_obj._move_point_y - this._player_obj._drow_y;
		
		if (this._player_obj.doUpdate(this) == false) {
			ret_flag = false;
		}
		
		if (this._player_obj._move_speed_x == 0 &&
			this._player_obj._move_speed_y == 0 &&
			move_end_camera_x != 0 &&
			move_end_camera_y != 0) {

			this._camera_x += move_end_camera_x;
			this._camera_y += move_end_camera_y;
			
		} else if (this._player_obj._move_speed_x != 0 ||
			this._player_obj._move_speed_y != 0) {

			this._camera_x -= this._player_obj._move_speed_x;
			this._camera_y -= this._player_obj._move_speed_y;
		}
		
		return ret_flag;
	}
	@Override
	protected boolean touchEvent(float touch_x, float touch_y) {
		this._player_obj.setMovePoint(touch_x - this._camera_x, touch_y - this._camera_y);
//		this._camera_x = touch_x;
		return false;
	}
	
	// 描画関数 
	public void doDrow(Canvas canvas) {
		
		Paint paint = new Paint();
	    paint.setColor(Color.argb(255, 0, 64, 128));
	    Rect rect = new Rect((int)this._touch_aria_x1, (int)this._touch_aria_y1, (int)this._touch_aria_x2, (int)this._touch_aria_y2);
	    canvas.drawRect(rect, paint);
//	    
//	    this.printDebug(canvas);
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_x="+this._camera_x, 0, 0+40*j, paint); j++;
		canvas.drawText("touch_y="+this._camera_y, 0, 0+40*j, paint); j++;
//	
		this._map_obj.doDrow(canvas, this);
		this._player_obj.doDrow(canvas, this);
	}
	
}
