package support

import java.util.*

object IDGenerator {
    fun generate(length: Int = 36) = UUID.randomUUID().toString().substring(0, length)
}