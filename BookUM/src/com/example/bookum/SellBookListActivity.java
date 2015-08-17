package com.example.bookum;

import com.parse.ParseException;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SellBookListActivity extends Activity {

	private EditText classId, bookId, bookSwap, price, email;
	public Book book;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sell_book_list);

		book = new Book();

		classId = (EditText) findViewById(R.id.EditText2);
		bookId = (EditText) findViewById(R.id.EditText3);
		bookSwap = (EditText) findViewById(R.id.EditText4);
		price = (EditText) findViewById(R.id.EditText5);
		email = (EditText) findViewById(R.id.EditText1);

		final Button submit = (Button) findViewById(R.id.Button2);
		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (email.getText().toString().equals("") == false){
					book.setEmail(email.getText().toString());
					if (classId.getText().toString().equals("") == false){
						book.setClassId(classId.getText().toString());
						if (bookId.getText().toString().equals("") == false){
							book.setBookId(bookId.getText().toString());
							if (bookSwap.getText().toString().equals("") == false || price.getText().toString().equals("") == false){
								if (bookSwap.getText().toString().equals("") == false){
									book.setBookSwap(bookSwap.getText().toString());
								}
								if (price.getText().toString().equals("") == false){
									book.setPrice(price.getText().toString());
								}
								book.saveInBackground();
								Intent i = new Intent(SellBookListActivity.this, SuccessfulSubmitActivity.class);
								startActivity(i);
							} else { 
								Context context = getApplicationContext();
								int duration = Toast.LENGTH_SHORT;
								CharSequence text = "You must swap or sell.";
								Toast toast = Toast.makeText(context, text, duration);
								toast.show();
							}
						} else {
							Context context = getApplicationContext();
							int duration = Toast.LENGTH_SHORT;
							CharSequence text = "Please enter a book name.";
							Toast toast = Toast.makeText(context, text, duration);
							toast.show();		
						}
					} else {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;
						CharSequence text = "Please enter a class ID.";
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				} else { 
					Context context = getApplicationContext();
					int duration = Toast.LENGTH_SHORT;
					CharSequence text = "Please enter your email.";
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();			
				}
			}
		});
	}

	public void sendSellable(View view){
	
		TextView tv = (TextView) SellBookListActivity.this.findViewById(R.id.EditText5);
	

		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox2);
		if (checkBox.isChecked()){
			checkBox.setChecked(true);
			tv.setVisibility(View.VISIBLE);
		}
		else {
			checkBox.setChecked(false);
			tv.setVisibility(View.INVISIBLE);
		}
	}

	//make this for swappable
	public void sendSwappable(View view){
		
		TextView tv = (TextView) SellBookListActivity.this.findViewById(R.id.EditText4);
	

		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
		if (checkBox.isChecked()){
			checkBox.setChecked(true);
			tv.setVisibility(View.VISIBLE);
		}
		else {
			checkBox.setChecked(false);
			tv.setVisibility(View.INVISIBLE);
		}
	}

}

