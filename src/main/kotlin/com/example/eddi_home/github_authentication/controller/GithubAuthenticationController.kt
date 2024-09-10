package com.example.eddi_home.github_authentication.controller

import com.example.eddi_home.github_authentication.controller.request_form.AccessTokenRequestForm
import com.example.eddi_home.github_authentication.service.GithubAuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/github")
class GithubAuthenticationController(
    private val githubAuthenticationService: GithubAuthenticationService
) {

    @PostMapping("/access-token")
    fun handleGithubRedirect(@RequestBody form: AccessTokenRequestForm): ResponseEntity<Any> {
        return try {
            val code = form.code
            val accessToken = githubAuthenticationService.requestAccessTokenWithAuthorizeCode(code)
            println("AccessToken: $accessToken")

            ResponseEntity.ok(userInfo)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process access token")
        }
    }
}