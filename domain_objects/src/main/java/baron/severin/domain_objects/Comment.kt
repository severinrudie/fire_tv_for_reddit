package baron.severin.domain_objects

import org.threeten.bp.LocalDateTime

data class Comment(val commentId: String, val userMetadata: UserMetadata,
                   val voteCount: VoteCount, val created: LocalDateTime,
                   val content: String, val childComments: List<Comment>)

