
package com.example.bitttt;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bitttt.MyPollToFresh.OnPullFreshListner;
import com.example.bitttt.MyTabQQ.OnSelectListner;

public class Activity_6 extends Activity {

    private MyTabQQ mytabqq;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
       
    }
}
