package me.tidbits.binarytree;

public class TreeNode<T> {
	T val;
	TreeNode<T> left;
	TreeNode<T> right;

	public static TreeNode<String> fromString(String s) {
		if (s == null)
			return null;

		char[] seq = s.toCharArray();
		if (seq[0] != '[' || seq[seq.length - 1] != ']') {
			return null;
		}

		String[] nodestr = s.substring(1, s.length() - 1).split(",");
		TreeNode<String>[] nodes = new TreeNode[nodestr.length];
		for (int i = 0; i <= nodestr.length / 2 - 1; i++) {
			TreeNode<String> node = nodes[i];

			if (node == null) {
				node = createSingleNode(nodestr[i]);
			}
			if (node != null) {

				int left = 2 * (i + 1) - 1;
				int right = 2 * (i + 1) + 1 - 1;

				if (left < nodestr.length) {
					node.left = createSingleNode(nodestr[left]);
					nodes[left] = node.left;
				}

				if (right < nodestr.length) {
					node.right = createSingleNode(nodestr[right]);
					nodes[right] = node.right;
				}
			}

			nodes[i] = node;
		}

		return nodes[0];
	}

	private static TreeNode<String> createSingleNode(String snode) {
		if ("null".equals(snode)) {
			return null;
		}

		TreeNode<String> node = new TreeNode<String>();
		node.val = snode;
		return node;
	}

	public static int height(TreeNode<?> root) {
		if (root == null) {
			return 0;
		}

		return Math.max(height(root.left), height(root.right)) + 1;
	}

	public static void main(String[] args) {
		String input = "[1,2,3,null,5,null,7]";
		TreeNode<String> root = fromString(input);
		System.out.print("Input: ");
		System.out.println(input);

		System.out.print("Level order pretty:");
		System.out.println(BinaryTreeTraversal.levelOrderPretty(root));
	}
}
