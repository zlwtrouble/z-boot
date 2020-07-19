package com.spring.boot.controller;

import java.util.HashMap;

public class ListNodeTest {

    public static void main(String[] args) {
//        ListNode listNode = new ListNode();
//        listNode.removeNthFromEnd()

    }


//    public ListNode removeNthFromEnd(ListNode head, int n)
//    {
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//        ListNode first = dummy;
//        ListNode second = dummy;
//        // Advances first pointer so that the gap between first
//        and second is n nodes apart
//        for (int i = 1; i <= n + 1; i++) {
//            first = first.next;
//        }
//        // Move first to the end, maintaining the gap
//        while (first != null) {
//            first = first.next;
//            second = second.next;
//        }
//        second.next = second.next.next;
//        return dummy.next;
//    }


    /**
     * 给定一个整数数组和一个整数，返回两个数组的索引，这两个索引指向的数字的加和等于指定的整数。需要最优的算法，分析算法的空间和时间复杂度
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {

            return new int[]{0, 0};
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }

        return new int[]{0, 0};
    }
}
