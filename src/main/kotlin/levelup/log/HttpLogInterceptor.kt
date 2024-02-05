package levelup.log

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import support.IDGenerator
import support.logger
import java.util.*

@Component
class HttpLogInterceptor : HandlerInterceptor {
    private val log = logger().value

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestId = IDGenerator.generate(8)
        RequestInfoHolder.set(requestId, System.currentTimeMillis())

        requestLog(requestId, request.method, request.requestURI)
        requestParamLog(requestId, request.parameterMap)
        requestHeaderLog(requestId, request.headerNames, request)

        return true
    }

    private fun requestLog(requestId: String, method: String, uri: String) =
        log.info("[{}] {} {}", requestId, method, uri)

    private fun requestParamLog(requestId: String, params: Map<String, Array<String>>) =
        log.info("[{}] parameters: {}", requestId, params.decorateParam())

    private fun requestHeaderLog(requestId: String, headers: Enumeration<String>, request: HttpServletRequest) =
        log.info(
            "[{}] headers: {}",
            requestId,
            headers.toList().associateWith { request.getHeader(it) }.decorateHeader()
        )

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        requestTimeLog(RequestInfoHolder.requestId, RequestInfoHolder.requestAt)
        RequestInfoHolder.clear()
    }

    private fun requestTimeLog(requestId: String, startTime: Long) =
        log.info("[{}] 응답 시간: {}ms", requestId, System.currentTimeMillis() - startTime)
}

private fun Map<String, String>.decorateHeader() =
    map { "${it.key}: ${it.value}" }.joinToString(separator = ", ", prefix = "{", postfix = "}")

private fun Map<String, Array<String>>.decorateParam() =
    map { "${it.key}: ${it.value.joinToString(separator = ", ", prefix = "[", postfix = "]")}" }
        .joinToString(separator = ", ", prefix = "{", postfix = "}")