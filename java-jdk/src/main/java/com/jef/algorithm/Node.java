package com.jef.algorithm;

/**
 * 节点
 *
 * @author Jef
 * @date 2021/12/23
 */
public class Node {

    /**
     * 数据
     */
    private int data;

    /**
     * 下一节点
     */
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}