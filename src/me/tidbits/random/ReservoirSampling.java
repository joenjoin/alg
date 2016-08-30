package me.tidbits.random;

import java.util.Arrays;
/**
 * Reservoir sampling
 */
import java.util.Random;

public class ReservoirSampling {

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ReservoirSampling sampling = new ReservoirSampling(head);

		// getRandom() should return either 1, 2, or 3 randomly. Each element
		// should have equal probability of returning.

		// Check randomness
		int[] count = new int[3];
		for (int i = 0; i < 300000; i++) {
			int sample = sampling.getRandom();
			count[sample - 1]++;
		}

		System.out.println(Arrays.toString(count));
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	private ListNode head;

	/**
	 * @param head
	 *            The linked list's head. Note that the head is guaranteed to be
	 *            not null, so it contains at least one node.
	 */
	public ReservoirSampling(ListNode head) {
		this.head = head;
	}

	/** Returns a random node's value. */
	public int getRandom() {
		int i = 1;
		ListNode res = head;
		Random rand;
		ListNode node = head;
		rand = new Random();
		while (node != null) {
			int j = rand.nextInt(i);
			if (i == j + 1) {
				res = node;
			}
			i++;
			node = node.next;
		}

		return res.val;
	}

}
