package levelup.domain

import org.springframework.data.jpa.repository.Query
import support.EntityRepository

interface QuestionRepository : EntityRepository<Question, Long> {
    @Query("SELECT DISTINCT e " +
            "FROM Exam e " +
            "JOIN FETCH e.questions " +
            "WHERE e.id = :examId")
    fun findWithQuestions(examId: Long): Exam
}