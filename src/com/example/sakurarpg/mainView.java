package com.example.sakurarpg;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class mainView extends SurfaceView {
	
	public mainView(Context context) {
		super(context);
		//hoge
	}
}

/*
import android.view.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

public class mainView extends View {
	private Paint paint = new Paint();
	//画像読み込み
    private Resources res = this.getContext().getResources();
    private final Bitmap IMG_LIGHT = BitmapFactory.decodeResource(res, R.drawable.light);
    
    private int touch_x = 0;
    private int touch_y = 0;
	public mainView(Context context) {
		super(context);
	}
	    
	//drow main view
    @Override
    public void onDraw(Canvas c) {
    	c.drawBitmap(IMG_LIGHT, touch_x, touch_y, paint);
    }
    
    //touch main event
    public boolean onTouchEvent(MotionEvent me) {
    	touch_x = (int)(me.getX());
    	touch_y = (int)(me.getY());
    	invalidate();
        return true;
    }
}
*/
