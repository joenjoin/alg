package me.tidbits.list;

public class SinglyLinkedList {

	/**
	 * Find middle of a singly linked list, for example:
	 * 
	 * @Input: 3->2->5->6->1
	 * @Output: 5->6->1
	 * 
	 * @Input: 7->5->8->3
	 * @Output: 8->3
	 */
	public static SingleListNode middle(SingleListNode head) {
		SingleListNode slow, fast;

		slow = fast = head;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	/**
	 * Reverse a singly linked list, for example:
	 * 
	 * @Input: 1->2->3
	 * @Output: 3->2->1
	 */
	public static SingleListNode reverse(SingleListNode head) {

		SingleListNode pre, cur, next;

		cur = head;
		pre = null;

		while (cur != null) {
			next = cur.next;
			cur.next = pre;

			pre = cur;
			cur = next;
		}

		return pre;
	}

	public static void main(String[] args) {
		SingleListNode list = createSinglelList(3, 2, 8, 5, 7, 1);
		// printSingleList(list);

		printSingleList(reverse(list));

		list = createSinglelList(3, 2, 8, 5, 7, 1);
		printSingleList(middle(list));
	}

	private static SingleListNode createSinglelList(int... arr) {
		SingleListNode head = new SingleListNode();

		SingleListNode node = head;
		for (int i = 0; i < arr.length; i++) {
			node.next = new SingleListNode();
			node.next.val = arr[i];
			node = node.next;
		}
		return head.next;
	}

	private static void printSingleList(SingleListNode head) {
		if (head == null) {
			System.out.println("Null list");
		}

		SingleListNode node = head;
		while (node != null) {
			System.out.print(node.val);
			node = node.next;
			if (node != null) {
				System.out.print("->");
			}
		}
		System.out.println();
	}

}
