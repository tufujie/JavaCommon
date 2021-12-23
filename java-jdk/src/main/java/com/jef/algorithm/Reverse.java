package com.jef.algorithm;

/**
 * 反转顺序
 *
 * @author Jef
 * @date 2021/12/23
 */
public class Reverse {

    /**
     * 用递归的方法反转链表
     */
    public static Node reverseNode(Node head) {
        // 1、递归结束条件
        if (head == null || head.getNext() == null) {
            return head;
        }
        // 递归反转 子链表
        Node newList = reverseNode(head.getNext());
        // 改变1，2节点的指向
        // 获取节点2
        Node next = head.getNext();
        // 节点2的next指向1
        next.setNext(head);
        // 节点1的next指向null
        head.setNext(null);
        // 把调整后的链表返回
        return newList;
    }

}