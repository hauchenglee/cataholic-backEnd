package com.web.mail.controller;

import com.web.exception.model.RestResult;
import com.web.exception.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mail")
public class MailController {

    private final ResultGenerator generator;

    @Autowired
    public MailController(ResultGenerator generator) {
        this.generator = generator;
    }

    @RequestMapping()
    public RestResult receiveMail() {
        return generator.getSuccessResult("");
    }
}
