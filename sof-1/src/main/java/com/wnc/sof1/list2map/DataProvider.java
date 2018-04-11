package com.wnc.sof1.list2map;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
	public static List<Student> getList() {
		List<Student> list = new ArrayList<Student>();
		for (int i = 1; i <= 1000000; i++) {
			list.add(new Student(i, "Sang" + i));
		}
		return list;
	}
}

class Student {
	private int id;
	private String name;

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
