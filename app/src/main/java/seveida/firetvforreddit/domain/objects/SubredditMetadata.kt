package seveida.firetvforreddit.domain.objects

import android.net.Uri
import seveida.firetvforreddit.response.objects.SubredditResponse

data class SubredditMetadata(val urlPath: Uri, val avatarImage: Uri,
                        val subredditId: String, val displayName: String) {
    companion object {

        internal fun fromResponse(response: SubredditResponse): SubredditMetadata { // TODO make ext fun on response
            val subredditName = response.data.children[0].data.subreddit
            val subredditId = response.data.after

            val urlPath = Uri.parse("www.example.com") // TODO
            val image = Uri.parse("www.example.com") // TODO

            return SubredditMetadata(urlPath, image, subredditId, subredditName)
        }
    }
}
