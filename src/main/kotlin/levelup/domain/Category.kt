package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@SQLDelete(sql = "UPDATE category SET deleted_at = NOW() WHERE category_id = ?")
@SQLRestriction("deleted_at IS NULL")
class Category(
    var name: String,
    var description: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long = 0L
) : SoftDeleteEntity()