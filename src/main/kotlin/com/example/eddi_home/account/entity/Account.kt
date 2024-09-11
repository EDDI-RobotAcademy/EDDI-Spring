package com.example.eddi_home.account.entity

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val nickname: String,

    @Column(nullable = false, unique = true)
    val email: String
)
