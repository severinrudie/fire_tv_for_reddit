package baron.severin.domain_objects


import android.net.Uri
import org.threeten.bp.LocalDateTime

data class ThreadMetadata(val threadId: String, val previewImageUrl: Uri,
                          val title: String, val op: UserMetadata,
                          val voteCount: VoteCount, val created: LocalDateTime,
                          val commentCount: Int)
