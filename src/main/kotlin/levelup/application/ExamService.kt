package levelup.application

import levelup.domain.ExamRepository
import levelup.domain.QuestionRepository
import levelup.domain.SubmissionRepository
import levelup.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class ExamService(
    private val userRepository: UserRepository,
    private val examRepository: ExamRepository,
    private val questionRepository: QuestionRepository,
    private val submissionRepository: SubmissionRepository
) {
    fun findWithQuestions(examId: Long) = questionRepository.findWithQuestions(examId)

    fun mark(userId: Long, examId: Long, answers: List<Any>) = submissionRepository.save(
        examRepository.find(examId).mark(userRepository.find(userId), answers)
    )
}