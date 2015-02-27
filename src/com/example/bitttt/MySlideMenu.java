
package com.example.bitttt;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Scroller;

public class MySlideMenu extends ViewGroup {

    public MySlideMenu(Context context) {
        super(context);
        init();
    }

    public MySlideMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private View leftmenuChild;
    private View rightmenuChild;
    private View centerChilde;

    public MySlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        leftmenuChild = this.getChildAt(0);
        centerChilde = this.getChildAt(1);
        rightmenuChild = this.getChildAt(2);
        scroller = new Scroller(context,new BounceInterpolator());
        init();
    }
    
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), 0);
        }
        invalidate();
    }
    
    

    /**
     * 初始化函数
     */
    public void init() {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = (int) ev.getX();
                oldy = (int) ev.getY();
                Log.i("fifi", "按下的x坐标为" + oldx);
                break;
            case MotionEvent.ACTION_MOVE:
                int nowX = (int) ev.getX();
                int dx = nowX - oldx;

                Log.i("fifi", "按下的x坐标为" + showWhich);

                if (showWhich == 0) {
                    // 表示只要在菜单的view就无法进行移动
                    if (nowX > Math.abs(getScrollX())) {
                        return true;
                    }
                } else if (showWhich == 2) {
                    if (nowX < Math.abs(getWidth() - getScrollX())) {
                        return true;
                    }
                } else {
                    if (Math.abs(dx) > 10) {
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    
    private Scroller scroller;
    
    private int halfW = 0;// 表示一般的宽度
    private int halfW222 = 0;// 表示一般的宽度
    private int showWhich = 1;
    private int daoweiWhich = 1;
    
    
    int oldx = 0;
    int oldy = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("fifi", "开始移动");
        int leftmenuChild_measuredWidth = leftmenuChild.getMeasuredWidth();
        int rightmenuChild_measuredWidth = rightmenuChild.getMeasuredWidth();
        halfW = leftmenuChild_measuredWidth / 20;
        halfW222 = leftmenuChild_measuredWidth - halfW;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldx = (int) event.getX();
                oldy = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - oldx);
                scrollBy(-dx, 0);
                if (getScrollX() < -leftmenuChild_measuredWidth) {

                    Log.i("fifi", "1111111");
                    scrollTo(-leftmenuChild_measuredWidth, 0);
                }
                if (getScrollX() > rightmenuChild_measuredWidth) {
                    scrollTo(rightmenuChild_measuredWidth, 0);
                }
                Log.i("fifi", "UUU" + (getScrollX()));
                oldx = (int) event.getX();
                oldy = (int) event.getY();
                //
                if (getScrollX() < 0) {
                    showWhich = 0;
                } else if (getScrollX() > 0) {
                    showWhich = 2;
                } else {
                    showWhich = 1;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (showWhich == 0) {
                    if (Math.abs(getScrollX()) < halfW222 && daoweiWhich == 0) {
                        showWhich = 1;
                    } else if(Math.abs(getScrollX()) >halfW  && daoweiWhich == 1){
                        showWhich = 0;
                    } else{
                        showWhich = daoweiWhich;
                    }
                } else if (showWhich == 2) {
                    if (Math.abs(getScrollX()) > halfW && daoweiWhich == 1) {
                        showWhich = 2;
                    }else if (Math.abs(getScrollX()) < halfW222 && daoweiWhich == 2) {
                        showWhich = 1;
                    }else {
                        showWhich = daoweiWhich;
                    }
                } else {
                    showWhich = 1;
                }

                toWitch(showWhich);
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    // 表示要到达哪个view
    public void toWitch(int whitch) {
        daoweiWhich = whitch;
        
        int leftmenuChild_measuredWidth = leftmenuChild.getMeasuredWidth();
        int rightmenuChild_measuredWidth = rightmenuChild.getMeasuredWidth();
        switch (whitch) {
            case 0:
                int startX = getScrollX();
                int dx = -leftmenuChild_measuredWidth - startX;
                scroller.startScroll(startX, 0, dx,0, Math.abs(dx)*2);
                break;
            case 1:
                int startX3 = getScrollX();
                int dx3 = 0 - startX3;
                scroller.startScroll(startX3, 0, dx3,0, Math.abs(dx3)*2);
                break;
            case 2:
                int startX2 = getScrollX();
                int dx2 = rightmenuChild_measuredWidth - startX2;
                scroller.startScroll(startX2, 0, dx2,0, Math.abs(dx2)*2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 记录总高度
        int mTotalHeight = 0;
        int mTotalWidth = 0;
        // 遍历所有子视图

        leftmenuChild = this.getChildAt(0);
        centerChilde = this.getChildAt(1);
        rightmenuChild = this.getChildAt(2);

        // 获取在onMeasure中计算的视图尺寸
        int menuChild_measuredWidth = leftmenuChild.getMeasuredWidth();
        int rightChilde_measuredWidth = rightmenuChild.getMeasuredWidth();

        centerChilde.layout(l, t, r, b);
        leftmenuChild.layout(l - menuChild_measuredWidth, t, l, b);
        rightmenuChild.layout(r, t, r + rightChilde_measuredWidth, b);
        //
        this.scrollTo(l, t);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("bibi", "measureHeigth" + measureHeigth);
        setMeasuredDimension(measureWidth, measureHeigth);

        // TODO Auto-generated method stub
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            Log.v("vivi",
                    "measureWidth is " + v.getMeasuredWidth() + "measureHeight is "
                            + v.getMeasuredHeight());
            int widthSpec = 0;
            int heightSpec = 0;
            LayoutParams params = v.getLayoutParams();
            Log.v("vivi", params.width + "sdfsfsdfsd");
            if (params.width > 0) {
                widthSpec = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
            } else if (params.width == -1) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.EXACTLY);
            } else if (params.width == -2) {
                widthSpec = MeasureSpec.makeMeasureSpec(measureWidth, MeasureSpec.AT_MOST);
            }

            if (params.height > 0) {
                heightSpec = MeasureSpec.makeMeasureSpec(params.height, MeasureSpec.EXACTLY);
            } else if (params.height == -1) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.EXACTLY);
            } else if (params.height == -2) {
                heightSpec = MeasureSpec.makeMeasureSpec(measureHeigth, MeasureSpec.AT_MOST);
            }
            v.measure(widthSpec, heightSpec);
        }
    }

}
