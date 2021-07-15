package com.jef.xmlfunction;

import com.jef.io.blog.FileGlobal;
import com.jef.util.XmlUtil;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLAnalysisTwo {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Document doc = XmlUtil.getRoot(FileGlobal.XMLDIR);
        Element element = doc.getDocumentElement();

        NodeList children = element.getChildNodes();
        System.out.println("读出所有内容--------");
        for(int i = 0 ; i < children.getLength(); i++) {
            Node childNode = children.item(i);
            System.out.println(i + "=" + childNode.getTextContent());
        }
        System.out.println("读出所有内容--------");
        System.out.println("过滤一层，读出所有element的内容--------");
        for(int i = 0 ; i < children.getLength(); i++) {
            Node node = children.item(i);
            if(node instanceof Element) {
                System.out.println(node.getTextContent());
            }
        }
        System.out.println("过滤一层，读出所有element的内容--------");
        System.out.println("过滤一层，读出所有element的内容2--------");
        String name = "";
        String size = "";
        for(int i = 0 ; i < children.getLength(); i++) {
            Node node = children.item(i);
            if(node instanceof Element) {
                Element childElement = (Element) node;
                Text textNode = (Text) childElement.getFirstChild();
                String text = textNode.getData().trim();
                if(childElement.getTagName().equals("name")) {
                    name = text;
                } else if(childElement.getTagName().equals("size")) {
                    size = text;
                    System.out.println("直接读出元素值");
                    System.out.println("unit=" + childElement.getAttribute("unit"));
                    System.out.println("直接读出元素值");
                }
            }
        }
        System.out.println("name=" + name);
        System.out.println("size=" + size);
        System.out.println("过滤一层，读出所有element的内容2--------");

        System.out.println("过滤一层，读出所有element的内容3--------");
        for(Node childNode = element.getFirstChild(); childNode != null; childNode = element.getNextSibling()) {
        }
        System.out.println("过滤一层，读出所有element的内容3--------");

        System.out.println("过滤一层，读出所有element的内容4--------");
        NamedNodeMap attributes = element.getAttributes();
        for(int i = 0; i < attributes.getLength(); i++) {
            Node childNode = attributes.item(i);
            System.out.println(childNode.getNodeName() + "=" + childNode.getNodeValue());
        }
        System.out.println("过滤一层，读出所有element的内容4--------");

    }
}
