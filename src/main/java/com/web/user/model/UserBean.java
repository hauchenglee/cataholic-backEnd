package com.web.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Data // @Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 自動化注入
@Component
//http://kriszhang.com/lombok/
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 此處使用auto自動新增 -> “违反主键/唯一约束条件”，原因不明
    // DataIntegrityViolationException
    private Integer userUUID;

//    @NotBlank(message = "name empty")
    private String userName;

    @NotBlank(message = "email empty")
//    @Email(message = "email pattern error")
    private String userEmail;

    @NotBlank(message = "password empty")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "password pattern error")
    private String userPassword;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIME)
//    https://stackoverflow.com/questions/25333711/what-is-the-use-of-the-temporal-annotation-in-hibernate
    @CreatedDate
//    https://blog.csdn.net/tianyaleixiaowu/article/details/77931903
    private Date userRegisterTime;
    private String userMailCode;
    private Integer userMailChecked; // 0 for non-activation, 1 for activation
    private String userForgotPasswordCode;
    private String userMailException;
    private Integer userAuthorization; // 1 for can use, 2 for banned, 3 for delete
}
