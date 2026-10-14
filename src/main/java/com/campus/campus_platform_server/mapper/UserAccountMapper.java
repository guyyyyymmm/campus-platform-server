package com.campus.campus_platform_server.mapper;

import com.campus.campus_platform_server.entity.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAccountMapper {

    @Select("""
            SELECT id, username, password,
                   nickname, email,
                   create_time AS createTime
            FROM user_account
            WHERE username = #{username}
               OR email = #{username}
            """)
    UserAccount findByUsername(String username);

    @Insert("""
            INSERT INTO user_account
            (username, password, nickname, email)
            VALUES
            (#{username}, #{password}, #{nickname}, #{email})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserAccount userAccount);
}