package com.color.harrison;

/**
 * @author HarrisonLee
 * @date 2020/4/8 21:56
 * @description 11.盛最多水的容器
 * @link https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {

    /**
     * 双指针法，依次比较每两个元素组成的容器的“盛水”容量
     * 移动较低的那一根线（由i或j指定），直到比较到i>=j
     *
     * 算法复杂度为O(n)
     * 空间复杂度为O(1)
     *
     * @param height
     * @return
     */
    public int solution01(int[] height){
        int result = 0;
        int i = 0, j = height.length - 1;
        int area = 0;
        while(i < j){
            area = Math.min(height[i], height[j])*(j - i);
            if(area > result){
                result = area;
            }
            if(height[i] > height[j]){
                --j;
            }else{
                ++i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        var container = new ContainerWithMostWater();
        var result = container.solution01(height);
        System.out.println(result);
    }
}
