
package com.example.bitttt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyVVVV extends View {

    public MyVVVV(Context context) {
        super(context);
       paint = new Paint();
       paint.setStyle(Style.STROKE);
       paint.setStrokeWidth(3);
       
       
       
       path = new Path();
       path.moveTo(0,0 );
       for(int i=0;i<=15;i++){
           path.lineTo(i*20, (float)Math.random()*60);
       }

       colors = new int[]{Color.BLACK,Color.BLUE,Color.BLACK,Color.BLUE,Color.BLACK,Color.BLUE,Color.BLACK };
       
       
        
        
        // TODO Auto-generated constructor stub
    }

    public MyVVVV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public MyVVVV(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    
    float phase;
    PathEffect[] effects = new PathEffect[3];
    int []  colors;
    private Paint paint;
    Path path;
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        //开始绘制图形
        
      canvas.drawColor(Color.WHITE);
      
      effects[0]= null;
      effects[1] = new CornerPathEffect(10);
      effects[2] = new DiscretePathEffect(30.f, 5.0f);
      
      canvas.translate(8, 8);
      for(int i=0;i<effects.length;i++){
          paint.setPathEffect(effects[i]);
          paint.setAntiAlias(true);
          paint.setColor(colors[i]);
          canvas.drawPath(path, paint);
          canvas.translate(0, 60);

      }
     invalidate();
     Log.i("vivi", "sdfsfsf==============");
    }
}
