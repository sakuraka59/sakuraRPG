package com.example.sakurarpg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class testOtherObject {
	private Bitmap imgValue;
	private float _drow_x = 0;
	private float _drow_y = 0;
	public testOtherObject(Resources resources) {
	//	Resources r = context.getResources();
		imgValue = BitmapFactory.decodeResource(resources, R.drawable.light);
	}
	public void doUpdate(float touch_x, float touch_y){
		this._drow_x = touch_x;
		this._drow_y = touch_y;
	}
	public void doDraw(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(36);
		paint.setColor(Color.YELLOW);
		canvas.drawBitmap(imgValue, this._drow_x, this._drow_y, paint);
		canvas.drawText("view x = "+this._drow_x, 0, 200, paint);
		canvas.drawText("view y = "+this._drow_y, 0, 240, paint);
		
	}
}
