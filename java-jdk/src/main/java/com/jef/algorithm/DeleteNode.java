package com.jef.algorithm;

/**
 * 删除中间节点
 *
 * @author Jef
 * @date 2021/5/30
 */
public class DeleteNode {

    public static void main(String[] args) {
        new DeleteNode().deleteNode(null);
    }


    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param node
     * @return int
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

}