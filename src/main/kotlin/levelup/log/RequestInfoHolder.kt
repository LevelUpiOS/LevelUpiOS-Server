package levelup.log

import org.springframework.stereotype.Component

@Component
object RequestInfoHolder {
    private val _requestId = ThreadLocal<String>()
    private val _requestAt = ThreadLocal<Long>()

    val requestId: String
        get() = _requestId.get()

    val requestAt: Long
        get() = _requestAt.get()

    fun set(requestId: String, requestAt: Long) {
        _requestId.set(requestId)
        _requestAt.set(requestAt)
    }

    fun clear() {
        _requestId.remove()
        _requestAt.remove()
    }
}