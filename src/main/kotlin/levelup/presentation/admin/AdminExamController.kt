package levelup.presentation.admin

import levelup.application.ExamService
import levelup.application.admin.AdminExamService
import levelup.presentation.admin.dto.ExamCreateForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("$ADMIN_BASE_PATH/exams")
class AdminExamController(
    private val adminExamService: AdminExamService,
    private val examService: ExamService
) {
    @GetMapping("/{examId}")
    fun exam(@PathVariable examId: Long, model: Model): String {
        val exam = examService.findWithQuestions(examId)
        model.addAttribute("exam", exam)
        return "admin/exam/exam"
    }

    @GetMapping("/create")
    fun createForm(@RequestParam categoryId: Long): String {
        return "admin/exam/add-form"
    }

    @PostMapping("/create")
    fun create(@RequestParam categoryId: Long, @ModelAttribute form: ExamCreateForm): String {
        adminExamService.create(categoryId, form.name)
        return "redirect:$ADMIN_BASE_PATH"
    }

    @GetMapping("/{examId}/update")
    fun updateForm(@PathVariable examId: Long, model: Model): String {
        val exam = examService.find(examId)
        model.addAttribute("exam", exam)
        return "admin/exam/update-form"
    }

    @PostMapping("/{examId}/update")
    fun update(@PathVariable examId: Long, @ModelAttribute form: ExamCreateForm): String {
        adminExamService.update(examId, form.name)
        return "redirect:$ADMIN_BASE_PATH"
    }

    @PostMapping("/{examId}/delete")
    fun delete(@PathVariable examId: Long): String {
        adminExamService.delete(examId)
        return "redirect:$ADMIN_BASE_PATH"
    }
}