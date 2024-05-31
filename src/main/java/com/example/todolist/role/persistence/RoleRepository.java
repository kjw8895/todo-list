package com.example.todolist.role.persistence;

import com.example.todolist.role.model.Role;
import com.example.todolist.role.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByType(RoleType type);
}
