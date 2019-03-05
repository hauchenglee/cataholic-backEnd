package com.web.session.service;

import javax.servlet.http.HttpSession;

public interface SessionService {
    void deleteSession(HttpSession session);
}
