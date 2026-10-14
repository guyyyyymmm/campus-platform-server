package com.campus.campus_platform_server.controller;

import com.campus.campus_platform_server.entity.LostItem;
import com.campus.campus_platform_server.service.LostItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/lost-items")
public class LostItemController {

    private final LostItemService lostItemService;

    public LostItemController(
            LostItemService lostItemService
    ) {
        this.lostItemService = lostItemService;
    }

    @GetMapping
    public List<LostItem> findAll() {
        return lostItemService.findAll();
    }

    @GetMapping("/{id}")
    public LostItem findById(
            @PathVariable Long id
    ) {
        return lostItemService.findById(id);
    }

    @PostMapping
    public LostItem create(
            @RequestHeader("X-Username") String username,
            @RequestBody LostItem lostItem
    ) {
        lostItem.setCreatedBy(username);
        lostItemService.insert(lostItem);
        return lostItem;
    }

    @PutMapping("/{id}")
    public LostItem update(
            @PathVariable Long id,
            @RequestHeader("X-Username") String username,
            @RequestBody LostItem lostItem
    ) {
        lostItem.setId(id);

        int count =
                lostItemService.updateByIdAndCreatedBy(
                        lostItem,
                        username
                );

        if (count == 0) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "无权修改该失物信息"
            );
        }

        return lostItem;
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestHeader("X-Username") String username
    ) {
        int count =
                lostItemService.deleteByIdAndCreatedBy(
                        id,
                        username
                );

        if (count == 0) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "无权删除该失物信息"
            );
        }

        return "删除成功";
    }
}