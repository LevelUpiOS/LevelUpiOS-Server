package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Exam(
    val name: String,

    @OneToMany(mappedBy = "exam")
    val questions: MutableList<Question> = mutableListOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    val id: Long = 0L
) {
    fun add(question: Question) = questions.add(question)

    fun mark(user: User, answers: List<Any>): Submission {
        check(questions.isNotEmpty()) { "시험 문제가 없습니다." }
        require(questions.size == answers.size) { "정답의 길이가 맞지 않습니다." }

        return Submission(user).apply {
            this.answers = (questions zip answers).map { (question, answer) ->
                require(question.isCompatible(answer)) { "문제의 타입과 맞지 않습니다." }
                createAnswer(this, question, answer)
            }.toMutableList()
        }
    }
}