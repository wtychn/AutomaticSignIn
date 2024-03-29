package com.wtychn;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Properties;

public class SendEmail {

    public void send(String title, String message) throws MessagingException, GeneralSecurityException {
        final Map emailUsers = (Map) Config.properties.get("email");
        //创建一个配置文件并保存
        final Properties properties = new Properties();

        properties.setProperty("mail.host", (String) emailUsers.get("host"));

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        final String user = (String) emailUsers.get("user");
        final String password = (String) emailUsers.get("password");
        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect(properties.getProperty("mail.host"), user, password);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(user));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress((String) emailUsers.get("target")));

        //邮件标题
        mimeMessage.setSubject(title);

        //邮件内容
        mimeMessage.setContent(message, "text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();
    }

}
