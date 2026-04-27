package com.example.balancewise.data.local.entity

@Entity
data class User(
    @PrimaryKey val username: String,
    val password: String
)