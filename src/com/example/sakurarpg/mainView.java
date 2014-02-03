package com.example.sakurarpg;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class mainView extends SurfaceView {
	
	public mainView(Context context) {
		super(context);
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
	//‰æ‘œ“Ç‚İ‚İ
    private Resources res = this.getContext().getResources();
    private final Bitmap IMG_LIGHT = BitmapFactory.decodeResource(res, R.drawable.light);
    
    private int touch_x = 0;
    private int touch_y = 0;
	public mainView(Context context) {
		super(context);
	}

	    
	//•`Êˆ—
    @Override
    public void onDraw(Canvas c) {
    	c.drawBitmap(IMG_LIGHT, touch_x, touch_y, paint);
    }
    
    //ƒ^ƒbƒ`“ü—Íˆ—
    public boolean onTouchEvent(MotionEvent me) {
    	touch_x = (int)(me.getX());
    	touch_y = (int)(me.getY());
    	invalidate();
        return true;
    }
}
*/
