package com.web.session.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public void deleteSession(HttpSession session) {
        session.invalidate();
    }
}
