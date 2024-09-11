package com.example.eddi_home.github_authentication.service

import com.example.eddi_home.github_authentication.service.response.GithubUserInfoResponse

interface GithubAuthenticationService {
    fun requestAccessTokenWithAuthorizeCode(authorizeCode: String): String
    fun requestUserInfoWithAccessToken(accessToken: String): GithubUserInfoResponse
}