package levelup

import levelup.domain.Category
import levelup.domain.Exam
import levelup.domain.OXSolution
import levelup.domain.Question
import levelup.domain.Solution
import levelup.domain.User

fun createCategory(
    name: String = "Category",
    description: String = "Description"
) = Category(name, description)

fun createExam(
    category: Category = createCategory(),
    name: String = "Exam"
) = Exam(category, name)

fun createSolution(
    answer: Boolean = true,
    explanation: String = "Explanation"
) = OXSolution(answer, explanation)

fun createQuestion(
    exam: Exam = createExam(),
    paragraph: String = "Paragraph",
    solution: Solution = createSolution()
) = Question(exam, paragraph, solution)

fun createUser() = User()