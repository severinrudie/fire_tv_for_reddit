package seveida.firetvforreddit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import seveida.firetvforreddit.subreddit.SubredditFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SubredditFragment())
            .commit()
    }
}
