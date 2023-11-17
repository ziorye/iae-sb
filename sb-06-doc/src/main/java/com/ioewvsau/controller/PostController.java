package com.ioewvsau.controller;

import com.ioewvsau.common.R;
import com.ioewvsau.pojo.Post;
import com.ioewvsau.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/posts")
@Tag(name = "post-controller", description = "文章管理")
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    @Operation(summary = "文章列表", description = "支持分页的文章列表接口，默认显示第一页(page=1), 每页显示10条(perPage=10)")
    R index(@Parameter(description = "当前页码") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "每页显示数量") @RequestParam(value = "perPage", defaultValue = "10") int perPage)
    {
        List<Post> data = postService.getByPage(perPage, (page - 1) * perPage);
        return R.ok(data);
    }

    @PostMapping
    R store(@RequestBody Post post) {
        int affectedRows = postService.save(post);
        return R.ok(affectedRows);
    }

    @GetMapping("/{id}")
    R show(@PathVariable long id) {
        Post data = postService.getById(id);
        return R.ok(data == null ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value(), data);
    }

    @PutMapping("/{id}")
    R update(@PathVariable long id, @RequestBody Post post) {
        post.setId(id);
        int affectedRows = postService.update(post);
        return R.ok(affectedRows == 0 ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value(), affectedRows);
    }

    @DeleteMapping("/{id}")
    R delete(@PathVariable long id) {
        int affectedRows = postService.deleteById(id);
        return R.ok(affectedRows == 0 ? HttpStatus.NOT_FOUND.value() : HttpStatus.OK.value(), affectedRows);
    }

}
