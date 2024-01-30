package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Solution(
    val explanation: String = "",

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    val id: Long = 0L
) {
    abstract val answer: Any
}

@Entity
@Table(name = "ox_solution")
class OXSolution(
    override val answer: Boolean,
    explanation: String = "",
    id: Long = 0L
) : Solution(explanation, id)