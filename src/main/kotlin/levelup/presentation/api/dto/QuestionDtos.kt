package levelup.presentation.api.dto

import levelup.domain.Category
import levelup.domain.Exam
import levelup.domain.Question
import levelup.domain.Solution

data class QuestionResponse(val id: Long, val paragraph: String, val bookmark: Boolean) {
    constructor(question: Question, bookmark: Boolean) : this(
        question.id,
        question.paragraph,
        bookmark
    )
}

data class QuestionListResponse(private val _questions: List<Question>) {
    val questions = _questions.map { QuestionEntryResponse(it) }
}

data class QuestionEntryResponse(
    val id: Long,
    val paragraph: String,
    val solution: SolutionResponse,
    val category: CategoryNameResponse,
    val exam: ExamNameResponse
) {
    constructor(question: Question) : this(
        id = question.id,
        paragraph = question.paragraph,
        solution = SolutionResponse(question.solution),
        category = CategoryNameResponse(question.exam.category),
        exam = ExamNameResponse(question.exam)
    )
}

data class SolutionResponse(val answer: Any, val explanation: String) {
    constructor(solution: Solution) : this(solution.answer, solution.explanation)
}
data class CategoryNameResponse(val id: Long, val name: String) {
    constructor(category: Category) : this(category.id, category.name)
}
data class ExamNameResponse(val id: Long, val name: String) {
    constructor(exam: Exam) : this(exam.id, exam.name)
}