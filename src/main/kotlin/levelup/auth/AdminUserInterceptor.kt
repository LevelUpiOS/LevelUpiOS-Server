package levelup.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import levelup.domain.UserRole
import levelup.log.RequestInfoHolder
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import support.logger
import java.nio.file.AccessDeniedException

@Component
class AdminUserInterceptor(
    private val tokenProvider: TokenProvider
) : HandlerInterceptor {
    companion object {
        private const val LOGIN_ID = "loginId"
        private const val LOGIN_PAGE_URL = "/admin/auth"
    }

    val log = logger().value

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try {
            val cookie = request.cookies?.find { it.name == TokenProvider.AUTH_TOKEN }
                ?: throw AuthenticationException("인증 쿠키가 존재하지 않습니다.")
                    .also { log.warn("[{}] 관리자 인증 실패", RequestInfoHolder.requestId, it) }
            if (!tokenProvider.isValidToken(cookie.value)) throw AuthenticationException("잘못된 인증 쿠키입니다.")
                .also { log.warn("[{}] 관리자 인증 실패", RequestInfoHolder.requestId, it) }
            if (!tokenProvider.hasAuthority(cookie.value, UserRole.ADMIN)) throw AccessDeniedException("권한이 없습니다.")
                .also { log.warn("[{}] 관리자 인증 실패", RequestInfoHolder.requestId, it) }

            val loginId = tokenProvider.getSubject(cookie.value)
                .also { log.info("[{}] 인증 완료 id: {}", RequestInfoHolder.requestId, it) }
            request.setAttribute(LOGIN_ID, loginId)
            return true
        } catch (e: AuthenticationException) {
            return redirectToLoginPage(response)
        } catch (e: AccessDeniedException) {
            return redirectToLoginPage(response)
        }
    }

    private fun redirectToLoginPage(response: HttpServletResponse): Boolean {
        response.sendRedirect(LOGIN_PAGE_URL)
        return false
    }
}