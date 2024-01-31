package levelup.presentation.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import levelup.application.CategoryService
import levelup.presentation.api.dto.CategoryListResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Category", description = "카테고리 관련 API")
@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @Operation(
        summary = "카테고리 조회",
        description = "카테고리 목록과 하위 시험 목록을 호출해주는 API"
    )
    @GetMapping
    fun find() = ResponseEntity.ok(CategoryListResponse(categoryService.findWithExam()))
}