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
        //��ʂ��c�����ŌŒ肷��
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //���C�A�E�g��p�ӂ���
        LinearLayout l = new LinearLayout(this);
        setContentView(l);
        //View���Z�b�g����
        l.addView(new mainView(this));
    }
    
}
