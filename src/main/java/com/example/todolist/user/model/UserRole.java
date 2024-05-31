package com.example.todolist.user.model;

import com.example.todolist.role.model.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Entity
@Table(name = "TEST_USER_ROLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    public UserRole(User user, Role role) {
        this.id = new UserRoleId(user, role);
        this.user = user;
        this.role = role;
    }

    public static UserRole create(User user, Role role) {
        return new UserRole(user, role);
    }
}
