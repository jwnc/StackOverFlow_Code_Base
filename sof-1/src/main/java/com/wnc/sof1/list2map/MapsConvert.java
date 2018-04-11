package com.wnc.sof1.list2map;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

/**
 * google集合类Maps 和 Multimaps
 * 
 * @author wnc
 *
 */
public class MapsConvert {

	public static void main(String[] args) {
		List<Student> students = DataProvider.getList();
		// Key值重复时会抛出异常IllegalStateException
		// students.add(new Student(1, "s"));
		Map<Integer, Student> mappedRoles = Maps.uniqueIndex(students, Student::getId);
		System.out.println(mappedRoles.size());

		students.add(new Student(1, "s"));
		ImmutableListMultimap<Integer, Student> multiMap = Multimaps.index(students, Student::getId);
		System.out.println(mappedRoles.size());
		System.out.println(multiMap.get(1));
	}
}
