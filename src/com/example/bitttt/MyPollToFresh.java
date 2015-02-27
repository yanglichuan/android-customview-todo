
package com.example.bitttt;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

public class MyPollToFresh extends ListView implements OnScrollListener {

    public MyPollToFresh(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private ImageView ivv;
    private TextView tvv;
    private ImageView fii;
    private TextView ftt;

    public MyPollToFresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        initheader(context);
        initfooter(context);
        this.context = context;

        setOnScrollListener(this);
    }

    private ObjectAnimator abjAnim;
    private ObjectAnimator abjAnim_footer;
    Context context;
    private int headerH = 0;
    private int footerH = 0;
    private View headerView;
    private View footerView;

    // 初始化footer
    public void initfooter(Context context) {
        footerView = View.inflate(getContext(), R.layout.list_footer, null);
        fii = (ImageView) footerView.findViewById(R.id.fii);
        ftt = (TextView) footerView.findViewById(R.id.ftt);
        android.view.ViewGroup.LayoutParams params = new LayoutParams(-1, -2);
        footerView.setLayoutParams(params);
        this.addFooterView(footerView);
        final ViewTreeObserver vto = footerView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
            public boolean onPreDraw()
            {
                footerH = footerView.getMeasuredHeight();
                setpadd_footer(-footerH);
                footerView.getViewTreeObserver().removeOnPreDrawListener(this);

                abjAnim_footer = ObjectAnimator.ofInt(new Object(), "bb", 0, footerH);
                abjAnim_footer.setDuration(300);
                abjAnim_footer.addUpdateListener(new AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (Integer) animation.getAnimatedValue();
                        Log.i("vivi", "onAnimationUpdateonAnimationUpdateonAnimationUpdate" + value);
                        setpadd_footer(-value);
                        invalidate();
                    }
                });
                return true;
            }
        });
    }

    public void initheader(Context context) {
        headerView = View.inflate(getContext(), R.layout.list_header, null);
        ivv = (ImageView) headerView.findViewById(R.id.ivv);
        tvv = (TextView) headerView.findViewById(R.id.tvv);
        android.view.ViewGroup.LayoutParams params = new LayoutParams(-1, -2);
        headerView.setLayoutParams(params);
        this.addHeaderView(headerView);

        final ViewTreeObserver vto = headerView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
            public boolean onPreDraw()
            {
                headerH = headerView.getMeasuredHeight();
                setpadd(-headerH);
                headerView.getViewTreeObserver().removeOnPreDrawListener(this);
                Log.i("bibi", "只调用一次" + headerH);

                abjAnim = ObjectAnimator.ofInt(new Object(), "bb", 0, headerH);
                abjAnim.setDuration(300);
                abjAnim.addUpdateListener(new AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (Integer) animation.getAnimatedValue();
                        Log.i("vivi", "onAnimationUpdateonAnimationUpdateonAnimationUpdate" + value);
                        setpadd(-value);
                        invalidate();
                    }
                });
                return true;
            }
        });
    }

    public void setpadd(int h) {
        headerView.setPadding(0, h, 0, 0);
    }

    public void setpadd_footer(int h) {
        footerView.setPadding(0, h, 0, 0);
    }

    public int padingH() {
        Log.i("bibi", "得到 的高度为" + headerView.getPaddingTop());
        return headerView.getPaddingTop();
    }

    public int padingH_footer() {
        Log.i("bibi", "得到 的高度为" + headerView.getPaddingTop());
        return footerView.getPaddingTop();
    }

    public void setTip(String str) {
        tvv.setText(str);
    }

    public void setTip_footer(String str) {
        ftt.setText(str);
    }

    public void setBitmap(int resId) {
        ivv.setImageDrawable(context.getResources().getDrawable(resId));
    }

    public void setBitmap_ftt(int resId) {
        fii.setImageDrawable(context.getResources().getDrawable(resId));
    }

    int oldX = 0;
    int oldY = 0;
    private static final int STATE_DOWN_PULL = 1;
    private static final int STATE_SHIFANG_PULL = 2;
    private static final int STATE_DONGING_PULL = 3;
    private int current_state = -1;
    private int current_state_footer = -1;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = (int) ev.getX();
                oldY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getX();
                int y = (int) ev.getY();
                int dx = x - oldX;
                int dy = (y - oldY) / 2;

                if (mFirstVisibleItem == 0 && dy > 0 && current_state != STATE_DONGING_PULL) {
                    current_state = STATE_DOWN_PULL;
                    setpadd(-headerH + dy);
                    // 设置大于0
                    if ((-headerH + dy) > 0) {
                        setpadd(0);
                        setTip("释放后刷新");
                        current_state = STATE_SHIFANG_PULL;
                        if (listner != null) {
                            listner.onShifangToFresh();
                        }
                        createAnimation(ivv);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                // 释放后刷新
                if (current_state == STATE_SHIFANG_PULL) {
                    current_state = STATE_DONGING_PULL;
                    setpadd(0);
                    setTip("正在刷新");
                    if (listner != null) {
                        listner.onDoingToFresh();
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public MyPollToFresh(Context context) {
        super(context);
    }

    public void setOveredFresh() {
        current_state = STATE_DOWN_PULL;
        setpadd(-headerH);
        if (listner != null) {
            listner.onOverToFresh();
        }
        abjAnim.start();
    }

    public interface OnPullFreshListner {
        public void onShifangToFresh();

        public void onDoingToFresh();

        public void onOverToFresh();
    }

    OnPullFreshListner listner;

    public void setOnPullFreshListner(OnPullFreshListner l) {
        this.listner = l;
    }

    public interface OnLoadMoreListner {
        public void onShifangToFresh();

        public void onDoingToFresh();

        public void onOverToFresh();
    }

    OnLoadMoreListner listner_footer;

    public void setOnLoadMoreListner(OnLoadMoreListner l) {
        this.listner_footer = l;
    }

    /**
     * * @param scrollState The current scroll state. One of
     * {@link #SCROLL_STATE_IDLE}, {@link #SCROLL_STATE_TOUCH_SCROLL} or
     * {@link #SCROLL_STATE_IDLE}.
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_TOUCH_SCROLL:
            case SCROLL_STATE_IDLE:
                if ((mLastVisibleItem == (mTotalItemCount - 1))) {
                    setpadd_footer(0);
                    setSelection((mTotalItemCount - 1));
                }
                break;
            case SCROLL_STATE_FLING:
                break;
            default:
                break;
        }
    }

    private int mFirstVisibleItem = 0;
    private int mLastVisibleItem = 0;
    private int mTotalItemCount = 0;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
            int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mLastVisibleItem = this.getLastVisiblePosition();
        mTotalItemCount = totalItemCount;
    }

    RotateAnimation ra = null;

    public void createAnimation(View v) {
        if (ra == null) {
            ra = new RotateAnimation(0, 180,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            ra.setFillAfter(true);
            ra.setDuration(500);
            ra.setFillEnabled(true);
            ra.setInterpolator(new LinearInterpolator());
            ra.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }
            });
        }
        v.setAnimation(ra);
        v.startAnimation(ra);
    }
}
