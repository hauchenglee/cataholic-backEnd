package com.web.session.controller;

import com.web.session.service.SessionService;
import com.web.user.model.UserBean;
import com.web.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api")
@Validated
public class SessionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public SessionController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping(value = "/session")
    @ResponseStatus(HttpStatus.OK)
    public Object getSession() {
        return null;
    }

    @PostMapping(value = "/session")
    @ResponseStatus(HttpStatus.CREATED)
    public void postSession(@NotEmpty(message = "Email cannot empty") String userEmail, @NotEmpty(message = "Password cannot empty") String password, HttpSession session) {
        UserBean userBean = userService.checkLogin(userEmail, password);
        if (userBean == null) {
            throw new NullPointerException();
        }
        session.setAttribute("PostSession", userBean);
    }

    @PutMapping(value = "/session")
    @ResponseStatus(HttpStatus.OK)
    public void putSession() {
    }

    @DeleteMapping(value = "/session/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable("id") HttpSession session) {
        sessionService.deleteSession(session);
    }
}
