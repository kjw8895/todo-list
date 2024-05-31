package com.example.todolist.user;

import com.example.todolist.user.application.UserService;
import com.example.todolist.user.application.dto.UserDto;
import com.example.todolist.user.model.User;
import com.example.todolist.user.model.UserStatus;
import com.example.todolist.user.persistence.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    private Long userId;
    private UserDto.Request request;

    @BeforeEach
    public void setUp() {
        // given
        request = new UserDto.Request("nickName", "password");

        // when
        UserDto.Response response = service.create(request);
        userId = response.id();
    }

    /**
     * 회원 가입
     */
    @Test
    public void createUserTest() throws Exception {
        // then
        User findUser = repository.findById(userId).get();
        Assertions.assertEquals(request.nickName(), findUser.getNickName());
    }

    @Test
    @WithMockUser(username= "nickName", password = "password")
    public void withdrawal() throws Exception {
        // when
        service.delete(userId);

        // then
        User findUser = repository.findById(userId).get();
        Assertions.assertEquals(findUser.getStatus(), UserStatus.WITHDRAWAL);
    }
}
