package com.young.speaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

public class YoungProgress extends View {
    private Paint backPaint;
    private Paint progressPaint;
    private Paint textPaint;
    private float strokeWidth = 50;
    private float halfStrokeWidth = 50/2;
    private float radius = 200;
    private RectF rectF;
    private int progress = 0;
    private int targetProgress = 90;
    private int maxProgress = 100;
    private int width;
    private int height;


    public YoungProgress(Context context) {
        super(context, null);
        init();
        Log.d("young","init1...");
    }

    public YoungProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
        Log.d("young","init2...");
    }

    public YoungProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.d("young","init3...");
    }

    private void init() {
        backPaint = new Paint();
        backPaint.setColor(Color.WHITE);
        backPaint.setAntiAlias(true);
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setStrokeWidth(strokeWidth);
        progressPaint = new Paint();
        progressPaint.setColor(Color.GREEN);
        progressPaint.setAntiAlias(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(strokeWidth);
        textPaint = new Paint();
        textPaint.setColor(Color.GREEN);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(80);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("young","onMeasure...");
        width = getRealSize(widthMeasureSpec);
        height = getRealSize(heightMeasureSpec);
        Log.d("young","width: "+width+" height: "+height);
        setMeasuredDimension(width,height);
    }

    private int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        Log.d("young","size: "+size);
        if(mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED){
            result = (int) (radius*2+strokeWidth);
        }else{
            result = size;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("young","onDraw...");
        initRect();
        //重要-----------------------------------------------
        float angle = progress/(float)maxProgress*360;
        Log.e("young","angle: "+angle);
        canvas.drawCircle(width/2,height/2,radius,backPaint);
        canvas.drawArc(rectF,-90,angle,false,progressPaint);
        canvas.drawText(progress+"%",width/2,height/2+halfStrokeWidth,textPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    private void initRect() {
        if(rectF == null){
            rectF = new RectF();
            int viewSize = (int) (radius*2);
            int left = (width - viewSize)/2;
            int top = (height - viewSize)/2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            Log.d("young","left: "+left+" top: "+top+" right: "+right+" bottom"+bottom);
            rectF.set(left,top,right,bottom);
        }

    }
}
