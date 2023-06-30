package com.jef.designpattern.structure.composite;

/**
 * 组合模式-客户端
 *
 * @author Jef
 * @date 2023/6/30
 */
public class CompositeClient {

    public static void main(String[] args) {
        Directory directoryRoot = new Directory("root");
        Directory directoryUser = new Directory("user");
        File fileOne = new File("test.html", 100);
        File fileTwo = new File("test2.html", 200);
        directoryRoot.add(directoryUser);
        directoryRoot.add(fileOne);
        directoryRoot.add(fileTwo);
        directoryRoot.printList();
    }
}