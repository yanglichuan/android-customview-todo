
package com.example.bitttt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyVVVV222 extends View {

    public MyVVVV222(Context context) {
        super(context);
        
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        
        path = new Path();
        
        
        bitmap = Bitmap.createBitmap(W, H, Config.ARGB_8888);
        canvas_bitmap = new Canvas(bitmap);
        
        
        // TODO Auto-generated constructor stub
    }

    public MyVVVV222(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyVVVV222(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    Path path = null;
    
    int oldx;
    int oldy;
    
    int W  = 300;
    int H = 400;
    Paint paint = null;
    Bitmap bitmap = null;
    Canvas canvas_bitmap = null;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = (int) event.getX();
                oldy = (int) event.getY();
                path.moveTo(oldx, oldy);;
                break;
            case MotionEvent.ACTION_MOVE:
                int x  = (int) event.getX();
                int y = (int) event.getY();
                path.quadTo(oldx, oldy, x, y);
                canvas_bitmap.drawPath(path, paint);
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_UP:
                canvas_bitmap.drawPath(path, paint);
                path.reset();
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // 开始绘制图形
       canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
