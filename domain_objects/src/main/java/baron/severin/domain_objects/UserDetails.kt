package baron.severin.domain_objects

import android.net.Uri

import org.threeten.bp.LocalDateTime

data class UserDetails(val userMetadata: UserMetadata,
                       val avatar: Uri, val karma: Int,
                       val cakeDay: LocalDateTime, val commentsMade: List<Comment>)
