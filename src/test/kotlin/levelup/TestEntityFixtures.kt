package levelup

import levelup.domain.Category
import levelup.domain.Exam
import levelup.domain.OXSolution
import levelup.domain.Question
import levelup.domain.Solution
import levelup.domain.User
import levelup.domain.UserRole

fun createCategory(
    name: String = "Category",
    description: String = "Description"
) = Category(name, description)

fun createExam(name: String = "Exam") = Exam(name)

fun createSolution(
    answer: Boolean = true,
    explanation: String = "Explanation"
) = OXSolution(answer, explanation)

fun createQuestion(
    exam: Exam = createExam(),
    paragraph: String = "Paragraph",
    solution: Solution = createSolution()
) = Question(exam, paragraph, solution)

fun createUser(
    role: UserRole = UserRole.USER
) = User(role)