package levelup.application.admin

import levelup.domain.Category
import levelup.domain.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AdminCategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun create(name: String, description: String) =
        categoryRepository.save(Category(name = name, description = description))

    fun delete(categoryId: Long) {
        categoryRepository.deleteById(categoryId)
    }
}