package support

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseTimeEntity(
    @CreatedDate
    open val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    open val updatedAt: LocalDateTime = createdAt
)

@MappedSuperclass
abstract class SoftDeleteEntity(
    private val deletedAt: LocalDateTime? = null
) : BaseTimeEntity()