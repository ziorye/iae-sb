package com.ioewvsau.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    long id;
    String title;
    String slug;
    String cover;
    String description;
    String content;
    int status;
    long userId;
    int viewCount;
    Date createdAt;
    Date updatedAt;
}
