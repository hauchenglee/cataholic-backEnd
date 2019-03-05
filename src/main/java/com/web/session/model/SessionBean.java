package com.web.session.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;

@Data // @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class SessionBean {
    HttpSession session;
}
