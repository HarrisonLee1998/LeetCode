package com.color.harrison;

import java.util.Arrays;

/**
 * @author HarrisonLee
 * @date 2020/4/8 22:09
 * @description 16. 最接近的三数之和
 * @link https://leetcode-cn.com/problems/3sum-closest/
 */
public class ThreeSumClosest {

    /**
     * 这道题和3Sum那道很相似，可以在其代码实现上更改
     * 注意利用好题中所说的只存在唯一答案这个关键信息
     *
     * 时间复杂度：O(n^2^)
     * 空间复杂度：O(1)
     * @param nums
     * @param target
     * @return
     */
    public int solution01(int[] nums, int target){
        Arrays.sort(nums);
        if(nums.length < 3){
            return target;
        }
        // 这里赋给result的值也是下面第一次遍历时求得的和
        int result = nums[0] + nums[1] + nums[nums.length-1];
        for(var i = 0;i <nums.length - 2;++i){
            if(i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            int temp = 0;
            while(j < k){
                temp = nums[i] + nums[j] + nums[k];
                if(temp == target){
                    return temp;
                }
                /*
                注意这里的do...while去重与while去重方式有点不一样，
                而且这里是与target比较，而不是result比较，纯属粗心大意
                 */
                if(temp > target){
                    do{
                        --k;
                    }while(j < k && nums[k+1] == nums[k]);
                }else {
                    do{
                        ++j;
                    }while(j < k && nums[j] == nums[j-1]);
                }
                if(Math.abs(temp - target) < Math.abs(result - target)){
                    result = temp;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[]nums={1, 1, -1, -1, 3};
        int target = -1;
        var obj = new ThreeSumClosest();
        System.out.println(obj.solution01(nums, target));
    }
}
