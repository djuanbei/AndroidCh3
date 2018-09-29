package com.example.calprime;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String UPPPER_NUM = "upper";

    EditText editText;

    CalThread calThread;

    class CalThread extends Thread {
        public Handler mhander;

        public void run() {
            Looper.prepare();

            mhander = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPPER_NUM);
                        List<Integer> nums = new ArrayList<Integer>();
                        for (int i = 2; i <= upper; i++) {
                            boolean state = true;
                            for (int j = 2; state && j <= Math.sqrt(i); j++) {
                                if (i != 2 && i % j == 0) {
                                    state = false;
                                }
                            }
                            if (state) {
                                nums.add(i);
                            }
                        }
                        Toast.makeText(MainActivity.this, nums.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            };
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.et);
        calThread=new CalThread();
        calThread.start();

    }

    public void cal(View souce){
        Message msg=new Message();
        msg.what=0x123;
        Bundle bundle=new Bundle();
        bundle.putInt(UPPPER_NUM, Integer.parseInt(editText.getText().toString()));
        msg.setData(bundle);
        calThread.mhander.sendMessage(msg);
    }
}
