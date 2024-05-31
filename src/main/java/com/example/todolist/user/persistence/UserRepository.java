package com.example.todolist.user.persistence;

import com.example.todolist.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String nickName);
}
