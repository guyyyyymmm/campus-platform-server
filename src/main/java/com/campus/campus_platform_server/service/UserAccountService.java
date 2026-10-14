package com.campus.campus_platform_server.service;

import com.campus.campus_platform_server.entity.UserAccount;
import com.campus.campus_platform_server.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountMapper userAccountMapper;

    public UserAccountService(UserAccountMapper userAccountMapper) {
        this.userAccountMapper = userAccountMapper;
    }

    public UserAccount register(UserAccount userAccount) {
        UserAccount existing =
                userAccountMapper.findByUsername(
                        userAccount.getUsername()
                );

        if (existing != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        userAccountMapper.insert(userAccount);
        return userAccount;
    }

    public UserAccount login(String username, String password) {
        UserAccount user =
                userAccountMapper.findByUsername(username);

        if (user == null
                || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException(
                    "用户名或密码错误"
            );
        }

        user.setPassword(null);
        return user;
    }
}