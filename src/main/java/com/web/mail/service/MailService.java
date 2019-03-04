package com.web.mail.service;

import com.web.user.model.UserBean;

public interface MailService {
    public void generateMail1(UserBean userBean, String code);

    public void generateMail2(UserBean userBean, String code);

    public void generateMail3(UserBean userBean, String code);

    public void doMailSendUtil(UserBean userBean);
}
