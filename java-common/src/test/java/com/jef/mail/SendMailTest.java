package com.jef.mail;

import com.jef.util.MailOperationUtil;
import com.jef.util.RandomUtil;
import org.junit.Test;

/**
 * 发送邮件测试
 *
 * @author Jef
 * @date 2020/4/4
 */
public class SendMailTest {

    @Test
    public void testSendMail() {
        String password = "******";
        String host = "smtp.qq.com";
        String from = "240623879@qq.com";
        String to = "2375864266@qq.com";// 收件人
        String subject = "欢迎使用北辰云";
        // 邮箱内容
        StringBuffer sb = new StringBuffer();
        String yzm = RandomUtil.generateDigitalString(6);
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:30px; line-height:50px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>欢迎使用北辰云，您的注册码为：<br/><h2 style='color:green'>" +yzm +"</h2><br/>本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>北辰云网络技术有限公司</div>"
                +"</div>");
        MailOperationUtil operation = new MailOperationUtil();
        try {
            String res = operation.sendMail(from, password, host, from, to, subject, sb.toString());
            System.out.println("发送邮件响应消息=" + res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}