package com.example.todolist.user.model;

import com.example.todolist.role.model.Role;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;

    public UserRoleId(User user, Role role) {
        this.userId = user.getId();
        this.roleId = role.getId();
    }
}
