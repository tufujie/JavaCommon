package com.jef.demo;

import com.jef.demo.dao.IUserDao;
import com.jef.demo.entity.User;
import com.jef.demo.impl.UserServiceImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * mockito测试
 *
 * @author tufujie
 * @date 2023/7/21
 */
@DisplayName("mockito测试")
// @ExtendWith注解，使用MockitoExtension，会识别@InjectMocks和@Mock等注解
@ExtendWith({MockitoExtension.class})
// @MockitoSetting注解，设置为Strictness.LENIENT，忽略没被使用的mock方法，避免重复代码
@MockitoSettings(strictness = Strictness.LENIENT)
public class MockitoTest {

    private static final String MOCKITO = "mockito";

    // @Mock注解的对象会被加入到context中，自动组装给@InjectMocks注解的对象
    @Mock
    private IUserDao userDao;

    // @InjectMocks注解的对象的代码会被真实调用，也是需要测试的代码
    @InjectMocks
    private UserServiceImpl userService;

    private MockWebServer mockWebServer = new MockWebServer();

    @Test
    @DisplayName("mockito api 测试")
    void testMockitoApi() {
//        用来创建一个虚假的对象，如User。这个对象的类型就是User，可以传递给别的方法
        User user = mock(User.class);
        Assertions.assertNotNull(user.getAdmin(), MOCKITO + "mock对象异常");
        ArrayList mockedArrayList = mock(ArrayList.class);
        Assertions.assertTrue(mockedArrayList instanceof List, MOCKITO + "mock List对象异常");

//        可以自定义触发某个方法时，返回特定的内容。
        // 把user对象的值赋给userDao.getByUser(Mockito.any())，后面所有userDao.getByUser(Mockito.any())的值都是user对象
        when(userDao.getByUser(Mockito.any())).thenReturn(user);
        User userSecond = userDao.getByUser(Mockito.any());
        Assertions.assertEquals(user, userSecond);

        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userDao.getUserList(Mockito.any())).thenReturn(userList);
        List<User> userListSecond = userDao.getUserList(Mockito.any());
        Assertions.assertEquals(userList, userListSecond);
//        对于有参数的方法，可以设定不论传入什么参数，都return特定值。anyInt()等同理。
        Map<String, String> map = mock(Map.class);
        String testValue = "testValue";
        Mockito.when(map.get(anyString())).thenReturn(testValue);
        String testKey = "testKey";
        String testKeyValue = map.get(testKey);
        Map<String, String> allMap = mock(Map.class);
        Assertions.assertEquals(testValue, testKeyValue, MOCKITO + "when thenReturn异常");
//        有时候，我们设置了anyString()之后，想使用实际传入的参数，就需要thenAnswer()。getArguments即为传入的实际参数。
        Mockito.when(map.get(anyString())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            String str = (String) args[0];
            return allMap.get(str);
        });
        String actualTestValue = map.get(testKey);
        Assertions.assertNull(actualTestValue, MOCKITO + "when thenAnswer异常");
    }

    /**
     * Mockito 会追踪 Mock 对象的所用方法调用和调用方法时所传递的参数. 我们可以通过 verify() 静态方法来来校验指定的方法调用是否满足断言
     */
    @Test
    @DisplayName("verify api 测试")
    public void testVerify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        when(mockedList.size()).thenReturn(5);
        Assertions.assertEquals(mockedList.size(), 5);
/*        第一句校验 mockedList.add("one") 至少被调用了 1 次(atLeastOnce)
        第二句校验 mockedList.add("two") 被调用了 1 次(times(1))
        第三句校验 mockedList.add("three times") 被调用了 3 次(times(3))
        第四句校验 mockedList.isEmpty() 从未被调用(never)*/
        verify(mockedList, atLeastOnce()).add("one");
        verify(mockedList, times(1)).add("two");
        verify(mockedList, times(3)).add("three times");
        verify(mockedList, never()).isEmpty();
        // 断言方法被调用过
        User userSecond = userDao.getByUser(new User());
        verify(userDao).getByUser(Mockito.any());
    }


    /**
     * 使用 spy() 部分模拟对象
     * Mockito 提供的 spy 方法可以包装一个真实的 Java 对象, 并返回一个包装后的新对象. 若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象
     * 这个例子中我们实例化了一个 LinkedList 对象, 然后使用 spy() 方法对 list 对象进行部分模拟. 接着我们使用 when(…).thenReturn(…) 方法链来规定 spy.size() 方法返回值是 100. 随后我们给 spy 添加了两个元素, 然后再 调用 spy.get(0) 获取第一个元素.
     * 这里有意思的地方是: 因为我们没有定制 add(“one”), add(“two”), get(0), get(1), 因此通过 spy 调用这些方法时, 实际上是委派给 list 对象来调用的。
     * 然而我们 定义了 spy.size() 的返回值, 因此当调用 spy.size() 时, 返回 100
     */
    @Test
    @DisplayName("spy api 测试")
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        // 对 spy.size() 进行定制，即特别配置
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        // 因为我们没有对 get(0), get(1) 方法进行定制,
        // 因此这些调用其实是调用的真实对象的方法.
        Assertions.assertEquals(spy.get(0), "one");
        Assertions.assertEquals(spy.get(1), "two");

        Assertions.assertEquals(spy.size(), 100);
    }

    /**
     * 参数捕获
     * Mockito 允准我们捕获一个 Mock 对象的方法调用所传递的参数
     */
    @Test
    @DisplayName("ArgumentCaptor api 测试")
    public void testCaptureArgument() {
        List<String> list = Arrays.asList("1", "2");
        List mockedList = mock(List.class);
        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        mockedList.addAll(list);
        // 我们通过 verify(mockedList).addAll(argument.capture()) 语句来获取 mockedList.addAll 方法所传递的实参 list，调用和赋值
        verify(mockedList).addAll(argument.capture());

        Assertions.assertEquals(2, argument.getValue().size());
        Assertions.assertEquals(list, argument.getValue());
    }

    @Test
    @DisplayName("web server 200")
    public void testWebServer() {
        String url = "http://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort() + "/interfaceName";
        Dispatcher dispatcher = getDispatcher(url, "", 200);
        mockWebServer.setDispatcher(dispatcher);
    }

    private Dispatcher getDispatcher(String url, String body, Integer httpStatus) {
        return new Dispatcher() {
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                if (url.equals(recordedRequest.getPath())) {
                    return new MockResponse()
                            .setResponseCode(httpStatus)
                            .setBody(body)
                            .addHeader("Content-Type", "application/json");
                } else {
                    return new MockResponse().setResponseCode(404);
                }
            }
        };
    }

}