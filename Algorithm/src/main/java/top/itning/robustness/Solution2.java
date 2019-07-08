package top.itning.robustness;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 *
 * @author itning
 * @date 2019/7/8 8:32
 */
public class Solution2 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(67);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(24);
        ListNode listNode4 = new ListNode(58);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode listNode = new Solution2().ReverseList(listNode1);
        System.out.println(listNode.val);
    }

    public ListNode ReverseList(ListNode head) {
        ListNode newHead = null;
        ListNode next;
        // 1>2>3>4 1.next 2 next 3 next 4
        // 1<2<3<4 4 next 3 next 2 next 1
        while (head != null) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
