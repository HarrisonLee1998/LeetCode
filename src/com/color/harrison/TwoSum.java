package com.color.harrison;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HarrisonLee
 * @date 2020-01-08 12:11
 * @description 1.两数之和
 * @link https://leetcode-cn.com/problems/two-sum/
 */
public class TwoSum {

    /**
     * 法一: 暴力破解法
     * <p>
     * 时间复杂度： O(n^2^)
     * 空间复杂度: O(1)
     *
     * @param nums   数组
     * @param target 目标值
     * @return 返回值为整型数组
     */
    private static int[] solution01(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    /**
     * 法二: 哈希法
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param nums   数组
     * @param target 目标值
     * @return 返回值为整型数组
     */
    private static int[] solution02(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(100);
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution02(nums, target);
        for (int e : result) {
            System.out.println(e);
        }
    }
}

