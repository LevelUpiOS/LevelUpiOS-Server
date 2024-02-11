package levelup.presentation.admin

import levelup.application.QuestionService
import levelup.presentation.admin.dto.QuestionUpdateForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("$ADMIN_BASE_PATH/questions")
class AdminQuestionController(
    private val questionService: QuestionService
) {
    @GetMapping("/{questionId}/update")
    fun updateForm(@PathVariable questionId: Long, model: Model): String {
        val question = questionService.findWithSolution(questionId)
        model.addAttribute("question", question)
        return "admin/question/update-form"
    }

    @PostMapping("/{questionId}/update")
    fun update(@PathVariable questionId: Long, @ModelAttribute form: QuestionUpdateForm, model: Model): String {
        val question = questionService.update(questionId, form.paragraph, form.answer, form.explanation)
        return "redirect:$ADMIN_BASE_PATH/exams/${question.exam.id}"
    }
}