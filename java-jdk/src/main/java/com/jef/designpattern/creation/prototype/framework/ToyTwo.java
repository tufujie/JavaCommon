package com.jef.designpattern.creation.prototype.framework;

/**
 * 玩具2号
 *
 * @author Jef
 * @date 2023/6/29
 */
public class ToyTwo implements Product {

    private String productName;

    public ToyTwo(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public Product createClone() {
        Product product = null;
        try {
            product = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void use(String productName) {
        System.out.println(productName + " from ToyTwo used");
    }
}