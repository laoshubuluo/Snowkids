package com.rat.nm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

	/**
	 * 筛重
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> removeDuplicateWithOrder(List<T> list) {
		Set<T> set = new HashSet<T>();
		List<T> newList = new ArrayList<T>();
		for (Iterator<T> iter = list.iterator(); iter.hasNext();) {
			T element = iter.next();
			if (!set.contains(element)) {
				set.add(element);
				newList.add(element);
			}
		}
		return newList;
	}

}
