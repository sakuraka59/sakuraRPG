package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.*;

public class charaPlayer extends charaBase{
	
	
	public charaPlayer(Resources resources) {
		super();
		// TODO Auto-generated constructor stub
		this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
		this._drow_w = this._img_value.getWidth();
		this._drow_h = this._img_value.getHeight();
		
		// start point
		this._drow_x = 600;
		this._drow_y = 900;
		this._move_point_x = this._drow_x;
		this._move_point_y = this._drow_y;

		this._move_speed_base = 4.0f;
		
	//	this._action_status = 2;
	}

	@Override
	public boolean doUpdate(gameField _field_obj) {
		// TODO: Implement this method
		if(super.doUpdate() == false){
			return false;
		}
		
	
		
		return true;
	}

	@Override
	public void doDrow(Canvas canvas, gameField game_field_obj)	{

		// touch point effect
		float point_x = this._move_point_x + game_field_obj._camera_x;
		float point_y = this._move_point_y + game_field_obj._camera_y;
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.argb(255, 255, 255, 255));
		Rect rect = new Rect(
			(int)(point_x - 2),
			(int)(point_y - 2),
			(int)(point_x + 2),
			(int)(point_y + 2)
		);
		canvas.drawRect(rect, paint);
		
		int setting_x = 6;
		int setting_y = 4;
		rect = new Rect(
			(int)(point_x - 2 - setting_x),
			(int)(point_y - 2 - setting_y),
			(int)(point_x + 2 - setting_x),
			(int)(point_y + 2 - setting_y)
		);
		canvas.drawRect(rect, paint);
		
		
		rect = new Rect(
			(int)(point_x - 2 + setting_x),
			(int)(point_y - 2 - setting_y),
			(int)(point_x + 2 + setting_x),
			(int)(point_y + 2 - setting_y)
		);
		canvas.drawRect(rect, paint);
		
		
		rect = new Rect(
			(int)(point_x - 2 + setting_x),
			(int)(point_y - 2 + setting_y),
			(int)(point_x + 2 + setting_x),
			(int)(point_y + 2 + setting_y)
		);
		canvas.drawRect(rect, paint);
		
		
		rect = new Rect(
			(int)(point_x - 2 - setting_x),
			(int)(point_y - 2 + setting_y),
			(int)(point_x + 2 - setting_x),
			(int)(point_y + 2 + setting_y)
		);
		canvas.drawRect(rect, paint);
		
		// TODO: Implement this method
		super.doDrow(canvas, game_field_obj);
		
		if (this._lock_chara_obj instanceof charaBase){
			int set_x = 10;
			int set_y = 5;
			int setdrow_x = (int)(this._lock_chara_obj._drow_x + game_field_obj._camera_x);
			int setdrow_y = (int)(this._lock_chara_obj._drow_y + game_field_obj._camera_y) - 100;
			
			rect = new Rect(
				(int)(setdrow_x - set_x),
				(int)(setdrow_y - set_y),
				(int)(setdrow_x + set_x),
				(int)(setdrow_y + set_y)
			);
			canvas.drawRect(rect, paint);
		}
		
		
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_a="+this._move_angle, 0, 600+40*j, paint); j++;
//		canvas.drawText("enemy_x="+this._lock_chara_obj._drow_x, 0, 600+40*j, paint); j++;
//		canvas.drawText("enemy_y="+this._lock_chara_obj._drow_y, 0, 600+40*j, paint); j++;
		
	}
	
	
}
