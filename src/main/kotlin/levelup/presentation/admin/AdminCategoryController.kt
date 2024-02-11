package levelup.presentation.admin

import levelup.application.CategoryService
import levelup.application.admin.AdminCategoryService
import levelup.presentation.admin.dto.CategoryForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("$ADMIN_BASE_PATH/categories")
class AdminCategoryController(
    private val categoryService: CategoryService,
    private val adminCategoryService: AdminCategoryService
) {
    @GetMapping("/create")
    fun addCategoryForm() = "admin/category/add-form"

    @PostMapping("/create")
    fun addCategory(@ModelAttribute form: CategoryForm): String {
        adminCategoryService.create(name = form.name, description = form.description)
        return "redirect:$ADMIN_BASE_PATH"
    }

    @GetMapping("/{categoryId}/update")
    fun updateForm(@PathVariable categoryId: Long, model: Model): String {
        val category = categoryService.find(categoryId)
        model.addAttribute("category", category)
        return "admin/category/update-form"
    }

    @PostMapping("/{categoryId}/update")
    fun update(@PathVariable categoryId: Long, @ModelAttribute form: CategoryForm): String {
        adminCategoryService.update(categoryId, form.name, form.description)
        return "redirect:$ADMIN_BASE_PATH"
    }

    @PostMapping("/{categoryId}/delete")
    fun delete(@PathVariable categoryId: Long): String {
        adminCategoryService.delete(categoryId)
        return "redirect:$ADMIN_BASE_PATH"
    }
}