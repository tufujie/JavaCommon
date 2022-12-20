package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.constant.BasicEntity;
import com.jef.constant.BasicList;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;
import com.jef.util.ListUtil;
import com.jef.util.NumberUtils;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingLong;

/**
 * jdk8集合便捷操作测试
 * 虽然对集合的遍历和操作可以用以前常规的方式，但是当业务逻辑复杂的时候，你会发现代码量很多，可读性很差，明明一行代码解决的事情，你却写了好几行。试试lambda表达式，试试Stream，你会有不一样的体验。
 * 惰性求值与及早求值
 * 惰性求值：只描述Stream，操作的结果也是Stream，这样的操作称为惰性求值。**惰性求值可以像建造者模式一样链式使用，最后再使用及早求值得到最终结果。
 * 及早求值：得到最终的结果而不是Stream，这样的操作称为及早求值。
 *
 * @author Jef
 * @date 2019/3/13
 */
public class ListStreamTest {

    /**
     * filter（过滤筛选功能）
     * 内部就是Predicate接口。惰性求值。
     * 集合的筛选
     * 对集合进行过滤操作，满足条件的会被添加到过滤后的集合中
     *
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testFilter() {
        List<User> users = BasicList.getUserList();
        ListUtil.systemPrintUserList(users, "未筛选之前user集合=");
        List<User> userFilterList = users.stream().filter(obj -> BasicConstant.USER_NAME.equals(obj.getName())).collect(Collectors.toList());
        ListUtil.systemPrintUserList(userFilterList, "筛选之后user集合=");
    }

    /**
     * map(转换功能，映射)
     * 内部就是Function接口。惰性求值
     * 集合转换
     * map就是将对应的元素按照给定的方法进行转换。
     *
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testMap() {
        List<User> users = BasicList.getUserList();
        // 获取集合中的某个字段的值，形成list集合，ids可能重复
        List<Long> idList = users.stream()
                .map(User::getId).collect(Collectors.toList());
        ListUtil.systemPrintIDList(idList);
        // 获取集合中的某个字段的值，形成集合，ids不重复
        Set<Long> idsSet = users.stream()
                .map(User::getId).collect(Collectors.toSet());
        System.out.println("id set集合，去重后=" + idsSet);
    }

    /**
     * 将多个Stream合并为一个Stream
     * 调用Stream.of的静态方法将两个list转换为Stream，再通过flatMap将两个流合并为一个。
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testFlatMap() {
        List<User> users = BasicList.getUserList();
        List<User> userList = Stream.of(users, BasicList.getUserListV2()).flatMap(Collection::stream).collect(Collectors.toList()) ;
        System.out.println(users);
        System.out.println(userList);
    }

    /**
     * distinct(去重)
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testDistinct() {
        List<User> users = BasicList.getUserList();
        List<Long> idList = users.stream().map(User::getId).collect(Collectors.toList());
        ListUtil.systemPrintIDList(idList);
        // 集合去重（基本类型）
        List<Long> ids = idList.stream().distinct().collect(Collectors.toList());
        ListUtil.systemPrintIDList(ids);
        // 集合去重（引用对象），两个重复的“Jef”进行了去重，这不仅因为使用了distinct()方法，而且因为User对象重写了equals和hashCode()方法，否则去重是无效的。
        List<Long> idsV2 = users.stream().distinct().map(User::getId).collect(Collectors.toList());
        ListUtil.systemPrintIDList(idsV2, "id list集合去重2=");
    }

    /**
     * sorted(排序)
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testSort() {
        List<User> users = BasicList.getUserList();
        List<Long> idList = users.stream().map(User::getId).collect(Collectors.toList());
        ListUtil.systemPrintIDList(idList);
        // 集合排序（默认排序）
        List<Long> idListSort = idList.stream().sorted().collect(Collectors.toList());
        ListUtil.systemPrintIDList(idListSort, "排序后");
        ListUtil.systemPrintUserList(users, "未排序前");
        // 集合排序（指定排序规则）
        List<User> userList = users.stream().sorted(comparing(User::getId)).sorted(comparing(User::getAge)).collect(Collectors.toList());
        ListUtil.systemPrintUserList(userList, "按id和age排序后");
        // 集合排序（指定排序规则），直接影响原来的集合
        users.sort((obj1, obj2) -> obj1.getType() - obj2.getType());
        ListUtil.systemPrintUserList(users, "按type排序后");
    }

    /**
     * limit（限制返回个数）
     * 集合limit，返回前n个元素
     */
    @Test
    public void testLimit() {
        List<User> users = BasicList.getUserList();
        List<User> userList = users.stream().limit(2).collect(Collectors.toList());
        ListUtil.systemPrintUserList(userList, "limit之后");
    }

