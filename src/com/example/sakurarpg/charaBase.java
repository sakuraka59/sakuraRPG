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
	protected float _move_speed_base = 0;
	
	
	protected Bitmap _img_value;
	
	//　コンストラクタ（初期化
	public charaBase(Resources resources) {
	//	Resources r = context.getResources();
		
	}
	
	// 描画関数 
	public void doDrow(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		canvas.drawBitmap(this._img_value, this._drow_x - (this._drow_w / 2), this._drow_y - (this._drow_h / 2), paint);
		
	}
}
