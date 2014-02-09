package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
	protected double _move_angle = 0;
	
	
	protected Bitmap _img_value;
	
	//　コンストラクタ（初期化
	public charaBase(Resources resources) {
	//	Resources r = context.getResources();
		
	}
	public boolean doUpdate() {
		
		
		
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
		
		return true;
	}
	// 描画関数 
	public void doDrow(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		canvas.drawBitmap(this._img_value, this._drow_x - (this._drow_w / 2), this._drow_y - (this._drow_h / 2), paint);
		
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		int j=2;
		canvas.drawText("touch_a="+this._move_angle, 0, 600+40*j, paint); j++;
	
		
	}
	// テスト角度求める関数
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
}
