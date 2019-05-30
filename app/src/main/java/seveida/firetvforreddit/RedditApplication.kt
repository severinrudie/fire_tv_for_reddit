package seveida.firetvforreddit

import android.app.Application
import baron.severin.io.RetrofitProvider
import com.jakewharton.threetenabp.AndroidThreeTen
import baron.severin.io.SubredditRepo

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
