package utils;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode buildList(Integer[] arr) {
        ListNode head = new ListNode(0), node = head;

        for (int i : arr) {
            node.next = new ListNode(i);
            node = node.next;
        }
        return head.next;
    }
}
