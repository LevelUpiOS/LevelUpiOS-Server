package support

import jakarta.servlet.http.Cookie

fun createCookie(name: String, value: String, path: String = "/", age: Int? = null) =
    Cookie(name, value).apply {
        this.path = path
        age?.let { this.maxAge = age }
    }

fun invalidateCookie(cookie: Cookie?, path: String = "/") = cookie?.apply {
    this.path = path
    this.maxAge = 0
}