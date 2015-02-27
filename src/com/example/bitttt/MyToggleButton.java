
package com.example.bitttt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyToggleButton extends View {

    private int toggleW = 100;
    private int toggleH = 30;
    private Bitmap topBit;
    private int bitW;
    private int bitH;

    public MyToggleButton(Context context) {
        super(context);
        init();
    }

    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化函数
     */
    private Paint paint;

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        topBit = BitmapFactory.decodeResource(getResources(), R.drawable.ic_corp_icon);
        bitW = topBit.getWidth();
        bitH = topBit.getHeight();
    }

    public void resetPaint() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
        if (!bchecked) {
            padingBall.x = getMeasuredHeight() / 2;
        } else {
            padingBall.x = (getMeasuredWidth() - getMeasuredHeight() / 2);
        }
        padingBall.y = getMeasuredHeight() / 2;
    }

    private int measureWidth(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (toggleW + getPaddingLeft()
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
            result = toggleH + getPaddingTop()
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

    private boolean bchecked = true;
    private int padingBianju = 10;

    private int oldx;
    private int oldy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = (int) event.getX();
                oldy = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                int dx = x - oldx;
                padingBall.x += dx;

                if (padingBall.x < getMeasuredHeight() / 2) {
                    padingBall.x = getMeasuredHeight() / 2;
                } else if (padingBall.x > (getMeasuredWidth() - getMeasuredHeight() / 2)) {
                    padingBall.x = (getMeasuredWidth() - getMeasuredHeight() / 2);
                }
                if (padingBall.x < (getMeasuredWidth() / 2)) {
                    bchecked = false;
                } else {
                    bchecked = true;
                }
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_UP:
                if (!bchecked) {
                    padingBall.x = getMeasuredHeight() / 2;
                } else {
                    padingBall.x = (getMeasuredWidth() - getMeasuredHeight() / 2);
                }
                // 回调监听在次=========
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawColor(Color.YELLOW);
        // 开始绘制图形
        RectF re1 = new RectF(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10);
        paint.setColor(Color.GREEN);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(0);
        paint.setTextSize(30);
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        paint.setTypeface(font);
        FontMetrics sF = paint.getFontMetrics();
        int textH = (int) Math.ceil(sF.descent - sF.top) + 2;
        int textW = (int) paint.measureText("OFF");
        Log.i("bbb", "textH=" + textH);

        padingBall.r = getMeasuredHeight() / 2;
        padingBall.maxleftx = getMeasuredHeight() / 2;
        padingBall.maxrightx = (getMeasuredWidth() - getMeasuredHeight() / 2);

        if (bchecked) {
            Paint t = new Paint();
            t.setAntiAlias(true);
            // 开始渐变
            LinearGradient dd = new LinearGradient(0, 0,
                    getMeasuredWidth(), getMeasuredHeight(),
                    Color.BLUE, Color.CYAN, Shader.TileMode.MIRROR);
            t.setShader(dd);
            t.setShadowLayer(45, 10, 10, Color.YELLOW);
            canvas.drawRoundRect(re1, 20, 20, t);
            canvas.drawText("ON", padingBianju, (getMeasuredHeight() + textH / 2) / 2, paint);

            RadialGradient mRadialGradient = new RadialGradient(padingBall.x,
                    padingBall.y,
                    padingBall.r,
                    new int[] {
                            Color.BLUE, Color.CYAN
                    }, null,
                    Shader.TileMode.REPEAT);
            Paint t2 = new Paint();
            t2.setAntiAlias(true);
            t2.setShader(mRadialGradient);
            canvas.drawCircle(padingBall.x,
                    padingBall.y,
                    padingBall.r, t2);
        } else {
            Paint t = new Paint();
            t.setAntiAlias(true);
            // 开始渐变
            LinearGradient dd = new LinearGradient(0, 0,
                    getMeasuredWidth(), getMeasuredHeight(),
                    Color.CYAN, Color.BLUE, Shader.TileMode.MIRROR);
            t.setShader(dd);
            t.setShadowLayer(45, 10, 10, Color.YELLOW);
            canvas.drawRoundRect(re1, 20, 20, t);
            canvas.drawText("OFF", getMeasuredWidth() - textW - padingBianju,
                    (getMeasuredHeight() + textH / 2) / 2, paint);

            RadialGradient mRadialGradient = new RadialGradient(padingBall.x, padingBall.y,
                    padingBall.r,
                    new int[] {
                            Color.BLUE, Color.CYAN
                    }, null,
                    Shader.TileMode.REPEAT);
            Paint t2 = new Paint();
            t2.setAntiAlias(true);
            t2.setShader(mRadialGradient);
            canvas.drawCircle(padingBall.x, padingBall.y,
                    padingBall.r, t2);
        }
        Log.i("vivi", "调用ondraw");
    }
    public PadingBall padingBall = new PadingBall();
    class PadingBall {
        public int r;
        public int x;
        public int y;
        public int maxleftx;
        public int maxrightx;
    }
}
