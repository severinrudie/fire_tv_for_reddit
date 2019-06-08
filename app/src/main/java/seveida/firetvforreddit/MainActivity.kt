package seveida.firetvforreddit

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import baron.severin.business_logic.Event
import baron.severin.business_logic.EventRelay
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.toolbar.*
import seveida.firetvforreddit.ext.isSelect
import seveida.firetvforreddit.settings.SettingsFragment
import seveida.firetvforreddit.subreddit.SubredditFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var eventRelay: EventRelay
    @Inject lateinit var toolbarStyler: ToolbarStyler

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContentFragmentContainer, SubredditFragment())
            .replace(R.id.settingsFragmentContainer, SettingsFragment()) // TODO do this / animate on event
            .commit()
    }

    override fun onStart() {
        super.onStart()
        setupToolbar()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingFragmentInjector

    private fun setupToolbar() {
        toolbarStyler.observeToolbarState(WeakReference(this), compositeDisposable)

        toolbarRightET.setImeActionLabel("Load Subreddit", KeyEvent.KEYCODE_ENTER)

        toolbarRightET.setOnEditorActionListener { view, _, event ->
            if (event.isSelect()) {
                eventRelay.get.accept(Event.SubredditSelected(view.text))
                return@setOnEditorActionListener true
            }
            false
        }
    }
}