    /**
     * skip(跳过几个，删除n个元素)
     *
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testSkip() {
        List<User> users = BasicList.getUserList();
        List<User> userList = users.stream().skip(2).collect(Collectors.toList());
        ListUtil.systemPrintUserList(userList, "skip之后");
    }

    /**
     * groupby
     * 数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，可以使用任意值对数据分组。Collectors.groupingBy接收一个Function做转换。
     * Collectors.groupingBy与SQL 中的 group by 操作是一样的。
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testGroupBy() {
        List<User> users = BasicList.getUserList();
        Map<Integer, List<User>> userListMap = users.stream().collect(Collectors.groupingBy(User::getType));
        System.out.println("list进行分类一对多Map，=" + userListMap);

        Map<String, List<User>> userListMultiPropertyMap = users.stream().collect(Collectors.groupingBy(this::fetchUserGroupKey));
        System.out.println("list进行分类一对多属性map，=" + userListMultiPropertyMap);
        Map<String, List<User>> userListMultiPropertyTwoMap = users.stream().collect(Collectors.groupingBy(item -> item.getName() + item.getPhone()));
        System.out.println("list进行分类一对多属性two Map，=" + userListMultiPropertyTwoMap);
        Map<Integer, List<Long>> userListMultiPropertyThreeMap = users.stream().collect(Collectors.groupingBy(User::getType, Collectors.mapping(User::getId, Collectors.toList())));
        System.out.println("list进行分类一对多属性three Map，=" + userListMultiPropertyThreeMap);

        // TreeMap默认为按照key升序
        Map<Integer, List<User>> userListMapSort = users.stream()
                .collect(Collectors.groupingBy(User::getType, TreeMap::new, Collectors.toList()));
        System.out.println("list进行分类一对多Map排序升序，=" + userListMapSort);

        // LinkedHashMap默认为按照key添加的顺序排序
        Map<Integer, List<User>> userListMapSort2 = users.stream()
                .collect(Collectors.groupingBy(User::getType, LinkedHashMap::new, Collectors.toList()));
        System.out.println("list进行分类一对多Map排序按添加，=" + userListMapSort2);
        Map<Integer, BigDecimal> listMapTotalMap = Maps.newHashMap();
        userListMapSort2.forEach((key, value) -> listMapTotalMap.put(key, value.stream().map(User::getValue).reduce(BigDecimal.ZERO, NumberUtils::add)));
        System.out.println("list进行分类一对多map后汇总，即Map转Map=" + listMapTotalMap);
        Map<Integer, BigDecimal> listMapTotalMap2 = users.stream()
                .collect(Collectors.groupingBy(User::getType, Collectors.mapping(User::getValue, Collectors.reducing(BigDecimal.ZERO, (obj1, obj2) -> NumberUtils.add(obj1, obj2)))));
        System.out.println("list进行分类一对多map后汇总，即List转Map=" + listMapTotalMap2);
    }

    /**
     * 用户多属性分组
     *
     * @param user
     * @return
     */
    private String fetchUserGroupKey(User user) {
        return user.getName() + user.getPhone();
    }


    @Test
    public void testPartitioningBy() {
        List<User> users = BasicList.getUserList();
        Map<Boolean, List<User>> listMap = users.stream().collect(
                Collectors.partitioningBy(obj -> obj.getName().contains("TEST")));
        System.out.println(listMap);
    }

