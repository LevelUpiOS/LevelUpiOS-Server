package levelup.domain

import support.EntityRepository

interface BookmarkRepository : EntityRepository<Bookmark, Long> {
    fun deleteByUserIdAndQuestionId(userId: Long, questionId: Long)
}

fun BookmarkRepository.delete(userId: Long, questionId: Long) =
    deleteByUserIdAndQuestionId(userId, questionId)