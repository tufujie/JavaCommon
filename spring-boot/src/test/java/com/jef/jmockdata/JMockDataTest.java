package com.jef.jmockdata;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.TypeReference;
import com.github.jsonzou.jmockdata.mocker.IntegerMocker;
import com.jef.dao.IUserDao;
import com.jef.entity.User;
import com.jef.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.mockito.Mockito.when;

import java.util.List;

/**
 * @author tufujie
 * @date 2023/7/21
 */
@DisplayName("用户测试")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JMockDataTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserDao userDao;



    @Test
    @DisplayName("根据id查询用户: 查询到用户")
    void testQueryUser() {
        MockConfig mockConfig = new MockConfig();
        final DataConfig statusDC = mockConfig.subConfig("admin");
        mockConfig.registerMocker(new IntegerMocker() {
            @Override
            public Integer mock(DataConfig mockConfig) {
                // 修改admin字段为固定值
                if (statusDC == mockConfig) {
                    return 0;
                }
                return super.mock(mockConfig);
            }
        }, Integer.class);
        // 排除类中特定字段不生成测试数据
        mockConfig.excludes("id");
        User user = JMockData.mock(User.class, mockConfig);
        Assertions.assertTrue(user.getAdmin() == 0, "单用户测试数据：是否管理员异常");
        // 把user对象的值赋给userDao.getByUser(Mockito.any())，后面所有userDao.getByUser(Mockito.any())的值都是user对象
        when(userDao.getByUser(Mockito.any())).thenReturn(user);
        User userSecond = userService.getByUser(Mockito.any());
        Assertions.assertEquals(user, userSecond);
        // 生成集合数据
        List<User> userList = JMockData.mock(new TypeReference<List<User>>(){}, mockConfig);
        Assertions.assertNotNull(userList.get(0).getAdmin(), "列表用户测试数据：是否管理员异常");
    }
}
