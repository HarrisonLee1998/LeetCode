package com.color.harrison;

/**
 * @author HarrisonLee
 * @date 2020-01-09 17:11
 * @description 2.两数之和
 * @link https://leetcode-cn.com/problems/add-two-numbers/submissions/
 */
public class AddTwoNumbers {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

    private static ListNode generateList(int[] nums) {
        ListNode root = new ListNode(-1);
        ListNode temp = root;
        for (int x : nums) {
            ListNode node = new ListNode(x);
            temp.next = node;
            temp = node;
        }
        return root.next;
    }

    /**
     * 由于个位保存在最前面，故可以通过依次递加的方式
     * 每个节点只是一位数，可以设置变量carry来判断是否有进位
     * 事先是不知道l1和l2长度的，所以最后需要进行判断，由于结果绑定在l1上面，
     * 所以如果l2更长，需要将l2移接到l1上面
     * 在最后可能存在新增节点的情况，即当最高位进位了
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 返回相加后的第一个有效节点
     */
    private static ListNode solution01(ListNode l1, ListNode l2) {
        /*
        将返回结果绑定在l1上面
         */
        ListNode t1 = new ListNode(-1);
        ListNode t2 = new ListNode(-1);
        t1.next = l1;
        t2.next = l2;
        ListNode root = t1;
        int carry = 0;
        while (l1 != null && l2 != null) {
            l1.val += l2.val += carry;
            carry = l1.val / 10;
            // 取个位数字
            l1.val %= 10;
            t1 = l1;
            l1 = t1.next;
            t2 = l2;
            l2 = t2.next;
        }
        if (l1 == null) {
            // 由于l2比l1长，所以把l2接到l1尾部
            t1.next = l2;
            while (carry != 0 && l2 != null) {
                l2.val += carry;
                carry = l2.val / 10;
                l2.val %= 10;
                t1 = l2;
                l2 = t1.next;
            }
        }
        if (l2 == null) {
            while (carry != 0 && l1 != null) {
                l1.val += carry;
                carry = l1.val / 10;
                l1.val %= 10;
                t1 = l1;
                l1 = t1.next;
            }
        }
        if (carry != 0) {
            t1.next = new ListNode(carry);
        }
        return root.next;
    }

    /**
     * 参考 LeetCode 官方解法
     * 方案一的算法思路比较直接和明显，但是实现过程很冗杂
     * 比如头结点(非有效)的设置： 可以简化为只设置一个头结点
     * 以及解决两条长度不一致： 可以简化为只一种情况
     * 当然解法一相比解法二也有优点，即基本上在原空间上操作的
     * 也正是因为是在原空间上操作，所以导致了判断条件比较冗杂
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 返回相加后的第一个有效节点
     */
    public static ListNode solution02(ListNode l1, ListNode l2) {
        // 头结点， 无效值
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        int carry = 0, x, y;
        while (l1 != null || l2 != null) {
            x = l1 == null ? 0 : l1.val;
            y = l2 == null ? 0 : l2.val;
            curr.next = new ListNode((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
            curr = curr.next;
        }
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }
        return dummyNode.next;
    }

    public static void main(String... args) {
        int[] nums1 = new int[]{5};
        int[] nums2 = new int[]{5};
        ListNode l1 = generateList(nums1), l2 = generateList(nums2);
        ListNode result = solution02(l1, l2);
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) {
                System.out.print("->");
            }
            result = result.next;
        }
    }
}
