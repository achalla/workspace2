package com.example.bookum;

import java.util.ArrayList; 

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;

public class BuyBookListActivity extends ListActivity {
	
	private ClassQueryAdapter classAdapter;
	
	static String course;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		course = intent.getStringExtra(BookSearchActivity.EXTRA_MESSAGE);
		getListView().setClickable(false);
		
		classAdapter = new ClassQueryAdapter(this);
		
		setListAdapter(classAdapter);
	}
}
