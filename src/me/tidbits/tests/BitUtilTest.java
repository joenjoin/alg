package me.tidbits.tests;

import java.util.Random;

import me.tidbits.bitwise.BitUtil;

public class BitUtilTest {

    public static void main(String[] args) {
        testReverseBits();
    }

    public static void testReverseBits() {
        Random r = new Random();
        int count = 1000000;
        for (int i = 0; i < count; i++) {
            int n = r.nextInt(Integer.MAX_VALUE);

            assertEquals(BitUtil.reverseBits(n), Integer.reverse(n), n + "");
        }
        System.out.println("All test passed.");
    }

    private static void assertEquals(int m, int n, String msg) {
        if (m != n) {
            throw new RuntimeException(m + "!=" + n + " " + msg);
        }
    }

    private static void assertEquals(int m, int n) {
        assertEquals(m, n, "");
    }
}
