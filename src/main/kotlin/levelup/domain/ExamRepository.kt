package levelup.domain

import org.springframework.data.jpa.repository.Query
import support.EntityRepository

interface ExamRepository : EntityRepository<Exam, Long> {
    @Query("SELECT e " +
            "FROM Exam e " +
            "JOIN FETCH e.questions q " +
            "JOIN FETCH q.solution s " +
            "WHERE e.id = :examId")
    fun findWithSolution(examId: Long): Exam
}