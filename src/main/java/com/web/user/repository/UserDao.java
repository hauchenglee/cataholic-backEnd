package com.web.user.repository;

import com.web.user.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<UserBean, String> {

    UserBean findByUserEmail(String userEmail);

    UserBean findByUserEmailAndUserPassword(String userEmail, String userPassword);

    @Modifying(clearAutomatically = true)
    @Query(value = "update UserBean u set u.userMailException=?1")
    int updateUserMailException(String exception);
}
