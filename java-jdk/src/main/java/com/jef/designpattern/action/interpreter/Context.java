package com.jef.designpattern.action.interpreter;

import com.jef.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;


/**
 * 上下文，用来包含解释器需要的一些全局信息
 *
 * @author Jef
 * @date 2019/2/26
 */
public class Context {
    /**
     * 上一个被处理的元素
     */
    private Element preEle = null;
    /**
     * Dom解析xml的Document对象
     */
    private Document document = null;
    /**
     * 上一次被处理的多个元素
     */
    private List<Element> preEles = new ArrayList<Element>();

    /**
     * 构造方法
     * @param filePathName 需要读取的xml的路径和名字
     * @throws Exception
     */
    public Context(String filePathName) throws Exception {
        // 通过辅助的xml工具类来获取被解析的xml对应的Document对象
        this.document = XmlUtil.getRoot(filePathName);
    }

    /**
     * 重新初始化上下文
     */
    public void reInitV1() {
        preEle = null;
    }

    /**
     * 重新初始化上下文
     */
    public void reInit() {
        preEles = new ArrayList<Element>();
    }

    /**
     * 各个Expression公共使用的方法
     * @author Jef
     * @date 2019/3/5
     * @param pEle 父元素
     * @param eleName 当前元素的名称
     * @return
     */
    public Element getNowEle(Element pEle, String eleName) {
        NodeList tempNodeList = pEle.getChildNodes();
        for (int i = 0; i < tempNodeList.getLength(); i++) {
            if (tempNodeList.item(i) instanceof Element) {
                Element nowEle = (Element) tempNodeList.item(i);
                if (nowEle.getTagName().equals(eleName)) {
                    return nowEle;
                }
            }
        }
        return null;
    }

    /**
     * 各个Expression公共使用的方法
     * 根据父元素和当前元素的名称来获取当前的多个元素的集合
     * @author Jef
     * @date 2019/3/5
     * @param pEle 父元素
     * @param eleName 当前元素的名称
     * @return 当前的多个元素的集合
     */
    public List<Element> getNowEles(Element pEle, String eleName) {
        List<Element> elements = new ArrayList<Element>();
        NodeList tempNodeList = pEle.getChildNodes();
        for (int i = 0; i < tempNodeList.getLength(); i++) {
            if (tempNodeList.item(i) instanceof Element) {
                Element nowEle = (Element) tempNodeList.item(i);
                if (nowEle.getTagName().equals(eleName)) {
                    elements.add(nowEle);
                }
            }
        }
        return elements;
    }


    public Element getPreEle() {
        return preEle;
    }

    public void setPreEle(Element preEle) {
        this.preEle = preEle;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Element> getPreEles() {
        return preEles;
    }

    public void setPreEles(List<Element> nowEles) {
        this.preEles = nowEles;
    }
}