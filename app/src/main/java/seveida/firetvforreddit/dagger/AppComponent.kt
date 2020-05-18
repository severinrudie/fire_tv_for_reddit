package seveida.firetvforreddit.dagger

import android.app.Application
import android.content.res.Resources
import baron.severin.business_logic.dagger.BusinessLogicModule
import baron.severin.common.dagger.AppScope
import baron.severin.io.dagger.IoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import seveida.firetvforreddit.RedditApplication

@AppScope
@Component(
        modules = [
            ActivityBindingModule::class,
            AndroidInjectionModule::class,
            BusinessLogicModule::class,
            IoModule::class,
            AppModule::class
        ]
)
interface AppComponent {
    fun inject(app: RedditApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun resources(resources: Resources): Builder
        @BindsInstance
        fun application(application: Application): Builder
        fun businessLogicModule(businessLogicModule: BusinessLogicModule): Builder
        fun appModule(appModule: AppModule): Builder
        fun ioModule(ioModule: IoModule): Builder
        fun build(): AppComponent
    }
}
