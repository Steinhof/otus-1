package com.overlap.otus

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(private val userDetailsService: UserDetailsService) {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers("/signup", "/users", "/public/**").permitAll()
            .anyExchange().authenticated()
            .and()
            .formLogin()
            .and()
            .build()
    }

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        val authenticationManager = UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)
        authenticationManager.setPasswordEncoder(NoOpPasswordEncoder.getInstance())

        return authenticationManager
    }
}
