package levelup.application.admin

import levelup.domain.Category
import levelup.domain.CategoryRepository
import levelup.domain.ExamRepository
import levelup.domain.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class AdminCategoryService(
    private val categoryRepository: CategoryRepository,
    private val examRepository: ExamRepository,
    private val questionRepository: QuestionRepository
) {
    fun create(name: String, description: String) =
        categoryRepository.save(Category(name = name, description = description))

    fun delete(categoryId: Long) {
        questionRepository.deleteByCategoryId(categoryId)
        examRepository.deleteByCategoryId(categoryId)
        categoryRepository.deleteById(categoryId)
    }

    fun update(categoryId: Long, name: String, description: String) {
        val category = categoryRepository.find(categoryId)
        category.update(name, description)
    }
}