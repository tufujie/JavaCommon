package com.jef.designpattern.action.interpreter;

/**
 * 用于处理自定义xml取值表达式的接口
 * @author Jef
 * @date 2019/2/26
 */
public abstract class ReadXmlExpression {

    /**
     * 解释表达式
     * @author Jef
     * @date 2019/2/26
     * @param c 上下文
     * @return java.lang.String[] 解析后的值，为了通用，可能是单个值，也可能是多个值，
     * 因此就返回一个数组
     */
    public abstract String[] interpret(Context c);
    /**
     * 解释表达式
     * @author Jef
     * @date 2019/2/26
     * @param c 上下文
     * @return java.lang.String[] 解析后的值，为了通用，可能是单个值，也可能是多个值，
     * 因此就返回一个数组
     */
//    public abstract String[] interpretListV2(Context c);
}