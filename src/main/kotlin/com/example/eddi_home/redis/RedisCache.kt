package com.example.eddi_home.redis

interface RedisCache {
    fun setKeyAndValue(key:String, value:String)
    fun getValueByKey(key: String): String?
    fun deleteByKey(key: String)
    fun isKeyExists(key: String): Boolean
}