package levelup.application.admin

import levelup.domain.ExamRepository
import levelup.domain.Question
import levelup.domain.QuestionRepository
import levelup.domain.createSolution
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class AdminQuestionService(
    private val examRepository: ExamRepository,
    private val questionRepository: QuestionRepository
) {
    fun create(examId: Long, paragraph: String, answer: Any, explanation: String): Question {
        val exam = examRepository.find(examId)
        return questionRepository.save(Question(exam, paragraph, createSolution(answer, explanation)))
    }

    fun delete(questionId: Long) {
        questionRepository.deleteById(questionId)
    }
}