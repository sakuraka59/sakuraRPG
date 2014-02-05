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
	public testOtherObject(Resources resources) {
	//	Resources r = context.getResources();
		imgValue = BitmapFactory.decodeResource(resources, R.drawable.light);
	}
	public void doDraw(Canvas canvas) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		canvas.drawBitmap(imgValue, 200, 300, paint);
	}
}
