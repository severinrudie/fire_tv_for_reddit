package baron.severin.io

import baron.severin.common.dagger.AppScope
import baron.severin.domain_objects.SubredditDetails
import baron.severin.response_objects.conversions.toSubredditDetails
import dagger.Lazy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import net.dean.jraw.oauth.OAuthHelper
import net.dean.jraw.RedditClient

@AppScope
class SubredditRepo @Inject internal constructor(
        private val subredditApi: SubredditApi,
        private val reddit: Lazy<RedditClient>
) {

    init {
        println("SEVTEST: subredditRepo init")
        val t = Observable.fromCallable { reddit.get() }
                .map { it.subreddit("aww") }
                .subscribeOn(Schedulers.io())
                .subscribe { println("SEVTEST: ${it.subreddit} successfully retrieved\"") }
    }

    // TODO handle failure case
    fun getSubreddit(subreddit: CharSequence): Observable<SubredditDetails> {
        return subredditApi.getSubreddit(subreddit)
            .subscribeOn(Schedulers.io())
            .map { it.toSubredditDetails() }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
