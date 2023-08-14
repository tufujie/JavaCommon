package com.jef.demo.dao;

import com.jef.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {

    User getByUser(User user);

}
