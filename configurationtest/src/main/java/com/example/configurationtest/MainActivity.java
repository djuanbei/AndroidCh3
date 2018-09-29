package com.example.configurationtest;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    EditText ori, navigation, touch, mnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ori = (EditText) findViewById(R.id.ori);
        navigation = (EditText) findViewById(R.id.navigation);
        touch = (EditText) findViewById(R.id.touch);
        mnc = (EditText) findViewById(R.id.mnc);

        Button bn = (Button) findViewById(R.id.bn);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration cfg = getResources().getConfiguration();

                String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向" : "纵向";
                String mncCode = cfg.mnc + "";
                String navName = cfg.navigation == Configuration.NAVIGATION_NONAV ? "没有方向" : "另一个方向";
                String touvhName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸支持" : "有触摸支持";
                navigation.setText(navName);
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touvhName);
            }
        });
        
    }

}
