package levelup.auth

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthenticationConfig(private val tokenProvider: TokenProvider) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginUserInterceptor(tokenProvider))
            .addPathPatterns("/api/**")
            .excludePathPatterns("/api/v*/auth")
            .order(2)

        registry.addInterceptor(AdminUserInterceptor(tokenProvider))
            .addPathPatterns("/admin")
            .excludePathPatterns("/admin/auth")
            .order(3)
    }
}