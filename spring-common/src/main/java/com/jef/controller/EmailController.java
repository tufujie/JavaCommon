package com.jef.controller;

import com.jef.entity.Mail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Jef
 * @date 20180729
 */
@Controller
@RequestMapping(value = "/mail")
public class EmailController {

    /**
     * 写邮件
     * @param mv
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/gotoMail", method = RequestMethod.GET)
    public ModelAndView gotoRegister(ModelAndView mv) {
        mv.setViewName("mail/mailForm");
        return mv;
    }

    /**
     * 发送邮件给一个人
     * @param model
     * @param mail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMailToOnePerson", method = RequestMethod.POST)
    public String sendMailToOnePerson(Model model, Mail mail, ModelAndView mv) {
        String result;
        // 收件人的电子邮件
        String to = mail.getTo();
        // 发件人的电子邮件
        String from = mail.getFrom();
        // 假设你是从本地主机发送电子邮件
        String host = "localhost";
        // 获取系统属性对象
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        // 获取默认的Session对象。
        Session mailSession = Session.getDefaultInstance(properties);
        try{
            // 创建一个默认的MimeMessage对象。
            MimeMessage message = new MimeMessage(mailSession);
            // 设置 From: 头部的header字段
            message.setFrom(new InternetAddress(from));
            // 设置 To: 头部的header字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 设置 Subject: header字段
            message.setSubject(mail.getTitle());
            // 现在设置的实际消息
            message.setText(mail.getContent());
            // 发送消息
            Transport.send(message);
            result = "Sent message successfully....";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "Error: unable to send message....";
        }
        return result;
    }

}
