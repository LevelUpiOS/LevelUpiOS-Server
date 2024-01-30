package levelup.presentation.api

import levelup.application.ExamService
import levelup.presentation.api.dto.ExamResponse
import levelup.presentation.api.dto.ExamSolveRequest
import levelup.presentation.api.dto.SubmissionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/exams")
class ExamController(
    private val examService: ExamService
) {
    @GetMapping("/{examId}")
    fun find(@PathVariable examId: Long): ResponseEntity<ExamResponse> {
        val exam = examService.findWithQuestions(examId)
        return ResponseEntity.ok(ExamResponse(exam.id, exam.name, exam.questions.map { it.paragraph }))
    }

    @PostMapping("/{examId}")
    fun solve(
        @RequestAttribute loginId: Long,
        @PathVariable examId: Long,
        @RequestBody request: ExamSolveRequest
    ): ResponseEntity<SubmissionResponse> {
        val submission = examService.mark(loginId, examId, request.answers)
        return ResponseEntity.ok(SubmissionResponse(submission))
    }
}