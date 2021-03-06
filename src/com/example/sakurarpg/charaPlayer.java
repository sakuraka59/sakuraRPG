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
		
		this._base_hp = 10;
		this._max_hp = 10;
		this._now_hp = 10;
		
		this._base_attack = 3;
		this._now_attack = 3;
		
	//	this._action_status = 2;
	}

	public boolean doUpdate(gameField field_obj) {
		// TODO: Implement this method
		if(super.doUpdate(field_obj) == false){
			return false;
		}
		
	
		
		return true;
	}

	@Override
	public void doDrow(Canvas canvas, gameField game_field_obj)	{

		// touch point effect
		float point_x = this._move_point_x - game_field_obj._camera_x;
		float point_y = this._move_point_y - game_field_obj._camera_y;
		
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
		
		// lock npc chara point
		if (this._lock_chara_obj instanceof charaBase){
			int set_x = 10;
			int set_y = 5;
			int setdrow_x = (int)(this._lock_chara_obj._drow_x - game_field_obj._camera_x);
			int setdrow_y = (int)(this._lock_chara_obj._drow_y - game_field_obj._camera_y) - 100;
			
			rect = new Rect(
				(int)(setdrow_x - set_x),
				(int)(setdrow_y - set_y),
				(int)(setdrow_x + set_x),
				(int)(setdrow_y + set_y)
			);
			canvas.drawRect(rect, paint);
		}
		paint.setColor(Color.argb(255, 255, 0, 0));
		paint.setTextSize(36);
		int j = 0;
		canvas.drawText("pc x ="+this._drow_x, 0, 300+50+(30*j), paint);j++;
		canvas.drawText("pc y="+this._drow_y, 0, 300+50+(30*j), paint);j++;
		
		/*
		
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("chara_speed="+this._move_speed_base, 0, 600+40*j, paint); j++;
		
		if (this._set_skill_obj instanceof skillBase) {
			canvas.drawText("chara_skill="+this._set_skill_obj._area_num, 0, 600+40*j, paint); j++;
			canvas.drawText("chara_skill="+this._set_skill_obj._area_max, 0, 600+40*j, paint); j++;
			
		}
		/*
		if (this._set_skill_obj instanceof skillBase) {
			canvas.drawText("skill_move_num = "+this._set_skill_obj._move_frame, 0, 600+40*j, paint); j++;
			canvas.drawText("skill_move_max = "+this._set_skill_obj._skill_chara_move[this._set_skill_obj._move_num][2], 0, 600+40*j, paint); j++;
		}
		*/
//		canvas.drawText("enemy_x="+this._lock_chara_obj._drow_x, 0, 600+40*j, paint); j++;
//		canvas.drawText("enemy_y="+this._lock_chara_obj._drow_y, 0, 600+40*j, paint); j++;
		
	}
	
	
}
