package com.example.eddi_home.account.service

import com.example.eddi_home.account.entity.Account
import com.example.eddi_home.account.repository.AccountRepository
import com.example.eddi_home.redis.RedisCache
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val redisCache: RedisCache
) : AccountService {

    override fun createAccount(nickname: String, email: String) {
        val account = Account(nickname = nickname, email = email)
        accountRepository.save(account)

//        redisCache.setKeyAndValue("account:$nickname", email)
    }

    override fun isEmailTaken(email: String): Boolean {
        return accountRepository.findByEmail(email) != null
    }
}