package com.ziorye.sb03data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/user_schema.sql", "/import_user.sql"})
public class JdbcTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void testQueryForObject() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        Assertions.assertEquals(2, aLong);
    }
}