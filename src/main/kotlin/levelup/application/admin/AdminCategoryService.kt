package levelup.application.admin

import levelup.domain.Category
import levelup.domain.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

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

    fun update(categoryId: Long, name: String, description: String) {
        val category = categoryRepository.find(categoryId)
        category.update(name, description)
    }
}