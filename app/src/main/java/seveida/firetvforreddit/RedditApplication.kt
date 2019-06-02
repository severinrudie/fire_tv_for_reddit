package seveida.firetvforreddit

import android.app.Activity
import android.app.Application
import baron.severin.business_logic.DataFlowInitializer
import baron.severin.business_logic.dagger.BusinessLogicModule
import baron.severin.io.dagger.IoModule
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import seveida.firetvforreddit.dagger.AppModule
import seveida.firetvforreddit.dagger.DaggerAppComponent

class RedditApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var dataFlowInitializer: DataFlowInitializer

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        injectDependencies()
        dataFlowInitializer.init()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // TODO clear caches here
    }

    private fun injectDependencies() {
        val appComponent = DaggerAppComponent.builder()
                .ioModule(IoModule)
                .businessLogicModule(BusinessLogicModule)
                .appModule(AppModule)
                .resources(applicationContext.resources)
                .app(this)
                .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