    /** 
     * min(求最小值)
     * max同理，求最大值
     * max、min接收一个Comparator（例子中使用java8自带的静态函数，只需要传进需要比较值即可。）并且返回一个Optional对象，该对象是java8新增的类，专门为了防止null引发的空指针异常。
     * 可以使用isPresent()判断是否有值；可以使用orElse(new Object())，当值为null时就使用给定值；也可以使用orElseGet(() -> new Object());这需要传入一个Supplier的lambda表达式。
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testMin() {
        List<User> users = BasicList.getUserList();
        Optional<User> minAgeUser = users.stream().min(Comparator.comparingInt(User::getAge));
        // 判断是否有值
        if (minAgeUser.isPresent()) {
            System.out.println("最小年龄的用户" + minAgeUser);
        }
    }
    
    /** 
     * anyMatch/allMatch/noneMatch（匹配）
     * anyMatch：Stream 中任意一个元素符合传入的 predicate，返回 true
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     * @author Jef
     * @date 2020/1/17
     */
    @Test
    public void testMatch() {
        List<User> users = BasicList.getUserList();
        Boolean anyMatch = users.stream().anyMatch(obj -> BasicConstant.USER_NAME.equals(obj.getName()));
        if (anyMatch) {
            System.out.println("有名叫" + BasicConstant.USER_NAME + "的用户");
        }
        Boolean allMatch = users.stream().allMatch(s -> s.getAge() >= 1);
        if (allMatch) {
            System.out.println("所有用户都满1周岁");
        }
        Boolean noneMatch = users.stream().noneMatch(s -> BasicConstant.STR_TESTVIP123.equals(s.getName()));
        if (noneMatch) {
            System.out.println("没有叫" + BasicConstant.STR_TESTVIP123 + "的用户");
        }
    }

    @Test
    public void testSetValue() {
        List<User> users = BasicList.getUserList();
        System.out.println("未修改前的user集合=" + users);
        // 设置集合中的某些字段的值
        Consumer<User> userConsumer = user1 -> user1.setName(user1.getName() + "Add");
        Consumer<User> userConsumerV2 = user2 -> user2.setAge(user2.getAge() + 1);
        users.forEach(userConsumer);
        users.forEach(userConsumerV2);
        System.out.println("修改之后的user集合=" + users);
        // 遍历修改值
        users.forEach(vo -> {
            vo.setName(vo.getName() + "赋予的值");
            // 遍历设置虚岁
            vo.setAge2(vo.getAge() + 1);
        });
        System.out.println("遍历修改之后=" + users);
    }

