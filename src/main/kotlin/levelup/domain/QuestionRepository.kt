package levelup.domain

import org.springframework.data.jpa.repository.Query
import support.EntityRepository

interface QuestionRepository : EntityRepository<Question, Long> {
    @Query("SELECT DISTINCT e " +
            "FROM Exam e " +
            "LEFT JOIN FETCH e.questions q " +
            "LEFT JOIN FETCH q.solution s " +
            "WHERE e.id = :examId")
    fun findWithQuestions(examId: Long): Exam

    @Query("SELECT q " +
            "FROM Question q " +
            "JOIN FETCH q.exam e " +
            "JOIN FETCH e.category c " +
            "JOIN FETCH q.solution s ")
    override fun findAll(): List<Question>

    @Query("SELECT q " +
            "FROM Question q " +
            "JOIN FETCH q.exam e " +
            "JOIN FETCH e.category c " +
            "JOIN FETCH q.solution s " +
            "WHERE q.id IN " +
            "(SELECT b.question.id " +
            "FROM Bookmark b " +
            "WHERE b.user.id = :userId)")
    fun findBookmarkQuestions(userId: Long): List<Question>

    @Query("SELECT q " +
            "FROM Question q " +
            "WHERE q.exam.id = :examId " +
            "AND q.id IN " +
            "(SELECT b.question.id " +
            "FROM Bookmark b " +
            "WHERE b.user.id = :userId)")
    fun findBookmarkQuestionsInExam(userId: Long, examId: Long): Set<Question>

    @Query("SELECT q " +
            "FROM Question q " +
            "JOIN FETCH q.solution " +
            "WHERE q.id = :questionId")
    fun findWithSolution(questionId: Long): Question
}