package me.tidbits.tests;

public class EntryTest {
    public static void main(String[] args) {

        int i = 0x00000001;

        System.out.println("" + Integer.toHexString(i << 32));
    }
}
