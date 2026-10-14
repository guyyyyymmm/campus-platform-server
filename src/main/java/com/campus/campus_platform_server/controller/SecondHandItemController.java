package com.campus.campus_platform_server.controller;

import com.campus.campus_platform_server.entity.SecondHandItem;
import com.campus.campus_platform_server.service.SecondHandItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/second-hand")
@CrossOrigin(origins = "http://localhost:5173")
public class SecondHandItemController {

    private final SecondHandItemService secondHandItemService;

    public SecondHandItemController(
            SecondHandItemService secondHandItemService
    ) {
        this.secondHandItemService = secondHandItemService;
    }

    @GetMapping
    public List<SecondHandItem> findAll() {
        return secondHandItemService.findAll();
    }

    @PostMapping
    public SecondHandItem create(
            @RequestHeader("X-Username") String username,
            @RequestBody SecondHandItem item
    ) {
        item.setCreatedBy(username);
        secondHandItemService.insert(item);
        return item;
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestHeader("X-Username") String username
    ) {
        int count =
                secondHandItemService.deleteByIdAndCreatedBy(
                        id,
                        username
                );

        if (count == 0) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "无权删除该商品"
            );
        }

        return "删除成功";
    }

    @PutMapping("/{id}")
    public SecondHandItem update(
            @PathVariable Long id,
            @RequestHeader("X-Username") String username,
            @RequestBody SecondHandItem item
    ) {
        item.setId(id);

        int count =
                secondHandItemService.updateByIdAndCreatedBy(
                        item,
                        username
                );

        if (count == 0) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "无权修改该商品"
            );
        }

        return item;
    }
}