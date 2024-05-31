package com.example.todolist.common.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@UtilityClass
public class AuthUtils {
    public static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    // nickName 이 unique 한 값이라고 가정
    public boolean matches(String nickName) {
        return nickName.equals(getAuth().getName());
    }
}
