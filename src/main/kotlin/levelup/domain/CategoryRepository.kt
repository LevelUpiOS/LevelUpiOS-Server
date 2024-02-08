package levelup.domain

import org.springframework.data.jpa.repository.Query
import support.EntityRepository

interface CategoryRepository : EntityRepository<Category, Long> {
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.exams")
    fun findAllWithExams(): List<Category>
}