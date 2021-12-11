package com.jef.designpattern.action.interpreter;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 元素作为终结符对应的解释器
 * @author Jef
 * @date 2019/3/1
 */
public class ElementTerminalExpression extends ReadXmlExpression {

    /**
     * 元素的名字
     */
   private String eleName = "";

    public ElementTerminalExpression(String name) {
        this.eleName = name;
    }

/*    public String[] interpretV1(Context c) {
        // 先取出上下文中的当前元素作为父级元素
        Element pEle = c.getPreEle();
        // 查找到当前元素名称对应的xml元素
        Element ele = null;
        if (pEle == null) {
            // 说明现在获取的是根元素
            ele = c.getDocument().getDocumentElement();
        } else {
            // 根据父级元素和要查找的元素的名称来获取当前的元素
            ele = c.getNowEle(pEle, eleName);
            // 把当前获取的元素放到上下文中
            c.setPreEle(ele);
        }
        // 然后需要去获取这个元素的值
        String[] ss = new String[1];
        ss[0] = ele.getFirstChild().getNodeValue();
        return ss;
    }

    public String[] interpretList(Context c) {
        // 先取出上下文中的当前元素作为父级元素
        List<Element> pEles = c.getPreEles();
        // 查找到当前元素名称对应的xml元素
        Element ele = null;
        if (pEles.size() == 0) {
            // 说明现在获取的是根元素
            ele = c.getDocument().getDocumentElement();
        } else {
            // 获取当前的元素
            ele = c.getNowEles(pEles.get(0), eleName).get(0);
        }
        // 然后需要去获取这个元素的值
        String[] ss = new String[1];
        ss[0] = ele.getFirstChild().getNodeValue();
        return ss;
    }*/

    @Override
    public String[] interpret(Context c) {
        // 先取出上下文中的当前元素作为父级元素
        List<Element> pEles = c.getPreEles();
        // 获取当前的多个元素
        List<Element> nowEles = new ArrayList<Element>();
        for (Element ele : pEles) {
            nowEles.addAll(c.getNowEles(ele, eleName));
        }
        // 然后来获取这些元素的值
        String[] ss = new String[nowEles.size()];
        for (int i = 0; i < ss.length; i++) {
            ss[i] = nowEles.get(i).getFirstChild().getNodeValue();
        }
        return ss;
    }
}