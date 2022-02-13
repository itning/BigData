package top.itning.test;

import org.junit.jupiter.api.Test;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author itning
 * @since 2022/2/13 17:02
 */
public class MergeTwoSortedListsTest {
    @Test
    void mergeTwoSortedListsTest() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode first = null;
        ListNode listNode = null;
        for (; ; ) {
            if (null != list1 && null != list2) {
                if (list1.val <= list2.val) {
                    if (null == listNode) {
                        listNode = new ListNode(list1.val);
                        first = listNode;
                    } else {
                        listNode.next = new ListNode(list1.val);
                        listNode = listNode.next;
                    }
                    list1 = list1.next;
                } else {
                    if (null == listNode) {
                        listNode = new ListNode(list2.val);
                        first = listNode;
                    } else {
                        listNode.next = new ListNode(list2.val);
                        listNode = listNode.next;
                    }
                    list2 = list2.next;
                }
            } else if (null != list1) {
                if (null == listNode) {
                    listNode = new ListNode(list1.val);
                    first = listNode;
                } else {
                    listNode.next = new ListNode(list1.val);
                    listNode = listNode.next;
                }
                list1 = list1.next;
            } else if (null != list2) {
                if (null == listNode) {
                    listNode = new ListNode(list2.val);
                    first = listNode;
                } else {
                    listNode.next = new ListNode(list2.val);
                    listNode = listNode.next;
                }
                list2 = list2.next;
            } else {
                break;
            }
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
    }
}
