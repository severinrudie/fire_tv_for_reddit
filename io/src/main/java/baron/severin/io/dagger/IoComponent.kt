package baron.severin.io.dagger

import baron.severin.io.SubredditRepo
import dagger.Component

@IoScope
@Component(modules = [IoModule::class])
interface IoComponent {
    fun subredditRepo(): SubredditRepo
}
