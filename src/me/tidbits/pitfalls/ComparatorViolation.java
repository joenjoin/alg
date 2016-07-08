package me.tidbits.pitfalls;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class ComparatorViolation {

	private static class Location {
		int x;

		Location(int x) {
			this.x = x;
		}

		@Override
		public String toString() {
			return "" + x;
		}
	}

	public static void main(String[] args) {

		Comparator<Location> comparator = new Comparator<Location>() {

			/**
			 * According to this comparator, o1 can be < o2 or >o2 depending on
			 * o1.compare(o2) or o2.compare(o1), which violates the comparator
			 * rule: o1 > o2 and o1 < o2
			 */
			@Override
			public int compare(Location o1, Location o2) {
				if (o1.x - o2.x <= 0) {
					return -1;
				}
				return 1;
			}
		};

		System.out.println(java.security.AccessController
				.doPrivileged(new sun.security.action.GetBooleanAction("java.util.Arrays.useLegacyMergeSort"))
				.booleanValue());

		System.out.println(System.getProperty("java.util.Arrays.useLegacyMergeSort"));

		Random rand = new Random(1);

		LinkedList<Location> locations = new LinkedList<Location>();

		try {
			while (true) {
				locations.clear();

				for (int i = 0; i < 32; i++) {
					int x = rand.nextInt(3);
					locations.add(new Location(x));
				}

				Collections.sort(locations, comparator);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			for (Location l : locations) {
				System.out.println(l.toString());
			}
		}
	}
}
