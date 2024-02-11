package levelup.presentation.admin.dto

data class QuestionUpdateForm(
    val paragraph: String,
    val answer: Boolean,
    val explanation: String
)