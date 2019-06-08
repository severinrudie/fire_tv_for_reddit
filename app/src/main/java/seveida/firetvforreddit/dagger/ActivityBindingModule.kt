package seveida.firetvforreddit.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import seveida.firetvforreddit.MainActivity
import seveida.firetvforreddit.settings.SettingsFragment
import seveida.firetvforreddit.subreddit.SubredditFragment

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun subredditFragment(): SubredditFragment

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment
}
