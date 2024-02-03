package levelup.application

import levelup.domain.BookmarkRepository
import levelup.domain.QuestionRepository
import levelup.domain.UserRepository
import levelup.domain.delete
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class QuestionService(
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val bookmarkRepository: BookmarkRepository
) {
    fun mark(userId: Long, questionId: Long) {
        val user = userRepository.find(userId)
        val question = questionRepository.find(questionId)
        bookmarkRepository.save(question.bookmark(user))
    }

    fun unmark(userId: Long, questionId: Long) = bookmarkRepository.delete(userId, questionId)
}