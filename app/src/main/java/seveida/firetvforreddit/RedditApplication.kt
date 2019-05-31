package seveida.firetvforreddit

import android.app.Activity
import android.app.Application
import baron.severin.business_logic.dagger.BusinessLogicModule
import baron.severin.io.dagger.IoModule
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import seveida.firetvforreddit.dagger.DaggerAppComponent

class RedditApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

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
        val appComponent = DaggerAppComponent.builder()
                .ioModule(IoModule)
                .businessLogicModule(BusinessLogicModule)
                .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
