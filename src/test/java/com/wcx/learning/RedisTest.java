package com.wcx.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

/**
 * @author wuchuanxiang
 * @date 2021/8/17
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    // 关于String类型的测试
    @Test
    public void Strings() {
        //
        redisTemplate.opsForValue().set("stringKey1", "stringValue1");
        Object stringValue1 = redisTemplate.opsForValue().get("stringKey1");
        System.out.println("stringKey1  --->  " + stringValue1);//  stringKey1  --->  stringValue1

        // 获取一个不存在的key
        Object notExistValue = redisTemplate.opsForValue().get("notExistKey");
        System.out.println("notExistKey  --->  " + notExistValue);//  stringKey1  --->  stringValue1



        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("stringKey1", "stringValue1");
        System.out.println(aBoolean?"stringKey1不存在且设置成功":"stringKey1存在设置失败");// stringKey1存在设置失败

        Boolean aBoolean1 = redisTemplate.opsForValue().setIfAbsent("stringKey2", "stringValue1");
        System.out.println(aBoolean1?"stringKey2不存在且设置成功":"stringKey2存在设置失败"); //stringKey2不存在且设置成功


        HashMap<String, Object> multiSetIfAbsentMap = new HashMap<>();
        multiSetIfAbsentMap.put("multiSetIfAbsentKey1", "multiSetIfAbsentValue1");
        multiSetIfAbsentMap.put("multiSetIfAbsentKey2", "multiSetIfAbsentValue2");
        Boolean aBoolean2 = redisTemplate.opsForValue().multiSetIfAbsent(multiSetIfAbsentMap);
        System.out.println(aBoolean2?"multiSetIfAbsentMap设置成功":"multiSetIfAbsentMap设置失败");


        HashMap<String, Object> multiSetIfAbsentMap2 = new HashMap<>();
        multiSetIfAbsentMap.put("multiSetIfAbsentKey1", "multiSetIfAbsentValue1");
        multiSetIfAbsentMap.put("multiSetIfAbsentKey3", "multiSetIfAbsentValue3");
        Boolean aBoolean3 = redisTemplate.opsForValue().multiSetIfAbsent(multiSetIfAbsentMap2);
        System.out.println(aBoolean3?"multiSetIfAbsentMap2设置成功":"multiSetIfAbsentMap2设置失败");


    }

}
