package com.campus.campus_platform_server.mapper;

import com.campus.campus_platform_server.entity.LostItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LostItemMapper {

    @Select("""
            SELECT id, name, description, location, `time`,
                   status, category, contact, image_url AS imageUrl,
                                              created_by AS createdBy
            FROM lost_item
            ORDER BY id DESC
            """)
    List<LostItem> findAll();

    @Select("""
            SELECT id, name, description, location, `time`,
                   status, category, contact, image_url AS imageUrl,
                                              created_by AS createdBy
            FROM lost_item
            WHERE id = #{id}
            """)
    LostItem findById(Long id);

    @Insert("""
        INSERT INTO lost_item
        (name, description, location, `time`,
         status, category, contact, image_url, created_by)
        VALUES
        (#{name}, #{description}, #{location}, #{time},
         #{status}, #{category}, #{contact},
         #{imageUrl}, #{createdBy})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LostItem lostItem);

    @Update("""
        UPDATE lost_item
        SET name = #{item.name},
            description = #{item.description},
            location = #{item.location},
            `time` = #{item.time},
            status = #{item.status},
            category = #{item.category},
            contact = #{item.contact},
            image_url = #{item.imageUrl}
        WHERE id = #{item.id}
          AND created_by = #{createdBy}
        """)
    int updateByIdAndCreatedBy(
            @Param("item") LostItem item,
            @Param("createdBy") String createdBy
    );

    @Delete("""
        DELETE FROM lost_item
        WHERE id = #{id}
          AND created_by = #{createdBy}
        """)
    int deleteByIdAndCreatedBy(
            @Param("id") Long id,
            @Param("createdBy") String createdBy
    );
}