package com.jef.algorithm;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 反转测试
 *
 * @author Jef
 * @date 2021/12/23
 */
public class ReverseTest {

    @Test
    public void testReverse() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node3.setNext(node4);
        node2.setNext(node3);
        node1.setNext(node2);
        // 输出一开始node
        Node tempNode = node1;
        while (tempNode != null) {
            PrintUtil.printf("before data:%s", tempNode.getData());
            tempNode = tempNode.getNext();
        }
        Node reverseNode = Reverse.reverseNode(node1);
        PrintUtil.printSplitLine();
        // 输出最终node
        while (reverseNode != null) {
            PrintUtil.printf("after data:%s", reverseNode.getData());
            reverseNode = reverseNode.getNext();
        }
    }

}