package levelup.application

import levelup.domain.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun findWithExam() = categoryRepository.findAllWithExams()
}