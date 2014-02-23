package com.example.sakurarpg.skill;

import com.example.sakurarpg.charaBase;
import com.example.sakurarpg.skillBase;


public class normalAttack extends skillBase {
	//-----------------------------------
	//	[add angle, 1frame speed, frame num]
	//-----------------------------------

	public float[][] _skill_chara_move = {
			{	0,	1,		20},
			{	0,	5,	10}
	};

	//public float _start_range = 150;
	public normalAttack(charaBase chara_obj){
		this._start_range = 50;
		this._move_max = this._skill_chara_move.length;
		this.skillInit(chara_obj, this._skill_chara_move);
		
	}
}
