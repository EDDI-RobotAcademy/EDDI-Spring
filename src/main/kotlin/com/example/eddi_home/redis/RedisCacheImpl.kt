package com.example.eddi_home.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import java.time.Duration

class RedisCacheImpl(
    private val redisTemplate: StringRedisTemplate
) : RedisCache {

    override fun setKeyAndValue(key: String, value: String, duration: Duration?) {
        val valueOps: ValueOperations<String, String> = redisTemplate.opsForValue()

        if (duration != null) {
            valueOps.set(key, value, duration)
            return
        }

        valueOps.set(key, value)
    }

    override fun getValueByKey(key: String): String? {
        val valueOps: ValueOperations<String, String> = redisTemplate.opsForValue()
        return valueOps.get(key)
    }

    override fun deleteByKey(key: String) {
        redisTemplate.delete(key)
    }

    override fun isKeyExists(key: String): Boolean {
        return getValueByKey(key) != null
    }
}