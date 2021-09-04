package com.jef.service.impl;

import com.jef.service.IHumanService;
import org.springframework.stereotype.Service;

/**
 * 女人
 * @author Jef
 * @create 2018/5/12 14:31
 */
@Service(value = "womanService")
public class WomanServiceImpl implements IHumanService {

    @Override
    public void speak() {
        System.out.println("女人说话");
    }

    @Override
    public void walk() {
        System.out.println("女人走路");
    }
}
