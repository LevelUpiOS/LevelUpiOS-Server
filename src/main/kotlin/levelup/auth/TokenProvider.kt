package levelup.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import levelup.domain.UserRole
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

@ConfigurationProperties("jwt")
data class JwtKey(val key: String)

@Component
class TokenProvider(
    jwtKey: JwtKey
) {
    companion object {
        const val AUTH_TOKEN = "X_AUTH_TOKEN"
        const val ROLE = "role"
    }

    private val key = Keys.hmacShaKeyFor(jwtKey.key.toByteArray())

    fun createToken(payload: String, role: UserRole): String {
        val claims: Claims = Jwts.claims().setSubject(payload)
        return Jwts.builder()
            .setClaims(claims)
            .addClaims(mapOf(ROLE to role.name))
            .setIssuedAt(Date())
            .signWith(key)
            .compact()
    }

    fun getSubject(token: String): String = getClaims(token).body.subject

    fun isValidToken(token: String): Boolean {
        return try {
            getClaims(token)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun getClaims(token: String) = Jwts.parserBuilder()
        .setSigningKey(key.encoded)
        .build()
        .parseClaimsJws(token)
}