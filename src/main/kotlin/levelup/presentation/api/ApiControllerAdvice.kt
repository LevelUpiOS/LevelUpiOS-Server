package levelup.presentation.api

import jakarta.servlet.http.HttpServletRequest
import levelup.log.RequestInfoHolder
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import support.logger

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptions(e: IllegalArgumentException, request: HttpServletRequest) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(HttpStatus.BAD_REQUEST, e, request))

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptions(e: AuthenticationException, request: HttpServletRequest) =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse(HttpStatus.UNAUTHORIZED, e, request))

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementExceptions(e: NoSuchElementException, request: HttpServletRequest) =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ExceptionResponse(HttpStatus.NOT_FOUND, e, request))

    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateExceptions(e: IllegalStateException, request: HttpServletRequest) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e, request))
}

data class ExceptionResponse(
    private val _status: HttpStatus,
    private val error: Exception,
    private val request: HttpServletRequest
) {
    val id: String = RequestInfoHolder.requestId
    val status: String = _status.name
    val statusCode: Int = _status.value()
    val message: String? = error.localizedMessage
    private val log = logger().value

    init {
        when {
            _status.is4xxClientError -> log.warn("[{}] <{} {}> {}", id, statusCode, status, message)
            _status.is5xxServerError -> log.error("[{}] <{} {}> {}", id, statusCode, status, message)
        }
    }
}