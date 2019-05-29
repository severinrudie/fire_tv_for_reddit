package baron.severin.response_objects.conversions

import android.net.Uri
import baron.severin.domain_objects.ThreadMetadata
import baron.severin.domain_objects.UserMetadata
import baron.severin.domain_objects.VoteCount
import baron.severin.response_objects.raw.response.Child
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

fun Child.toThreadMetadata(): ThreadMetadata {
    val data = this.data

    val id = data.id
    val imageUri = Uri.parse(data.thumbnail)
    val title = data.title

    val opName = data.author
    val opId = data.authorFullname
    val op = UserMetadata(opId, opName)

    val voteCount = VoteCount(data.ups, data.downs)

    val created = Instant.ofEpochMilli(data.created.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val commentCount = data.numComments
    return ThreadMetadata(id, imageUri, title, op, voteCount, created, commentCount)
}

