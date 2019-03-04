package com.web.mail.service;

import com.web.index.security.Base64Security;
import com.web.mail.model.MailBean;
import com.web.mail.utils.MailSendUtil;
import com.web.user.model.UserBean;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private MailBean mailBean;
    private String title;
    private String content;
    private String requestURI = "http://localhost:8080/mail/" + "?";

    // 註冊激活郵件
    @Override
    public void generateMail1(UserBean userBean, String code) {
        String userEmail = userBean.getUserEmail();
        userEmail = Base64Security.valueEncoder(userEmail);

        // 樣板化郵件內容
        String queryString = "state=1&id=" + userEmail + "&code=" + code;
        String requestURL = requestURI + queryString;

        // 郵件標題與內容
        title = "激活郵件";
        content = "<html><head></head><body><h2>請點擊連接以進行下一步操作</h2><h2><a href='" + requestURL + "'>點我</href></h2></body></html>";

        // 存入mailBean裡
        mailBean = new MailBean(1, title, content, code);
    }

    // 忘記密碼郵件
    @Override
    public void generateMail2(UserBean userBean, String code) {
        String userEmail = userBean.getUserEmail();
        userEmail = Base64Security.valueEncoder(userEmail);

        // 樣板化郵件內容
        String queryString = "state=2&id=" + userEmail + "&code=" + code;
        String requestURL = requestURI + queryString;
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

        // 郵件標題與內容
        title = "忘記密碼認證信";
        content = "<html><head></head><body><h2>親愛的用戶，您在：" + timestamp.toString() + " 時提交了修改密碼的請求，若這是您本人提出，請點擊連接以進行下一步操作</h2><h2><a href='" + requestURL + "'>點我</href></h2></body></html>";

        // 存入mailBean裡
        mailBean = new MailBean(2, title, content, code);
    }

    // 修改密碼通知郵件
    @Override
    public void generateMail3(UserBean userBean, String code) {
    }

    @Override
    public void doMailSendUtil(UserBean userBean) {
        new Thread(new MailSendUtil(userBean, mailBean)).start();
    }
}
