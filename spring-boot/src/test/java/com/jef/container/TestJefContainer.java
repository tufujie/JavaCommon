package com.jef.container;

import com.jef.entity.User;
import com.jef.service.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * @author tufujie
 * @date 2023/9/20
 */
@ActiveProfiles({"integrated", "dev"})
@SpringBootTest(classes = TestApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class TestJefContainer {

    @Autowired
    IUserService userService;

    @Test
    @DisplayName("测试获取用户信息")
    public void testGetUserList() {
        List<User> userList = userService.getAllUser();
        System.out.println(userList);
    }
}