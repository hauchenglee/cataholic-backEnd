package com.web.mail.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailBean {
    private int state;
    private String title;
    private String content;
    private String code;
}
