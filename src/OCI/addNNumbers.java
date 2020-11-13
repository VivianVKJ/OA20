package OCI;

import utils.ListNode;

public class addNNumbers {
    public static void main(String[] args) {
        Integer[] list1 = {9, 9, 9, 9};
        Integer[] list2 = {9, 9};
        Integer[] list3 = {2, 1};
        ListNode l1 = ListNode.buildList(list1);
        ListNode l2 = ListNode.buildList(list2);
        ListNode l3 = ListNode.buildList(list3);
        ListNode[] lists = {l1, l2, l3, l3};
        ListNode sum = addNNumbers(lists, 0, lists.length - 1);
        while (sum != null) {
            System.out.print(sum.val);
            sum = sum.next;
        }
    }

    public static ListNode add2Numbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), node = head;
        int ten = 0;
        while (l1 != null || l2 != null || ten != 0) {
            int sum = ten;
            if (l1 != null) sum += l1.val;
            if (l2 != null) sum += l2.val;
            node.next = new ListNode(sum % 10);
            ten = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            node = node.next;
        }
        return head.next;
    }

    //Iterate: add two until last
    //Recursively: divide and
    public static ListNode addNNumbers(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        int mid = (l + r) / 2;
        ListNode l1 = addNNumbers(lists, l, mid);
        ListNode l2 = addNNumbers(lists, mid + 1, r);
        return add2Numbers(l1, l2);
    }
}
