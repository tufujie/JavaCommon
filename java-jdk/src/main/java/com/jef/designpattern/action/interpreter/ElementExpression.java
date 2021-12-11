package com.jef.designpattern.action.interpreter;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 元素作为非终结符对应的解释器，解释并执行中间元素
 * @author Jef
 * @date 2019/2/26
 */
public class ElementExpression extends ReadXmlExpression {
    /**
     * 用来记录组合的ReadXmlExpression元素
     */
    private Collection<ReadXmlExpression> eles = new ArrayList<ReadXmlExpression>();

    /**
     * 元素的名称
     */
    private String eleName = "";

    public ElementExpression(String eleName) {
        this.eleName = eleName;
    }

    public boolean addEle(ReadXmlExpression ele) {
        this.eles.add(ele);
        return true;
    }

    public boolean removeEle(ReadXmlExpression ele) {
        this.eles.remove(ele);
        return true;
    }

/*    public String[] interpretV1(Context c) {
        // 先取出上下文中的当前元素作为父级元素
        // 查找到当前元素名称对应的xml元素，并设置到上下文中
        Element pEle = c.getPreEle();
        if (pEle == null) {
            // 说明现在获取的是根元素
            c.setPreEle(c.getDocument().getDocumentElement());
        } else {
            // 根据父级元素和要查找的元素的名称来获取当前的元素
            Element nowEle = c.getNowEle(pEle, eleName);
            // 把当前获取的元素放到上下文中
            c.setPreEle(nowEle);
        }
        // 循环调用子元素的interpret方法
        String[] ss = null;
        for (ReadXmlExpression ele : eles) {
            ss = ele.interpret(c);
        }
        return ss;
    }*/

    @Override
    public String[] interpret(Context c) {
        // 先取出上下文中的父级元素
        List<Element> pEles = c.getPreEles();
        Element ele = null;
        // 把当前获取的元素放到上下文中
        List<Element> nowEles = new ArrayList<Element>();
        if (pEles.size() == 0) {
            // 说明现在获取的是根元素
            ele = c.getDocument().getDocumentElement();
            pEles.add(ele);
            c.setPreEles(pEles);
        } else {
           for (Element tempEle : pEles) {
                nowEles.addAll(c.getNowEles(tempEle, eleName));
                if (nowEles.size() > 0) {
                    // 找到一个就停止
                    break;
                }
           }
            List<Element> tempList = new ArrayList<Element>();
            tempList.add(nowEles.get(0));
            c.setPreEles(tempList);
        }
        // 循环调用子元素的interpret方法
        String[] ss = null;
        for (ReadXmlExpression tempEle : eles) {
            ss = tempEle.interpret(c);
        }
        return ss;
    }

    /*@Override
    public String[] interpret(Context c) {
        // 先取出上下文中的父级元素
        List<Element> pEles = c.getPreEles();
        // 把当前获取的元素放到上下文中，这次是获取多个元素
        List<Element> nowEles = new ArrayList<Element>();
        for (Element tempEle : pEles) {
            nowEles.addAll(c.getNowEles(tempEle, eleName));
        }
        c.setPreEles(nowEles);
        // 循环调用子元素的interpret方法
        String[] ss = null;
        for (ReadXmlExpression ele : eles) {
            ss = ele.interpret(c);
        }
        return ss;
    }*/

}