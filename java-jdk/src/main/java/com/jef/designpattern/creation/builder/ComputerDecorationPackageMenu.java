package com.jef.designpattern.creation.builder;

import com.jef.entity.hardware.Matter;
import com.jef.util.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 笔记本硬件装饰
 *
 * @author Jef
 * @date 2021/12/7
 */
public class ComputerDecorationPackageMenu implements ComputerMenu {
    List<Matter> matterList = new ArrayList<>();

    /**
     * 总价格
     */
    private BigDecimal price;

    /**
     * 等级
     */
    private String level;

    public ComputerDecorationPackageMenu(String level) {
        this.level = level;
    }

    @Override
    public ComputerMenu appendCpu(Matter matter) {
        matterList.add(matter);
        price = NumberUtils.add(price, matter.getPrice());
        return this;
    }

    @Override
    public ComputerMenu appendMemory(Matter matter) {
        matterList.add(matter);
        price = NumberUtils.add(price, matter.getPrice());
        return this;
    }

    @Override
    public StringBuilder getDetail() {
        StringBuilder sb = new StringBuilder();
        sb.append("推荐标题【").append(level).append("】\n");
        sb.append("总费用：").append(price).append("\n");
        sb.append("费用明细如下：\n");
        for (Matter matter : matterList) {
            sb.append("类型【").append(matter.getType()).append("】品牌【").append(matter.getBrand()).append("】型号【").append(matter.getModel())
                    .append("】【价格】").append(matter.getPrice()).append("\n");
        }
        return sb;
    }
}