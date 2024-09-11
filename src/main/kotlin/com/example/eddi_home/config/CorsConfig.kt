package com.example.eddi_home.config

import com.example.eddi_home.utility.PropertyUtility
import org.springframework.http.HttpHeaders;
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableScheduling
class CorsConfig(
    private val corsProperties: CorsProperties
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        val originsList = corsProperties.allowedOrigins
        println("Allowed Origins List: $originsList")

        registry.addMapping("/**")
            .allowedOrigins(*originsList.toTypedArray())
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .exposedHeaders(HttpHeaders.AUTHORIZATION)
            .allowCredentials(true)
    }
}
