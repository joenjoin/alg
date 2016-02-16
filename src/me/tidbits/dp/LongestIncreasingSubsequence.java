package me.tidbits.dp;

import java.util.Arrays;

import me.tidbits.utils.ListUtil;

/**
 * Find the longest subsequence of an array, subsequence means not necessarily
 * consecutive
 * 
 * @Input 2 5 6 1 3 8 7 4 9
 * @Output 2 5 6 8 9
 */
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// int[] input = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
		// int[] input = { 2, 5, 6, 0, 1, 3, 4, 7, 9, 8, 10 };
		// int[] input = { 6, 7, 8, 9, 1, 2, 3, 4, 5 };
		// int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int[] input = ListUtil.generateRandomArray(10, false, 100, 10);
		System.out.println("Input: " + Arrays.toString(input));
		int res1 = longestIncreasingSubsequence_n2(input);
		System.out.println("--------------");
		int res2 = longestIncreasingSubsequence_nlogn(input);

		// for (int i = 0; i < 10000; i++) {
		// int[] input = ListUtil.generateRandomArray(10000, false, 100000, 0);
		//
		// int res1 = longestIncreasingSubsequence_n2(input);
		// int res2 = longestIncreasingSubsequence_nlogn(input);
		//
		// if (res1 != res2) {
		// System.out.println(res1 + " vs " + res2);
		// throw new RuntimeException("Failed.");
		// }
		// }

		System.out.println("All passed.");
	}

	/**
	 * n2 worst case
	 * 
	 * @param input
	 * @return
	 */
	public static int longestIncreasingSubsequence_n2(int[] input) {
		// farr[i] = longest length of subsequence ends at input[i]
		int[] farr = new int[input.length];

		// Record longest subsequence in index linked mode
		int[] index_arr = new int[input.length];

		for (int i = 0; i < input.length; i++) {

			int sublongest = -1;
			int sublongestindex = -1;
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i] && farr[j] > sublongest) {
					sublongest = farr[j];
					sublongestindex = j;
				}
			}

			if (sublongest != -1) {
				farr[i] = sublongest + 1;
				index_arr[i] = sublongestindex;
			} else {
				farr[i] = 1;
				index_arr[i] = i;
			}
		}

		// Output longest subsequence
		int maxLength = 0;
		int maxIndex = -1;
		for (int i = 0; i < farr.length; i++) {
			if (farr[i] > maxLength) {
				maxLength = farr[i];
				maxIndex = i;
			}
		}

		for (int k = 0; k < farr.length; k++) {
			System.out.print(farr[k] + " ");
		}
		System.out.println();

		for (int k = 0; k < index_arr.length; k++) {
			System.out.print(index_arr[k] + " ");
		}
		System.out.println();

		System.out.println(maxIndex);

		int i = maxIndex;
		while (true) {
			System.out.print(input[i] + " ");
			if (i == index_arr[i])
				break;
			i = index_arr[i];
		}
		System.out.println();

		return maxLength;
	}

	/**
	 * 
	 * nlogn worst case
	 * 
	 * @param input
	 * @return
	 */
	public static int longestIncreasingSubsequence_nlogn(int[] input) {
		int[] table = new int[input.length];
		int[] position = new int[input.length];
		int len;

		table[0] = input[0];
		len = 1;

		for (int i = 1; i < input.length; i++) {
			if (input[i] < table[0]) {
				table[0] = input[i];
				position[i] = 0;
			} else if (input[i] > table[len - 1]) {
				table[len] = input[i];
				position[i] = len;
				len++;
			} else {
				// Find longest active list whose end item smaller than
				// input i
				int l = 0;
				int r = len - 1;
				int mid = 0;
				while (l <= r) {
					mid = l + (r - l) / 2;

					if (mid == l)
						break;
					if (table[mid] > input[i]) {
						r = mid;
					} else {
						l = mid;
					}
				}

				table[r] = input[i];
				position[i] = r;
			}
		}

		// System.out.println(Arrays.toString(position));

		int len2 = len - 1;
		for (int i = position.length - 1; i >= 0; i--) {
			if (position[i] == len2) {
				System.out.print(input[i] + " ");
				len2--;
			}
		}

		System.out.println();
		return len;
	}
}
