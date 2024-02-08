package levelup.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import levelup.application.UserService
import levelup.auth.TokenProvider
import levelup.domain.UserRole
import levelup.presentation.api.dto.UserAuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import support.createCookie

@Tag(name = "Authentication", description = "User 인증 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
class UserAuthController(
    private val userService: UserService,
    private val tokenProvider: TokenProvider
) {
    @Operation(
        summary = "인증 토큰 생성",
        description = "앱을 처음 설치해서 인증 토큰이 없는 경우 호출, 새로운 유저를 생성해서 인증 토큰을 발급해서 전달"
    )
    @PostMapping
    fun generate(response: HttpServletResponse): ResponseEntity<UserAuthResponse> {
        val user = userService.generate()
        val token = tokenProvider.createToken(user.id.toString(), UserRole.USER)
        response.addCookie(createCookie(TokenProvider.AUTH_TOKEN, token, "/api"))
        return ResponseEntity.ok(UserAuthResponse(generateCookieHeader(token)))
    }

    private fun generateCookieHeader(token: String) = "${TokenProvider.AUTH_TOKEN}=$token"
}