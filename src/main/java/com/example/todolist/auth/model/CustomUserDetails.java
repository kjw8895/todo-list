package com.example.todolist.auth.model;

import com.example.todolist.role.model.RoleType;
import com.example.todolist.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final User user;
    // lazy loading exception 방지
    private final List<RoleType> roleTypes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roleTypes.stream()
                .map(type -> new SimpleGrantedAuthority(type.name()))
                .toList();

        return new ArrayList<>(simpleGrantedAuthorities);
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getNickName();
    }
}
