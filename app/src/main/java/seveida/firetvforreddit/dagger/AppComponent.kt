package seveida.firetvforreddit.dagger

import baron.severin.business_logic.dagger.BusinessLogicComponent
import baron.severin.io.dagger.IoComponent
import dagger.Component
import dagger.android.AndroidInjectionModule
import seveida.firetvforreddit.RedditApplication

@AppScope
@Component(
        modules = [ActivityBindingModule::class, AndroidInjectionModule::class],
        dependencies = [IoComponent::class, BusinessLogicComponent::class]
)
interface AppComponent {
    fun inject(app: RedditApplication)
}
