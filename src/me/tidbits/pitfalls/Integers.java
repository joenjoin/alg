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

    /**
     * 15.17.3. Remainder Operator %
     *
     * It follows from this rule that the result of the remainder operation can
     * be negative only if the dividend is negative, and can be positive only if
     * the dividend is positive. Moreover, the magnitude of the result is always
     * less than the magnitude of the divisor.
     */
    public static void negModulus() {
        out.println(5 % 3); // produce 2

        out.println(5 % (-3)); // same with 5 % 3, produce 2

        out.println((-5) % 3); // produce -2

        out.println((-5) % (-3)); // same with -5 % 3, produce -2
    }

    public static void main(String[] args) {
        rightShift();
        leftShift();
        negModulus();
    }
}
