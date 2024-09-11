package com.example.eddi_home.account.controller

import com.example.eddi_home.account.controller.request_form.EmailRequestForm
import com.example.eddi_home.account.controller.request_form.NicknameRequestForm
import com.example.eddi_home.account.service.AccountService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
class AccountControllerTest {

    @MockBean
    private lateinit var accountService: AccountService

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `이메일_중복되는_경우`() {
        val email = "test@example.com"
        `when`(accountService.isEmailTaken(email)).thenReturn(true)

        val emailRequest = EmailRequestForm(email)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/account/check-email-duplication")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(emailRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("true", result.response.contentAsString)
    }

    @Test
    fun `이메일_중복되지_않을_경우`() {
        val email = "test@example.com"
        `when`(accountService.isEmailTaken(email)).thenReturn(false)

        val emailRequest = EmailRequestForm(email)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/account/check-email-duplication")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(emailRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("false", result.response.contentAsString)
    }

    @Test
    fun `닉네임이_중복되는_경우`() {
        val nickname = "testNickname"
        `when`(accountService.isNicknameTaken(nickname)).thenReturn(true)

        val nicknameRequest = NicknameRequestForm(nickname)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/account/check-nickname-duplication")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(nicknameRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("true", result.response.contentAsString)
    }

    @Test
    fun `닉네임이_중복되지_않는_경우`() {
        val nickname = "testNickname"
        `when`(accountService.isNicknameTaken(nickname)).thenReturn(false)

        val nicknameRequest = NicknameRequestForm(nickname)
        val result = mockMvc.perform(MockMvcRequestBuilders.post("/account/check-nickname-duplication")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(nicknameRequest)))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        assertEquals("false", result.response.contentAsString)
    }
}
