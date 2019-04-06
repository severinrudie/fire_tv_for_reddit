package seveida.firetvforreddit.domain.objects

import seveida.firetvforreddit.response.objects.SubredditResponse

data class SubredditDetails(val subredditMetadata: SubredditMetadata,
                       val threadMetadataList: List<ThreadMetadata>)

fun SubredditResponse.toSubredditDetails(): SubredditDetails {
    val metadata = this.toSubredditMetadata()

    val threads = this.data.children.map { it.toThreadMetadata() }

    return SubredditDetails(metadata, threads)
}
