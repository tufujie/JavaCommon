package com.jef.designpattern.creation.builder;

import com.jef.entity.hardware.Matter;

/**
 * 笔记本的硬件
 *
 * @author Jef
 * @date 2021/12/7
 */
public interface ComputerMenu {

    /**
     * 添加CPU
     */
    ComputerMenu appendCpu(Matter matter);

    /**
     * 添加内存
     */
    ComputerMenu appendMemory(Matter matter);

    /**
     * 获取费用明细
     */
    StringBuilder getDetail();
}