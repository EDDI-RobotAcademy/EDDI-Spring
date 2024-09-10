package com.example.eddi_home.github_authentication.controller

import com.example.eddi_home.github_authentication.service.GithubAuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/github")
class GithubAuthenticationController(
    private val githubAuthenticationService: GithubAuthenticationService
) {

    @GetMapping("/access-token")
    fun handleGithubRedirect(@RequestParam code: String?): ResponseEntity<Any> {
        return if (code != null) {
            val accessToken: String = githubAuthenticationService.requestAccessTokenWithAuthorizeCode(code)
            println("AccessToken: $accessToken")
            // 액세스 토큰을 사용하여 필요한 후속 작업 수행
//            ResponseEntity.ok(userInfo)
            ResponseEntity.ok("Access Token: $accessToken")
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization code is missing")
        }
    }
}