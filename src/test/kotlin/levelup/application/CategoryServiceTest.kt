package levelup.application

import jakarta.persistence.EntityManager
import levelup.createCategory
import levelup.createExam
import levelup.domain.CategoryRepository
import levelup.domain.ExamRepository
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class CategoryServiceTest(
    @Autowired private val categoryService: CategoryService,
    @Autowired private val categoryRepository: CategoryRepository,
    @Autowired private val examRepository: ExamRepository,
    @Autowired private val em: EntityManager,
) {
    @Test
    fun `findWithExam() 실행 시 exam도 fetch한다`() {
        val category1 = categoryRepository.save(createCategory())
        val category2 = categoryRepository.save(createCategory())

        val exam1 = examRepository.save(createExam(category = category1))
        val exam2 = examRepository.save(createExam(category = category2))

        em.flush()
        em.clear()

        val categories = categoryService.findWithExam()
        assertThat(Hibernate.isInitialized(categories[0].exams)).isTrue()
        assertThat(categories).hasSize(2)
    }
}