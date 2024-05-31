package com.example.todolist.role.model;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@Entity
@Table(name = "TEST_ROLE")
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 50)
    private RoleType type;
}
