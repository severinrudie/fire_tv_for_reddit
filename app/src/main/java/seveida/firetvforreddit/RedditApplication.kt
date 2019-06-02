package seveida.firetvforreddit

import android.app.Activity
import android.app.Application
import baron.severin.business_logic.DataFlowInitializer
import baron.severin.business_logic.dagger.BusinessLogicModule
import baron.severin.common.dagger.DiConstants
import baron.severin.io.UserlessLoginApi
import baron.severin.io.dagger.IoModule
import baron.severin.io.requestUserlessCredentials
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import dagger.android.DispatchingAndroidInjector
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import seveida.firetvforreddit.dagger.AppModule
import seveida.firetvforreddit.dagger.DaggerAppComponent
import java.util.*
import javax.inject.Named

class RedditApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var dataFlowInitializer: DataFlowInitializer


    @Inject lateinit var retrofit: Retrofit
    @field:[Inject Named(DiConstants.DEVICE_UUID)] lateinit var deviceId: UUID

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



        // TODO temp below
        val api = retrofit.create(UserlessLoginApi::class.java)
        val t = api.requestUserlessCredentials(deviceId)
                .subscribeOn(Schedulers.io())
//                .subscribe { res: Response<UserlessAuthorization> ->
                .subscribe { res ->
                    println("SEVTEST: response: $res")
                    if (res.isSuccessful) {
                        println("SEVTEST: auth: ${res.body()}")
                    }
                }

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
