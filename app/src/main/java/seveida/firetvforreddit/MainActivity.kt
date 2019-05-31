package seveida.firetvforreddit

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
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
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var stateObs: StateObservable
    @Inject lateinit var eventRelay: EventRelay

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

    private fun observeToolbarState() {
        compositeDisposable += stateObs.get
                .map { it.toolbarState }
                .subscribe {
                    toolbarLeftTV.text = it.title
                    toolbarRightET.setText("")
                }
    }

    private fun setupToolbar() {
        observeToolbarState()

        toolbarRightET.setImeActionLabel("Load Subreddit", KeyEvent.KEYCODE_ENTER)

        toolbarRightET.setOnEditorActionListener { view, _, event ->
            if (event.isSelect()) {
                eventRelay.get.accept(Event.SubredditSelected(view.text.toString()))
                return@setOnEditorActionListener true
            }
            false
        }
    }
}
