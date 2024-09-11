package com.example.eddi_home.github_authentication.repository

import com.example.eddi_home.github_authentication.service.response.GithubUserInfoResponse

interface GithubAuthenticationRepository {
    fun requestAccessToken(authorizeCode: String): String
    fun requestUserInfo(accessToken: String): GithubUserInfoResponse
}