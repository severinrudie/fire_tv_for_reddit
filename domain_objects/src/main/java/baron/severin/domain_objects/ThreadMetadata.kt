package baron.severin.domain_objects

import android.net.Uri


import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import seveida.firetvforreddit.response.objects.Child
import seveida.firetvforreddit.response.objects.Data_

data class ThreadMetadata(val threadId: String, val previewImageUrl: Uri,
                          val title: String, val op: UserMetadata,
                          val voteCount: VoteCount, val created: LocalDateTime,
                          val commentCount: Int)

fun Child.toThreadMetadata(): ThreadMetadata {
    val data = this.data

    val id = data.id
    val imageUri = Uri.parse(data.thumbnail)
    val title = data.title

    val opName = data.author
    val opId = data.authorFullname
    val op = baron.severin.domain_objects.UserMetadata(opId, opName)

    val voteCount = baron.severin.domain_objects.VoteCount(data.ups, data.downs)

    val created = Instant.ofEpochMilli(data.created.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val commentCount = data.numComments
    return baron.severin.domain_objects.ThreadMetadata(id, imageUri, title, op, voteCount, created, commentCount)
}
