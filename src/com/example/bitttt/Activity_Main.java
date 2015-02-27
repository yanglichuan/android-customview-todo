
package com.example.bitttt;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

public class Activity_Main extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    
    public void bt_function1(View view){
    	startActivity(new Intent(this,Activity_1.class));
    }
    
    public void bt_function2(View view){
    	startActivity(new Intent(this,Activity_2.class));
    }
    
    
    public void bt_function3(View view){
    	startActivity(new Intent(this,Activity_3.class));
    }
    
    
    
    public void bt_function4(View view){
    	startActivity(new Intent(this,Activity_4.class));
    }
    
    
    public void bt_function5(View view){
    	startActivity(new Intent(this,Activity_5.class));
    }
    
    
    public void bt_function6(View view){
    	startActivity(new Intent(this,Activity_6.class));
    }
    
    
    
    public void bt_function7(View view){
    	startActivity(new Intent(this,Activity_7.class));
    }
    
    
    public void bt_function8(View view){
    	startActivity(new Intent(this,Activity_8.class));
    }
    
    
    
    
    
    
    
    
    
}
