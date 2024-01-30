package levelup.presentation.api

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import levelup.application.UserService
import levelup.auth.TokenProvider
import levelup.presentation.api.dto.UserAuthResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class UserAuthController(
    private val userService: UserService,
    private val tokenProvider: TokenProvider
) {
    @PostMapping
    fun generate(response: HttpServletResponse): ResponseEntity<UserAuthResponse> {
        val user = userService.generate()
        val token = tokenProvider.createToken(user.id.toString(), user.role)
        response.addCookie(Cookie(TokenProvider.AUTH_TOKEN, token))
        return ResponseEntity.ok(UserAuthResponse(token))
    }
}