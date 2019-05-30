package baron.severin.io

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import baron.severin.domain_objects.SubredditDetails
import baron.severin.response_objects.conversions.toSubredditDetails
import javax.inject.Inject

class SubredditRepo @Inject internal constructor(private val subredditApi: SubredditApi) {

    fun getSubreddit(subreddit: String): Observable<SubredditDetails> {
        return subredditApi.getSubreddit(subreddit)
            .subscribeOn(Schedulers.io())
            .map { it.toSubredditDetails() }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
