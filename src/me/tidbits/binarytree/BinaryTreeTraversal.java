package me.tidbits.binarytree;

import java.util.LinkedList;

public class BinaryTreeTraversal {

	public static String levelOrderPretty(TreeNode root) {
		int height = TreeNode.height(root);

		StringBuilder res = new StringBuilder("[");
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		queue.offer(root);

		int count = 0;
		TreeNode n;
		while (!queue.isEmpty()) {
			count++;

			n = queue.poll();

			if (n == null) {
				res.append("null");
			} else {

				res.append(n.val);

				if ((count < (1 << height - 1))) {
					queue.offer(n.left);
					queue.offer(n.right);
				}
			}
			res.append(",");
		}

		res.append("]");
		return res.toString();
	}

	public static void preOrder(TreeNode root) {

	}

	public static void inOrder(TreeNode root) {

	}

	/**
	 * traverse tree in post order non-iteratively
	 * 
	 * @param root
	 */
	public static void postOrder(TreeNode root) {

	}
}
