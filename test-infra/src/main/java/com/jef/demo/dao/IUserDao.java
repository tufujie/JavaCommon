package com.jef.demo.dao;

import com.jef.demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    User getByUser(User user);

    List<User> getUserList(User user);

}
