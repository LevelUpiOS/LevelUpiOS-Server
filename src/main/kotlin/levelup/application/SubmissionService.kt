package levelup.application

import levelup.domain.SubmissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SubmissionService(
    private val submissionRepository: SubmissionRepository
) {
    fun findWithAnswers(submissionId: Long) = submissionRepository.findWithAnswers(submissionId)

    fun findLatestSubmissions(userId: Long) = submissionRepository.findLatestSubmissionsByUser(userId)
}