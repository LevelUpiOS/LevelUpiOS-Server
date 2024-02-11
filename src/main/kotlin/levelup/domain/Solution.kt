package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE solution SET deleted_at = NOW() WHERE solution_id = ?")
@SQLRestriction("deleted_at IS NULL")
abstract class Solution(
    var explanation: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    override val id: Long = 0L
) : SoftDeleteEntity() {
    abstract val answer: Any
    abstract fun update(answer: Any, explanation: String)
}

@Entity
@Table(name = "ox_solution")
class OXSolution(
    override var answer: Boolean,
    explanation: String = "",
    id: Long = 0L
) : Solution(explanation, id) {
    override fun update(answer: Any, explanation: String) {
        require(answer is Boolean) { "문제의 타입과 맞지 않습니다." }
        this.answer = answer
        this.explanation = explanation
    }
}

inline fun <reified T> createSolution(answer: T, explanation: String) = when (answer) {
    is Boolean -> OXSolution(answer, explanation)
    else -> throw IllegalArgumentException("지원하지 않는 정답 타입입니다. ${T::class.simpleName}")
}