package com.web.user.service;

import com.web.user.model.UserBean;

import java.util.List;

public interface UserService {

    UserBean saveUser(UserBean userBean);

    UserBean getUser(Long userUUID);

    List<UserBean> getUserList();

    void deleteUser(Long userUUID);

    UserBean updateUser(Long userUUID, UserBean userBean);

    UserBean checkLogin(String userId, String userPassword);
}