    @Test
    public void testListToMap() {
        List<User> users = BasicList.getUserList();

        User user1 = new User();
        user1.setId(1L);
        user1.setName(BasicConstant.USER_NAME);
        user1.setPhone(BasicConstant.USER_PHONE);
        user1.setAge(1);
        user1.setType(1);
        users.add(user1);
        try {
            Map<Long, User> mapV2 = users.stream().collect(
                    Collectors.toMap(User::getId, vo -> vo));
            System.out.println(mapV2);
        } catch (Exception e) {
            System.out.println("list中key重复产生异常，异常信息=" + e);
        }
        // List转Map，针对上述list中ID重复的情况的处理
        Map<Long, User> mapV2 = users.stream().collect(
                Collectors.toMap(User::getId, vo -> vo, (v1, v2) -> v2));
        // List转Map<Object, Object>
        Map<Long, String> map = users.stream().collect(
                Collectors.toMap(User::getId, User::getName, (v1, v2) -> v2));
        Map<Long, String> mapNameAndPhone = users.stream().collect(
                Collectors.toMap(User::getId, vo -> vo.getName() + vo.getPhone(), (v1, v2) -> v2));
        Map<String, Boolean> mapNameAndPhoneMap = users.stream().collect(
                Collectors.toMap(vo -> vo.getName() + vo.getPhone(), User::getAdmin, (v1, v2) -> v2));
        System.out.println("list转mapV2=" + mapV2);
        System.out.println("mapNameAndPhone" + mapNameAndPhone);
        System.out.println("list转map=" + map);
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getName, vo -> vo));
        System.out.println("list转一对一map，=" + userMap);

        Map<String, Object> map2 = new HashMap<>( );
        map2.put("age", "11");
        map2.put("name", "demo2");
        Map<String, Object> map3 = new HashMap<>( );
        map3.put("age", "33");
        map3.put("name", "demo3");
        Map<String, Object> map4 = new HashMap<>( );
        map4.put("age", "44");
        map4.put("name", "demo4");
        Map<String, Object> map5 = new HashMap<>( );
        map5.put("age", "22");
        map5.put("name", "demo5");
        List<Map<String, Object>> listMap = new ArrayList<>();
        listMap.add(map2);
        listMap.add(map3);
        listMap.add(map4);
        listMap.add(map5);

        // List<Map>与List<Object>类型，取出name的值拼成一个list
        List<String> nameList = listMap.stream().map(m -> m.get("name").toString()).collect(Collectors.toList());
        System.out.println("nameList=" + nameList);

        // List<Map>转Map
        // key是Map中的一个值，value是Map
        Map<String, Object> nameMap = listMap.stream().collect(Collectors.toMap(m -> m.get("name").toString(), obj -> obj));
        System.out.println("nameMap=" + nameMap);
        Map<String, Object> nameMapV2 = Maps.newHashMap();
        listMap.forEach(obj -> nameMapV2.put(obj.get("name").toString(), obj));
        System.out.println("nameMapV2=" + nameMapV2);
        Map<String, Object> nameMapV3 = Maps.newHashMap();
        for (Map<String, Object> mapTemp : listMap) {
            nameMapV3.put((String) mapTemp.get("age"), mapTemp.get("name"));
        }
        System.out.println("nameMapV3=" + nameMapV3);
    }

    /**
     * count
     * 统计功能，一般都是结合filter使用，因为先筛选出我们需要的再统计即可。及早求值
     * @author Jef
     * @date 2021/2/22
     */
    @Test
    public void testCount() {
        List<User> userList = BasicList.getUserList();
        long count = userList.stream().filter(obj -> obj.getAge() > 1).count();
        System.out.println("大于1岁的用户有" + count + "个");
    }

    @Test
    public void testMaxBy() {
        User userOne = BasicEntity.getUser(), userTwo = BasicEntity.getUserV2();
        userOne.setOrderInfos(BasicList.getOrderInfoList());
        userTwo.setOrderInfos(BasicList.getOrderInfoList());
        //将ostClass1、ostClass2转换为Stream
        Stream<User> classStream = Stream.of(userOne, userTwo);
        User userBiggest = biggestGroup(classStream);
        System.out.println("购买商品最多的的用户是：" + userBiggest.getName());
        System.out.println("用户1平均订单号是：" + averagePriceOfOrderInfo(userOne.getOrderInfos()));
    }

    /**
     * 获取购买商品最多的用户
     */
    private static User biggestGroup(Stream<User> userStream) {
        return userStream.max(comparing(obj -> obj.getOrderInfos().size()))
                .orElseGet(User::new);
    }

    /**
     * 计算平均店铺号
     */
    private static double averagePriceOfOrderInfo(List<OrderInfo> orderInfos) {
        return orderInfos.stream().collect(averagingLong(OrderInfo::getId));
    }

    @Test
    public void testRemoveIf() {
        List<User> userList = BasicList.getUserList();
        System.out.println("before=" + userList);
        // 移除不需要的，直接影响原来的集合
        userList.removeIf(obj -> obj.getType() == 2);
        System.out.println("after" + userList);
    }

    @Test
    public void testStreamOf() {
        List<String> strings = Stream.of("1", "2").collect(Collectors.toList());
        System.out.println(strings);
        strings = Stream.of(new String[]{"1", "2"}).collect(Collectors.toList());
        System.out.println(strings);
    }

    @Test
    public void testConcat() {
        List<String> strings = Stream.of("1", "2").collect(Collectors.toList());
        List<String> strings2 = Stream.of("3", "4").collect(Collectors.toList());
        List<String> stringsResult = Stream.concat(strings.stream(), strings2.stream()).collect(Collectors.toList());
        System.out.println(stringsResult);
        // 简化写法
        Stream<String> stringsStream = Stream.of("1", "2");
        Stream<String> strings2Stream = Stream.of("3", "4");
        List<String> stringsStreamResult = Stream.concat(stringsStream, strings2Stream).collect(Collectors.toList());
        System.out.println(stringsStreamResult);
    }

}