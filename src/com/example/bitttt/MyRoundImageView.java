
package com.example.bitttt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class MyRoundImageView extends View {

    private Bitmap topBit;

    public MyRoundImageView(Context context) {
        super(context);
        init();
    }

    public MyRoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private int suggestW = 30;
    private int suggestH = 30;
    /**
     * 初始化函数
     */
    private Paint paint;
    private int bitW;
    private int bitH;

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        topBit = BitmapFactory.decodeResource(getResources(), R.drawable.picture_emergency);
        bitW = topBit.getWidth();
        bitH = topBit.getHeight();
    }

    public void resetPaint() {

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
            result = (int) (suggestW + getPaddingLeft()
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
            result = suggestH + getPaddingTop()
                    + getPaddingBottom();
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
        paint.setAntiAlias(true);
        int tempW = getMeasuredWidth();
        int tempH = getMeasuredHeight();

        float scale = Math.min((float) tempW / (float) bitW, (float) tempH / (float) bitH);
        // 定义矩阵对象
        Matrix matrix = new Matrix();
        // 缩放原图
        matrix.postScale(scale, scale);
        // bmp.getWidth(), bmp.getHeight()分别表示缩放后的位图宽高
        Bitmap dstbmp = Bitmap.createBitmap(topBit, 0, 0, topBit.getWidth(), topBit.getHeight(),
                matrix, true);

        Bitmap circleImage = createCircleImage(dstbmp, dstbmp.getWidth());
        canvas.drawBitmap(circleImage, 0, 0, paint);
    }

    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

}
