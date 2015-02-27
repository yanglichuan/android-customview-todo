
package com.example.bitttt;

import java.util.ArrayList;

import com.example.bitttt.MyPollToFresh.OnPullFreshListner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class Activity_7 extends Activity {


	
	private  MyPollToFresh polllist;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        
        polllist = (MyPollToFresh) this.findViewById(R.id.polllist);
        
        ArrayList<String> arrays = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
			arrays.add("item"+i);
		}
        polllist.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrays));
       
        
        polllist.setOnPullFreshListner(new OnPullFreshListner() {
			@Override
			public void onShifangToFresh() {
				
			}
			
			@Override
			public void onOverToFresh() {
				
			}
			
			@Override
			public void onDoingToFresh() {
				
			}
		});
    }
}
