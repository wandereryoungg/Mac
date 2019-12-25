package com.young.speaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    ProgressBar progressBar;
    SpeechSynthesizer speechSynthesizer;
    private InitListener initListener;
    private SynthesizerListener synthesizerListener;
    private int progress = 0;
    private int progress1 = 0;
    private YoungProgress youngProgress;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    progressBar.setProgress(progress);
                    break;
                case 2:
                    youngProgress.setProgress(progress1);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        SpeechUtility.createUtility(MainActivity.this,"appid=5df0af51");
        speechSynthesizer = SpeechSynthesizer.createSynthesizer(MainActivity.this,initListener);
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
        speechSynthesizer.setParameter(SpeechConstant.SPEED,"50");
        speechSynthesizer.setParameter(SpeechConstant.PITCH,"50");
        speechSynthesizer.setParameter(SpeechConstant.VOLUME,"50");
        initListener = new InitListener() {
            @Override
            public void onInit(int i) {

            }
        };
        synthesizerListener = new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        };
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speechSynthesizer.startSpeaking("赵芹- -",synthesizerListener);
            }
        });
        btn2 = findViewById(R.id.btn2);
        progressBar = findViewById(R.id.progressbar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progress <= 100){
                            try {
                                handler.sendEmptyMessage(1);
                                Thread.sleep(500);
                                progress++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
        youngProgress = findViewById(R.id.young_progress);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress1 <=100){
                            try {
                                handler.sendEmptyMessage(2);
                                Thread.sleep(500);
                                progress1++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
