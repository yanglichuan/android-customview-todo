
package com.example.bitttt;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyZuhe extends ViewGroup {

    public MyZuhe(Context context) {
        super(context);
        init();
    }

    public MyZuhe(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyZuhe(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * 初始化函数
     */
    public void init(){
        
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 记录总高度  
        int mTotalHeight = 0;  
        int mTotalWidth = 0;  
        // 遍历所有子视图  
        int childCount = getChildCount();  
        for (int i = 0; i < childCount; i++) {  
            View childView = getChildAt(i);  
  
            // 获取在onMeasure中计算的视图尺寸  
            int measureHeight = childView.getMeasuredHeight();  
            int measuredWidth = childView.getMeasuredWidth();  
            
            //子view的布局
            childView.layout(l+mTotalWidth, t+mTotalHeight, l+mTotalWidth+measuredWidth, t+mTotalHeight  
                    + measureHeight);  
  
            mTotalHeight += measureHeight;  
            mTotalWidth  += measuredWidth;  
        }   
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("bibi", "measureHeigth"+measureHeigth);
        setMeasuredDimension(measureWidth, measureHeigth);
        
        // TODO Auto-generated method stub
        for(int i= 0;i<getChildCount();i++){
            View v = getChildAt(i);
            Log.v("vivi", "measureWidth is " +v.getMeasuredWidth() + "measureHeight is "+v.getMeasuredHeight());
            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = v.getLayoutParams();
            Log.v("vivi", params.width+"sdfsfsdfsd");
            if(params.width > 0){
                widthSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
            }else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.AT_MOST);
            }

            if(params.height > 0){
                heightSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
            }else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.AT_MOST);
            }
            v.measure(widthSpec, heightSpec);
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
    }
    
}
