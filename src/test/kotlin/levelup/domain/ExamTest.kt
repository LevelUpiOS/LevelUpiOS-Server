package levelup.domain

import levelup.createExam
import levelup.createQuestion
import levelup.createUser
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test

class ExamTest {
    @Test
    fun `시험지를 채점한다`() {
        val exam = createExam()
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))

        assertThat(exam.mark(createUser(), listOf(true, false, true, false)).score)
            .isCloseTo(50.0, Offset.offset(0.1))
    }

    @Test
    fun `시험 문제가 없는 경우 오류를 발생한다`() {
        val exam = createExam()

        assertThatThrownBy { exam.mark(createUser(), listOf()) }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun `답안의 숫자가 맞지 않는 경우 오류를 발생한다`() {
        val exam = createExam()
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))

        assertThatThrownBy { exam.mark(createUser(), listOf(true, false)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `답안의 타입이 맞지 않는 경우 오류를 발생한다`() {
        val exam = createExam()
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))
        exam.add(createQuestion(exam = exam))

        assertThatThrownBy { exam.mark(createUser(), listOf(true, false, 1, "Optional")) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}