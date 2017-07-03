package com.example.suggestfruits;

import java.util.ArrayList;

import com.example.suggestfruits.exceptions.NotFoundFruitException;

public final class CalendarFruit {
	
	private ArrayList<String> names;
	private ArrayList<String []> fruits;
	
	public CalendarFruit() {
		fruits = new ArrayList<String[]>();
		names = new ArrayList<String>();
	}
	
	public final void setFruits(String name , String [] setFruits) {
		names.add(name);
		fruits.add(setFruits);
	}
	
	public boolean existsMonth(String name) {
		return (names.indexOf(name)!=-1);
		
	}
	
	public final ArrayList<String[]> getFruits ()  {
		return fruits;
	}
	
	public final String [] getFruits (String name) throws NotFoundFruitException {
		int index = names.indexOf(name);
		if (index==-1) {
			throw new NotFoundFruitException();
		}
		return fruits.get(index);
	}
	

}
