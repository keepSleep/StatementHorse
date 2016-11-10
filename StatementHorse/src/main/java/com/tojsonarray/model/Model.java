package com.tojsonarray.model;

import java.util.ArrayList;

public class Model {
	private ArrayList<String> categories;
	private String name;
	private ArrayList<Long> data;
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Long> getData() {
		return data;
	}
	public void setData(ArrayList<Long> data) {
		this.data = data;
	}


}
	