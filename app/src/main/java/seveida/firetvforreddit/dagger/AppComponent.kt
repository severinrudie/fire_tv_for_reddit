package seveida.firetvforreddit.dagger

import baron.severin.business_logic.dagger.BusinessLogicModule
import baron.severin.common.dagger.AppScope
import baron.severin.io.dagger.IoModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import seveida.firetvforreddit.RedditApplication

@AppScope
@Component(
        modules = [ActivityBindingModule::class, AndroidInjectionModule::class, BusinessLogicModule::class, IoModule::class]
)
interface AppComponent {
    fun inject(app: RedditApplication)
}
