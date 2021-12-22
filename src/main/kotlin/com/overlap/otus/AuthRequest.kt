package com.overlap.otus

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

data class AuthRequest(private val user: UserRequest) : Authentication {
    override fun getName(): String {
        return user.login
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getCredentials(): Any {
        return user.password
    }

    override fun getDetails(): Any {
        return ""
    }

    override fun getPrincipal(): Any {
        return user.login
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {

    }
}