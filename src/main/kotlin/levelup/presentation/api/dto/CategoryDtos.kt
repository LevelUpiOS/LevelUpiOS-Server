package levelup.presentation.api.dto

import levelup.domain.Category
import levelup.domain.Exam

data class CategoryListResponse(private val _categories: List<Category>) {
    val categories: List<CategoryResponse> = _categories.map { CategoryResponse(it.exams) }
}

data class CategoryResponse(private val _exams: List<Exam>) {
    val exams: List<ExamResponse> = _exams.map { ExamResponse(it.id, it.name) }
}