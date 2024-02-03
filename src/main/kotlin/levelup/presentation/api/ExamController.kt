package levelup.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import levelup.application.ExamService
import levelup.presentation.api.dto.ExamQuestionResponse
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

@Tag(name = "Exam", description = "시험 관련 API")
@RestController
@RequestMapping("/api/v1/exams")
class ExamController(
    private val examService: ExamService
) {
    @Operation(
        summary = "시험 문제 조회",
        description = "시험 id를 이용해서 시험 내용과 문제 정보 조회"
    )
    @GetMapping("/{examId}")
    fun find(@PathVariable examId: Long): ResponseEntity<ExamQuestionResponse> {
        val exam = examService.findWithQuestions(examId)
        return ResponseEntity.ok(ExamQuestionResponse(exam))
    }

    @Operation(
        summary = "시험 문제 풀기",
        description = "정답을 입력해서 시험 문제를 풀면 시험 결과 반환",
    )
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