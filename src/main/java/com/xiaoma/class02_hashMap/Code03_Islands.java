package com.xiaoma.class02_hashMap;

/**
 * 岛问题
 * 【题目】
 * 一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右 四个位置相连，如
 * 果有一片1连在一起，这个部分叫做一个岛，求一个矩阵中有多少个岛?
 * 【举例】
 * 001010
 * 111010
 * 100100
 * 000000
 * 这个矩阵中有三个岛
 * 【进阶】
 * 如何设计一个并行算法解决这个问题
 * @author xiaoma
 * @date 2022/12/6 10:55
 */
public class Code03_Islands {

    public static int countIslands(int[][] m) {
        if (m == null || m.length == 0) {
            System.out.println("输出的参数为空!");
            return -1;
        }
        int row = m.length;
        int col = m[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (m[i][j] == 1) {
                    res++;
                    // 开始感染过程
                    infect(m , i , j , row , col);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] m , int i , int j , int row , int col) {
        if (m == null || i < 0 || i >= row || j < 0 || j >= col || m[i][j] != 1) {
            return;
        }
        m[i][j] = 2;
        infect(m , i , j - 1 , row , col);
        infect(m , i - 1 , j , row , col);
        infect(m , i , j + 1 , row , col);
        infect(m , i + 1 , j , row , col);
    }

    public static void main(String[] args) {
        int[][] m1 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 0, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println( "m1数组的岛的数量:" + countIslands(m1));
        int[][] m2 = {  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        System.out.println( "m2数组的岛的数量:" + countIslands(m2));
    }
}
