package levelup.presentation.api.dto

import levelup.domain.Question

data class QuestionResponse(val id: Long, val paragraph: String) {
    constructor(question: Question) : this(
        question.id,
        question.paragraph
    )
}