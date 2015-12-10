package me.tidbits.bitwise;

public class BitUtil {
    /**
     * Detect if two integers have opposite signs
     * 
     * @return true iff x and y have opposite signs
     */
    public static boolean oppositeSign(int x, int y) {
        return ((x ^ y) < 0);
    }

    /**
     * Reverse bits of an integer
     * 
     * 826510138 = 0b00110001010000111000101100111010
     * 
     * reverse-->
     * 
     * 1557250700 = 0b01011100110100011100001010001100
     * 
     */
    public static int reverseBits(int n) {
        n = ((n >>> 1) & 0x55555555) | ((n & 0x55555555) << 1);
        n = ((n >>> 2) & 0x33333333) | ((n & 0x33333333) << 2);
        n = ((n >>> 4) & 0x0F0F0F0F) | ((n & 0x0F0F0F0F) << 4);
        n = ((n >>> 8) & 0x00FF00FF) | ((n & 0x00FF00FF) << 8);
        n = (n >>> 16) | (n << 16);

        return n;
    }

    /**
     * 
     */
    public static int abs(int n) {
        return n;
    }
}
