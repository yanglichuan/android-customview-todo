
package com.example.bitttt;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    public GameView(Context context) {
        super(context);
        
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        
        path = new Path();
        
        
        bitmap = Bitmap.createBitmap(W, H, Config.ARGB_8888);
        canvas_bitmap = new Canvas(bitmap);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameView(Context context, AttributeSet attrs) {
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
    
  
    
    //初始化的时候必须由外界来设置
    public int screenW;
    public int screenH;
    
    public void setdDD(){
        screenW = getMeasuredWidth();
        screenH = getMeasuredHeight();
        racketRight = racketLeft + racketW;
        racketBottom = screenH;
        racketTop =  screenH - racketH;
    }
    
    //小球的高度和宽度
    public int ballW = 100;
    public int ballH = 100;
    public int ballR =  30;
    
    public int racketW = 100;
    public int racketH = 50;
    public int racketLeft = 100;
    public int racketRight = screenW - racketLeft - racketW;
    public int racketBottom = screenH;
    public int racketTop =  screenH - racketH;
    
    
    private Timer timer = new Timer();
    boolean bDrop = true;
    
    ///100ms 刷新一次
    public void beginGame(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               Log.i("vivi",Thread.currentThread().getName());
               if(bDrop){
                   if(ballH >= (screenH - ballR - racketH)){
                       bDrop = false;
                       ballH -= 5;
                   }else{
                       ballH += 5;
                   }
               }else{
                  if(ballH <= (ballR)){
                      ballH += 5;
                      bDrop = true;
                  }else{
                      ballH -= 5;
                  }
              }
                handler.sendEmptyMessage(100);
            }
        }, 1000 , 20);    
    }

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
          GameView.this.invalidate();
        };
    };
    
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        setdDD();
        //绘制小球
        canvas.drawCircle(ballW, ballH, ballR , paint);
        //绘制球拍
        canvas.drawRect(racketLeft , racketTop, racketRight    , racketBottom, paint);

        
        // 开始绘制图形
    }
    
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = (int) event.getX();
                oldy = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x  = (int) event.getX();
                int y = (int) event.getY();
                int dx = x - oldx;
                racketLeft += dx;
                
                if(racketLeft<0){
                    racketLeft = 0;
                }else if(racketLeft > (screenW - racketW)){
                    racketLeft = (screenW - racketW);
                }
                oldx = x;
                oldy = y;
                break;
            case MotionEvent.ACTION_UP:
                x  = (int) event.getX();
                y = (int) event.getY();
                oldx = x;
                oldy = y;
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }
    
    
    
    
}
