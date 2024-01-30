package levelup.application

import levelup.domain.User
import levelup.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {
    fun generate() = userRepository.save(User())
}