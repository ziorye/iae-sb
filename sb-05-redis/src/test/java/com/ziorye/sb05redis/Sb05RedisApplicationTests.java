package com.ziorye.sb05redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.UUID;

@SpringBootTest
class Sb05RedisApplicationTests {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void string() {
        String key = "key-for-string";
        String value = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key, value);

        Assertions.assertEquals(value, redisTemplate.opsForValue().get(key));
    }

    @Test
    void list() {
        String key = "key-for-list";

        redisTemplate.opsForList().leftPush(key, "X");
        redisTemplate.opsForList().leftPush(key, "Y");
        redisTemplate.opsForList().leftPush(key, "Z");

        Assertions.assertEquals("Z", redisTemplate.opsForList().leftPop(key));
    }

    @Test
    void set() {
        String key = "key-for-set";

        redisTemplate.opsForSet().add(key, "X", "Y", "Z", "Z");

        Assertions.assertEquals(3, redisTemplate.opsForSet().size(key));
    }

    @Test
    void zset() {
        String key = "key-for-zset";

        redisTemplate.opsForZSet().add(key, "Alice", 86.08);
        redisTemplate.opsForZSet().add(key, "Bob", 98.35);
        redisTemplate.opsForZSet().add(key, "Trudy", 92.56);

        ZSetOperations.TypedTuple<String> popMax = redisTemplate.opsForZSet().popMax(key);

        Assertions.assertEquals("Bob", popMax.getValue());
        Assertions.assertEquals(98.35, popMax.getScore());
    }

    @Test
    void hash() {
        String key = "key-for-hash";

        redisTemplate.opsForHash().put(key, "key1", "value1");
        redisTemplate.opsForHash().put(key, "key2", "value2");
        redisTemplate.opsForHash().put(key, "key3", "value3");

        Assertions.assertEquals("value2", redisTemplate.opsForHash().get(key, "key2"));
    }

}
