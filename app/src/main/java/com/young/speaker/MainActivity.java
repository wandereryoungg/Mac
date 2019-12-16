package com.young.speaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    SpeechSynthesizer speechSynthesizer;
    private InitListener initListener;
    private SynthesizerListener synthesizerListener;

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

                speechSynthesizer.startSpeaking("赵芹是大笨蛋哈哈哈",synthesizerListener);
            }
        });
    }
}
