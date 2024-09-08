package com.example.eddi_home.github_authentication.service

interface GithubAuthenticationService {
    fun requestAccessTokenWithAuthorizeCode(authorizeCode: String): String
}