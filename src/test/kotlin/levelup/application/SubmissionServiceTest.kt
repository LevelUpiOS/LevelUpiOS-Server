package levelup.application

import jakarta.persistence.EntityManager
import levelup.createCategory
import levelup.createExam
import levelup.createQuestion
import levelup.createUser
import levelup.domain.CategoryRepository
import levelup.domain.ExamRepository
import levelup.domain.QuestionRepository
import levelup.domain.SubmissionRepository
import levelup.domain.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class SubmissionServiceTest(
    @Autowired private val submissionService: SubmissionService,
    @Autowired private val categoryRepository: CategoryRepository,
    @Autowired private val examRepository: ExamRepository,
    @Autowired private val questionRepository: QuestionRepository,
    @Autowired private val submissionRepository: SubmissionRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val em: EntityManager
) {
    @Test
    fun `findWithAnswers() 시 answers를 함께 fetch한다`() {
        val category = categoryRepository.save(createCategory())
        val exam = examRepository.save(createExam(category = category))
        questionRepository.save(exam.add(createQuestion(exam = exam)))
        questionRepository.save(exam.add(createQuestion(exam = exam)))
        val user = userRepository.save(createUser())
        val submission = submissionRepository.save(exam.mark(user, listOf(true, false)))

        em.flush()
        em.clear()

        val find = submissionService.findWithAnswers(submission.id)
        assertThat(Hibernate.isInitialized(find.answers))
        assertThat(find.answers).hasSize(2)
    }

    @Test
    fun `findLatestSubmissions() 시 Exam마다 최근 Submission을 반환한다`() {
        val category = categoryRepository.save(createCategory())
        val exam = examRepository.save(createExam(category = category))
        questionRepository.save(exam.add(createQuestion(exam = exam)))
        questionRepository.save(exam.add(createQuestion(exam = exam)))
        val user = userRepository.save(createUser())
        val submission1 = submissionRepository.save(exam.mark(user, listOf(true, false)))
        val submission2 = submissionRepository.save(exam.mark(user, listOf(true, false)))
        val submission3 = submissionRepository.save(exam.mark(user, listOf(true, false)))

        em.flush()
        em.clear()

        val submissions = submissionService.findLatestSubmissions(user.id)
        assertThat(Hibernate.isInitialized(submissions[0].exam))
        assertThat(submissions.map { it }).containsExactly(submission3)
    }
}