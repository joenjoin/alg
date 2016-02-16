package me.tidbits.utils;

import java.util.HashSet;
import java.util.Random;

import me.tidbits.list.SingleListNode;

public class ListUtil {

	public static int[] generateRandomArray(int length, boolean permitDuplicate, int max, int min) {
		int[] res = new int[length];

		HashSet<Integer> values = new HashSet<Integer>();

		Random random = new Random();
		for (int i = 0; i < length;) {
			double next = random.nextDouble();
			int e = (int) (next * (max - min)) + min;

			if (!permitDuplicate && values.contains(e)) {
				continue;
			}
			values.add(e);

			res[i] = e;
			i++;
		}

		return res;
	}

	public static SingleListNode<Integer> generateRandomList(int length, boolean permitDuplicate, int max, int min) {
		SingleListNode<Integer> head, cur;

		head = new SingleListNode<Integer>();
		cur = head;

		HashSet<Integer> values = new HashSet<Integer>();

		Random random = new Random();
		while (length > 0) {
			double next = random.nextDouble();
			int e = (int) (next * (max - min)) + min;

			if (!permitDuplicate && values.contains(e)) {
				continue;
			}
			values.add(e);

			SingleListNode<Integer> node = new SingleListNode<Integer>();
			node.val = e;
			cur.next = node;

			cur = node;
			length--;
		}

		return head.next;
	}

	public static String asString(SingleListNode<?> head) {
		StringBuilder buf = new StringBuilder();
		while (head != null && head.next != null) {
			buf.append(head.val);
			buf.append(",");
			head = head.next;
		}

		if (head != null) {
			buf.append(head.val);
		}

		return buf.toString();
	}

	// public static <T> T[] asArray(SingleListNode<T> head, int len) {
	// T[] res = new Object[len];
	//
	// for (int i = 0; i < len; i++) {
	// res[i] = head.val;
	// head = head.next;
	// }
	//
	// return res;
	// }
}
