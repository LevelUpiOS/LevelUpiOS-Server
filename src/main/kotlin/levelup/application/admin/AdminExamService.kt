package levelup.application.admin

import levelup.domain.CategoryRepository
import levelup.domain.Exam
import levelup.domain.ExamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class AdminExamService(
    private val categoryRepository: CategoryRepository,
    private val examRepository: ExamRepository
) {
    fun create(categoryId: Long, name: String): Exam {
        val category = categoryRepository.find(categoryId)
        return examRepository.save(Exam(category, name))
    }
}