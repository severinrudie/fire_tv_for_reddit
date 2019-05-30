package seveida.firetvforreddit.dagger

import baron.severin.io.dagger.IoComponent
import dagger.Component
import dagger.android.AndroidInjectionModule
import seveida.firetvforreddit.RedditApplication

@AppScope
@Component(
        modules = [ActivityBindingModule::class, AndroidInjectionModule::class],
        dependencies = [IoComponent::class]
)
interface AppComponent {
    fun inject(app: RedditApplication)
}
