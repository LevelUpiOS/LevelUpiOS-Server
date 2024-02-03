package levelup.presentation.api.dto

import levelup.domain.Exam

data class ExamQuestionResponse(
    val id: Long,
    val name: String,
    val questions: List<QuestionResponse>
) {
    constructor(exam: Exam) : this(
        id = exam.id,
        name = exam.name,
        questions = exam.questions.map { QuestionResponse(it) }
    )
}

data class ExamResponse(
    val id: Long,
    val name: String
) {
    constructor(exam: Exam) : this(
        id = exam.id,
        name = exam.name
    )
}

data class ExamSolveRequest(
    val answers: List<Any>
)