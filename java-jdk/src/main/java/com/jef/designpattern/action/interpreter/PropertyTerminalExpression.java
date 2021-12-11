package com.jef.designpattern.action.interpreter;

import org.w3c.dom.Element;

import java.util.List;

/**
 * 属性作为终结符对应的解释器
 * @author Jef
 * @date 2019/3/1
 */
public class PropertyTerminalExpression extends ReadXmlExpression {

    /**
     * 属性的名字
     */
    private String propName;

    public PropertyTerminalExpression(String propName) {
        this.propName = propName;
    }

   /* @Override
    public String[] interpret(Context c) {
        // 直接获取最后的元素属性的值
        String[] ss = new String[1];
        ss[0] = c.getPreEle().getAttribute(this.propName);
        return ss;
    }*/

    @Override
    public String[] interpret(Context c) {
        // 获取最后的多个元素
        List<Element> eles = c.getPreEles();
        String[] ss = new String[eles.size()];
        // 循环多个元素，获取每个元素属性的值
        for (int i = 0; i < ss.length; i++) {
            ss[i] = eles.get(i).getAttribute(this.propName);
        }
        return ss;
    }

}