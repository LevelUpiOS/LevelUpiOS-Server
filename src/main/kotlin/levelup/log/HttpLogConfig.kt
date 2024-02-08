package levelup.log

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class HttpLogConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(HttpLogInterceptor()).order(1)
            .addPathPatterns("/api/**")
            .addPathPatterns("/admin/**")
    }
}