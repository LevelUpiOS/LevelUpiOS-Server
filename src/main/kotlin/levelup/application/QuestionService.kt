package levelup.application

import levelup.domain.BookmarkRepository
import levelup.domain.Question
import levelup.domain.QuestionRepository
import levelup.domain.UserRepository
import levelup.domain.delete
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional(readOnly = true)
class QuestionService(
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    @Transactional
    fun mark(userId: Long, questionId: Long) {
        val user = userRepository.find(userId)
        val question = questionRepository.find(questionId)
        bookmarkRepository.save(question.bookmark(user))
    }

    @Transactional
    fun unmark(userId: Long, questionId: Long) = bookmarkRepository.delete(userId, questionId)

    fun findWithSolution(questionId: Long) = questionRepository.findWithSolution(questionId)

    fun findAll() = questionRepository.findAll()

    fun findBookmarkQuestions(userId: Long) = questionRepository.findBookmarkQuestions(userId)

    fun findBookmarkQuestions(userId: Long, examId: Long) = questionRepository.findBookmarkQuestionsInExam(userId, examId)

    @Transactional
    fun update(questionId: Long, paragraph: String, answer: Any, explanation: String): Question {
        val question = questionRepository.find(questionId)
        question.update(paragraph, answer, explanation)
        return question
    }
}