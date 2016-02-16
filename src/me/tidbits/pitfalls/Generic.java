package me.tidbits.pitfalls;

import java.util.LinkedList;
import java.util.List;

public class Generic<T> {

	public static void main(String[] args) {

		Generic<Integer> generic = new Generic<Integer>();

		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addFirst(1);
		list.addFirst(1);

		/**
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * 
		 * Runtime type cast error.
		 * 
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
		Integer[] i = (Integer[]) genericMethod1(list);

		Integer j = genericMethod2(list);
	}

	public static <T> T[] genericMethod1(List<T> input) {
		T[] res = (T[]) new Object[input.size()];

		int i = 0;
		for (T t : input) {
			res[i] = t;
			i++;
		}
		return res;
	}

	public static <T> T genericMethod2(List<T> input) {
		return input.get(0);
	}
}
