package me.tidbits.dp;

import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.lang3.ArrayUtils;

import me.tidbits.list.SingleListNode;
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

		// System.out.println(longestIncreasingSubsequence_n2(input));
		// System.out.println("-----------");
		// System.out.println(longestIncreasingSubsequence_nlogn(input));

		int[] input = ListUtil.generateRandomArray(10, false, 100, 10);

		System.out.println(Arrays.toString(input));
		System.out.println("-----------");
		System.out.println(longestIncreasingSubsequence_n2(input));
		System.out.println("-----------");
		System.out.println(longestIncreasingSubsequence_nlogn(input));
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
		int len;

		table[0] = input[0];
		len = 1;

		for (int i = 1; i < input.length; i++) {
			if (input[i] > table[len - 1]) {
				table[len] = input[i];
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
					if (input[i] < table[mid]) {
						r = mid - 1;
					} else {
						l = mid;
					}
				}

				table[mid] = input[i];
			}
		}

		return len;
	}
}
