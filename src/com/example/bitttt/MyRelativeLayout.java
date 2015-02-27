
package com.example.bitttt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyRelativeLayout extends RelativeLayout {
    private MyToggleButton checkBox;
    private TextView  textView;
    public MyRelativeLayout(Context context) {
        super(context);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.item_relative, this); 
        checkBox = (MyToggleButton) view.findViewById(R.id.cbb);
        textView = (TextView) view.findViewById(R.id.tvv);
        
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBox.setChecked(!checkBox.isChecked());
            }
        });
    }
    
    /**
     * 初始化函数
     */
    public void init(){
    }
    
    
    public void setChecked(boolean b){
    }
    
}
