package support

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface EntityRepository<T, ID> : JpaRepository<T, ID>

inline fun <reified T, ID> EntityRepository<T, ID>.find(id: ID): T =
    findByIdOrNull(id) ?: throw NoSuchElementException("${T::class.simpleName}에 id ${id}가 존재하지 않습니다.")