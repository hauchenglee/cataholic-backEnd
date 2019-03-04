package com.web.user.controller;

import com.web.exception.model.RestResult;
import com.web.exception.utils.ResultGenerator;
import com.web.index.utils.CodeUtil;
import com.web.mail.service.MailService;
import com.web.user.model.UserBean;
import com.web.user.service.UserService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
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

    @GetMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getUser(@PathVariable("id") Long userUUID) throws NotFoundException {
        return userService.getUser(userUUID);
    }

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserBean> getUserList() {
        return userService.getUserList();
    }

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public Object saveUser(@Valid UserBean userBean) {
        userBean.setUserMailChecked(0);
        userBean.setUserAuthorization(1);
        String code = CodeUtil.generateUniqueCode();
        mailService.generateMail1(userBean, code);
        mailService.doMailSendUtil(userBean);
        return userService.saveUser(userBean);
    }

    @PatchMapping(value = "/users/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserBean updateUser(@PathVariable("id") Long userUUID, UserBean userBean) {
        return userService.updateUser(userUUID, userBean);
    }

    @DeleteMapping(value = "")
    @ResponseStatus
    public Object deleteUser() {
        return null;
    }


    @GetMapping(value = "/session")
    @ResponseStatus
    public Object getSession() {
        return null;
    }

    @PostMapping(value = "/session")
    @ResponseStatus
    public Object postSession(@NotEmpty(message = "Email cannot empty") String userEmail, @NotEmpty(message = "Password cannot empty") String password, HttpSession session) {
        UserBean userBean = userService.checkLogin(userEmail, password);
        if (userBean != null) {
            session.setAttribute("login", userBean);
            return generator.getSuccessResult("Login Success", userBean);
        }
        return generator.getFailResult("Login Error");
    }

    @PutMapping(value = "/session")
    @ResponseStatus
    public Object putSession() {
        return null;
    }

    @DeleteMapping(value = "/session")
    @ResponseStatus
    public Object deleteSession() {
        return null;
    }
}
