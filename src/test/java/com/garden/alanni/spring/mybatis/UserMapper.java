package com.garden.alanni.spring.mybatis;

import com.garden.alanni.spring.User;

/**
 * @author 吴宇伦
 */
public interface UserMapper {
    public void insert(User user);
    public void get(Long id);
}
