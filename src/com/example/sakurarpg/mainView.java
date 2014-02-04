package com.example.sakurarpg;

//	import android.view.MotionEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mainView extends SurfaceView implements SurfaceHolder.Callback {
	private Context mContext; // コンテキスト
	private testImageView mThread; // スレッドのインスタンス

	public mainView(Context context) {
		super(context);

		// コンテキストをメンバー変数に代入
		this.mContext = context;

		//サーフェイスホルダーを取得(SurfaceView)
		SurfaceHolder holder = getHolder();

		// コールバックを設定(SurfaceHolder)
		holder.addCallback(this);

		// キーイベントが取得できるようにフォーカスを受け取れるようにしておく
		setFocusable(true);
	}
	// ************** SurfaceHolder.Callbackの３兄弟 **********************
	// surface生成時にコールバックされる
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// スレッド生成しインスタンス化
		mThread = new testImageView(holder, mContext);

		// スレッド内のメインループ動作を許可する
		// 先に動作許可しておかないとスレッド起動しても直ぐ終了してしまう
		mThread.enableRunning(true);

		// スレッドを起動する
		try {
			mThread.start();
		} catch (IllegalThreadStateException e) {
			// スレッド起動失敗
		}
	}

	// surface変更時にコールバックされる
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	// surface破棄時にコールバックされる
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// スレッド内のメインループ動作を停止する
		mThread.enableRunning(false);
	
		// スレッドを確実に停止させるためwhileでループさせる
		while (true) {
			try {
				// スレッドを停止させる
				mThread.join();
				break;
			} catch (InterruptedException e) {
			
			}
		} 
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
