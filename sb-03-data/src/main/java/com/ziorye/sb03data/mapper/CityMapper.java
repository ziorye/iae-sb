package com.ziorye.sb03data.mapper;

import com.ziorye.sb03data.bean.City;
import org.apache.ibatis.annotations.Select;

public interface CityMapper {
    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
    City findById(long id);

    /*@Insert("INSERT INTO city (name, state, country) VALUES(#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")*/
    void insertCity(City city);
}
