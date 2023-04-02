package com.jef.algorithm.listnode;

import com.jef.entity.ListNode;

/**
 * 链表相关功能
 */
public class NodeListFunction {

    /**
     * 两数相加
     * 给定两个⾮空链表来表示两个⾮负整数。位数按照逆序⽅式存储，它们的每个节点只存储单个数字。将两数相加返回⼀个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 示例：
     * 输⼊：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        // carry 表示进位数
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            // 进位数
            carry = sum / 10;
            // 新节点的数值为sum % 10
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 翻转链表
     * 输⼊⼀个链表，反转链表后，输出链表的所有元素。
     */
    public ListNode reverseList(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            // 保存要反转到头的那个节点
            next = head.next;
            // 要反转的那个节点指向已经反转的上⼀个节点(备注:第⼀次反转的时候会指向null)
            head.next = pre;
            // 上⼀个已经反转到头部的节点
            pre = head;
            // ⼀直向链表尾⾛
            head = next;
        }
        return pre;
    }

    /**
     * 链表中倒数第k个节点
     * 输⼊⼀个链表，输出该链表中倒数第k个结点。
     */
    public static ListNode findKthToTail(ListNode head, int k) {
        // 如果链表为空或者k⼩于等于0
        if (head == null || k <= 0) {
            return null;
        }
        // 声明两个指向头结点的节点
        ListNode node1 = head, node2 = head;
        // 记录节点的个数
        int count = 0;
        // 记录k值，后⾯要使⽤
        int index = k;
        // p指针先跑，并且记录节点数，当node1节点跑了k-1个节点后，node2节点开始跑，
        // 当node1节点跑到最后时，node2节点所指的节点就是倒数第k个节点
        while (node1 != null) {
            node1 = node1.next;
            count++;
            if (k < 1) {
                node2 = node2.next;
            }
            k--;
        }
        // 如果节点个数⼩于所求的倒数第k个节点，则返回空
        if (count < index) {
            return null;
        }
        return node2;
    }

    /**
     * 删除链表的倒数第N个节点
     * 给定⼀个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 给定⼀个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第⼆个节点后，链表变为 1->2->3->5.
     * 两次遍历法
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑结点，哑结点⽤来简化某些极端情况，例如列表中只含有⼀个结点，或需要删除列表的头部
        ListNode dummy = new ListNode(0);
        // 哑结点指向头结点
        dummy.next = head;
        // 保存链表⻓度
        int length = 0;
        ListNode len = head;
        while (len != null) {
            length++;
            len = len.next;
        }
        length = length - n;
        ListNode target = dummy;
        // 找到 L-n 位置的节点
        while (length > 0) {
            target = target.next;
            length--;
        }
        // 把第 (L - n)个结点的 next 指针重新链接⾄第 (L - n + 2)个结点
        target.next = target.next.next;
        return dummy.next;
    }

    /**
     * 删除链表的倒数第N个节点
     * 给定⼀个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 给定⼀个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第⼆个节点后，链表变为 1->2->3->5.
     * 进阶：⼀次遍历法
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 声明两个指向头结点的节点
        ListNode node1 = dummy, node2 = dummy;
        // node1 节点先跑，node1节点 跑到第 n 个节点的时候,node2 节点开始跑
        // 当node1 节点跑到最后⼀个节点时，node2 节点所在的位置就是第 （L-n ） 个节点，也就是倒数第 n+1（L代表总链表⻓度）
        while (node1 != null) {
            node1 = node1.next;
            if (n < 1 && node1 != null) {
                node2 = node2.next;
            }
            n--;
        }
        node2.next = node2.next.next;
        return dummy.next;
    }

    /**
     * 合并两个排序的链表
     * 输⼊两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满⾜单调不减规则。
     * 递归版本
     */
    public static ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }

    /**
     * 非递归版本
     */
    public static ListNode merge2(ListNode list1, ListNode list2) {
        // list1为空，直接返回list2
        if (list1 == null) {
            return list2;
        }
        // list2为空，直接返回list1
        if (list2 == null) {
            return list1;
        }
        ListNode mergeHead = null;
        ListNode current = null;
        // 当list1和list2不为空时
        while (list1 != null && list2 != null) {
            // 取最⼩值作头结点
            if (list1.val <= list2.val) {
                if (mergeHead == null) {
                    mergeHead = current = list1;
                } else {
                    current.next = list1;
                    // current节点保存list1节点的值因为下⼀次还要⽤
                    current = list1;
                }
                // list1指向下⼀个节点
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = current = list2;
                } else {
                    current.next = list2;
                    // current节点保存list2节点的值因为下⼀次还要⽤
                    current = list2;
                }
                // list2指向下⼀个节点
                list2 = list2.next;
            }
        }
        if (list1 == null) {
            current.next = list2;
        } else {
            current.next = list1;
        }
        return mergeHead;
    }
}