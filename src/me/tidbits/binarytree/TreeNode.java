package me.tidbits.binarytree;

import java.util.Arrays;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public static TreeNode fromString(String s) {
		if (s == null)
			return null;

		char[] seq = s.toCharArray();
		if (seq[0] != '[' || seq[seq.length - 1] != ']') {
			return null;
		}

		String[] nodestr = s.substring(1, s.length() - 1).split(",");
		System.out.println(Arrays.toString(nodestr));
		TreeNode[] nodes = new TreeNode[nodestr.length];
		for (int i = 0; i <= nodestr.length / 2 - 1; i++) {
			TreeNode node = nodes[i];

			if (node == null) {
				node = constructPureNode(nodestr[i]);
			}
			if (node != null) {

				int left = 2 * (i + 1) - 1;
				int right = 2 * (i + 1) + 1 - 1;

				if (left < nodestr.length) {
					node.left = constructPureNode(nodestr[left]);
					nodes[left] = node.left;
				}

				if (right < nodestr.length) {
					node.right = constructPureNode(nodestr[right]);
					nodes[right] = node.right;
				}
			}

			nodes[i] = node;
		}

		return nodes[0];
	}

	private static TreeNode constructPureNode(String snode) {

		System.out.println("Constr " + snode);
		if ("null".equals(snode)) {
			return null;
		}

		TreeNode node = new TreeNode();
		node.val = Integer.parseInt(snode);
		return node;
	}

	public static int height(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return Math.max(height(root.left), height(root.right)) + 1;
	}

	public static void main(String[] args) {
		TreeNode root = fromString("[1,2,3,null,5,null,7]");

		System.out.println(BinaryTreeTraversal.levelOrderPretty(root));
	}
}
