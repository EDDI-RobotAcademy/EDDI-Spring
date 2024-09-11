package com.example.eddi_home.account.service

interface AccountService {
    fun createAccount(nickname: String, email: String)
    fun isEmailTaken(email: String): Boolean
}