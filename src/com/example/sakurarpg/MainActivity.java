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
        //画面を縦方向で固定する
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //レイアウトを用意する
        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        //Viewをセットする
        l.addView(new mainView(this));
    }
    
}
