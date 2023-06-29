package com.jef.designpattern.creation.prototype.framework;

import java.util.HashMap;

/**
 * @author Jef
 * @date 2023/6/29
 */
public class Manager {

    private HashMap showcase = new HashMap();

    public void register(String productName, Product product) {
        showcase.put(productName, product);
    }

    public Product create(String productName) {
        Product product = (Product) showcase.get(productName);
        return product.createClone();
    }

}