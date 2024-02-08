package levelup.presentation.admin

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import levelup.application.CategoryService
import levelup.application.admin.AdminUserService
import levelup.auth.TokenProvider
import levelup.domain.UserRole
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import support.createCookie
import support.invalidateCookie

private const val ADMIN_BASE_PATH = "/admin"

@Controller
@RequestMapping(ADMIN_BASE_PATH)
class AdminUserController(
    private val tokenProvider: TokenProvider,
    private val adminUserService: AdminUserService,
    private val categoryService: CategoryService
) {
    companion object {
        private const val ADMIN_ID = "ADMIN"
        private const val AUTHENTICATE_PATH = "/auth"
        private const val LOGOUT_PATH = "/logout"
    }

    @GetMapping(AUTHENTICATE_PATH)
    fun loginForm() = "admin-login"

    @PostMapping(AUTHENTICATE_PATH)
    fun login(
        @RequestParam("password") password: String,
        response: HttpServletResponse,
        model: Model
    ): String {
        return if (adminUserService.login(password)) {
            val token = tokenProvider.createToken(ADMIN_ID, UserRole.ADMIN)
            response.addCookie(createCookie(TokenProvider.AUTH_TOKEN, token, ADMIN_BASE_PATH))
            "redirect:$ADMIN_BASE_PATH"
        } else {
            model.addAttribute("loginError", true)
            "admin-login"
        }
    }

    @PostMapping("$AUTHENTICATE_PATH$LOGOUT_PATH")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        request.cookies.find { it.name == TokenProvider.AUTH_TOKEN }
            ?.let { response.addCookie(invalidateCookie(it, ADMIN_BASE_PATH)) }
        return "redirect:$ADMIN_BASE_PATH$AUTHENTICATE_PATH"
    }

    @GetMapping
    fun home(model: Model): String {
        val categories = categoryService.findWithExam()
        model.addAttribute("categories", categories)
        return "admin-home"
    }
}