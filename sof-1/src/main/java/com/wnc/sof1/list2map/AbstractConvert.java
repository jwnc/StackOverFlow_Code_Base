package com.wnc.sof1.list2map;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 提供泛型方法来转换
 * 
 * @author wnc
 *
 */
public class AbstractConvert {
	/**
	 * Returns a map where each entry is an item of {@code list} mapped by the key
	 * produced by applying {@code mapper} to the item.
	 *
	 * @param list
	 *            the list to map
	 * @param mapper
	 *            the function to produce the key from a list item
	 * @return the resulting map
	 * @throws IllegalStateException
	 *             on duplicate key
	 */
	public static <K, T> Map<K, T> toMapBy(List<T> list, Function<? super T, ? extends K> mapper) {
		return list.stream().collect(Collectors.toMap(mapper, Function.identity()));
	}

	public static void main(String[] args) {
		List<Student> students = DataProvider.getList();
		// Key值重复时会抛出异常IllegalStateException
		// students.add(new Student(1, "s"));
		toMapBy(students, Student::getId);
	}
}
