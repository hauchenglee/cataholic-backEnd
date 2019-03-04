package com.web.mail.utils;

import com.web.mail.model.MailBean;
import com.web.user.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
//JavaMail实现注册邮箱验证案例：https://www.jianshu.com/p/8f8d7a46888f
public class MailSendUtil implements Runnable {
    private UserBean userBean;
    private MailBean mailBean;

    @Autowired
    public MailSendUtil(UserBean userBean, MailBean mailBean) {
        this.userBean = userBean;
        this.mailBean = mailBean;
    }

    @Override
    //多線程
    //什么情况下使用多线程：https://blog.csdn.net/l_blackeagle/article/details/68962569
    //Java thread中对异常的处理策略：https://shmilyaw-hotmail-com.iteye.com/blog/1881302
    //Java多线程——<七>多线程的异常捕捉：https://www.cnblogs.com/brolanda/p/4725138.html
    //Java 多线程为何会出现无法捕获异常的现象?：https://www.zhihu.com/question/67790293
    public void run() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String senderEmail = "java010cataholic@gmail.com";// 发件人电子邮箱
        String senderPassword = "cataholiccataholic";// 发件人电子邮箱
        String hostMail = "smtp.gmail.com"; // 指定发送邮件的主机Gmail

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", hostMail);
        properties.put("mail.smtp.port", "587");

        String userEmail = userBean.getUserEmail();

        try {
            //1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword); // 发件人邮箱账号、授权码
                }
            });

            //2.创建邮件对象
            Message message = new MimeMessage(session);
            //2.1设置发件人
            message.setFrom(new InternetAddress(senderEmail));
            //2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

            String title = mailBean.getTitle();
            String content = mailBean.getContent();

            //2.3设置邮件主题
            message.setSubject(title);
            //2.4设置邮件内容
            message.setContent(content, "text/html;charset=UTF-8");
            //3.发送邮件
            Transport.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
}
