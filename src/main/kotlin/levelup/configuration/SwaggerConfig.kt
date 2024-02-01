package levelup.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import levelup.auth.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(
            Components().addSecuritySchemes(
                TokenProvider.AUTH_TOKEN,
                SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            )
        )
        .info(apiInfo)

    private val apiInfo = Info()
        .title("LevelUpiOS API Docs")
        .version("0.1.0")
}