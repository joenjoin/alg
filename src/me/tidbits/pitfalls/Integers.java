package me.tidbits.pitfalls;

import static java.lang.System.out;

/**
 * Here I present some pitfalls of Integer data type
 */
public class Integers {

    /**
     * The integer left shift << takes only 5 bits of the right operand, just
     * like AND right operand with 0b11111
     * 
     * JLS 15.19. Shift Operators (slightly paraphrased):
     *
     * If the promoted type of the left-hand operand is int, only the five
     * lowest-order bits of the right-hand operand are used as the shift
     * distance. It is as if the right-hand operand were subjected to a bitwise
     * logical AND operator & with the mask value 0x1f ,or 0b11111. The shift
     * distance actually used is therefore always in the range 0 to 31,
     * inclusive.
     *
     */
    public static void leftShift() {
        out.println(1 << 31); // = 0x80000000

        out.println(1 << 32); // 1<<32 === 1<<0 --> 0x00000001

        out.println(1 << 33); // 1<<33 === 1<<1 --> 0x00000002
    }

    public static void rightShift() {
        out.println(0x0F >> 1); // =0x07
        out.println(0x0F >>> 1); // =0x07

        out.println(0x0F >> 31); // = 0
        out.println(0x0F >>> 31); // = 0

        out.println(0x0F >> 32); // 0F>>32 === 0F>>0 --> 0F
        out.println(0x0F >>> 32); // 0F>>32 === 0F>>0 --> 0F

        out.println(0x0F >> 33); // 0F>>33 === 0F>>1 --> 07
        out.println(0x0F >>> 33); // 0F>>33 === 0F>>1 --> 07
    }

    public static void main(String[] args) {
        rightShift();
    }
}
