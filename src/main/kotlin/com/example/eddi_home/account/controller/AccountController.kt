package com.example.eddi_home.account.controller

import com.example.eddi_home.account.controller.request_form.AccountRequestForm
import com.example.eddi_home.account.controller.request_form.EmailRequestForm
import com.example.eddi_home.account.controller.request_form.NicknameRequestForm
import com.example.eddi_home.account.service.AccountService
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
    fun applyAccount(@RequestBody requestForm: AccountRequestForm): ResponseEntity<String> {
        val userToken = accountService.createAccount(requestForm.nickname, requestForm.email)
        return ResponseEntity(userToken, HttpStatus.CREATED)
    }

    @PostMapping("/check-email-duplication")
    fun checkEmailDuplication(@RequestBody emailRequestForm: EmailRequestForm): ResponseEntity<Boolean> {
        val isTaken = accountService.isEmailTaken(emailRequestForm.email)
        return ResponseEntity(isTaken, HttpStatus.OK)
    }

    @PostMapping("/check-nickname-duplication")
    fun checkNicknameDuplication(@RequestBody nicknameRequestForm: NicknameRequestForm): ResponseEntity<Boolean> {
        val isTaken = accountService.isNicknameTaken(nicknameRequestForm.nickname)
        return ResponseEntity(isTaken, HttpStatus.OK)
    }
}