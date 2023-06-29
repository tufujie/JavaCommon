package com.jef.designpattern.creation.prototype;

import com.jef.designpattern.creation.prototype.framework.Manager;
import com.jef.designpattern.creation.prototype.framework.Product;
import com.jef.designpattern.creation.prototype.framework.ToyOne;
import com.jef.designpattern.creation.prototype.framework.ToyTwo;

/**
 * 原型模式-测试客户端
 *
 * @author Jef
 * @date 2023/6/29
 */
public class ProtoTypeClient {

    public static void main(String[] args) {
        // 准备
        Manager manager = new Manager();
        ToyOne toyOne = new ToyOne("玩具1");
        ToyTwo toyTwo = new ToyTwo("玩具2");
        manager.register(toyOne.getProductName(), toyOne);
        manager.register(toyTwo.getProductName(), toyTwo);
        // 生成
        Product productMessageBox = manager.create(toyOne.getProductName());
        Product productMessageString = manager.create(toyTwo.getProductName());
        // 使用
        productMessageBox.use(toyOne.getProductName());
        productMessageString.use(toyTwo.getProductName());
    }

}