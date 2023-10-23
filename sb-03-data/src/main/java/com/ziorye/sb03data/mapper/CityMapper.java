package com.ziorye.sb03data.mapper;

import com.ziorye.sb03data.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {
    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
    City findById(long id);
}
