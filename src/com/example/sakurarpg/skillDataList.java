package com.example.sakurarpg;

import com.example.sakurarpg.*;
import java.util.*;

public class skillDataList{
	private int _frame_num = 0;
	private ArrayList<skillData> _sill_area = new ArrayList<skillData>();
	
	public skillDataList (int frame_num) {
		this._frame_num = frame_num;
	}
	public int getFrameNum() {
		return this._frame_num;
	}
	
	public void setSkillArea(int type, float check_a1, float check_a2, float check_b1, float check_b2) {
		this._sill_area.add(new skillData(type, check_a1, check_a2, check_b1, check_b2));
	}
	public skillData getSkillArea(int index){
		return this._sill_area.get(index);
	}
	public int getSkillSize(){
		return this._sill_area.size();
	}
}
