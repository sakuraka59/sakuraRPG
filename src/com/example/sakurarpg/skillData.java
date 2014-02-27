package com.example.sakurarpg;

public class skillData {
	int _skill_type = 0;
	float _check_a_1 = 0;
	float _check_a_2 = 0;
	float _check_b_1 = 0;
	float _check_b_2 = 0;
	
	public skillData(int type, float check_a1, float check_a2, float check_b1, float check_b2) {
		this._skill_type = type;
		this._check_a_1 = check_a1;
		this._check_a_2 = check_a2;
		this._check_b_1 = check_b1;
		this._check_b_2 = check_b2;
	}
}
