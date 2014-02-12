package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.*;
import android.animation.*;

public class fieldMap {
	private Bitmap _test_img_value;
	
	private int chip_w = 40 + 1;
	private int chip_h = 40 + 1;
	
	public fieldMap(Resources resources) {
		super();
		
		// test map bitmap
		_test_img_value = BitmapFactory.decodeResource(resources, R.drawable.light);
		
		// TODO Auto-generated constructor stub
	//	this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
	}
	
	public void doDrow(Canvas canvas, gameField game_field_obj) {
		
		float base_x = game_field_obj._touch_aria_x1 + game_field_obj._camera_x;
		float base_y = game_field_obj._touch_aria_y1 + game_field_obj._camera_y;
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		
//		canvas.drawBitmap(this._test_img_value, base_x, base_y, paint);
		paint.setColor(Color.argb(255, 255, 255, 255));

		for (int x = 0; x < 10; x++) {
			this.testMapChip(
				canvas,
				Color.argb(255, 0, 255, 128),
				(int)(Math.floor(base_x + x * (this.chip_w -1))),
				base_y + 0,
				paint
			);
		}
		
		/*
		Rect rect = new Rect(
			(int)(base_x + 10),
			(int)(base_y + 20),
			(int)(base_x + 30),
			(int)(base_y + 40)
		);
		canvas.drawRect(rect, paint);
		*/
		
		paint.setTextSize(18);
		paint.setColor(Color.YELLOW);
		canvas.drawText("hoge",
			game_field_obj._camera_x + (game_field_obj._touch_aria_x1 + game_field_obj._touch_aria_x2)/2,
			game_field_obj._camera_y + (game_field_obj._touch_aria_y1 + game_field_obj._touch_aria_y2)/2,
			paint
		);
//		canvas.drawText("touch_x="+this._move_point_x, 0, 600+40*j, paint); j++;
//		canvas.drawText("touch_y="+this._move_point_y, 0, 600+40*j, paint); j++;

	}
	private void testMapChip(Canvas canvas, int set_color, float drow_x, float drow_y, Paint paint){
		
		paint.setColor(set_color);
		Rect rect = new Rect(
			(int)Math.floor(drow_x),
			(int)(drow_y),
			(int)Math.ceil(drow_x + this.chip_w),
			(int)(drow_y + this.chip_h)
		);
		canvas.drawRect(rect, paint);
		/*
		paint.setTextSize(18);
		paint.setColor(Color.BLACK);
		canvas.drawText(""+(Math.ceil(drow_x + this.chip_w)),
			drow_x,
			drow_y+20,
			paint
		);
		canvas.drawText(""+((drow_x + this.chip_w)),
			drow_x,
			drow_y+35,
			paint
		);
		//*/
	}
}
