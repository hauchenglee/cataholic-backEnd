package com.web.user.repository;

import com.web.user.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserBean, Long> {

    UserBean findByUserUUID(Long userUUID);

    UserBean findByUserEmailAndUserPassword(String userEmail, String userPassword);
}
