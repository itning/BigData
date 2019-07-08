package top.itning.robustness;

import java.util.LinkedList;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * @author itning
 * @date 2019/7/7 22:07
 */
public class Solution {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(67);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(24);
        ListNode listNode4 = new ListNode(58);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode listNode = new Solution().FindKthToTail(listNode1, 4);
        System.out.println(listNode.val);
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        if (k < 1) {
            return null;
        }
        if (head == null) {
            return null;
        }
        LinkedList<ListNode> linkedList = new LinkedList<>();
        ListNode next = head;
        do {
            linkedList.add(next);
            next = next.next;
        } while (next != null);
        if (k > linkedList.size()) {
            return null;
        }
        return linkedList.get(linkedList.size() - k);
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
