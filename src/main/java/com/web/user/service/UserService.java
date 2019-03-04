package com.web.user.service;

import com.web.user.model.UserBean;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserBean saveUser(UserBean userBean);

    Optional<UserBean> getUser(Long userUUID);

    List<UserBean> getUserList();

    void deleteUser(UserBean userBean);

    UserBean updateUser(Long userUUID, UserBean userBean);

    UserBean checkLogin(String userId, String userPassword);
}
