package com.web.user.service;

import com.web.user.model.UserBean;

public interface UserService {

    /**
     * register user
     * @param userBean
     * @return msg if successful or null
     */
    UserBean saveUser(UserBean userBean);

    UserBean findByUserEmail(String userId);

    /**
     * check id and password when login
     * @param userId
     * @param userPassword
     * @return msg if successful or null
     */
    UserBean checkLogin(String userId, String userPassword);

    /**
     * update sending mail result
     * @param exception
     * @return Invalid Addresses or null
     */
    int updateUserMailException(String exception);
}
