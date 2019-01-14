package com.jackoak.math;

public class NumberUtil {

    /**
     * 数值 是否 在 min 和 max 之间
     * @param current
     * @param num1
     * @param num2
     * @return
     */
    public static boolean rangeInDefined(double current, double num1, double num2)
    {
        return Math.max(Math.min(num1,num2), current) == Math.min(current, Math.max(num1,num2));
    }

    public static void main(String[] args){
        System.out.println(rangeInDefined(3,6,3));
    }
}
