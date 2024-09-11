package com.example.eddi_home.utility

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
class PropertyUtility(
    private val environment: Environment
) {
    fun getProperty(key: String): String? {
        return environment.getProperty(key)
    }
}