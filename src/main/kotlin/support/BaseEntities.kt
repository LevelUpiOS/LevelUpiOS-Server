package support

import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    abstract val id: Long

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as BaseTimeEntity
        return id == other.id
    }

    override fun hashCode() = id.hashCode()
}

@MappedSuperclass
abstract class BaseTimeEntity(
    @CreatedDate
    open val createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    open val updatedAt: LocalDateTime = createdAt,
) : BaseEntity()

@MappedSuperclass
abstract class SoftDeleteEntity(
    private val deletedAt: LocalDateTime? = null
) : BaseTimeEntity()