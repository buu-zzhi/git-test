package com.example.gittest.service;

import com.example.gittest.entity.User;
import com.example.gittest.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

}
