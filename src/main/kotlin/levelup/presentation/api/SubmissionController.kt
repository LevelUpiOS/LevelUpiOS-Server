package levelup.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import levelup.application.SubmissionService
import levelup.presentation.api.dto.SubmissionResponse
import levelup.presentation.api.dto.SubmissionScoreListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Submission", description = "문제 풀이 결과 관련 API")
@RestController
@RequestMapping("/api/v1/submissions")
class SubmissionController(
    private val submissionService: SubmissionService
) {
    @Operation(
        summary = "최신 Submission 조회",
        description = "각 Exam 당 최신 Submission 결과 반환"
    )
    @GetMapping
    fun findLatest(@RequestAttribute loginId: Long): ResponseEntity<SubmissionScoreListResponse> {
        val submissions = submissionService.findLatestSubmissions(loginId)
        return ResponseEntity.ok(SubmissionScoreListResponse(submissions))
    }

    @Operation(
        summary = "id로 Submission 조회",
        description = "Submission의 시험 점수나 채점 결과 반환"
    )
    @GetMapping("/{submissionId}")
    fun findSubmission(@PathVariable submissionId: Long): ResponseEntity<SubmissionResponse> {
        val submission = submissionService.findWithAnswers(submissionId)
        return ResponseEntity.ok(SubmissionResponse(submission))
    }
}