package levelup.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import levelup.auth.TokenProvider.Companion.AUTH_TOKEN
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class LoginUserInterceptor(
    private val tokenProvider: TokenProvider
) : HandlerInterceptor {
    companion object {
        private const val LOGIN_ID = "loginId"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val cookie = request.cookies?.find { it.name == AUTH_TOKEN }
            ?: throw AuthenticationException("인증 쿠키가 존재하지 않습니다.")
        if (!tokenProvider.isValidToken(cookie.value)) throw AuthenticationException("잘못된 인증 쿠키입니다.")
        request.setAttribute(LOGIN_ID, tokenProvider.getSubject(cookie.value))
        return true
    }
}