package levelup.presentation.admin

import levelup.application.admin.AdminCategoryService
import levelup.presentation.admin.dto.LoginForm
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("$ADMIN_BASE_PATH/categories")
class AdminCategoryController(
    private val adminCategoryService: AdminCategoryService
) {
    @GetMapping("/create")
    fun addCategoryForm() = "admin/category/add-form"

    @PostMapping("/create")
    fun addCategory(@ModelAttribute loginForm: LoginForm): String {
        adminCategoryService.create(name = loginForm.name, description = loginForm.description)
        return "redirect:$ADMIN_BASE_PATH"
    }

    @PostMapping("/{categoryId}/delete")
    fun delete(@PathVariable categoryId: Long): String {
        adminCategoryService.delete(categoryId)
        return "redirect:$ADMIN_BASE_PATH"
    }
}