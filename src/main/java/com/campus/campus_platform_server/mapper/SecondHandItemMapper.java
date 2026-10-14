package com.campus.campus_platform_server.mapper;

import com.campus.campus_platform_server.entity.SecondHandItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SecondHandItemMapper {

    @Select("""
            SELECT id, name, description, price,
                   location, contact,
                   image_url AS imageUrl,
                   status,
                   created_by AS createdBy,
                   create_time AS createTime
            FROM second_hand_item
            ORDER BY id DESC
            """)
    List<SecondHandItem> findAll();

    @Insert("""
            INSERT INTO second_hand_item
            (name, description, price, location,
             contact, image_url, status, created_by)
            VALUES
            (#{name}, #{description}, #{price}, #{location},
             #{contact}, #{imageUrl}, #{status}, #{createdBy})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SecondHandItem item);

    @Delete("""
        DELETE FROM second_hand_item
        WHERE id = #{id}
          AND created_by = #{createdBy}
        """)
    int deleteByIdAndCreatedBy(
            @Param("id") Long id,
            @Param("createdBy") String createdBy
    );

    @Update("""
        UPDATE second_hand_item
        SET name = #{item.name},
            description = #{item.description},
            price = #{item.price},
            location = #{item.location},
            contact = #{item.contact},
            image_url = #{item.imageUrl},
            status = #{item.status}
        WHERE id = #{item.id}
          AND created_by = #{createdBy}
        """)
    int updateByIdAndCreatedBy(
            @Param("item") SecondHandItem item,
            @Param("createdBy") String createdBy
    );
}