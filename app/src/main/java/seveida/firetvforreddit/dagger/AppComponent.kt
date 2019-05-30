package seveida.firetvforreddit.dagger

import baron.severin.io.dagger.IoComponent
import dagger.Component
import seveida.firetvforreddit.RedditApplication

@AppScope
@Component(dependencies = [IoComponent::class])
interface AppComponent {
    fun inject(app: RedditApplication)
}
