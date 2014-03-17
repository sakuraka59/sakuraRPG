package com.example.sakurarpg;


import java.util.*;
import android.graphics.*;

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
	protected ArrayList<skillMove> _skill_chara_move = new ArrayList<skillMove>();
	
	protected int _move_frame = 0;
	protected int _move_frame_max = 0;
	protected int _move_num = 0;
	protected int _move_max = 0;
	private ArrayList<charaBase> _all_chara_obj = new ArrayList<charaBase>();
	private ArrayList<charaBase> _hit_chara_obj = new ArrayList<charaBase>();

	// attack hit area
	private ArrayList<skillDataList> _skill_data_obj = new ArrayList<skillDataList>();
	protected int _area_frame = 0;
	protected int _area_num = 0;
	protected int _area_max = 0;
	
	public skillBase() {

	}
	public void skillInit(charaBase chara_obj, gameField field_obj, ArrayList<skillMove> skill_chara_move, ArrayList<skillDataList> skill_data_obj) {
	
		this._set_chara_obj = chara_obj;
		this._set_chara_obj._move_speed_save = this._set_chara_obj._move_speed_base;
		this._set_chara_obj._stop_to_range = this._start_range;
		this._skill_chara_move = skill_chara_move;
		this._skill_data_obj = skill_data_obj;
		
		//this._all_chara_obj = field_obj.getCharaList();
		this._all_chara_obj = field_obj._all_chara_obj_list;
		
	}
	//----------------------------------------------
	public void skillUpdate() {
		//*
		
		
		if (this._move_num >= this._move_max && this._area_num >= this._area_max) {
			this.skillEnd();
	//		this._set_chara_obj._action_status = 12;
			return;
			
		}
		
		
		boolean check_hit = this.checkSkillHit();
		
		if (this._move_frame >= this._skill_chara_move.get(this._move_num)._frame_num) {
			if (this.nextSkillMove() == true && check_hit == true) {
				this.skillEnd();
				return;
			}
		}
		
		if ( check_hit == true) {
			this.skillEnd();
			return;
		}
		// chara move
		this._set_chara_obj._drow_x += this._set_chara_obj._move_speed_x;
		this._set_chara_obj._drow_y += this._set_chara_obj._move_speed_y;
		
		// next frame
		this._move_frame++;
		this._area_frame++;
		// */
	}
	//----------------------------------------------
	public void charaSkillMoveSet() {
		
		if (_skill_chara_move.get(this._move_num) instanceof skillMove) {
			this._set_chara_obj._move_angle += _skill_chara_move.get(this._move_num)._add_angle;
			this._set_chara_obj._move_speed_base = _skill_chara_move.get(this._move_num)._one_frame_speed;
			this._set_chara_obj._move_speed_x = (float)(Math.cos(this._set_chara_obj._move_angle * Math.PI / 180 ) * this._set_chara_obj._move_speed_base);
			this._set_chara_obj._move_speed_y = (float)(Math.sin(this._set_chara_obj._move_angle * Math.PI / 180 ) * this._set_chara_obj._move_speed_base);
		}
		
	}
	//----------------------------------------------
	private boolean nextSkillMove(){
		
		if (this._move_num >= this._move_max) {
			return true;
		}
		
		int move_frame = 0;//this._skill_chara_move.get(this._area_num).;
		/*
		if (this._skill_chara_move instanceof ArrayList<skillMove>) {
			move_frame = this._skill_chara_move.get(this._area_num)._frame_num;
		}
		*/
		while (this._move_frame >= move_frame) {
			this._move_frame = 0;
			this._move_num++;
			//!(this._skill_chara_move.get(this._move_num) instanceof skillMove)
			
			if (this._move_num >= this._move_max) {
				return true;
			}
			
			if (move_frame <= 0) {
				continue;
			}
		//	this.charaSkillMoveSet();
			//*/
			
			
		}
		return false;
		//	*/
	}
	//----------------------------------------------
	private boolean checkSkillHit() {

		
		if (this._area_num >= this._area_max) {
			return true;
		}
		
		int area_frame = this._skill_data_obj.get(this._area_num).getFrameNum();
		if (this._skill_data_obj instanceof ArrayList<skillDataList>) {
			area_frame = this._skill_data_obj.get(this._area_num).getFrameNum();
		}
		
		int count = 0;
		while (this._area_frame >= area_frame) {
			this._area_frame = 0;
			this._area_num++;

			if (this._area_num >= this._area_max) {
				return true;
			}
			if (this._skill_data_obj instanceof ArrayList<skillDataList>) {
				area_frame = this._skill_data_obj.get(this._area_num).getFrameNum();
			} else {
				area_frame = 0;
			}
			if (area_frame <= 0) {
				continue;
			}
		//	this.charaSkillMoveSet();
			if (count == 0) {
				break;
			}
			count++;
		}
		
		skillDataList skill_data = this._skill_data_obj.get(this._area_num);
		int now_area_num = skill_data.getSkillSize();
		// skill hit check
		for (int i = 0; i < now_area_num; i++) {
			skillData skill_area_data = skill_data.getSkillArea(i);
			if (skill_area_data._skill_type == 3) {
				this._hit_chara_obj = new ArrayList<charaBase>();
				return false;
			}
			for (int j = 0; j < this._all_chara_obj.size(); j++) {
				
				charaBase set_chara_obj = this._set_chara_obj;
				charaBase check_chara_obj = this._all_chara_obj.get(j);
				
				boolean check_flag = false;
				for (int k = 0; k < this._hit_chara_obj.size(); k++) {
					
					if (this._hit_chara_obj.get(k) == check_chara_obj) {
						check_flag = true;
						break;
					}
					
				}
				
				if (check_flag == true) {
					continue;
				}
				
				// no hit chara
				if (set_chara_obj == check_chara_obj) {
					continue;
				}
				
				// hit check
				switch (skill_area_data._skill_type) {
					case 2:
						
						double range = set_chara_obj.getTargetRange(
							set_chara_obj._drow_x,
							set_chara_obj._drow_y,
							check_chara_obj._drow_x,
							check_chara_obj._drow_y
						);
						double target_angle = set_chara_obj.getTargetAngle(
							set_chara_obj._drow_x,
							set_chara_obj._drow_y,
							check_chara_obj._drow_x,
							check_chara_obj._drow_y
						);
						double chara_angle = set_chara_obj._move_angle;
						double target_angle_min = chara_angle + skill_area_data._check_a_1;
						double target_angle_max = chara_angle + skill_area_data._check_a_2;
						boolean angle_check_flag = false;
						
						double max_angle = 360;
						// angle check
						if (target_angle_min <= chara_angle &&
							target_angle_max >= chara_angle) {
							
							angle_check_flag = true;
						}
						if (angle_check_flag == false && target_angle_min < 0) {
							
							if (chara_angle >= target_angle_min + max_angle) {
								
								angle_check_flag = true;
							}
						}
						if (angle_check_flag == false && target_angle_max >= max_angle) {
							
							if (chara_angle <= target_angle_max - max_angle) {

								angle_check_flag = true;
							}
						}
						
						if (
							(float)range >= skill_area_data._check_b_1 &&
							(float)range <= skill_area_data._check_b_2 &&
							angle_check_flag ==true ) {
							check_chara_obj.damage();
							this._hit_chara_obj.add(check_chara_obj);
						}
						break;
				}
			}
		}
		return false;
		// */
	}
	//----------------------------------------------
	private void skillEnd() {
		// attack_end
		this._set_chara_obj._move_point_x = this._set_chara_obj._drow_x;
		this._set_chara_obj._move_point_y = this._set_chara_obj._drow_y;
		this._set_chara_obj._action_status = 12;
		this._set_chara_obj._move_speed_base = this._set_chara_obj._move_speed_save;
	}
	
	public void debugSkillRangeDrow(Canvas canvas, Paint paint, gameField game_field_obj) {
		
		paint.setColor(Color.RED);
		
		Path path = new Path();
		float base_x = this._set_chara_obj._drow_x - game_field_obj._camera_x ;
		float base_y = this._set_chara_obj._drow_y - game_field_obj._camera_y;
		
		if (this._skill_data_obj instanceof ArrayList<skillData> &&
			this._skill_data_obj.size() > 0 &&
			this._area_num < this._skill_data_obj.size() &&
			this._area_num < this._area_max) {

			skillDataList skill_data = this._skill_data_obj.get(this._area_num);
		
			int now_area_num = skill_data.getSkillSize();
		
			
			for (int i = 0; i < now_area_num; i++) {
				
				skillData skill_area_data = skill_data.getSkillArea(i);
			
				float center_angle = (skill_area_data._check_a_1 + skill_area_data._check_a_2) / 2;
				// left top
				float set_x1 = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + skill_area_data._check_a_1) * Math.PI / 180 ) * skill_area_data._check_b_2);
				float set_y1 = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + skill_area_data._check_a_1) * Math.PI / 180 ) * skill_area_data._check_b_2);
			
				// left bottom
				float set_x1_2 = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + skill_area_data._check_a_1) * Math.PI / 180 ) * skill_area_data._check_b_1);
				float set_y1_2 = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + skill_area_data._check_a_1) * Math.PI / 180 ) * skill_area_data._check_b_1);
				
				// center bottom
				float set_x1_c = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + center_angle) * Math.PI / 180 ) * skill_area_data._check_b_1);
				float set_y1_c = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + center_angle) * Math.PI / 180 ) * skill_area_data._check_b_1);
				
				// right top
				float set_x2 = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + skill_area_data._check_a_2) * Math.PI / 180 ) * skill_area_data._check_b_2);
				float set_y2 = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + skill_area_data._check_a_2) * Math.PI / 180 ) * skill_area_data._check_b_2);
			
				// center top
				float set_x2_c = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + center_angle) * Math.PI / 180 ) * skill_area_data._check_b_2);
				float set_y2_c = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + center_angle) * Math.PI / 180 ) * skill_area_data._check_b_2);
				
				// right bottom
				float set_x2_2 = base_x + (float)(Math.cos((this._set_chara_obj._move_angle + skill_area_data._check_a_2) * Math.PI / 180 ) * skill_area_data._check_b_1);
				float set_y2_2 = base_y + (float)(Math.sin((this._set_chara_obj._move_angle + skill_area_data._check_a_2) * Math.PI / 180 ) * skill_area_data._check_b_1);
				
//			 = (float)(Math.sin(this._set_chara_obj._move_angle * Math.PI / 180 ) * this._set_chara_obj._move_speed_base);
				
			
				path.reset();
				
				//*
				path.moveTo(set_x1, set_y1);
				path.lineTo(set_x2_c, set_y2_c);
				path.lineTo(set_x2, set_y2);
				path.lineTo(set_x2_2, set_y2_2);
				path.lineTo(set_x1_c, set_y1_c);
				path.lineTo(set_x1_2, set_y1_2);
				/*/
				path.moveTo(set_x1, set_y1);
			
				path.quadTo(set_x2_c, set_y2_c, set_x2, set_y2);
				path.lineTo(set_x2_2, set_y2_2);
				
				path.quadTo(set_x1_c, set_y1_c, set_x1_2, set_y1_2);
				path.lineTo(set_x1, set_y1);
				// */
				path.close();
				canvas.drawPath(path, paint);
	//	*/
			}
	//
		}
	//	canvas.drawPath(path, paint);
	}
}
