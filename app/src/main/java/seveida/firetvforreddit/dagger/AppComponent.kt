package seveida.firetvforreddit.dagger

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
        modules = [ActivityBindingModule::class, AndroidInjectionModule::class, BusinessLogicModule::class, IoModule::class]
)
interface AppComponent {
    fun inject(app: RedditApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun resources(resources: Resources): Builder
        fun businessLogicModule(businessLogicModule: BusinessLogicModule): Builder
        fun ioModule(ioModule: IoModule): Builder
        fun build(): AppComponent
    }
}
