package levelup.presentation.api

import levelup.application.CategoryService
import levelup.presentation.api.dto.CategoryListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun find() = ResponseEntity.ok(CategoryListResponse(categoryService.findWithExam()))
}