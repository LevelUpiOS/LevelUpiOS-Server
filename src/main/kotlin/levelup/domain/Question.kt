package levelup.domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@SQLDelete(sql = "UPDATE question SET deleted_at = NOW() WHERE question_id = ?")
@SQLRestriction("deleted_at IS NULL")
class Question(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    val exam: Exam,

    val paragraph: String,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "solution_id")
    val solution: Solution,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    override val id: Long = 0L
) : SoftDeleteEntity() {
    fun isCompatible(answer: Any) = solution.answer::class == answer::class

    fun bookmark(user: User) = Bookmark(user, this)
}