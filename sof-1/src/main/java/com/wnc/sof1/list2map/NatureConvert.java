package com.wnc.sof1.list2map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原始遍历转换
 * 
 * @author wnc
 *
 */
public class NatureConvert {
	public static void main(String[] args) {
		List<Student> students = DataProvider.getList();
		Map<Integer, Student> map = new HashMap<Integer, Student>();
		for (Student student : students) {
			map.put(student.getId(), student);
		}
	}

}
