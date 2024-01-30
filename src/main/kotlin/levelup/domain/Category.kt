package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@SQLDelete(sql = "UPDATE category SET deleted_at = NOW() WHERE category_id = ?")
@SQLRestriction("deleted_at IS NULL")
class Category(
    var name: String,
    var description: String = "",

    @OneToMany(mappedBy = "category")
    val exams: List<Exam> = listOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    override val id: Long = 0L
) : SoftDeleteEntity()