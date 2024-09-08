package com.example.eddi_home.github_authentication.repository

interface GithubAuthenticationRepository {
    fun requestAccessToken(authorizeCode: String): String
}