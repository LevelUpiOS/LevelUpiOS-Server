package levelup.presentation.api

import levelup.application.SubmissionService
import levelup.presentation.api.dto.SubmissionResponse
import levelup.presentation.api.dto.SubmissionScoreListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/submissions")
class SubmissionController(
    private val submissionService: SubmissionService
) {
    @GetMapping
    fun findLatest(@RequestAttribute loginId: Long): ResponseEntity<SubmissionScoreListResponse> {
        val submissions = submissionService.findLatestSubmissions(loginId)
        return ResponseEntity.ok(SubmissionScoreListResponse(submissions))
    }

    @GetMapping("/{submissionId}")
    fun findSubmission(@PathVariable submissionId: Long): ResponseEntity<SubmissionResponse> {
        val submission = submissionService.findWithAnswers(submissionId)
        return ResponseEntity.ok(SubmissionResponse(submission))
    }
}