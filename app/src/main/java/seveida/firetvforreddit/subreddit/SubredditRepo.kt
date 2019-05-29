package seveida.firetvforreddit.subreddit

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import baron.severin.domain_objects.SubredditDetails
import baron.severin.domain_objects.toSubredditDetails
import baron.severin.response_objects.SubredditResponse

class SubredditRepo internal constructor(retrofit: Retrofit) {

    private val api: SubredditApi = retrofit.create(SubredditApi::class.java)

    fun getSubreddit(subreddit: String): Observable<baron.severin.domain_objects.SubredditDetails> {
        return api.getSubreddit(subreddit)
            .subscribeOn(Schedulers.io())
            .map { it.toSubredditDetails() }
            .observeOn(AndroidSchedulers.mainThread())
    }
}

internal interface SubredditApi {
    @GET("/r/{sub}.json")
    fun getSubreddit(@Path("sub") subreddit: String): Observable<baron.severin.response_objects.SubredditResponse>
}

