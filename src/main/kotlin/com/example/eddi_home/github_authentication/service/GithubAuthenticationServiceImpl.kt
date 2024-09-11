package com.example.eddi_home.github_authentication.service

import com.example.eddi_home.github_authentication.repository.GithubAuthenticationRepository
import com.example.eddi_home.github_authentication.service.response.GithubUserInfoResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GithubAuthenticationServiceImpl(
    private val githubAuthenticationRepository: GithubAuthenticationRepository,
    private val restTemplate: RestTemplate
) : GithubAuthenticationService {

    override fun requestAccessTokenWithAuthorizeCode(authorizeCode: String): String {
        return githubAuthenticationRepository.requestAccessToken(authorizeCode)
    }

    override fun requestUserInfoWithAccessToken(accessToken: String): GithubUserInfoResponse {
        return githubAuthenticationRepository.requestUserInfo(accessToken)
    }
}