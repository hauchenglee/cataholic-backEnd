package com.web.user.controller;

import com.web.exception.model.RestResult;
import com.web.index.utils.CodeUtil;
import com.web.exception.utils.ResultGenerator;
import com.web.mail.service.MailService;
import com.web.user.model.UserBean;
import com.web.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    // http://localhost:8080/user/register?userEmail=myadmin&userPassword=123456
    // http://localhost:8080/user/login?userEmail=myadmin&userPassword=123456
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final ResultGenerator generator;
    private final MailService mailService;

    @Autowired
    public UserController(UserService userService, ResultGenerator generator, MailService mailService) {
        this.userService = userService;
        this.generator = generator;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/register")
    //RequestMapping動態賦值：
    //https://blog.csdn.net/j080624/article/details/55193269
    //https://blog.csdn.net/ff906317011/article/details/78552426
    public RestResult register(@Valid UserBean userBean) {
        userBean.setUserMailChecked(0);
        userBean.setUserAuthorization(1);
        String code = CodeUtil.generateUniqueCode(); // 產生激活碼邮件
        mailService.generateMail1(userBean, code);
        mailService.doMailSendUtil(userBean);
        return generator.getSuccessResult("Register Success", userService.saveUser(userBean));
    }

    @PostMapping(value = "/login")
    public RestResult login(@NotEmpty(message = "Email cannot empty") String userEmail, @NotEmpty(message = "Password cannot empty") String password, HttpSession session) {
        UserBean userBean = userService.checkLogin(userEmail, password);
        if (userBean != null) {
            session.setAttribute("login", userBean);
            return generator.getSuccessResult("Login Success", userBean);
        }
        return generator.getFailResult("Login Error");
    }
}
