package com.jef.designpattern.creation.builder;

import com.jef.entity.hardware.cpu.AMDCpu;
import com.jef.entity.hardware.cpu.IntelCpu;
import com.jef.entity.hardware.memory.KingstonMemory;
import com.jef.entity.hardware.memory.SansungMemory;

/**
 * 笔记本电脑组装
 * 组合
 *
 * @author Jef
 * @date 2021/12/7
 */
public class ComputerBuilder {

    /**
     * 组装方案1
     */
    public static ComputerMenu getLevelOne() {
        return new ComputerDecorationPackageMenu("性价比之王").appendCpu(new AMDCpu()).appendMemory(new SansungMemory());
    }

    /**
     * 组装方案2
     */
    public static ComputerMenu getLevelTwo() {
        return new ComputerDecorationPackageMenu("性能之王").appendCpu(new IntelCpu()).appendMemory(new KingstonMemory());
    }
}