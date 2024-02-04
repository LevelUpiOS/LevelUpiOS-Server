package levelup.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import levelup.application.QuestionService
import levelup.presentation.api.dto.QuestionListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Question", description = "문제 관련 API")
@RestController
@RequestMapping("/api/v1/questions")
class QuestionController(
    private val questionService: QuestionService
) {
    @Operation(
        summary = "북마크 생성",
        description = "id에 해당하는 문제에 대한 북마크 생성",
        responses = [ApiResponse(responseCode = "204")]
    )
    @PostMapping("/{questionId}/bookmark")
    fun markQuestion(@RequestAttribute loginId: Long, @PathVariable questionId: Long): ResponseEntity<Void> {
        questionService.mark(loginId, questionId)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "북마크 삭제",
        description = "id에 해당하는 문제에 대한 북마크 삭제",
        responses = [ApiResponse(responseCode = "204")]
    )
    @DeleteMapping("/{questionId}/bookmark")
    fun unmarkQuestion(@RequestAttribute loginId: Long, @PathVariable questionId: Long): ResponseEntity<Void> {
        questionService.unmark(loginId, questionId)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "문제 조회",
        description = "전체 문제 조회"
    )
    @Parameter(name = "bookmarkOnly", description = "true인 경우 북마크된 문제만 조회", required = false)
    @GetMapping
    fun findQuestions(
        @RequestAttribute loginId: Long,
        @RequestParam(required = false) bookmarkOnly: Boolean? = null
    ): ResponseEntity<QuestionListResponse> {
        return when (bookmarkOnly) {
            true -> ResponseEntity.ok(QuestionListResponse(questionService.findBookmarkQuestions(loginId)))
            else -> ResponseEntity.ok(QuestionListResponse(questionService.findAll()))
        }
    }
}