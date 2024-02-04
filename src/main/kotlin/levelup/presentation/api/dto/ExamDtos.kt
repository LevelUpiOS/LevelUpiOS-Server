package levelup.presentation.api.dto

import levelup.domain.Exam
import levelup.domain.Question

data class ExamQuestionResponse(
    val id: Long,
    val name: String,
    val questions: List<QuestionResponse>
) {
    constructor(exam: Exam, bookmarkQuestions: Set<Question>) : this(
        id = exam.id,
        name = exam.name,
        questions = exam.questions.map { QuestionResponse(it, bookmarkQuestions.contains(it)) }
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