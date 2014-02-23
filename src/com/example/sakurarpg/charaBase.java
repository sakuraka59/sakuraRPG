package com.example.sakurarpg;

//import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.*;


public class charaBase {
	// キャラクターの表示座標等
	protected float _drow_x = 0;
	protected float _drow_y = 0;
	protected float _drow_w = 0;
	protected float _drow_h = 0;
	
	protected float _move_point_x = 0;
	protected float _move_point_y = 0;
	protected float _move_speed_x = 0;
	protected float _move_speed_y = 0;
	protected float _move_speed_base = 0;
	protected float _move_speed_save = 0;
	protected double _move_angle = 0;
	
	protected float _stop_to_range = 50;
	
	
	protected charaBase _lock_chara_obj;
	
	protected skillBase _set_skill_obj;
	
	//-----------------------------------
	//	0:	stop
	//	1:	move
	//	2:	attack move
	//	11:	attack
	//	12:	attack end
	//	13:	damage hit
	//-----------------------------------
	protected int _action_status = 0;
	
	
	protected Bitmap _img_value;
	
	//　コンストラクタ（初期化
	public charaBase() {
	//	Resources r = context.getResources();
		
	}
	//-------------------------------------------
	public boolean doUpdate() {

		switch (this._action_status) {
			case 0:
			case 1:
				this.normalMove();
				break;
			case 2:
				this.targetApproachMove();
				break;
			case 11:
				this._set_skill_obj.skillUpdate();
				break;
			case 12:
				this._action_status = 0;
				break;
		}
		return true;
	}
	//-------------------------------
	//	action method
	//-------------------------------
	private void normalMove() {
		if (this._action_status <= 10) {
			if (this._move_speed_x != 0) {

				if ((this._move_speed_x > 0 &&
					this._drow_x + this._move_speed_x > this._move_point_x) ||
					(this._move_speed_x < 0 &&
					this._drow_x + this._move_speed_x < this._move_point_x) ) {

					this._drow_x = this._move_point_x;
					this._move_speed_x = 0;

				}

				this._drow_x += this._move_speed_x;
			}

			if (this._move_speed_y != 0) {

				if ((this._move_speed_y > 0 &&
					this._drow_y + this._move_speed_y > this._move_point_y) ||
					(this._move_speed_y < 0 &&
					this._drow_y + this._move_speed_y < this._move_point_y) ) {

					this._drow_y = this._move_point_y;
					this._move_speed_y = 0;

				}

				this._drow_y += this._move_speed_y;
			}
		}
	}
	//-------------------------------------------
	private void targetApproachMove() {
		
		if (this._lock_chara_obj instanceof charaBase) {
			// action_status変えたくないので、個別に処理する
			//this.setMovePoint(this._lock_chara_obj._drow_x, this._lock_chara_obj._drow_y);
			this._move_point_x = this._lock_chara_obj._drow_x;
			this._move_point_y = this._lock_chara_obj._drow_y;
			this.setMoveSpeed();
			
			double target_range = this.getTargetRange(this._drow_x, this._drow_y, this._lock_chara_obj._drow_x, this._lock_chara_obj._drow_y);
			if (this._stop_to_range > target_range) {
				this._move_speed_x = 0;
				this._move_speed_y = 0;
				this._move_point_x = this._drow_x;
				this._move_point_y = this._drow_y;
				
				// go to skill
				this._action_status = 11;
				this._set_skill_obj.charaSkillMoveSet();
			} else {
				this.normalMove();
			}
		}
		
	}
	//-------------------------------------------
	// 描画関数 
	public void doDrow(Canvas canvas, gameField game_field_obj) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		
		float check_x = game_field_obj._view_aria_x1 + game_field_obj._camera_x - this._drow_w;
		float check_x2 = game_field_obj._view_aria_x2 + game_field_obj._camera_x + this._drow_w;
		float check_y = game_field_obj._view_aria_y1 + game_field_obj._camera_y - this._drow_h;
		float check_y2 = game_field_obj._view_aria_y2 + game_field_obj._camera_y + this._drow_h * 2;
		/*
		if ((Math.floor(base_x + (this._drow_x -1))) < game_field_obj._touch_aria_x1 + (this._drow_w *(-1)) ||
			(Math.floor(base_x + (this._drow_x -1))) > game_field_obj._touch_aria_x2 ||
			(Math.floor(base_y + (this._drow_y -1))) < game_field_obj._touch_aria_y1 - (this._drow_h *(-1)) ||
			(Math.floor(base_y + (this._drow_y -1))) > game_field_obj._touch_aria_y2) {
			return;
		}
		*/
		if (this._drow_x < check_x || this._drow_x > check_x2 ||
			this._drow_y < check_y || this._drow_y > check_y2) {
			return;
		}
		canvas.drawBitmap(this._img_value, this._drow_x - (this._drow_w / 2) - game_field_obj._camera_x, this._drow_y - (this._drow_h / 4 * 3) - game_field_obj._camera_y, paint);
	}

	//-------------------------------------------
	public void setMovePoint(float touch_x, float touch_y) {
//		this._move_point_x = touch_x + game_field_obj._camera_x;
//		this._move_point_y = touch_y + game_field_obj._camera_y;
		if (this._action_status <= 10) {
			this._move_point_x = touch_x;
			this._move_point_y = touch_y;
			
			this._action_status = 1;
		
			this.setMoveSpeed();
		}
	}
	//-------------------------------------------
	public void setMoveSpeed() {
		this._move_angle = angle();
		this._move_speed_x = (float)(Math.cos(this._move_angle * Math.PI / 180 ) * this._move_speed_base);

		this._move_speed_y = (float)(Math.sin(this._move_angle * Math.PI / 180 ) * this._move_speed_base);

	}
	// 角度求める関数
	public double angle() {
		
		float mx = this._drow_x - this._move_point_x;
		float my = this._drow_y - this._move_point_y;

		double degree;

		if (mx == 0 && my == 0) {
			degree = 0;
		} else {
			degree = Math.atan2(my, mx) * (180 / Math.PI);
		}
		return degree + 180;
	}
	
	public double getTargetRange(float x1, float y1, float x2, float y2){
		double dx = Math.pow(x1 - x2, 2);
		double dy = Math.pow(y1 - y2, 2);
		double distance = Math.sqrt(dx + dy);
		return distance;
	}
	public void setLockEnemy(charaNpc enemy_obj) {
		this._lock_chara_obj = enemy_obj;
	}
	
}
