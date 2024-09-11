package com.example.eddi_home.account.repository

import com.example.eddi_home.account.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Account?
    fun findByNickname(nickname: String): Account?
}