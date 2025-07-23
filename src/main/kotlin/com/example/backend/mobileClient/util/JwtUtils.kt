package com.example.backend.mobileClient.util

import com.example.backend.mobileClient.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtils(private val jwtProperties: JwtProperties) {
    private val key: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())

    fun generateToken(userDetails: UserDetails): String {
        println(jwtProperties)
        return Jwts.builder()
            .setSubject(userDetails.username)
            .setIssuer(jwtProperties.issuer)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.expirationMs))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String? {
        return parseToken(token)?.body?.subject
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return parseToken(token)?.body?.expiration?.before(Date()) ?: true
    }

    fun parseToken(token: String): Jws<Claims>? {
        return try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        } catch (e: Exception) {
            null
        }
    }
}