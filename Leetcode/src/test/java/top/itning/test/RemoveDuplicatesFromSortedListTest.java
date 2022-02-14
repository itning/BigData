package top.itning.test;

import org.junit.jupiter.api.Test;

/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * @author itning
 * @since 2022/2/14 13:09
 */
public class RemoveDuplicatesFromSortedListTest {
    @Test
    void removeDuplicatesFromSortedListTest() {
        ListNode listNode = deleteDuplicates(new ListNode(1, new ListNode(1)));
        System.out.println(listNode);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode first = new ListNode(head.val);
        ListNode listNode = first;
        while (head != null) {
            if (listNode.val != head.val) {
                listNode.next = new ListNode(head.val);
                listNode = listNode.next;
            }
            head = head.next;
        }
        return first;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "val=" + val + ", next=" + next;
        }
    }
}
