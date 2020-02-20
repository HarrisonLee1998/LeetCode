package com.color.harrison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author HarrisonLee
 * @date 2020/2/20 22:33
 * @description 18.四数之和
 * @link https://leetcode-cn.com/problems/4sum/
 */
public class FourSum {

    /**
     *本题是3Sum的升级版，不过逻辑都是一样的
     * 在3Sum的解法上改一下，使用2层外层for循环
     *
     * 时间复杂度: O(n^4^)
     * 空间复杂度: O(1)
     *
     * @param nums 操作数组nums
     * @param target 目标和值
     * @return
     */
    public static List<List<Integer>> solution01(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        if(nums.length == 0 || nums[0] >0 || nums[len-1] <0) {
            return result;
        }
        for(int i = 0; i < len - 3; ++i) {
            /**
             * 注意这两个外层的for循环的去重手段都是一样的，至于为什么这么做，原因和3Sum一样
             */
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for(int j = i + 1; j < len - 2; ++j) {
                /**
                 *
                 */
                if(j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int x = j + 1;
                int y = len -1;
                while(x < y) {
                    if(nums[i] + nums[j] + nums[x] + nums[y] == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[x], nums[y]));
                        /**
                         * 下面两个while循环的作用和3Sum中一样，为了去重
                         */
                        while(x < y && nums[x] == nums[x+1]) {
                            ++x;
                        }
                        while(x < y && nums[y] == nums[y-1]) {
                            --y;
                        }
                        ++x;
                        --y;
                    } else if(nums[i] + nums[j] + nums[x] + nums[y] < target) {
                        ++x;
                    } else {
                        --y;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        final int target = 0;
        List<List<Integer>> lists = solution01(nums, target);
        for(List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }
}
