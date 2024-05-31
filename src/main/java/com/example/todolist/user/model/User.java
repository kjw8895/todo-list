package com.example.todolist.user.model;

import com.example.todolist.common.exception.CommonException;
import com.example.todolist.common.exception.CommonExceptionCode;
import com.example.todolist.common.model.BaseEntity;
import com.example.todolist.role.model.Role;
import com.example.todolist.role.model.RoleType;
import com.example.todolist.user.application.dto.UserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Entity
@Table(name = "TEST_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NICK_NAME", unique = true)
    private String nickName;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 50)
    private UserStatus status = UserStatus.ACTIVATION;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(UserRole.create(this, role));
    }

    public boolean isActivation() {
        return UserStatus.ACTIVATION.equals(this.status);
    }

    public void withdrawal() {
        if (!isActivation()) {
            throw new CommonException(CommonExceptionCode.INVALID_REQUEST);
        }
        this.status = UserStatus.WITHDRAWAL;
    }

    public List<RoleType> getRoleTypes() {
        return this.roles.stream()
                .map(UserRole::getRole)
                .map(Role::getType)
                .toList();
    }

    public static User create(UserDto.Request dto, String encryptedPassword) {
        User user = new User();
        user.nickName = dto.nickName();
        user.password = encryptedPassword;
        return user;
    }

}
