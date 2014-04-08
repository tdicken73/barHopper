package com.example.barhopper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditBars extends MainActivity{
	MyDatabase db = new MyDatabase(this);
	Bar bar = new Bar();
	Bar test = new Bar();
	int id;
	Button mButton;
	EditText mEdit1; //Name of bar
	EditText mEdit2; //Address
	EditText mEdit3; //Hours
	@Override
	
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.add_bars);
	    
	    bar.setName("");
	    bar.setAddress("");
	    bar.setHours("");
	    
	    
	    mButton = (Button)findViewById(R.id.add_submit);
	    mEdit1 = (EditText)findViewById(R.id.nameField);
	    mEdit2 = (EditText)findViewById(R.id.addressField);
	    mEdit3 = (EditText)findViewById(R.id.hoursField);
	    mButton.setOnClickListener(
	        new View.OnClickListener()
	        {
	            public void onClick(View view)
	            {
	            	String entryName = mEdit1.getText().toString();
	            	String entryAddress = mEdit2.getText().toString();
	            	String entryHours = mEdit3.getText().toString();
	            	//NEED TO SET ID
	            	bar.setName(entryName);
	            	bar.setAddress(entryAddress);
	            	bar.setHours(entryHours);
	            	bar.setId(1);
	            	db.addBar(bar);
	            }
	        });
	}	
}
