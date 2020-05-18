package baron.severin.presentation_objects

import android.net.Uri

data class ThreadItemState(
        val imageUrl: Uri?,
        val title: CharSequence,
        val author: CharSequence,
        val voteCount: CharSequence,
        val comments: CharSequence,
        val viewed: Boolean
)
