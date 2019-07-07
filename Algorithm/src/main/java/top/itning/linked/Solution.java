package top.itning.linked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 *
 * @author itning
 * @date 2019/7/7 17:37
 */
public class Solution {
    public static void main(String[] args) {
        //67,0,24,58
        ListNode listNode1 = new ListNode(67);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(24);
        ListNode listNode4 = new ListNode(58);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ArrayList<Integer> arrayList = new Solution().printListFromTailToHead(listNode1);
        System.out.println(arrayList);
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>(0);
        }
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode next = listNode;
        do {
            deque.push(next.val);
            next = next.next;
        } while (next != null);
        ArrayList<Integer> arrayList = new ArrayList<>(deque.size());
        Integer last = deque.poll();
        while (last != null) {
            arrayList.add(last);
            last = deque.poll();
        }
        return arrayList;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
