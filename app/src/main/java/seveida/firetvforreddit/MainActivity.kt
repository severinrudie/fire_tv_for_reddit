package seveida.firetvforreddit

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
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
                .distinctUntilChanged()
                .subscribe {
                    toolbarLeftTV.text = it.title
                    toolbarRightET.setText("")
                    toolbarRightET.hint = it.inputHint
                }

        compositeDisposable += stateObs.get
                .map { it.colors }
                .distinctUntilChanged()
                .subscribe { colors ->
                    toolbarContainer.setBackgroundColor(colors.primary.toInt())
                    toolbarLeftTV.setTextColor(colors.text.toInt())

                    val colorStateList = ColorStateList(
                            /* states */ arrayOf(
                                    intArrayOf(android.R.attr.state_focused), // Focused
                                    intArrayOf(-android.R.attr.state_focused) // Unfocused
                            ),
                            /* colors */ intArrayOf(
                                    colors.accent.toInt(),
                                    colors.text.toInt()
                            )
                    )
                    toolbarRightET.setTextColor(colorStateList)
                    ViewCompat.setBackgroundTintList(toolbarRightET, colorStateList)
                    // TODO set edittext cursor color
                    // TODO maybe when focused, ET background == white
                }
    }

    private fun setupToolbar() {
        observeToolbarState()

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
