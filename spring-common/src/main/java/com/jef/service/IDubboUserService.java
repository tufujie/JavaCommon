package com.jef.service;

import com.jef.entity.User;

public interface IDubboUserService {

    User getByName(String name);
}
