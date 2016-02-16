package me.tidbits.dp;

public class LongestCommonSubsequeunce {

	/**
	 * 
	 * Longest common subsequence of 2 strings
	 * 
	 * @param input
	 * @return length of LCS
	 */
	public static int longestCommonSubsequence(String str1, String str2) {
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		int[][] aux = new int[arr2.length + 1][arr1.length + 1];
		int r, l;

		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr1.length; j++) {
				// aux index
				r = i + 1;
				l = j + 1;
				if (arr1[j] == arr2[i]) {
					aux[r][l] = aux[r - 1][l - 1] + 1;
				} else {
					aux[r][l] = Math.max(aux[r - 1][l], aux[r][l - 1]);
				}
			}
		}

		return aux[arr2.length][arr1.length];
	}

	public static void main(String[] args) {
		System.out.println(longestCommonSubsequence("ABCABCBA", "CBABCABCC"));
	}
}
