package com.jef.designpattern.structure.bridge.two;

/**
 * @author Jef
 * @date 2023/6/29
 */
public class BridgeClient {

    public static void main(String[] args) {
        Display display = new Display(new StringDisplayImpl("test1"));
        CountDisplay countDisplay = new CountDisplay(new StringDisplayImpl("test2"));

        display.display();
        countDisplay.multiDisplay(5);
    }

}