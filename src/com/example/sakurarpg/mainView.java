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
	private Context				mContext;	// �R���e�L�X�g
	private testImageView		mThread;	// �X���b�h�̃C���X�^���X
	
	public mainView(Context context) {
		super(context);

		// �R���e�L�X�g�������o�[�ϐ��ɑ��
		this.mContext = context;

		//�T�[�t�F�C�X�z���_�[���擾(SurfaceView)
		SurfaceHolder holder = getHolder();

		// �R�[���o�b�N��ݒ�(SurfaceHolder)
		holder.addCallback(this);

		// �L�[�C�x���g���擾�ł���悤�Ƀt�H�[�J�X���󂯎���悤�ɂ��Ă���
		setFocusable(true);
	}
	// ************** SurfaceHolder.Callback�̂R�Z�� **********************
	// surface�������ɃR�[���o�b�N�����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// �X���b�h�������C���X�^���X��
		mThread = new testImageView(holder, mContext);

		// �X���b�h���̃��C�����[�v�����������
		// ��ɓ��싖���Ă����Ȃ��ƃX���b�h�N�����Ă������I�����Ă��܂�
		mThread.enableRunning(true);

		// �X���b�h���N������
		try {
			mThread.start();
		} catch (IllegalThreadStateException e) {
			// �X���b�h�N�����s
		}
	}

	// surface�ύX���ɃR�[���o�b�N�����
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	// surface�j�����ɃR�[���o�b�N�����
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// �X���b�h���̃��C�����[�v������~����
		mThread.enableRunning(false);

		// �X���b�h���m���ɒ�~�����邽��while�Ń��[�v������
		while (true) {
			try {
				// �X���b�h���~������
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
