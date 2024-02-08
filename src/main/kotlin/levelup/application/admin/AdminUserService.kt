package levelup.application.admin

import levelup.domain.AdminInfo
import org.springframework.stereotype.Service

@Service
class AdminUserService(
    private val adminInfo: AdminInfo
) {
    fun login(password: String) = adminInfo.isValid(password)
}