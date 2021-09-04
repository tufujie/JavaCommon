package com.jef.service.impl;

import com.jef.service.IHumanService;
import org.springframework.stereotype.Service;

/**
 * 男人
 *
 * @author Jef
 * @create 2018/5/12 14:31
 */
@Service(value = "manService")
public class ManServiceImpl implements IHumanService {

    @Override
    public void speak() {
        System.out.println("男人说话");
    }

    @Override
    public void walk() {
        System.out.println("男人走路");
    }
}
