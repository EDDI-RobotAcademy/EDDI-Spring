package com.example.eddi_home.account.service

interface AccountService {
    fun createAccount(nickname: String, email: String): String
    fun isEmailTaken(email: String): Boolean
    fun isNicknameTaken(nickname: String): Boolean
}