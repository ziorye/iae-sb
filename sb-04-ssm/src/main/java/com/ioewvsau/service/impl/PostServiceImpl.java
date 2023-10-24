package com.ioewvsau.service.impl;

import com.ioewvsau.dao.PostMapper;
import com.ioewvsau.pojo.Post;
import com.ioewvsau.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;

    @Override
    public List<Post> getAll() {
        return postMapper.selectAll();
    }

    @Override
    public List<Post> getByPage(int rowCount, int offset) {
        return postMapper.selectByPage(rowCount, offset);
    }

    @Override
    public Post getById(long id) {
        return postMapper.selectById(id);
    }

    @Override
    public boolean save(Post post) {
        postMapper.add(post);
        return true;
    }

    @Override
    public boolean update(Post post) {
        postMapper.update(post);
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        postMapper.deleteById(id);
        return true;
    }
}
