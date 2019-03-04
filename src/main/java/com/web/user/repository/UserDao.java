package com.web.user.repository;

import com.web.user.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserBean, Long> {

    UserBean findByUserEmailAndUserPassword(String userEmail, String userPassword);

    @Modifying
    @Query
    UserBean updateUser();
}
