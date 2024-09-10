package com.example.eddi_home.github_authentication.repository

import com.example.eddi_home.github_authentication.service.response.GithubUserInfoResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class GithubAuthenticationRepositoryImpl(
    private val restTemplate: RestTemplate
) : GithubAuthenticationRepository {

    @Value("\${github.client.id}")
    private lateinit var clientId: String

    @Value("\${github.client.secret}")
    private lateinit var clientSecret: String

    @Value("\${github.redirect.uri}")
    private lateinit var redirectUri: String

    override fun requestAccessToken(authorizeCode: String): String {
        val url = "https://github.com/login/oauth/access_token"
        val params = mapOf(
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "code" to authorizeCode,
            "redirect_uri" to redirectUri
        )

        val response = restTemplate.postForEntity(url, params, String::class.java)

        return response.body ?: throw RuntimeException("access token 획득 실패")
    }

    override fun requestUserInfo(accessToken: String): GithubUserInfoResponse {

        val cleanedToken = accessToken.split("&")[0].split("=")[1]
        println("cleanedToken: $cleanedToken")

        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $cleanedToken")

        val entity = HttpEntity<String>("", headers)
        val response: ResponseEntity<String> = restTemplate.exchange(
            "https://api.github.com/user",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        val responseBody = response.body ?: throw RuntimeException("user info 획득 실패")

        val objectMapper = jacksonObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(responseBody)
    }
}
