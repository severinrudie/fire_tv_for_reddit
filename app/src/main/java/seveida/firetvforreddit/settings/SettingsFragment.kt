package seveida.firetvforreddit.settings

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import baron.severin.business_logic.Event
import baron.severin.business_logic.EventRelay
import baron.severin.business_logic.StateObservable
import baron.severin.business_logic.dagger.INITIAL_STATE
import baron.severin.presentation_objects.Colors
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.thread_item.view.*
import seveida.firetvforreddit.ColorAnimator
import seveida.firetvforreddit.R
import javax.inject.Inject
import javax.inject.Named

class SettingsFragment : Fragment() {

    @Inject lateinit var stateObsWrapper: StateObservable
    @Inject lateinit var eventRelayWrapper: EventRelay
    @Inject @field:[Named(INITIAL_STATE)] lateinit var initialColors: Colors

    private val stateObs get() = stateObsWrapper.get
    private val eventRelay get() = eventRelayWrapper.get

    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onStart() {
        super.onStart()

        observeColors()
        setPickerColors()


        //TODO temp.  this whole PR is horrible and temp...
        themePickerMain.setOnClickListener { eventRelay.accept(Event.ColorsChanged(initialColors)) }
        themePickerDark.setOnClickListener { eventRelay.accept(Event.ColorsChanged(Colors(
                R.color.colorPrimary,
                R.color.accent_material_dark,
                R.color.colorAccent,
                R.color.abc_hint_foreground_material_light,
                R.color.abc_hint_foreground_material_light,
                R.color.color_white,
                android.R.color.transparent
                ))) }
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }

    private fun observeColors() {
        compositeDisposable += stateObs.map { it.colors }
                .distinctUntilChanged()
                .subscribe { colors ->
                    val currentBackground = (settingsRoot?.background as? ColorDrawable)?.color
                    if (currentBackground == null) {
                        settingsRoot.background = ColorDrawable(colors.primary)
                    } else {
//                        val backgroundAnimator = ColorAnimator.get((settingsRoot.background as ColorDrawable).color, colors.primary)
                        val backgroundAnimator = ColorAnimator.get({ (settingsRoot?.background as? ColorDrawable)?.color }, colors.primary)

                        compositeDisposable += backgroundAnimator.subscribe {
                            settingsRoot.background = ColorDrawable(it)
                        }
                    }


                    //TODO
                    val t = themePickerMain.backgroundTintList
                    val getCurrentAccent = { t?.getColorForState(intArrayOf(android.R.attr.state_focused), colors.accent) }

                    compositeDisposable += ColorAnimator.get(getCurrentAccent, colors.accent).subscribe {
                        val imageWrapperBackground = ColorStateList(
                                arrayOf(
                                        intArrayOf(android.R.attr.state_focused), // Focused
                                        intArrayOf(-android.R.attr.state_focused) // Unfocused
                                ),
                                intArrayOf(
                                        it,
                                        colors.transparent
                                )
                        )

                    themePickerMain.backgroundTintList = imageWrapperBackground
                    themePickerDark.backgroundTintList = imageWrapperBackground
                    }
                    //TODO


//                    val imageWrapperBackground = ColorStateList(
//                            arrayOf(
//                                    intArrayOf(android.R.attr.state_focused), // Focused
//                                    intArrayOf(-android.R.attr.state_focused) // Unfocused
//                            ),
//                            intArrayOf(
//                                    colors.accent,
//                                    colors.transparent
//                            )
//                    )
//
//                    themePickerMain.backgroundTintList = imageWrapperBackground
//                    themePickerDark.backgroundTintList = imageWrapperBackground
                }
    }

    private fun setPickerColors() {
        themePickerInnerMain.setBackgroundColor(resources.getColor(android.R.color.holo_blue_bright))
        themePickerInnerDark.setBackgroundColor(resources.getColor(android.R.color.background_dark))

    }
}
