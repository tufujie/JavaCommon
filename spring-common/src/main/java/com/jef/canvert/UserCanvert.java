package com.jef.canvert;

import com.jef.dto.UserDto;
import com.jef.entity.User;
import com.jef.common.utils.BeanPropertiesCopy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xueyuan
 * @dater 2016-11-24 0024.
 */
public class UserCanvert {

    /**
     * 从User转化为UserDto
     * @param user
     * @return
     */
    public static UserDto convertFromEntity(User user) {
        UserDto dto = new UserDto();
        try {
            BeanPropertiesCopy.propertiesCopy(user, dto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static List<UserDto> convertFromEntity(List<User> entities) {
        List<UserDto> dtos = new ArrayList<>();
        dtos.addAll(entities.stream().map(UserCanvert::convertFromEntity).collect(Collectors.toList()));
        return dtos;
    }

    /**
     * 从UserDto转化为User
     * @param dto
     * @return
     */
    public static User canvertFromDto(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setAge(dto.getAge());
        return user;
    }
}
