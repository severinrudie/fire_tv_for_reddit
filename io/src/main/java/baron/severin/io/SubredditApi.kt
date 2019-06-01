package baron.severin.io

import baron.severin.response_objects.raw.response.SubredditResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

internal interface SubredditApi {
    @GET("/r/{sub}.json")
    fun getSubreddit(@Path("sub") subreddit: CharSequence): Observable<SubredditResponse>
}
