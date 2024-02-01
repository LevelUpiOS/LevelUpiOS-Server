package levelup.presentation.api.dto

import levelup.domain.Category

data class CategoryListResponse(private val _categories: List<Category>) {
    val categories: List<CategoryResponse> = _categories.map { CategoryResponse(it) }
}

data class CategoryResponse(
    val id: Long,
    val name: String,
    val description: String,
    val exams: List<ExamResponse>
) {
    constructor(category: Category) : this(
        id = category.id,
        name = category.name,
        description = category.description,
        exams = category.exams.map { ExamResponse(it.id, it.name) }
    )
}