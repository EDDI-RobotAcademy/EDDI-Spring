package com.example.eddi_home.account.controller

import com.example.eddi_home.account.controller.request_form.AccountRequestForm
import com.example.eddi_home.account.controller.request_form.EmailRequest
import com.example.eddi_home.account.service.AccountService
import com.example.eddi_home.redis.RedisCache
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountService: AccountService,
) {

    @PostMapping("/apply")
    fun applyAccount(@RequestBody request: AccountRequestForm): ResponseEntity<String> {
        accountService.createAccount(request.nickname, request.email)
        return ResponseEntity("Account created successfully", HttpStatus.CREATED)
    }
}