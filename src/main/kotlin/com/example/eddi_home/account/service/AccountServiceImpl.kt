package com.example.eddi_home.account.service

import com.example.eddi_home.account.entity.Account
import com.example.eddi_home.account.repository.AccountRepository
import com.example.eddi_home.redis.RedisCache
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val redisCache: RedisCache
) : AccountService {

    override fun createAccount(nickname: String, email: String): String {
        val account = Account(nickname = nickname, email = email)
        accountRepository.save(account)

        val userToken = UUID.randomUUID().toString()
        redisCache.setKeyAndValue(userToken, account.id.toString())

        return userToken
    }

    override fun isEmailTaken(email: String): Boolean {
        return accountRepository.findByEmail(email) != null
    }

    override fun isNicknameTaken(nickname: String): Boolean {
        return accountRepository.findByNickname(nickname) != null
    }
}