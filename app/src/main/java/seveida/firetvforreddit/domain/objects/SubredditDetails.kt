package seveida.firetvforreddit.domain.objects

import java.util.ArrayList
import seveida.firetvforreddit.response.objects.Child
import seveida.firetvforreddit.response.objects.SubredditResponse

data class SubredditDetails(val subredditMetadata: SubredditMetadata,
                       val threadMetadataList: List<ThreadMetadata>) {
    companion object {

        fun fromResponse(response: SubredditResponse): SubredditDetails { // TODO make ext fun on response
            val metadata = SubredditMetadata.fromResponse(response)

            val threads = ArrayList<ThreadMetadata>(25)
            for (child in response.data.children) {
                threads.add(ThreadMetadata.fromResponse(child))
            }

            return SubredditDetails(metadata, threads)
        }
    }
}
