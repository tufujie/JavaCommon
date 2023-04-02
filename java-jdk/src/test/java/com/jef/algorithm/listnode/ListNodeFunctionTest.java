package com.jef.algorithm.listnode;

import com.jef.entity.ListNode;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/2
 */
public class ListNodeFunctionTest {

    @Test
    public void testAddTwoNumbers() {
        ListNode listNodeOne = new ListNode(2);
        ListNode listNodeOne2 = new ListNode(4);
        ListNode listNodeOne3 = new ListNode(3);
        listNodeOne.next = listNodeOne2;
        listNodeOne2.next = listNodeOne3;

        ListNode listNodeTwo = new ListNode(5);
        ListNode listNodeTwo2 = new ListNode(6);
        ListNode listNodeTwo3 = new ListNode(4);
        listNodeTwo.next = listNodeTwo2;
        listNodeTwo2.next = listNodeTwo3;

        ListNode listNodeResult = NodeListFunction.addTwoNumbers(listNodeOne, listNodeTwo);
        ListNode.listEachNode(listNodeResult);
    }

    @Test
    public void testReverseList() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().reverseList(a);
        ListNode.listEachNode(listNode);
    }

    @Test
    public void testFindKthToTail() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().findKthToTail(a, 3);
        System.out.println(listNode.val);
    }

    @Test
    public void testRemoveKthToTail() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().removeNthFromEnd(a, 3);
        System.out.println(listNode.val);
    }

    @Test
    public void testRemoveKthToTail2() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().removeNthFromEnd2(a, 3);
        System.out.println(listNode.val);
    }

    @Test
    public void testMerge() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode b = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().merge(a, b);
        ListNode.listEachNode(listNode);
    }

    @Test
    public void testMerge2() {
        ListNode a = ListNode.getBasicTestListNode();
        ListNode b = ListNode.getBasicTestListNode();
        ListNode listNode = new NodeListFunction().merge2(a, b);
        ListNode.listEachNode(listNode);
    }
}