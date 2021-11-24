package com.wcx.learning;

import com.wcx.learning.config.ICBCPayProperties;
import com.wcx.learning.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import java.time.Duration;

/**
 * @author wuchuanxiang
 * @date 2021/8/3
 */

@SpringBootTest
public class SpringbootTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ICBCPayProperties icbcPayProperties;

    @Test
    public void redisTest1() {
        Boolean f1 = redisTemplate.opsForValue().setIfAbsent("wucx", "123");
        System.out.println(f1);
        Boolean f2 = redisTemplate.opsForValue().setIfAbsent("wucx2", "123", Duration.ofSeconds(60));
        System.out.println(f2);

    }
    @Test
    public void redisTest2() {
        redisTemplate.delete("wucx");
        redisTemplate.delete("wucx2");
    }
    @Test
    public void icbcPayPropertiesTest() {
        System.out.println(icbcPayProperties.getAppKey());
        System.out.println(icbcPayProperties.getCustomerUrl());

    }
}
