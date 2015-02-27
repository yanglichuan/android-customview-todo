
package com.example.bitttt;

import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyTabQQ extends LinearLayout {

    Context context;

    public MyTabQQ(Context context) {
        super(context);
        init();
    }

    public MyTabQQ(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTabQQ(Context context, AttributeSet attrs) {
        super(context, attrs);
        //
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
        init();
    }

    //
    public void addItem(String title, int resId, boolean b) {
        View view = View.inflate(context, R.layout.like_qq_item, null);
        ImageView ivv = (ImageView) view.findViewById(R.id.bbb1);
        ivv.setImageDrawable(context.getResources().getDrawable(resId));
        TextView tvv = (TextView) view.findViewById(R.id.bbb2);
        tvv.setText(title);
        LinearLayout.LayoutParams params = new LayoutParams(-2, -1, 1);
        params.setMargins(20, 0, 20, 0);
        addView(view, params);
        map.put(title, view);
        if (b) {
            setselect(title);
        }
    }

    public void setS(String title) {
        if (listner != null) {
            listner.OnSelect(title);
        }
    }

    private HashMap<String, View> map = new HashMap<String, View>();

    public void setselect(String title) {
        for (Entry<String, View> bb : map.entrySet()) {
            View bbbbb = bb.getValue();
            ImageView ivv = (ImageView) bbbbb.findViewById(R.id.bbb1);
            TextView tvv = (TextView) bbbbb.findViewById(R.id.bbb2);
            ivv.setSelected(false);
            tvv.setSelected(false);
        }
        View view = map.get(title);
        ImageView ivv = (ImageView) view.findViewById(R.id.bbb1);
        TextView tvv = (TextView) view.findViewById(R.id.bbb2);
        ivv.setSelected(true);
        tvv.setSelected(true);
        setS(title);
    }

    public interface OnSelectListner {
        public void OnSelect(String title);
    }

    private OnSelectListner listner;

    public void setOnSelectListner(OnSelectListner l) {
        this.listner = l;
    }

    public void init() {

    }

}
