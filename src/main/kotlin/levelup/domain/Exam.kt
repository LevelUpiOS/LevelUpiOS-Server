package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@SQLDelete(sql = "UPDATE exam SET deleted_at = NOW() WHERE exam_id = ?")
@SQLRestriction("deleted_at IS NULL")
class Exam(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: Category,

    val name: String,

    @OneToMany(mappedBy = "exam")
    val questions: MutableList<Question> = mutableListOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    val id: Long = 0L
) : SoftDeleteEntity() {
    fun add(question: Question) = questions.add(question)

    fun mark(user: User, answers: List<Any>): Submission {
        check(questions.isNotEmpty()) { "시험 문제가 없습니다." }
        require(questions.size == answers.size) { "정답의 길이가 맞지 않습니다." }

        return Submission(user, this).apply {
            this.answers = (questions zip answers).map { (question, answer) ->
                require(question.isCompatible(answer)) { "문제의 타입과 맞지 않습니다." }
                createAnswer(this, question, answer)
            }.toMutableList()
        }
    }
}