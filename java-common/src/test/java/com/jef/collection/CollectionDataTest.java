package com.jef.collection;

import com.jef.common.GeneratorImplDemo;
import com.jef.util.CollectionData;
import com.jef.util.ListUtil;
import com.jef.util.StringUtils;
import org.junit.Test;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 使用generator对象填充集合对应的测试类
 * @author Jef
 * @date 20180728
 */
public class CollectionDataTest {
    @Test
    public void collectionDataTest() {
        Set<String> set = new LinkedHashSet<>();
        set.addAll(CollectionData.list(new GeneratorImplDemo(), GeneratorImplDemo.arrayLength));
        System.out.println(set);
    }

    /**
     * 集合取交集
     * @author Jef
     * @date 2020/3/29
     */
    @Test
    public void retainAllTest() {
        List<String> stringList = StringUtils.getTestStringList();
        List<String> stringV2List = StringUtils.getTestStringListV2();
        stringList.retainAll(stringV2List);
        ListUtil.systemPrintStringList(stringList, "retainAll result");
    }

}


