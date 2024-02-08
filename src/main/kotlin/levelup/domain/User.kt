package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.boot.context.properties.ConfigurationProperties
import support.SoftDeleteEntity

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE user_id = ?")
@SQLRestriction("deleted_at IS NULL")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    override val id: Long = 0L
) : SoftDeleteEntity()

enum class UserRole { USER, ADMIN }

@ConfigurationProperties("admin")
data class AdminInfo(private val password: String) {
    fun isValid(password: String) = this.password == password
}