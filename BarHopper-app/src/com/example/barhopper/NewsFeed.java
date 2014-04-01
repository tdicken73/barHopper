package com.example.barhopper;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NewsFeed extends MainActivity{
	
	//Test Code
	private Button button;
	 
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed);
 
		button = (Button) findViewById(R.id.buttonToast);
 
		button.setOnClickListener(new OnClickListener() {
 
			  @Override
			  public void onClick(View arg0) {
 
			     Toast.makeText(getApplicationContext(), 
                               "Button is clicked", Toast.LENGTH_LONG).show();
 
			  }
		});
	}
	
}
