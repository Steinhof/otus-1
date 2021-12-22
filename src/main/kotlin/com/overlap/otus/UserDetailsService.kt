package com.overlap.otus

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsService(private val users: UsersRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String): Mono<UserDetails> {
        val user = users.findByLogin(username) ?: throw UsernameNotFoundException("No user found with login: $username")

        return Mono.just(
            User(
                user.login,
                user.password,
                listOf()
            )
        )
    }

//    private fun getAuthorities(roles: List<String>): List<GrantedAuthority>? {
//        val authorities: MutableList<GrantedAuthority> = ArrayList()
//        for (role in roles) {
//            authorities.add(SimpleGrantedAuthority(role))
//        }
//        return authorities
//    }
}
