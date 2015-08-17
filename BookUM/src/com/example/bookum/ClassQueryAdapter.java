package com.example.bookum;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery;

public class ClassQueryAdapter extends ParseQueryAdapter<Book>{

	public ClassQueryAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Book>(){
			
			String search = getInput(); 

			@Override
			public ParseQuery<Book> create() {
				ParseQuery bookQuery = new ParseQuery("Book");
				bookQuery.whereContainedIn("classId", Arrays.asList(search));
				return bookQuery.orderByAscending("classId");
			}
			
		});
		
	}
	
	public static String getInput(){
		return BuyBookListActivity.course;
		
	}
	
	public View getItemView(Book book, View v, ViewGroup parent){
		if (v == null){
			v = View.inflate(getContext(), R.layout.class_list_items, null);
		}
		
		super.getItemView(book, v, parent);
		
		
		
		String bookName = book.getBookId();
		String email = book.getEmail();
		String bookSwap = book.getBookSwap();
		String val = book.getPrice();
		String val2 = "$";
		val2 = val2+val;
		
		email = "Contact info: " + email;
		val2 = "Book selling price: " + val2;
		bookSwap = "Book willing to swap for: " + bookSwap;
		bookName = "Book for sale: " + bookName;
		
		TextView emailID = (TextView) v.findViewById(R.id.emailID);
		TextView classID = (TextView) v.findViewById(R.id.classID);
		TextView bookID = (TextView) v.findViewById(R.id.bookID);
		TextView priceID = (TextView) v.findViewById(R.id.priceID);
		
		emailID.setText(email);
		classID.setText(bookName);
		bookID.setText(bookSwap);
		priceID.setText(val2);
		
		
		
		return v;
	}
}
