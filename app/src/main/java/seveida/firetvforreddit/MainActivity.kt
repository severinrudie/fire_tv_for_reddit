package seveida.firetvforreddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import baron.severin.io.SubredditRepo
import dagger.android.AndroidInjection
import seveida.firetvforreddit.subreddit.SubredditFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SubredditFragment())
            .commit()
    }
}
