package seveida.firetvforreddit

import android.app.Application
import baron.severin.io.dagger.IoModule
import com.jakewharton.threetenabp.AndroidThreeTen
import baron.severin.io.SubredditRepo
import baron.severin.io.dagger.DaggerIoComponent
import seveida.firetvforreddit.dagger.DaggerAppComponent
import javax.inject.Inject

class RedditApplication : Application() {

    @Inject lateinit var subredditRepo: SubredditRepo

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        injectDependencies()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // TODO clear caches here
    }

    private fun injectDependencies() {
        val ioComponent = DaggerIoComponent.builder()
                .ioModule(IoModule)
                .build()
        val appComponent = DaggerAppComponent.builder()
                .ioComponent(ioComponent)
                .build()
        appComponent.inject(this)
    }
}
