package com.web.user.service;

import com.web.user.model.UserBean;
import com.web.user.repository.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.web.index.utils.JpaUtil.getNullPropertyNames;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserBean saveUser(UserBean userBean) {
        return userDao.save(userBean);
    }

    @Override
    public Optional<UserBean> getUser(Long userUUID) {
        return userDao.findById(userUUID);
    }

    @Override
    public List<UserBean> getUserList() {
        return userDao.findAll();
    }

    @Override
    public void deleteUser(UserBean userBean) {
        userDao.delete(userBean);
    }

    @Override
    public UserBean updateUser(Long userUUID, UserBean userBean) {
        return userDao.updateUser();
    }

    @Override
    public UserBean checkLogin(String userEmail, String userPassword) {
        return userDao.findByUserEmailAndUserPassword(userEmail, userPassword);
    }
}
