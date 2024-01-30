package levelup.presentation.api.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class ExamResponse(
    val id: Long,
    val name: String,
    @field:JsonInclude(JsonInclude.Include.NON_NULL)
    val questions: List<String>? = null
)

data class ExamSolveRequest(
    val answers: List<Any>
)