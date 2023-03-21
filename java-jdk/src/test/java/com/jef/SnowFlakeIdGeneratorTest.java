package com.jef;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SnowFlakeIdGeneratorTest {

    /**
     * 雪花算法测试
     */
    @Test
    public void testSnowFlakeIdGenerator() {
        // 机器ID
        short uniqueWorkerId = 0;
        // 创建 IdGeneratorOptions 对象，可在构造函数中输入 WorkerId：
        IdGeneratorOptions options = new IdGeneratorOptions(uniqueWorkerId);
        // options.WorkerIdBitLength = 10; // 默认值6，限定 WorkerId 最大值为2^6-1，即默认最多支持64个节点。
        // options.SeqBitLength = 6; // 默认值6，限制每毫秒生成的ID个数。若生成速度超过5万个/秒，建议加大 SeqBitLength 到 10。
        // options.BaseTime = Your_Base_Time; // 如果要兼容老系统的雪花算法，此处应设置为老系统的BaseTime。
        // ...... 其它参数参考 IdGeneratorOptions 定义。

        // 保存参数（务必调用，否则参数设置不生效）：
        YitIdHelper.setIdGenerator(options);

        // 以上过程只需全局一次，且应在生成ID之前完成。
        // 初始化后，在任何需要生成ID的地方，调用以下方法：
        Set<Long> longSet = Sets.newHashSet();
        for (int i = 0; i < 100; i++) {
            long newId = YitIdHelper.nextId();
            longSet.add(newId);
            System.out.println("Id=" + newId);
        }
        Assert.assertEquals("出现ID重复", longSet.size(), 100);
    }
}
