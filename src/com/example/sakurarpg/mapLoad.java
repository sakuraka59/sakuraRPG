package com.example.sakurarpg;

public class mapLoad{
	/*
	//	map rule
	// 	0:no data
	//	1-99:no road
	//	200-299:on load
	//	300-399:up or down
	//	1000-:not in object(Obstacle)
	*/
	public int[][] test_map = {
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100,	100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,300,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		
		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		
	};
	
	public int[][] test_object = {
		{300,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,300, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100,	100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,100,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,200,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 100,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,300,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,100,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },
		{100,100,100,100,100, 100,100,100,100,100, 200,200,200,100,100, 100,100,100,100,100, 100,100,100,100,100, 100,100,100,100,100, },

	};
	public int[][] load_map;
	public int[][] load_obj;
	public void setMapData(){
		this.load_map = this.test_map;
		this.load_obj = this.test_object;
	}
	public int[][] getMapData(){
		return load_map;
	}
	public int[][] getObjData(){
		return load_obj;
	}
}
