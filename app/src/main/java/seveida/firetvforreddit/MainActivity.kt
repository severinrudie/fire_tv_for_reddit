package seveida.firetvforreddit

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import baron.severin.business_logic.Event
import baron.severin.business_logic.EventRelay
import baron.severin.business_logic.StateObservable
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.toolbar.*
import seveida.firetvforreddit.ext.isSelect
import seveida.firetvforreddit.subreddit.SubredditFragment
import seveida.firetvforreddit.view.components.TextInputAutoCompleteTextView
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
            .replace(R.id.fragmentContainer, SubredditFragment())
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
        toolbarStyler.observeToolbarState(WeakReference(this))

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
