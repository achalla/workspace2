package com.example.bookum;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Book")

public class Book extends ParseObject {
	
	public Book(){ }
	
	public void setClassId(String classId){
		put("classId", classId);
	}
	
	public void setBookId(String bookId){
		put("bookId", bookId);
	}
	
	public void setBookSwap(String bookSwap){
		put("bookSwap", bookSwap);
	}
	
	public void setPrice(String price){
		put("price", price);
	}
	
	public void setEmail(String email){
		put("email", email);
	}
	
	public String getClassId(){
		return getString("classId");
	}
	
	public String getBookId(){
		return getString("bookId");
	}
	
	public String getBookSwap(){
		return getString("bookSwap");
	}
	
	public String getPrice(){
		return getString("price");
		
	}
	
	public String getEmail(){
		return getString("email");
	}
}