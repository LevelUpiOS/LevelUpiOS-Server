package levelup.presentation.api

import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class ApiControllerAdvice {
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentExceptions(e: IllegalArgumentException) =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(HttpStatus.BAD_REQUEST, e))

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptions(e: AuthenticationException) =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse(HttpStatus.UNAUTHORIZED, e))

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementExceptions(e: NoSuchElementException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ExceptionResponse(HttpStatus.NOT_FOUND, e))

    @ExceptionHandler(IllegalStateException::class)
    fun illegalStateExceptions(e: IllegalStateException) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e))
}

data class ExceptionResponse(
    private val _status: HttpStatus,
    private val error: Exception
) {
    val id: String = UUID.randomUUID().toString().substring(0, 8)
    val status: String = _status.name
    val statusCode: Int = _status.value()
    val message: String? = error.localizedMessage
}