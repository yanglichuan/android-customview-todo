
package com.example.bitttt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyCheckButton extends View {
    Paint paint = null;
    private Context cc;
    public MyCheckButton(Context context) {
        super(context);
        init(context);
    }
    public MyCheckButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public MyCheckButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Bitmap offCheckBitmap;
    public Bitmap onCheckBitmap;
    public Bitmap mybitmap;
    
    public int toW;
    public int toH;
    public void init(Context cc) {
        this.cc = cc;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        
        //或者两个图片
        offCheckBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_check_off);
        onCheckBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_check_on);
      
        toW = Math.max(offCheckBitmap.getWidth(), onCheckBitmap.getWidth());
        toH = Math.max(offCheckBitmap.getHeight(),onCheckBitmap.getHeight());
        
        mybitmap = offCheckBitmap;
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
            result = (int) (toW+ getPaddingLeft()
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
            result = toH+ getPaddingTop()
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
        canvas.drawBitmap(mybitmap, 0, 0, paint);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(mybitmap == offCheckBitmap){
                    Log.i("vivi", "bbb");
                    mybitmap = onCheckBitmap;
                    if(listner!=null)
                         listner.onChecked(true);
                }else{
                    Log.i("vivi", "aaa");
                    mybitmap = offCheckBitmap;
                    if(listner!=null)
                         listner.onChecked(false);
                }
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
    
    public interface OnCheckedListner{
        public void  onChecked(boolean bChecked);
    }
    
    OnCheckedListner listner;
    public void setOnCheckedListner(OnCheckedListner listner){
        this.listner = listner;
    }
    

}
