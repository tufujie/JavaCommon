package com.jef.xmlfunction;

import com.jef.util.HttpClientUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 2017-06-29.
 * 具体xml格式参见Xml.xml
 */
public class XmlType {
    public static String accessToken = "accessToken";
    public static void main(String[] args) {
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding(HttpClientUtil.CHARSET_UTF);
        Element orders =  document.addElement("orders");
        orders.addElement( "access_token" ).setText(accessToken);
        orders.addElement("mark");
        orders.addElement("bid").setText("0");
        for(String extraOrderId : extraOrderIds) {
            Element order = orders.addElement("order");
            order.addElement("extraOderId", extraOrderId);
        }
        System.out.println(document.asXML());

    }

    public static final List<String> extraOrderIds = new ArrayList<String>() {
        {
            add("12345");
            add("45678");
        }
    };

}
