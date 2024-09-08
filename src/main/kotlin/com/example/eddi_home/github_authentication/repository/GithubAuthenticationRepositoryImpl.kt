package com.example.eddi_home.github_authentication.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class GithubAuthenticationRepositoryImpl : GithubAuthenticationRepository {

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

        // RestTemplate을 사용하여 액세스 토큰 요청
        val restTemplate = RestTemplate()
        val response = restTemplate.postForEntity(url, params, String::class.java)

        return response.body ?: throw RuntimeException("Failed to retrieve access token")
    }
}
