package levelup.presentation.admin

import levelup.application.QuestionService
import levelup.application.admin.AdminQuestionService
import levelup.presentation.admin.dto.QuestionForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("$ADMIN_BASE_PATH/questions")
class AdminQuestionController(
    private val questionService: QuestionService,
    private val adminQuestionService: AdminQuestionService
) {
    @GetMapping("/create")
    fun createForm(@RequestParam examId: Long): String {
        return "admin/question/add-form"
    }

    @PostMapping("/create")
    fun create(@RequestParam examId: Long, @ModelAttribute form: QuestionForm): String {
        adminQuestionService.create(examId, form.paragraph, form.answer, form.explanation)
        return "redirect:$ADMIN_BASE_PATH/exams/$examId"
    }

    @GetMapping("/{questionId}/update")
    fun updateForm(@PathVariable questionId: Long, model: Model): String {
        val question = questionService.findWithSolution(questionId)
        model.addAttribute("question", question)
        return "admin/question/update-form"
    }

    @PostMapping("/{questionId}/update")
    fun update(@PathVariable questionId: Long, @ModelAttribute form: QuestionForm, model: Model): String {
        val question = questionService.update(questionId, form.paragraph, form.answer, form.explanation)
        return "redirect:$ADMIN_BASE_PATH/exams/${question.exam.id}"
    }

    @PostMapping("/{questionId}/delete")
    fun delete(@RequestParam examId: Long, @PathVariable questionId: Long): String {
        adminQuestionService.delete(questionId)
        return "redirect:$ADMIN_BASE_PATH/exams/$examId"
    }
}