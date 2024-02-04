package levelup.domain

import levelup.createCategory
import levelup.createExam
import levelup.createQuestion
import levelup.createUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class QuestionRepositoryTest(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val categoryRepository: CategoryRepository,
    @Autowired private val examRepository: ExamRepository,
    @Autowired private val questionRepository: QuestionRepository,
    @Autowired private val bookmarkRepository: BookmarkRepository
) {
    lateinit var user1: User
    lateinit var user2: User

    lateinit var category: Category
    lateinit var exam1: Exam
    lateinit var exam2: Exam

    lateinit var question1: Question
    lateinit var question2: Question
    lateinit var question3: Question
    lateinit var question4: Question
    lateinit var question5: Question
    lateinit var question6: Question

    @BeforeEach
    fun beforeEach() {
        user1 = userRepository.save(createUser())
        user2 = userRepository.save(createUser())

        category = categoryRepository.save(createCategory())
        exam1 = examRepository.save(createExam(category = category))

        question1 = questionRepository.save(createQuestion(exam = exam1))
        question2 = questionRepository.save(createQuestion(exam = exam1))
        question3 = questionRepository.save(createQuestion(exam = exam1))
        bookmarkRepository.save(question1.bookmark(user1))
        bookmarkRepository.save(question2.bookmark(user2))
        bookmarkRepository.save(question3.bookmark(user1))
        bookmarkRepository.save(question3.bookmark(user2))

        exam2 = examRepository.save(createExam(category = category))
        question4 = questionRepository.save(createQuestion(exam = exam2))
        question5 = questionRepository.save(createQuestion(exam = exam2))
        question6 = questionRepository.save(createQuestion(exam = exam2))
        bookmarkRepository.save(question4.bookmark(user1))
        bookmarkRepository.save(question5.bookmark(user2))
        bookmarkRepository.save(question6.bookmark(user1))
        bookmarkRepository.save(question6.bookmark(user2))
    }

    @Test
    fun `북마크 표시된 문제를 찾는다`() {
        assertThat(questionRepository.findBookmarkQuestions(user2.id))
            .containsExactly(question2, question3, question5, question6)
    }

    @Test
    fun `시험 문제 중 북마크 표시된 문제를 찾는다`() {
        assertThat(questionRepository.findBookmarkQuestionsInExam(user2.id, exam2.id))
            .containsExactly(question5, question6)
    }
}