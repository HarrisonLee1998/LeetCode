package com.color.harrison;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author HarrisonLee
 * @date 2020/4/8 22:58
 * @description 36. 有效的数独
 * @link https://leetcode-cn.com/problems/valid-sudoku/
 */
public class ValidSudoku {

    /**
     * 注意9x9的数独，第一想法是暴力破解
     * 分别对三个条件进行遍历即可
     *
     * @param board
     * @return
     */
    public boolean solution01(char[][] board) {
        //验证行
        for (var i = 0; i < 9; ++i) {
            boolean[] test = new boolean[10];
            for (var j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    if (test[board[i][j] - '0']) {
                        return false;
                    } else {
                        test[board[i][j] - '0'] = true;
                    }
                }
            }
        }
        //验证列
        for (var i = 0; i < 9; ++i) {
            boolean[] test = new boolean[10];
            for (var j = 0; j < 9; ++j) {
                if (board[j][i] != '.') {
                    if (test[board[j][i] - '0']) {
                        return false;
                    } else {
                        test[board[j][i] - '0'] = true;
                    }
                }
            }
        }
        //验证小宫格
        /*
        这里小于9的条件写成了3， 纯属粗心大意
         */
        for (var i = 0; i < 9; i += 3) {
            for (var j = 0; j < 9; j += 3) {
                // i, j 分别是对应小宫格的最左上角的坐标
                boolean[] test = new boolean[10];
                for (var x = i; x < i + 3; ++x) {
                    for (var y = j; y < j + 3; ++y) {
                        if (board[x][y] != '.') {
                            if (test[board[x][y] - '0']) {
                                return false;
                            } else {
                                test[board[x][y] - '0'] = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 很明显， 暴力破解法虽然直接，但看起来很愚蠢
     * 通过参略官方解法，发现一次遍历即可解决
     * 其实解法一中前两次遍历的代码就很相似，只是把第三次想得太复杂了
     * 其实可以归结为一次对整个数独遍历，具体信息看代码实现
     *
     * @param board
     * @return
     */
    public boolean solution02(char[][] board) {
        boolean[][] rows = new boolean[10][10];
        boolean[][] cols = new boolean[10][10];
        boolean[][] boxes = new boolean[10][10];
        for (var i = 0; i < 9; ++i) {
            for (var j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    // 判断所在行
                    if (rows[i][num]) {
                        return false;
                    } else {
                        rows[i][num] = true;
                    }

                    // 判断所在列
                    if (cols[j][num]) {
                        return false;
                    } else {
                        cols[j][num] = true;
                    }

                    // 数独元素所属的小宫格的下标，从左到右， 从上到下
                    int k = i / 3 + j / 3 * 3;
                    if (boxes[k][num]) {
                        return false;
                    } else {
                        boxes[k][num] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //由于本题的测试用例较复杂，所以不在本地测试
        char[][] board = {{'.', '.', '.', '.', '5', '.', '.', '1', '.'}, {'.', '4', '.', '3', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '3', '.', '.', '1'}, {'8', '.', '.', '.', '.', '.', '.', '2', '.'}, {'.', '.', '2', '.', '7', '.', '.', '.', '.'}, {'.', '1', '5', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '2', '.', '.', '.'}, {'.', '2', '.', '9', '.', '.', '.', '.', '.'}, {'.', '.', '4', '.', '.', '.', '.', '.', '.'}};
        var obj = new ValidSudoku();
        boolean result = obj.solution02(board);
        System.out.println(result);
    }
}
