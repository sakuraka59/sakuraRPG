package com.example.sakurarpg.skill;

import com.example.sakurarpg.*;
import java.util.*;


public class normalAttack extends skillBase {
	
	//-----------------------------------
	//	[add angle, 1frame speed, frame num]
	//-----------------------------------
	public float[][] _skill_chara_move = {
			{	0,	1,	5},
			{	0,	5,	10}
	};
	
	//-----------------------------------
	//	[
	//		[type = 0, frame num, none, none, none, none]
	//		[type = 1, frame num, center_x, center_y, width, height]
	//		[type = 2, frame num, angle min, angle max, range min, range max]
	//	]
	//	frame num is first elemant only
	//	center x and y is charaBase x and y to Relative distance
	//-----------------------------------
//	protected skillBase[][] _skill_area = new skillBase[]
	/*{
		{
		//	{	0,	1,		20},
		//	{	2,	10,	-5,	5,	0,	10}
			new skillBase(2, 10, -5, 5, 0, 10),
		}
	};*/
//	protected ArrayList<ArrayList<skillBase>> _sill_area = new ArrayList<ArrayList<skillBase>>();
	//public float _start_range = 150;
	private ArrayList<skillDataList> _skill_data_obj = new ArrayList<skillDataList>();
	public normalAttack(charaBase chara_obj, gameField field_obj){
		this._start_range = 50;
		this._move_max = this._skill_chara_move.length;
		
		this._skill_data_obj.add(new skillDataList(5));
	//	this._skill_data_obj.get(0).setSkillArea(0, 0, 0, 0, 0);
		this._skill_data_obj.add(new skillDataList(10));
		this._skill_data_obj.get(1).setSkillArea(2, -5, 5, 0, 100);
		
		this._area_max = this._skill_data_obj.size();
		this.skillInit(chara_obj, field_obj, this._skill_chara_move, this._skill_data_obj);
		
	}
}
