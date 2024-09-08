package com.example.eddi_home.github_authentication.service

import com.example.eddi_home.github_authentication.repository.GithubAuthenticationRepository
import org.springframework.stereotype.Service

@Service
class GithubAuthenticationServiceImpl(
    private val githubAuthenticationRepository: GithubAuthenticationRepository
) : GithubAuthenticationService {

    override fun requestAccessTokenWithAuthorizeCode(authorizeCode: String): String {
        return githubAuthenticationRepository.requestAccessToken(authorizeCode)
    }
}