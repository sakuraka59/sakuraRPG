package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class gameField extends uiBase {
	private charaPlayer _player_obj;
	private charaNpc[] _enemy_obj_list;
	private fieldMap _map_obj;
	/*
	public float _touch_aria_x1 = 0;
	public float _touch_aria_y1 = 300;
	public float _touch_aria_x2 = 1600;
	public float _touch_aria_y2 = 1500;
//	*/
	public float _camera_x = 0;
	public float _camera_y = 0;
	
	public float hox;
	public gameField(Resources resources, charaPlayer player_obj) {
		super();
		this._player_obj = player_obj;
		this._map_obj = new fieldMap(resources);
		
		this._enemy_obj_list = new charaNpc[10];
		this._enemy_obj_list[0] = new charaNpc(resources);
		
		this._touch_aria_x1 = 0;
		this._touch_aria_y1 = 300;
		this._touch_aria_x2 = 1600;
		this._touch_aria_y2 = 1500;
		
		this._player_obj.setLockEnemy(this._enemy_obj_list[0]);
	}
	public boolean doUpdate() {
		boolean ret_flag = true;
		
		if (this._player_obj.doUpdate(this) == false) {
			ret_flag = false;
		}
		
		// move camera ues data
		float move_end_camera_x = this._player_obj._move_point_x - this._player_obj._drow_x;
		float move_end_camera_y = this._player_obj._move_point_y - this._player_obj._drow_y;
		
		
		
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
		
		// enemy update
		if (this._enemy_obj_list[0].doUpdate(this) == false) {
			ret_flag = false;
		}
		
		
		return ret_flag;
	}
	@Override
	protected boolean touchEvent(float touch_x, float touch_y, int touch_action) {
	
		float touch_point_x = touch_x - this._camera_x;
		float touch_point_y = touch_y - this._camera_y;
		
		int test_width = 150;
		this.hox = touch_point_y;
		if (this._touch_lock_status >= 0) {
			if (//*
			touch_point_x >= this._enemy_obj_list[0]._drow_x - (test_width / 2) &&
			touch_point_x <= this._enemy_obj_list[0]._drow_x + (test_width / 2) &&
			/*/
			touch_point_y >= this._enemy_obj_list[0]._drow_y - (test_width / 2) &&
			touch_point_y <= this._enemy_obj_list[0]._drow_y + (test_width / 2)
			// */
			this._touch_lock_status == 0
			) {
				
				// npc target lock
				this._player_obj._lock_chara_obj = this._enemy_obj_list[0];
				this._player_obj._action_status = 2;
				this._touch_lock_status = -1;
				
			} else if (this._touch_lock_status >= 0){
				
				// chara move
			//	this._player_obj.setMovePoint(touch_point_x, touch_point_y);
				this._touch_lock_status = 1;
			}
		}
		if (touch_action == 1) {
			this._touch_lock_status = 0;
		}
		
		return false;
		
	}
	
	// 描画関数 
	public void doDrow(Canvas canvas) {
		
		this._map_obj.doDrow(canvas, this);
		this._player_obj.doDrow(canvas, this);
		this._enemy_obj_list[0].doDrow(canvas, this);
		
/*
		Paint paint = new Paint();
	    paint.setColor(Color.argb(255, 0, 64, 128));
	    Rect rect = new Rect((int)this._touch_aria_x1, (int)this._touch_aria_y1, (int)this._touch_aria_x2, (int)this._touch_aria_y2);
	    canvas.drawRect(rect, paint);
//	    
//	    this.printDebug(canvas);
/*/
//*
		Paint paint = new Paint();
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_x="+this.hox, 0, 300+40*j, paint); j++;
		canvas.drawText("touch_a="+this._player_obj._action_status, 0, 300+40*j, paint); j++;
		canvas.drawText("touch_ex="+this._enemy_obj_list[0]._drow_y, 0, 300+40*j, paint); j++;
// */
// */
//	
		
	}
	
}
