package levelup.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.transaction.NotSupportedException
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import support.SoftDeleteEntity

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE answer SET deleted_at = NOW() WHERE answer_id = ?")
@SQLRestriction("deleted_at IS NULL")
abstract class Answer(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    val submission: Submission,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    val question: Question,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    override val id: Long = 0L
) : SoftDeleteEntity() {
    abstract val guess: Any
    val solution get() = question.solution
    val isCorrect get() = solution.answer == guess
}

@Entity
@Table(name = "ox_answer")
class OXAnswer(
    submission: Submission,
    question: Question,
    override val guess: Boolean
) : Answer(submission, question)

inline fun <reified T> createAnswer(submission: Submission, question: Question, guess: T) = when (guess) {
    is Boolean -> OXAnswer(submission, question, guess)
    else -> throw IllegalArgumentException("지원하지 않는 정답 타입입니다. ${T::class.simpleName}")
}