package levelup.application.admin

import levelup.domain.CategoryRepository
import levelup.domain.Exam
import levelup.domain.ExamRepository
import levelup.domain.QuestionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import support.find

@Service
@Transactional
class AdminExamService(
    private val categoryRepository: CategoryRepository,
    private val examRepository: ExamRepository,
    private val questionRepository: QuestionRepository
) {
    fun create(categoryId: Long, name: String): Exam {
        val category = categoryRepository.find(categoryId)
        return examRepository.save(Exam(category, name))
    }

    fun update(examId: Long, name: String): Exam {
        val exam = examRepository.find(examId)
        exam.update(name)
        return exam
    }

    fun delete(examId: Long) {
        questionRepository.deleteByExamId(examId)
        examRepository.deleteById(examId)
    }
}