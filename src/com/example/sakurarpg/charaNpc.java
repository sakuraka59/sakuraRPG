package com.example.sakurarpg;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.*;

public class charaNpc extends charaBase {
	
	private int testswitch = 0;
	public charaNpc(Resources resources) {
		super();
		// TODO Auto-generated constructor stub
		this._img_value = BitmapFactory.decodeResource(resources, R.drawable.teto_front);
		this._drow_w = this._img_value.getWidth();
		this._drow_h = this._img_value.getHeight();

		// start point
		this._drow_x = 300;
		this._drow_y = 500;
		this._move_point_x = this._drow_x;
		this._move_point_y = this._drow_y;

		this._move_speed_base = 4.0f;
		
		this.setMovePoint(900, 800);
	}
	@Override
	public boolean doUpdate(gameField _field_obj) {
		// TODO: Implement this method
		if(super.doUpdate() == false){
			return false;
		}
		
		if (this._move_speed_x == 0 &&
			this._move_speed_y == 0) {
				
			if (testswitch == 0) {
				this.setMovePoint(300, 500);
				testswitch = 1;
			} else {
				this.setMovePoint(900, 800);
				testswitch = 0;
			}
		}

		return true;
	}
}
