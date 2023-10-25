package com.ioewvsau.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "文章")
@Data
public class Post {
    long id;
    @Schema(description = "文章标题")
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
