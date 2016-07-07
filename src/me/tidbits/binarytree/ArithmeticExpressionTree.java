package me.tidbits.binarytree;

import java.util.Stack;

public class ArithmeticExpressionTree {

	private boolean isOperand(char c) {
		if (c <= 'z' && c >= 'a')
			return true;

		if (c <= 'Z' && c >= 'A')
			return true;

		if (c <= '9' && c >= '0')
			return true;

		return false;
	}

	private static int operatorPrecedence(char operator) {
		switch (operator) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;

		default:
			throw new IllegalArgumentException();
		}

	}

	private int compareOperator(char c1, char c2) {
		return operatorPrecedence(c1) - operatorPrecedence(c2);
	}

	private boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/';
	}

	public String getRpnFromInorder(String inorder) {
		StringBuilder rpn = new StringBuilder();

		char[] input = inorder.toCharArray();
		Stack<Character> operatorStack = new Stack<Character>();

		for (char c : input) {
			if (isOperand(c)) {
				rpn.append(c);
			} else if (isOperator(c)) {
				while (!operatorStack.isEmpty()) {
					char op = operatorStack.pop();

					if (compareOperator(c, op) <= 0) {
						rpn.append(op);
					} else {
						operatorStack.push(op);
						break;
					}
				}

				operatorStack.push(c);
			} else {
				System.out.println("Error: invalid input " + c);
			}
		}

		while (!operatorStack.isEmpty()) {
			rpn.append(operatorStack.pop());
		}

		return rpn.toString();
	}

	public TreeNode<Character> getTreeFromInorder(String inorder) {
		char[] input = inorder.toCharArray();
		Stack<TreeNode<Character>> treeStack = new Stack<TreeNode<Character>>();
		Stack<Character> operatorStack = new Stack<Character>();

		for (char c : input) {
			if (isOperand(c)) {
				TreeNode<Character> node = new TreeNode<Character>();
				node.val = c;

				treeStack.push(node);
			} else if (isOperator(c)) {

				while (!operatorStack.isEmpty()) {
					char op = operatorStack.pop();

					if (compareOperator(c, op) > 0) {
						operatorStack.push(op);
						break;
					} else {
						TreeNode<Character> node = new TreeNode<Character>();
						node.val = c;
						node.left = treeStack.pop();
						node.right = treeStack.pop();

						treeStack.push(node);
					}
				}

				operatorStack.push(c);
			}
		}

		while (!operatorStack.isEmpty()) {
			TreeNode<Character> node = new TreeNode<Character>();
			node.val = operatorStack.pop();
			node.left = treeStack.pop();
			node.right = treeStack.pop();

			treeStack.push(node);
		}

		if (treeStack.size() > 1) {
			throw new IllegalArgumentException("Incomplete expression tree construction.");
		}
		return treeStack.isEmpty() ? null : treeStack.pop();
	}

	public static void main(String[] args) {
		ArithmeticExpressionTree tree = new ArithmeticExpressionTree();
		System.out.println(tree.getRpnFromInorder("3+4*5"));

		TreeNode<Character> etree = tree.getTreeFromInorder("3+4*5");

		BinaryTreeTraversal.printPretty(etree);

	}
}
