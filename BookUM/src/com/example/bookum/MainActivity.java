package com.example.bookum;

import android.app.Activity; 
import com.parse.Parse;
import com.parse.ParseObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	ImageView sellButton, buyButton, aboutButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(savedInstanceState == null){

			ParseObject.registerSubclass(Book.class);
			Parse.initialize(this, "FxD4tHMvuTpHjAAq5qih8GFhnXWnXF7oAn8vh348", "V49i44AfmGo5Vwqp8kqxWsiHcsPWjNc9UeXsRZhG");

		}

		buyListener();
		sellListener();
		aboutListener();
	}

	public void aboutListener() {
		aboutButton = (ImageView) findViewById(R.id.AboutButton);
		aboutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent (MainActivity.this, OurInfoActivity.class);
				startActivity(intent0);
			}
		
		});
	}


	public void buyListener() {
		buyButton = (ImageView) findViewById(R.id.BuyButton);
		buyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(MainActivity.this, BookSearchActivity.class);
				startActivity(intent1);

			}


		});

	}
	public void sellListener() {
		sellButton = (ImageView) findViewById(R.id.SellButton);
		sellButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainActivity.this, SellBookListActivity.class);
				startActivity(intent2);

			}

		});
	}

	}


