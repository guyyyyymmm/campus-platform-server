package com.campus.campus_platform_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LostItem {

    private Long id;

    private String name;

    private String description;

    private String location;

    private String time;

    private String status;

    private String category;

    private String contact;

    private String imageUrl;
    public void setId(Long id) {
        this.id = id;
    }
    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}