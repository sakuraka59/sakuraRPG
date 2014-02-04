package com.example.sakurarpg;

import android.app.*;
import android.content.pm.ActivityInfo;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // screen to length
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // no drow title
   		requestWindowFeature(Window.FEATURE_NO_TITLE);
   		// GameView‚ð‰æ–Ê‚Æ‚µ‚ÄƒZƒbƒg‚·‚é
   		mainView mainView = new mainView(this);
   		setContentView(mainView);
   		/*
        // set layout
        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        // set view
        l.addView(new mainView(this));
        */
    }
    
}
