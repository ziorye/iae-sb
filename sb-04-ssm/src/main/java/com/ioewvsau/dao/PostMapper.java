package com.ioewvsau.dao;

import com.ioewvsau.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    List<Post> selectAll();

    List<Post> selectByPage(@Param("rowCount") int rowCount, @Param("offset") int offset);

    @Select("select * from post where id = #{id}")
    Post selectById(long id);

    List<Post> selectByCondition(@Param("status") int status, @Param("title") String title, @Param("content") String content);

    List<Post> selectByCondition(Post post);

    List<Post> selectByCondition(Map<String, Object> map);

    List<Post> selectByConditionWithDynamicSQL(Map<String, Object> map);

    List<Post> selectByOneCondition(Map<String, Object> map);

    void add(Post post);

    void update(Post post);

    void deleteById(long id);

    void deleteByIds(long[] ids);
}
