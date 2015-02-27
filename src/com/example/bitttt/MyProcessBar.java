
package com.example.bitttt;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class MyProcessBar extends View implements AnimatorUpdateListener {
    int c = Color.BLUE;
    Paint paint = null;
    int ballNum = 10;
    int ballspace = 20;
    int ballrr = 20;
    int duration = 200;

    ArrayList<Ball> balls = new ArrayList<MyProcessBar.Ball>();

    public MyProcessBar(Context context) {
        super(context);
        init();
    }

    public MyProcessBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyProcessBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private ValueAnimator bounceAnim;
    private List<Animator> animators = new ArrayList<Animator>();
    AnimatorSet set;
    AnimatorSet set_reverse;
    private List<Animator> animators_reverse = new ArrayList<Animator>();
    public void init() {
        paint = new Paint();
        paint.setColor(c);
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);

        set = new AnimatorSet();
        set_reverse = new AnimatorSet();

        for (int i = 0; i < ballNum; i++) {
            Ball object = new Ball();
            object.ballr = ballrr;
            object.ballx = (i * 2 + 1) * ballrr + i * ballspace;
            object.bally = ballrr;
            balls.add(object);

            ObjectAnimator bounceAnim1 = ObjectAnimator.ofInt(balls.get(i), "ballr", ballrr, 0);
            bounceAnim1.setDuration(duration);
            bounceAnim1.setInterpolator(new AccelerateInterpolator());
            bounceAnim1.addUpdateListener(this);
            animators.add(bounceAnim1);

            ObjectAnimator bounceAnim2 = ObjectAnimator.ofInt(balls.get(i), "ballr", 0, ballrr);
            bounceAnim2.setDuration(duration);
            bounceAnim2.setInterpolator(new AccelerateInterpolator());
            bounceAnim2.addUpdateListener(this);
            animators_reverse.add(bounceAnim2);
        }
        set.playSequentially(animators);

        set.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i("vivi", "KONGZHI  FANZHUAN");
                if (bFor) {
                    set = new AnimatorSet();
                    set.addListener(this);
                    set.playSequentially(animators_reverse);
                    set.start();
                    bFor = false;
                    Log.i("vivi", "*****");
                } else {
                    set = new AnimatorSet();
                    set.addListener(this);
                    set.playSequentially(animators);
                    set.start();
                    bFor = true;
                    Log.i("vivi", "######");
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                set.start();
            }
        }, 500);
    }

    boolean bFor = true;
    private Handler handler = new Handler();

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
            result = (int) (ballNum * ballrr * 2 + (ballNum - 1) * ballspace + getPaddingLeft()
                    + getPaddingRight());
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
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
            result = ballrr * 2 + getPaddingTop()
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

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.YELLOW);
        for (int i = 0; i < ballNum; i++) {
            Ball ball = balls.get(i);

            canvas.drawCircle(ball.ballx, ball.bally, ball.ballr, paint);
        }
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
