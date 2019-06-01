package baron.severin.io

import baron.severin.common.dagger.AppScope
import baron.severin.domain_objects.SubredditDetails
import baron.severin.response_objects.conversions.toSubredditDetails
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AppScope
class SubredditRepo @Inject internal constructor(private val subredditApi: SubredditApi) {

    // TODO handle failure case
    fun getSubreddit(subreddit: CharSequence): Observable<SubredditDetails> {
        return subredditApi.getSubreddit(subreddit)
            .subscribeOn(Schedulers.io())
            .map { it.toSubredditDetails() }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
