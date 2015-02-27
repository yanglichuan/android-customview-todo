
package com.example.bitttt;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MyFlowBar extends View implements AnimatorUpdateListener {
    int c = Color.BLUE;
    Paint paint = null;
    int ballrr = 160;
    int ttt = 20;

    public MyFlowBar(Context context) {
        super(context);
        init();
    }

    public MyFlowBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyFlowBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    
    public void init() {
        paint = new Paint();
        paint.setColor(c);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Cap.ROUND);
        paint.setAntiAlias(true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);  
        animator.setTarget(new Object());  
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               float ff =  (Float) animation.getAnimatedValue();
               degree = (int) ff;
               MyFlowBar.this.invalidate();
            }
        });
        animator.start();
    }

    // 初始化的时候必须由外界来设置
    public int screenW;
    public int screenH;

    public void setdDD() {
        screenW = getMeasuredWidth();
        screenH = getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) ( ballrr * 2 +ttt + getPaddingLeft()
                    + getPaddingRight());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * Determines the height of this view
     * 
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = ballrr * 2 +ttt+ getPaddingTop()
                    + getPaddingBottom();
            Log.i("bbb", "sdfsdf::" + result);
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int degree = 320;
    private int degree2 = 60;
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(ballrr+ttt/2, ballrr+ttt/2, ballrr, paint);
        
        paint.setColor(Color.RED);
        RectF oval1=new RectF(ttt/2,ttt/2,ttt/2+ballrr*2,ttt/2+ballrr*2);  
        canvas.drawArc(oval1, degree, degree2, false, paint);//小弧形  
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    //
    class Ball {
        private int ballx;
        private int bally;
        private int ballr = 30;

        public int getBallx() {
            return ballx;
        }

        public void setBallx(int ballx) {
            this.ballx = ballx;
        }

        public int getBally() {
            return bally;
        }

        public void setBally(int bally) {
            this.bally = bally;
        }

        public int getBallr() {
            return ballr;
        }

        public void setBallr(int ballr) {
            this.ballr = ballr;
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int animatedValue = (Integer) animation.getAnimatedValue();
        invalidate();
    }
}
