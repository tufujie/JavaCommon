package com.jef.xmlfunction;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class  XMLAnalysis {
    public static void main(String[] args) throws IOException, DocumentException, SAXException, ParserConfigurationException {
        parseOne();
    }

    private static void parseOne() throws UnsupportedEncodingException, DocumentException {
        String localReturn = "<Response service=\"OrderService\"><Head>ERR</Head><ERROR code=\"9000\">身份验证失败</ERROR></Response>";
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new ByteArrayInputStream(localReturn.getBytes("UTF-8")));
        documentParse(doc);
    }



    private static void documentParse(Document document) {
        Element retRoot = document.getRootElement();
        String head = retRoot.element("Head").getTextTrim();
        String code = retRoot.element("ERROR").attribute("code").getValue();
        String code2 = retRoot.element("ERROR").attributeValue("code");
        String errorMessage = retRoot.element("ERROR").getTextTrim();
        System.out.println("successFlag=" + head);
        System.out.println("code=" + code);
        System.out.println("code2=" + code2);
        System.out.println("errorMessage=" + errorMessage);
    }
}
