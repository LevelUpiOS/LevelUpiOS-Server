package levelup.application

import jakarta.persistence.EntityManager
import levelup.createCategory
import levelup.createExam
import levelup.createQuestion
import levelup.domain.CategoryRepository
import levelup.domain.ExamRepository
import levelup.domain.QuestionRepository
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ExamServiceTest(
    @Autowired private val examService: ExamService,
    @Autowired private val categoryRepository: CategoryRepository,
    @Autowired private val examRepository: ExamRepository,
    @Autowired private val questionRepository: QuestionRepository,
    @Autowired private val em: EntityManager
) {
    @Test
    fun `findWithQuestions() 실행 시 question을 함께 fetch한다`() {
        val category = categoryRepository.save(createCategory())
        val exam = examRepository.save(createExam(category = category))
        val question1 = questionRepository.save(createQuestion(exam = exam))
        val question2 = questionRepository.save(createQuestion(exam = exam))

        em.flush()
        em.clear()

        val findExam = examService.findWithQuestions(exam.id)
        assertThat(Hibernate.isInitialized(findExam.questions)).isTrue()
        assertThat(findExam.questions).hasSize(2)
    }
}