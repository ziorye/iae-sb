package com.ziorye.sb03data.service;

import com.ziorye.sb03data.bean.City;
import com.ziorye.sb03data.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;

    public City findById(Long id) {
        return cityMapper.findById(id);
    }
}
