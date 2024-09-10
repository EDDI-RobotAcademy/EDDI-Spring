package com.example.eddi_home.redis

import java.time.Duration

interface RedisCache {
    fun setKeyAndValue(key: String, value: String, duration: Duration? = null)
    fun getValueByKey(key: String): String?
    fun deleteByKey(key: String)
    fun isKeyExists(key: String): Boolean
}