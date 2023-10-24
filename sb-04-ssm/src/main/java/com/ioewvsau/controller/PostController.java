package com.ioewvsau.controller;

import com.ioewvsau.common.R;
import com.ioewvsau.pojo.Post;
import com.ioewvsau.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    R index(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "perPage", defaultValue = "10") int perPage)
    {
        List<Post> data = postService.getByPage(perPage, (page - 1) * perPage);
        return R.ok(data);
    }

    @PostMapping
    int store(@RequestBody Post post) {
        return postService.save(post);
    }

    @GetMapping("/{id}")
    Post show(@PathVariable long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    int update(@PathVariable long id, @RequestBody Post post) {
        post.setId(id);
        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    int delete(@PathVariable long id) {
        return postService.deleteById(id);
    }

}
