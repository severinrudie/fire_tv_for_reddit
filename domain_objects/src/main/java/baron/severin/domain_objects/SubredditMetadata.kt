package baron.severin.domain_objects

import android.net.Uri
import seveida.firetvforreddit.response.objects.SubredditResponse

data class SubredditMetadata(val urlPath: Uri, val avatarImage: Uri,
                        val subredditId: String, val displayName: String)

fun SubredditResponse.toSubredditMetadata(): SubredditMetadata {
    val subredditName = this.data.children[0].data.subreddit
    val subredditId = this.data.after

    val urlPath = Uri.parse("www.example.com") // TODO
    val image = Uri.parse("www.example.com") // TODO

    return baron.severin.domain_objects.SubredditMetadata(urlPath, image, subredditId, subredditName)
}
