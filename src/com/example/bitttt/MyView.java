
package com.example.bitttt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    
//    
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(300,300);
//    }
//    
   
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        //开始绘制图形
        
        
        
        
       canvas.drawColor(Color.YELLOW);
       Paint paint = new Paint();
       paint.setAntiAlias(true);
       paint.setStyle(Style.STROKE);
       paint.setColor(Color.BLUE);
       paint.setStrokeWidth(3);
       
       canvas.drawCircle(40, 40, 30, paint);
       canvas.drawRect(10, 80, 70 , 140, paint);
       canvas.drawRect(10, 150, 70, 190, paint);
       
       RectF re1 = new RectF(10, 200, 70, 230);
       canvas.drawRoundRect(re1, 15, 15, paint);
       
       RectF re11 = new RectF(10, 240, 70, 270);
       canvas.drawOval(re11, paint);
       
       Path p  = new Path();
       p.moveTo(10, 340);
       p.lineTo(70, 340);
       p.lineTo(40, 290);
       p.close();
       canvas.drawPath(p, paint);
       
       Path p2  = new Path();
       p2.moveTo(26, 360);
       p2.lineTo(54, 360);
       p2.lineTo(70, 392);
       p2.lineTo(40, 420);
       p2.lineTo(10, 392);
       p2.close();
       canvas.drawPath(p2, paint);
       
       
       
       // 开始绘制实心的
       paint.setStyle(Style.FILL);
       paint.setColor(Color.RED);
       
       canvas.drawCircle(120, 40, 30, paint);
       
       canvas.drawRect(90, 80, 150, 140, paint);
       
       
       
       //开始渐变
       LinearGradient dd = new  LinearGradient(0, 0, 40, 60,Color.RED, Color.GREEN ,Shader.TileMode.MIRROR);
       paint.setShader(dd);
       paint.setShadowLayer(45, 10, 10, Color.GRAY);
       canvas.drawCircle(200, 40, 30, paint);
       
       
       Log.i("vivi", "调用ondraw");
       
              
      
       
       
       
       
       
       
       
       
        
        
        
        
        
        
        
    }
    
    
    
    
    
    

}
