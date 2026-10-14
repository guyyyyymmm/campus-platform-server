package com.campus.campus_platform_server.service;

import com.campus.campus_platform_server.entity.SecondHandItem;
import com.campus.campus_platform_server.mapper.SecondHandItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondHandItemService {

    private final SecondHandItemMapper secondHandItemMapper;

    public SecondHandItemService(
            SecondHandItemMapper secondHandItemMapper
    ) {
        this.secondHandItemMapper = secondHandItemMapper;
    }

    public List<SecondHandItem> findAll() {
        return secondHandItemMapper.findAll();
    }

    public int insert(SecondHandItem item) {
        return secondHandItemMapper.insert(item);
    }

    public int deleteByIdAndCreatedBy(
            Long id,
            String createdBy
    ) {
        return secondHandItemMapper.deleteByIdAndCreatedBy(
                id,
                createdBy
        );
    }

    public int updateByIdAndCreatedBy(
            SecondHandItem item,
            String createdBy
    ) {
        return secondHandItemMapper.updateByIdAndCreatedBy(
                item,
                createdBy
        );
    }
}