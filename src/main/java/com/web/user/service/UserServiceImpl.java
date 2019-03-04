package com.web.user.service;

import com.web.user.model.UserBean;
import com.web.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserBean saveUser(UserBean userBean) {
        return userDao.save(userBean);
    }

    @Override
    public UserBean findByUserEmail(String userEmail) {
        return userDao.findByUserEmail(userEmail);
    }

    @Override
    public UserBean checkLogin(String userEmail, String userPassword) {
        return userDao.findByUserEmailAndUserPassword(userEmail, userPassword);
    }

    @Override
    public int updateUserMailException(String exception) {
        return userDao.updateUserMailException(exception);
    }
}
