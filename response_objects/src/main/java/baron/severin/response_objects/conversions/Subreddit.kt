package baron.severin.response_objects.conversions

import android.net.Uri
import baron.severin.domain_objects.SubredditDetails
import baron.severin.domain_objects.SubredditMetadata
import baron.severin.response_objects.raw.response.api.json.SubredditResponse

fun SubredditResponse.toSubredditDetails(): SubredditDetails {
    val metadata = this.toSubredditMetadata()

    val threads = this.data.children.map { it.toThreadMetadata() }

    return SubredditDetails(metadata, threads)
}

fun SubredditResponse.toSubredditMetadata(): SubredditMetadata {
    val subredditName = this.data.children[0].data.subreddit
    val subredditId = this.data.after

    val urlPath = Uri.parse("www.example.com") // TODO
    val image = Uri.parse("www.example.com") // TODO

    return SubredditMetadata(urlPath, image, subredditId, subredditName)
}
