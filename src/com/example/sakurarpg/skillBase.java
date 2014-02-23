package com.example.sakurarpg;


public class skillBase {
	protected charaBase _set_chara_obj;
	public float _start_range;
	
	//-----------------------------------
	//	_skillType
	//	0:chara_base_attack
	//	1:chara_range_attack
	//	2:enemy_one
	//	3:enemy_area_of_effect
	//	4:touch_area_of_effect
	//-----------------------------------
	protected int _skill_type;
	
	//-----------------------------------
	//	_effectType(attack not use)
	//	0:player_one
	//	1:player_area_of_effect
	//	2:enemy_one
	//	3:enemy_area_of_effect
	//	4:touch_area_of_effect
	//-----------------------------------
	protected int _effect_type;
	
	//-----------------------------------
	//	[add angle, 1frame speed, frame num]
	//-----------------------------------
	protected float _skill_chara_move[][];
	protected int _move_frame = 0;
	protected int _move_num = 0;
	protected int _move_max = 0;

	//-----------------------------------
	//	[center_x, center_y, width, height, frame num]
	//-----------------------------------
	protected int _skill_area[][] = new int[16][5];
	protected int _area_frame = 0;
	protected int _area_num = 0;
	protected int _area_max = 0;
	
	public skillBase() {

	}
	public void skillInit(charaBase chara_obj, float[][] skill_chara_move) {
	
		this._set_chara_obj = chara_obj;
		this._set_chara_obj._move_speed_save = this._set_chara_obj._move_speed_base;
		this._set_chara_obj._stop_to_range = this._start_range;
		this._skill_chara_move = skill_chara_move;
		
	}
	//----------------------------------------------
	public void skillUpdate() {
		// *
		if (this._move_num >= this._move_max) {
			this.skillEnd();
	//		this._set_chara_obj._action_status = 12;
			return;
			
		}
		if (this._move_frame >= this._skill_chara_move[this._move_num][2]) {
			if (this.nextSkillMove() == true) {
				this.skillEnd();
				return;
			}
		}
		/*/
		if (this._move_frame == 0) {
			this.charaSkillMoveSet();
		}
		if (this._move_frame > 30) {
			this.skillEnd();
			return;
		}
		// */
		// chara move
		this._set_chara_obj._drow_x += this._set_chara_obj._move_speed_x;
		this._set_chara_obj._drow_y += this._set_chara_obj._move_speed_y;
		
		// next frame
		this._move_frame++;
		
	}
	//----------------------------------------------
	public void charaSkillMoveSet() {
		this._set_chara_obj._move_angle += _skill_chara_move[this._move_num][0];
		this._set_chara_obj._move_speed_base = _skill_chara_move[this._move_num][1];
		this._set_chara_obj._move_speed_x = (float)(Math.cos(this._set_chara_obj._move_angle * Math.PI / 180 ) * this._set_chara_obj._move_speed_base);
		this._set_chara_obj._move_speed_y = (float)(Math.sin(this._set_chara_obj._move_angle * Math.PI / 180 ) * this._set_chara_obj._move_speed_base);

	}
	//----------------------------------------------
	private boolean nextSkillMove(){
		if (this._move_num >= this._move_max) {
			return true;
		}
		
		while (this._move_frame >= this._skill_chara_move[this._move_num][2]) {
			this._move_frame = 0;
			this._move_num++;
			
			if (this._move_num >= this._move_max) {
				return true;
			}
			
			if (this._skill_chara_move[this._move_num][2] <= 0) {
				continue;
			}
			this.charaSkillMoveSet();
			
			
		}
		return false;
	}
	private void skillEnd() {
		// attack_end
		
		
		this._set_chara_obj._move_point_x = this._set_chara_obj._drow_x;
		this._set_chara_obj._move_point_y = this._set_chara_obj._drow_y;
		this._set_chara_obj._action_status = 12;
		this._set_chara_obj._move_speed_base = this._set_chara_obj._move_speed_save;
	}
}
