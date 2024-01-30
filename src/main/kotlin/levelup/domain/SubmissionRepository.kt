package levelup.domain

import org.springframework.data.jpa.repository.Query
import support.EntityRepository

interface SubmissionRepository : EntityRepository<Submission, Long> {
    @Query("SELECT DISTINCT s " +
            "FROM Submission s " +
            "JOIN FETCH s.exam e " +
            "JOIN FETCH s.answers a " +
            "JOIN FETCH a.question q " +
            "JOIN FETCH q.solution " +
            "WHERE s.id " +
            "IN " +
            "(SELECT MAX (s.id) " +
            "FROM Submission s " +
            "GROUP BY s.exam)")
    fun findLatestSubmissionsByUser(userId: Long): List<Submission>

    @Query("SELECT DISTINCT s " +
            "FROM Submission s " +
            "JOIN FETCH s.answers a " +
            "JOIN FETCH a.question q " +
            "JOIN FETCH q.solution " +
            "WHERE s.id = :submissionId")
    fun findWithAnswers(submissionId: Long): Submission
}