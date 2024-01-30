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
    val explanation: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    val id: Long = 0L
) : SoftDeleteEntity() {
    abstract val answer: Any
}

@Entity
@Table(name = "ox_solution")
class OXSolution(
    override val answer: Boolean,
    explanation: String = "",
    id: Long = 0L
) : Solution(explanation, id)