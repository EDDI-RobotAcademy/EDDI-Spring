package com.example.eddi_home.config

import com.example.eddi_home.redis.RedisCache
import com.example.eddi_home.redis.RedisCacheImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class RedisConfig {

    @Bean
    fun redisCache(redisTemplate: StringRedisTemplate): RedisCache {
        return RedisCacheImpl(redisTemplate)
    }
}
