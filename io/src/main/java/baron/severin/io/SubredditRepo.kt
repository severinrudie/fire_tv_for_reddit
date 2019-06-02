package baron.severin.io

import android.net.Uri
import baron.severin.common.dagger.AppScope
import baron.severin.domain_objects.*
import baron.severin.io.dagger.RedditObs
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

@AppScope
class SubredditRepo @Inject internal constructor(
        private val redditObs: RedditObs
) {

    // TODO caching... so much caching required here
    // TODO handle failure case
    fun getSubreddit(subreddit: CharSequence): Observable<SubredditDetails> {
        return redditObs.get
                .map { client ->
                    val sub = client.subreddit(subreddit.toString())

                    val metadata = SubredditMetadata(
                            urlPath = Uri.parse(sub.about().url),
                            bannerImage = Uri.parse(sub.about().bannerImage),
                            subredditId = sub.about().id,
                            displayName = sub.about().name
                    )

                    val paginator = sub.posts().limit(20).build()

                    val threadList = paginator.next().children.map { submission ->
                        ThreadMetadata(
                                threadId = submission.id,
                                previewImageUrl = Uri.parse(submission.preview?.images?.firstOrNull()?.source?.url),
                                title = submission.title,
                                op = UserMetadata(
                                        userId = submission.author, // TODO fix this
                                        username = submission.author
                                ),
                                voteCount = VoteCount(
                                        max(0, submission.score),
                                        min(0, submission.score)
                                ),
                                created = Instant.ofEpochMilli(submission.created.time)
                                        .atZone(ZoneId.of("UTC"))
                                        .toLocalDateTime(),
                                commentCount = submission.commentCount
                        )
                    }
                    SubredditDetails(
                            subredditMetadata = metadata,
                            threadMetadataList = threadList
                    )
                }
                .subscribeOn(Schedulers.io())
    }
}
