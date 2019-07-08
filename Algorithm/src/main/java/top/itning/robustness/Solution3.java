package top.itning.robustness;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，
 * 当然我们需要合成后的链表满足单调不减规则。
 *
 * @author itning
 * @date 2019/7/8 13:07
 */
public class Solution3 {
    public static void main(String[] args) {


    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode listNode;
        if (list1.val <= list2.val) {
            listNode = list1;
            listNode.next = Merge(list1.next, list2);
        } else {
            listNode = list2;
            listNode.next = Merge(list1, list2.next);
        }
        return listNode;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
