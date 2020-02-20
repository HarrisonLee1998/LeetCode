package com.color.harrison;

import java.util.*;

/**
 * @author HarrisonLee
 * @date 2020/2/20 20:05
 * @description 15.三数之和
 * @link https://leetcode-cn.com/problems/3sum/
 */
public class ThreeSum {

    /**
     * 找出数组中三个值的和为0, 求所有这样的三元组
     * 最后的解决方案里面不能出现重复的三元组
     * 三元组中不能有同一个位置的元素多次组合而成，但是可以有相同的值
     * 如何找到三元组比较简单，外部一个循环内部套TwoSum的解法
     * 难点是如何去重，即找到一个三元组后如何判断该三元组不在结果中
     *
     * 第一次运行 超出时间限制，应该算法正确性没问题
     * 但是实现方式需要改进，怀疑导致超时的原因就是查重的实现
     *
     * 由于实在是不清楚更高效的实现方式，决定寻求解决方案
     *
     * 时间复杂度: O(n^2^)
     * 空间复杂度: O(n)
     *
     * @param nums 操作数组nums
     * @return
     */
    public static List<List<Integer>> solution01(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

         class Util {
             private final int TARGET = 0;

             private boolean listEquals(List<Integer> list1, List<Integer> list2) {
                assert list1.size() == list2.size();
                Collections.sort(list1);
                Collections.sort(list2);
                for(int i = 0; i < list1.size(); ++i) {
                    if(!list1.get(i).equals(list2.get(i))) {
                        return false;
                    }
                }
                return true;
            }

             public void insertIfNotDuplicated(List<Integer> tmp) {
                 // is duplicated ?
                 boolean flag = false;
                 for(List list : result) {
                     if(listEquals(tmp, list)) {
                         flag = true;
                         break;
                     }
                 }
                 if(!flag) {
                     result.add(tmp);
                 }
             }

             public List<List<Integer>> twoSum(int[] nums, int pos) {
                int target = TARGET - nums[pos];
                List<List<Integer>>list = new ArrayList<>();
                List<Integer>tmp = new ArrayList<>();
                for(int i = pos + 1; i < nums.length; ++i) {
                    if(tmp.contains(target - nums[i])) {
                        // 匹配到符合的三元组，添加到临时结果中
                        list.add(Arrays.asList(nums[pos], nums[i], target - nums[i]));
                    } else {
                        tmp.add(nums[i]);
                    }
                }
                return list;
             }
         }

         Util util = new Util();
         for(int i = 0; i < nums.length - 1; ++i) {
             List<List<Integer>> lists = util.twoSum(nums, i);
             for(List<Integer> list : lists) {
                 util.insertIfNotDuplicated(list);
             }
         }
         return result;
    }

    /**
     * 经过分析，发现solution01超时的原因之一是利用了List.contains(Object o)方法，该方法底层的实现是线性方式
     * 针对这一缺陷，应该该为哈希方式
     *
     * 上面优化后，还是超时
     *
     * 博客 https://www.cnblogs.com/grandyang/p/4481576.html
     * 参考上面链接的方法
     *
     * 时间复杂度: O(n^3^)
     * 空间复杂度: O(1)
     *
     * @param nums 操作数组nums
     */
    public static List<List<Integer>> solution02(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        final int indexOffset = 2;
        // 将源数组排序，后面操作依赖有序数组
        Arrays.sort(nums);
        if(nums.length == 0 || nums[0] > 0 || nums[len - 1] < 0) {
            return result;
        }
        for(int i = 0; i < len - indexOffset; ++i) {
            /**
            注意这里很关键，目的是去重
            首先，如果当前值和前一个数值相等而且当前数不是第一个数，则跳过这个值
            因为能够组成的三元组的情况就已经被前一个元素已经组合了，这一个值组成的情况必然和前一个重复
            注意思考如何重复的！
             */
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = 0 - nums[i];
            int x = i + 1;
            int y = len -1;
            while(x < y) {
                if(nums[x] + nums[y] == target) {
                    result.add(Arrays.asList(nums[i], nums[x], nums[y]));
                    /**
                     * 注意，下面两个while循环非常重要
                     * 目的是为了去重
                     */
                    while(x < y && nums[x] == nums[x + 1]) {
                        ++x;
                    }
                    while(x < y && nums[y] == nums[y - 1]) {
                        --y;
                    }
                    ++x;
                    --y;
                } else if(nums[x] + nums [y] < target) {
                    /**
                     * 这一个分支说明nums[i]+nums[x]+nums[y]小于0, 所以待取值区间需要往右移
                     */
                    ++x;
                } else {
                    /**
                     * 这一个分支说明nums[i]+nums[x]+nums[y]大于0, 所以待取值区间需要往左移
                     */
                    --y;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = solution02(nums);
        for(List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }
}
