package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.*;
import android.animation.*;
import java.util.*;
import android.view.animation.*;

public class fieldMap {
	private Bitmap _test_img_value;
	
	private int chip_w = 80 + 1;
	private int chip_h = 80 + 1;
	private mapLoad map_load_obj = new mapLoad();

	private float _obj_x1 = 0;
	private float _obj_x2 = 0;
	private float _obj_y1 = 0;
	private float _obj_y2 = 0;
	
	public fieldMap(Resources resources) {
		super();
		
		// test map bitmap
		_test_img_value = BitmapFactory.decodeResource(resources, R.drawable.light);
		
		this.map_load_obj.setMapData();
		// TODO Auto-generated constructor stub
	//	this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
	}
	public void objectHitCheck(gameField game_field_obj) {
		
		ArrayList<charaBase>chara_list = game_field_obj._all_chara_obj_list;
		int max_num = 1;//chara_list.size();
		
		int[][] object_list = this.map_load_obj.getObjData();
		if (max_num > 0) {
			
			for (int i = 0; i < max_num; i++) {
				charaBase check_chara = chara_list.get(0);
				
				//	now point to map data point
				
				int point_x = (int)(check_chara._drow_x / (this.chip_w - 1));
				int point_y = (int)(check_chara._drow_y / (this.chip_h - 1));
				
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						int check_x = point_x + x - 1;
						int check_y = point_y + y - 1;
						
						if (check_x < 0 || check_y < 0) {
							continue;
						}
						
						
						float chara_x1 = check_chara._drow_x - (check_chara._drow_w / 2);
						float chara_x2 = check_chara._drow_x + (check_chara._drow_w / 2);
						float chara_y1 = check_chara._drow_y - (check_chara._drow_h / 4 * 3);
						float chara_y2 = check_chara._drow_y + (check_chara._drow_h / 4);

						float obj_x1 = check_x * (this.chip_w - 1);
						float obj_x2 = (check_x + 1) * (this.chip_w - 1);
						float obj_y1 = check_y * (this.chip_h - 1);
						float obj_y2 = (check_y + 1) * (this.chip_h - 1);

						this._obj_x1 = check_x;
						this._obj_x2 = check_chara._drow_y;
						this._obj_y1 = chara_x1;
						this._obj_y2 = object_list[check_y][check_x];
						
						if (object_list[check_y][check_x] > 0) {
							
							///	hit check
							
							
							if (chara_x1 <= obj_x2 && chara_x2 >= obj_x1 &&
								chara_y1 <= obj_y2 && chara_y2 >= obj_y1){
									
								check_chara._drow_x = check_chara._before_x;
								check_chara._drow_y = check_chara._before_y;
								
							}
							
							
						}
						
					}
				}
				
			}
		}
	}
	public void doDrow(Canvas canvas, gameField game_field_obj) {
		
		float base_x = game_field_obj._view_aria_x1 - game_field_obj._camera_x;
		float base_y = game_field_obj._view_aria_y1 - game_field_obj._camera_y;
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		
//		canvas.drawBitmap(this._test_img_value, base_x, base_y, paint);
		paint.setColor(Color.argb(255, 255, 255, 255));

		int set_color = 0;
		
		
		int[][] map_main = this.map_load_obj.getMapData();
		
		
		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++){
				
				if ((Math.floor(base_x + x * (this.chip_w -1))) < game_field_obj._view_aria_x1 + (this.chip_w *(-1)) ||
					(Math.floor(base_x + x * (this.chip_w -1))) > game_field_obj._view_aria_x2 ||
					(Math.floor(base_y + y * (this.chip_h -1))) < game_field_obj._view_aria_y1 + (this.chip_h *(-1)) ||
					(Math.floor(base_y + y * (this.chip_h -1))) > game_field_obj._view_aria_y2) {
					
					continue;
				}
				
				switch (map_main[y][x]) {
					case 100:
						set_color = Color.argb(255, 0, 255, 128);
						break;
					case 200:
						set_color = Color.argb(255, 128, 0, 64);
						break;
					default:
						set_color = Color.argb(255, 0, 0, 0);
						break;
				}
			
				this.testMapChip(
					canvas,
					set_color,
					(int)(Math.floor(base_x + x * (this.chip_w -1))),
					(int)(Math.floor(base_y + y * (this.chip_h -1))),
					paint
				);
			}
		}
		
		
		int[][] obj_main = map_load_obj.getObjData();

		for (int x = 0; x < 20; x++) {
			for (int y = 0; y < 20; y++){

				if ((Math.floor(base_x + x * (this.chip_w -1))) < game_field_obj._view_aria_x1 + (this.chip_w *(-1)) ||
					(Math.floor(base_x + x * (this.chip_w -1))) > game_field_obj._view_aria_x2 ||
					(Math.floor(base_y + y * (this.chip_h -1))) < game_field_obj._view_aria_y1 + (this.chip_h *(-1)) ||
					(Math.floor(base_y + y * (this.chip_h -1))) > game_field_obj._view_aria_y2) {

					continue;
				}

				if (obj_main[y][x] != 100) {
					continue;
				}
				
				switch (obj_main[y][x]) {
					case 100:
						set_color = Color.argb(255, 128, 128, 128);
						break;
					default:
						set_color = Color.argb(255, 0, 0, 0);
						break;
				}

				
				
				int drow_x = (int)(Math.floor(base_x + x * (this.chip_w -1)));
				int drow_y = (int)(Math.floor(base_y + y * (this.chip_h -1)));
				paint.setColor(set_color);
				Rect rect = new Rect(
					(int)Math.floor(drow_x),
					(int)(drow_y) - this.chip_h,
					(int)Math.ceil(drow_x + this.chip_w),
					(int)(drow_y + this.chip_h)
				);
				canvas.drawRect(rect, paint);
				
				set_color = Color.argb(255, 0, 0, 0);
				this.testMapChip(
					canvas,
					set_color,
					(int)(Math.floor(base_x + x * (this.chip_w -1))),
					(int)(Math.floor(base_y + y * (this.chip_h -1))),
					paint
				);
				/*
				paint.setColor(Color.argb(255, 255, 0, 0));
				paint.setTextSize(36);
				int j = 0;
				canvas.drawText("objx1="+this._obj_x1, drow_x, drow_y+50+(30*j), paint);j++;
				canvas.drawText("objx2="+this._obj_x2, drow_x, drow_y+50+(30*j), paint);j++;
				canvas.drawText("objy1="+this._obj_y1, drow_x, drow_y+50+(30*j), paint);j++;
				canvas.drawText("objy2="+game_field_obj._camera_y, drow_x, drow_y+50+(30*j), paint);j++;
				*/
				
			}
		}
		// */
		/*
		Rect rect = new Rect(
			(int)(base_x + 10),
			(int)(base_y + 20),
			(int)(base_x + 30),
			(int)(base_y + 40)
		);
		canvas.drawRect(rect, paint);
		*/
		
		paint.setColor(Color.argb(255, 255, 0, 0));
		paint.setTextSize(36);
	//	canvas.drawText("hit="+this.test, 0, 1300, paint);
		
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
