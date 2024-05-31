package com.example.todolist.config;

import com.example.todolist.auth.application.CustomUserDetailsService;
import com.example.todolist.common.utils.JwtProvider;
import com.example.todolist.config.constant.AuthorizationConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AuthorizationConstant.authorization);

        // 헤더에 토큰이 있을 때
        if (authorizationHeader != null && authorizationHeader.startsWith(AuthorizationConstant.bearerTokenPrefix)) {
            String token = authorizationHeader.substring(AuthorizationConstant.authorizationHeaderSubstring);
            // 유효성 검증
            if (jwtProvider.validateToken(token)) {
                Long userId = jwtProvider.getUserId(token);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId.toString());

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}