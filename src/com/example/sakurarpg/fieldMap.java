package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class fieldMap {
	
	public fieldMap(Resources resources) {
		super();
		// TODO Auto-generated constructor stub
	//	this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
	}
	
	public void doDrow(Canvas canvas, gameField game_field_obj) {
		Paint paint=new Paint();
		paint.setAntiAlias(true);
//		canvas.drawBitmap(this._img_value, this._drow_x - (this._drow_w / 2) + game_field_obj._camera_x, this._drow_y - (this._drow_h / 2) + game_field_obj._camera_y, paint);

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
	
}
