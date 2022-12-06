 package com.xiaoma.class02_hashMap;

/**
 * BitMap 位运算
 *
 * @author xiaoma
 * @date 2022/12/5 12:42
 */
public class Code02_BitMap {
    public static void main(String[] args) {
        int[] arr = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10}; // int:4字节 32bit;arr数组可以表示320个bit位
        int i = 178; // 假设获取第 178 位的状态
        int numIndex = i / 32;
        int bitIndex = i % 32;

        System.out.println("arr[numIndex]二进制:" + Integer.toBinaryString(arr[numIndex]));

        // 获取178位的状态
        int result = ((arr[numIndex] >> bitIndex) & 1);
        System.out.println("获取第i位的状态:" + result);

        // 把178位的状态改成1
        result = arr[numIndex] | (1 << bitIndex);
        System.out.println("把i位的状态改成1:" + result);
        System.out.println("把i位的状态改成1(二进制):" + Integer.toBinaryString(result));

        // 把178位的状态改成0
        result = arr[numIndex] & (~(1 << bitIndex));
        System.out.println("把i位的状态改成0:" + result);
        System.out.println("把i位的状态改成0(二进制):" + Integer.toBinaryString(result));

        // 将178位的状态拿出来
        System.out.println( "将178位的状态拿出来:" + ((arr[i / 32] >> (i % 32)) & 1));
    }
}