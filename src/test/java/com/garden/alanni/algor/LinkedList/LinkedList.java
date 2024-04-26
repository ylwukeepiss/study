package com.garden.alanni.algor.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 吴宇伦
 */
public class LinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode deleteDuplicatesDifficu(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(-101, head);
        ListNode cur = head, next = cur.next;
        while (cur != null && next != null) {
            if (cur.val == next.val) {
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                pre.next = next;
                cur = next;
                if (cur != null) {
                    next = cur.next;
                }
            } else {
                pre = cur;
                cur = next;
                next = cur.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = new ListNode(head.val, head);
        ListNode first = pre, end = head;
        while (end != null) {
            if (first.val == end.val) {
                first.next = end.next;
                end = end.next;
            } else {
                first = first.next;
                end = end.next;
            }
        }
        return pre;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int result = 0;
        int carry = 0;
        int val = 0;
        ListNode head = new ListNode(-1), cur = head;
        while (l1 != null && l2 != null) {
            result = l1.val + l2.val + carry;
            carry = result / 10;
            if (carry > 0) {
                val = result % 10;
            } else {
                val = result;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode rest = l1;
        if (l1 == null) {
            rest = l2;
        }
       while (rest != null) {
            result = rest.val + carry;
            carry = result / 10;
            if (carry > 0) {
                val = result % 10;
            } else {
                val = result;
            }
            cur.next = new ListNode(val);
            cur = cur.next;
            rest = rest.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return head.next;
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
//        ListNode in = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
//        deleteDuplicates(in);
//        ListNode in = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5))))))));
//        ListNode in = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
//        ListNode head = deleteDuplicatesDifficu(in);
//        System.out.println(head);
//        char c = 'a';
//        System.out.println(c - '0');

//        ListNode head1 = new ListNode(9);
//        ListNode head2 = new ListNode(9);
//        ListNode l1 = head1, l2 = head2;
//        for (int i = 0; i < 6; ++i) {
//            if (i > 2) {
//                l2.next = new ListNode(9);
//                l2 = l2.next;
//            } else {
//                l1.next = new ListNode(9);
//                l2.next = new ListNode(9);
//                l1 = l1.next;
//                l2 = l2.next;
//            }
//        }
//        addTwoNumbers(head1, head2);

        ListNode head = new ListNode(3, new ListNode(2, new ListNode(4)));
        head.next.next.next = head;
        hasCycle(head);
    }

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int len = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = len - 1; i > 0; --i) {
            carry += (i >= a.length() ? 0 : (a.charAt(i) - '0'));
            carry += (i >= b.length() ? 0 : (b.charAt(i) - '0'));
            ans.append(carry);
            carry /= 2;
        }
        return ans.reverse().toString();
    }

//    public static void main(String[] args) {
//        LinkedListNode head = new LinkedListNode(-5);
//        LinkedListNode curr = head;
//        List<Integer> integers = Arrays.asList(2, 0, 1);
//        for (int i = 0; i < integers.size(); ++i) {
//            LinkedListNode node = new LinkedListNode(integers.get(i));
//            curr.next = node;
//            curr = node;
//        }
//        LinkedListNode linkedListNode = deleteNode(head, -5);
////        print(head);
//////        LinkedListNode middleNode = findMiddleNode(head);
//////        LinkedListNode rever = rever(middleNode);
////        boolean palindrome = isPalindrome(head);
////        System.out.println(palindrome);
////        print(head);
//        print(head);
//        addTwoNumbers(new LinkedListNode(5), new LinkedListNode(5));
//    }

    public static LinkedListNode addTwoNumbers(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode p = l1, q = l2;
        LinkedListNode ans = new LinkedListNode(0), r = ans;
        int carry = 0, res = 0, remainder = 0;
        while (p != null && q != null) {
            res = p.getVal() + q.getVal();
            carry = res / 10;
            remainder = res % 10;
            p = p.next;
            q = q.next;

            r.setVal(r.getVal() + remainder);
            r.next = new LinkedListNode(carry);
            r = r.next;
        }
        if (p != null) {
            r.next = p;
        } else {
            r.next = q;
        }
        return ans.next;
    }

    public static LinkedListNode deleteNode(LinkedListNode head, int val) {
        if (head == null) {
            return null;
        }
        LinkedListNode pre = new LinkedListNode(-1), curr = head;
        pre.next = head;
        LinkedListNode p = pre;
        while (curr != null) {
            if (curr.getVal() == val) {
                p.next = curr.next;
                break;
            }
            p = curr;
            curr = curr.next;
        }
        return pre.next;
    }

    /**
     * 判断是否为回文字符串
     * @param str 字符串
     * @return 判断结果
     */
    private static boolean isPalinDromStr(String str) {
        if (str == null || str.length() < 1) {
            return true;
        }
        int length = str.length();
        for (int i = 0, j = length - 1; i < j; ++i, --j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 链表反转
     * @param head 链表头节点
     * @return 反转后的头节点
     */
    private static LinkedListNode rever(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode pre = null;
        LinkedListNode curr = head;
        LinkedListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 判断链表是否为回文链表
     * @param head 链表头节点
     * @return 判断结果
     */
    private static boolean isPalindrome(LinkedListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
//        // 找到中间节点
//        LinkedListNode slow = head, fast = head;
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        // 反转中间节点之后的链表
//        LinkedListNode pre = null, begin = slow, next;
//        while (begin != null) {
//            next = begin.next;
//            begin.next = pre;
//            pre = begin;
//            begin = next;
//        }
        LinkedListNode middleNode = findMiddleNode(head);
        LinkedListNode begin = rever(middleNode);
        // 首尾判断
        while (begin != null) {
            if (head.getVal() != begin.getVal()) {
                return false;
            }
            head = head.next;
            begin = begin.next;
        }
        return true;
    }

    /**
     * 找到链表中间节点
     * @param head 单向链表头节点
     * @return 返回链表中间节点
     */
    private static LinkedListNode findMiddleNode(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode midNode = head, next = head;
        while (next != null && next.next != null) {
            midNode = midNode.next;
            next = next.next.next;
        }
        return midNode;
    }

    /**
     * 打印单向链表节点的值
     * @param head 链表头节点
     */
    private static void print(LinkedListNode head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.println(head.getVal());
            head = head.next;
        }
    }
}
