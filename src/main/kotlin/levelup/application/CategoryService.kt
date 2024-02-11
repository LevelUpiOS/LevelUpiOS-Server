package levelup.application

import levelup.domain.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional(readOnly = true)
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun findWithExam() = categoryRepository.findAllWithExams()

    fun find(categoryId: Long) = categoryRepository.find(categoryId)
}