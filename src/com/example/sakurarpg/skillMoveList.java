package com.example.sakurarpg;

import com.example.sakurarpg.*;
import java.util.*;

public class skillMoveList {
	

	private int _frame_num = 0;
	private ArrayList<skillMove> _sill_move = new ArrayList<skillMove>();

	public skillMoveList (int frame_num) {
		this._frame_num = frame_num;
	}
	public int getFrameNum() {
		return this._frame_num;
	}

	public void setSkillMove(float add_angle, float one_frame_speed, int frame_num) {
		//		[add angle, 1frame speed, frame num]
		this._sill_move.add(new skillMove(add_angle, one_frame_speed, frame_num));
	}
	public skillMove getSkillMove(int index){
		return this._sill_move.get(index);
	}
	public int getSkillMoveSize(){
		return this._sill_move.size();
	}
}
