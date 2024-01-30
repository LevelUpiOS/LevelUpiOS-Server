package levelup.presentation.api.dto

import levelup.domain.Answer
import levelup.domain.Submission

data class SubmissionScoreListResponse(private val _submissions: List<Submission>) {
    val submissions = _submissions.map { SubmissionScoreResponse(it) }
}

data class SubmissionScoreResponse(val id: Long, val examId: Long, val score: Double) {
    constructor(submission: Submission) : this(
        id = submission.id,
        examId = submission.exam.id,
        score = submission.score
    )
}

data class SubmissionResponse(val id: Long, val examId: Long, val score: Double, val results: List<ResultResponse>) {
    constructor(submission: Submission) : this(
        id = submission.id,
        examId = submission.exam.id,
        score = submission.score,
        results = submission.answers.map { ResultResponse(it) }
    )
}

data class ResultResponse(
    val question: String,
    val guess: Any,
    val answer: Any,
    val isCorrect: Boolean,
    val explanation: String
) {
    constructor(answer: Answer) : this(
        question = answer.question.paragraph,
        guess = answer.guess,
        answer = answer.solution.answer,
        isCorrect = answer.guess == answer.solution.answer,
        explanation = answer.solution.explanation
    )
}