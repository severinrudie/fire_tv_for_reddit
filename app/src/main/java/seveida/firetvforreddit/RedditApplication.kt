package seveida.firetvforreddit

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import seveida.firetvforreddit.subreddit.SubredditRepo

class RedditApplication : Application() {

    private val retrofit = RetrofitProvider.create()

    var subredditRepo = SubredditRepo(retrofit)

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // TODO clear caches here
    }
}
