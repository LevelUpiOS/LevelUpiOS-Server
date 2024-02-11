package levelup.presentation.admin

import levelup.application.ExamService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("$ADMIN_BASE_PATH/exams")
class AdminExamController(
    private val examService: ExamService
) {
    @GetMapping("/{examId}")
    fun exam(@PathVariable examId: Long, model: Model): String {
        val exam = examService.findWithQuestions(examId)
        model.addAttribute("exam", exam)
        return "admin/exam/exam"
    }
}