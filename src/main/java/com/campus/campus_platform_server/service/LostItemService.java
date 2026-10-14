package com.campus.campus_platform_server.service;

import com.campus.campus_platform_server.entity.LostItem;
import com.campus.campus_platform_server.mapper.LostItemMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostItemService {

    private final LostItemMapper lostItemMapper;

    public LostItemService(
            LostItemMapper lostItemMapper
    ) {
        this.lostItemMapper = lostItemMapper;
    }

    public List<LostItem> findAll() {
        return lostItemMapper.findAll();
    }

    public LostItem findById(Long id) {
        return lostItemMapper.findById(id);
    }

    public int insert(LostItem lostItem) {
        return lostItemMapper.insert(lostItem);
    }

    public int updateByIdAndCreatedBy(
            LostItem item,
            String createdBy
    ) {
        return lostItemMapper.updateByIdAndCreatedBy(
                item,
                createdBy
        );
    }

    public int deleteByIdAndCreatedBy(
            Long id,
            String createdBy
    ) {
        return lostItemMapper.deleteByIdAndCreatedBy(
                id,
                createdBy
        );
    }
}