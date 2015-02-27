    package com.example.bitttt;  
      
    import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
    /** 
     * 水波纹效果 
     * @author tony 
     * 
     */  
      
     public class WaterRipplesView extends View {  
      
        Shader mBitmapShader = null;  
        Bitmap mBitmapPn = null;  
        Paint mPaint = null;  
        Shader mRadialGradient = null;  
        Canvas mCanvas = null;  
        ShapeDrawable mShapeDrawable = null;  
      
        public WaterRipplesView(Context context) {  
            super(context);  
      
            // 初始化工作  
            Bitmap bitmapTemp = ((BitmapDrawable) getResources().getDrawable(  
                    R.drawable.ic_launcher)).getBitmap();  
            DisplayMetrics dm = getResources().getDisplayMetrics();  
            // 创建与当前使用的设备窗口大小一致的图片  
            mBitmapPn = Bitmap.createScaledBitmap(bitmapTemp, dm.widthPixels,  
                    dm.heightPixels, true);  
            // 创建BitmapShader object  
            mBitmapShader = new BitmapShader(mBitmapPn, Shader.TileMode.REPEAT,  
                    Shader.TileMode.MIRROR);  
            mPaint = new Paint();  
        }  
      
        public WaterRipplesView(Context context, AttributeSet attrs) {  
            super(context, attrs);  
        }  
      
        @Override  
        protected void onDraw(Canvas canvas) {  
            // TODO Auto-generated method stub  
            super.onDraw(canvas);  
      
            // 将图片裁剪为椭圆型  
            // 创建ShapeDrawable object，并定义形状为椭圆  
            mShapeDrawable = new ShapeDrawable(new OvalShape());// OvalShape:椭圆  
            // 设置要绘制的椭圆形的东西为ShapeDrawable图片  
            mShapeDrawable.getPaint().setShader(mBitmapShader);  
            // 设置显示区域  
            mShapeDrawable.setBounds(0, 0, mBitmapPn.getWidth(),  
                    mBitmapPn.getHeight());  
            // 绘制ShapeDrawable  
            mShapeDrawable.draw(canvas);  
            if (mRadialGradient != null) {  
                mPaint.setShader(mRadialGradient);  
                canvas.drawCircle(0, 0, 1000, mPaint);  
            }  
      
        }  
      
        // @覆写触摸屏事件  
        public boolean onTouchEvent(MotionEvent event) {  
            // @设置alpha通道（透明度）  
            mPaint.setAlpha(400);  
            mRadialGradient = new RadialGradient(event.getX(), event.getY(), 48,  
                    new int[] { Color.WHITE, Color.TRANSPARENT },null, Shader.TileMode.REPEAT);  
            // @重绘  
            postInvalidate();  
            return true;  
        }  
      
    }  
