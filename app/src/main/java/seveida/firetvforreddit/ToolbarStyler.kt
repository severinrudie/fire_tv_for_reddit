package seveida.firetvforreddit

import android.R.attr.state_focused
import android.app.Activity
import android.graphics.drawable.ColorDrawable
import androidx.core.view.ViewCompat
import baron.severin.business_logic.StateObservable
import baron.severin.presentation_objects.Colors
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.zipWith
import kotlinx.android.synthetic.main.toolbar.*
import seveida.firetvforreddit.ext.createColorStateList
import seveida.firetvforreddit.view.components.TextInputAutoCompleteTextView
import java.lang.ref.WeakReference
import javax.inject.Inject

class ToolbarStyler @Inject constructor(private val stateObs: StateObservable) {

    fun observeToolbarState(activity: WeakReference<Activity>, compositeDisposable: CompositeDisposable) {
        listOf(observeContent(activity, compositeDisposable), observeColors(activity, compositeDisposable))
    }

    private fun observeContent(activity: WeakReference<Activity>, compositeDisposable: CompositeDisposable) {
        compositeDisposable += stateObs.get
                .map { it.toolbarState }
                .distinctUntilChanged()
                .subscribe {
                    with (activity.get() ?: return@subscribe) {
                        toolbarLeftTV.text = it.title
                        toolbarRightET.setText("")
                        toolbarRightTIL.hint = it.inputHint
                    }
                }
    }

    private fun observeColors(activity: WeakReference<Activity>, cd: CompositeDisposable) {
        cd += stateObs.get
                .map { it.colors }
                .distinctUntilChanged()
                .subscribe { colors ->
                    with (activity.get() ?: return@subscribe) {
//                        toolbarContainer.setBackgroundColor(colors.primary)
                        cd += ColorAnimator.get({ (toolbarContainer?.background as? ColorDrawable)?.color }, colors.primary).subscribe {
                            toolbarContainer.setBackgroundColor(it)
                        }
//                        toolbarLeftTV.setTextColor(colors.text)
                        cd += ColorAnimator.get({ toolbarLeftTV.currentTextColor }, colors.text).subscribe {
                            toolbarLeftTV.setTextColor(it)
                        }

//                        toolbarRightET.setTextColor(colors.text)
                        cd += ColorAnimator.get({ toolbarRightET.currentTextColor }, colors.text).subscribe {
                            toolbarRightET.setTextColor(it)
                        }

//                        ViewCompat.setBackgroundTintList(toolbarRightET, colors.getHintColorList())
                        val getFocused = { toolbarRightET?.backgroundTintList?.getColorForState(intArrayOf(state_focused), colors.accent) } // TODO generify
                        val getUnfocused = { toolbarRightET?.backgroundTintList?.getColorForState(intArrayOf(-state_focused), colors.text) }
                        val focusedObs = ColorAnimator.get(getFocused, colors.accent)
                        val unfocusedObs = ColorAnimator.get(getUnfocused, colors.text)
                        focusedObs.zipWith(unfocusedObs).subscribe {
                            ViewCompat.setBackgroundTintList(toolbarRightET, getHintColorList(it.first, it.second))
                        }

//                        toolbarRightTIL.defaultHintTextColor = colors.getHintColorList()

                            val getFocused2 = { toolbarRightET?.backgroundTintList?.getColorForState(intArrayOf(state_focused), colors.accent) } // TODO generify
                            val getUnfocused2 = { toolbarRightET?.backgroundTintList?.getColorForState(intArrayOf(-state_focused), colors.text) }
                            val focusedObs2 = ColorAnimator.get(getFocused2, colors.accent)
                            val unfocusedObs2 = ColorAnimator.get(getUnfocused2, colors.text)
                            focusedObs2.zipWith(unfocusedObs2).subscribe {
                                ViewCompat.setBackgroundTintList(toolbarRightET, getHintColorList(it.first, it.second))
                            }




                        //TODO below

                        toolbarRightET.setOnFocusChangeListener { v, hasFocus ->
                            (v as TextInputAutoCompleteTextView)
                                    .textInputLayout
                                    .setBackgroundColor(colors.getRightETBackground(hasFocus))
                        }
                        val hasFocusNow = toolbarRightET.hasFocus()
                        toolbarRightTIL.setBackgroundColor(colors.getRightETBackground(hasFocusNow))
                    }
                }
    }

    private fun Colors.getRightETBackground(hasFocus: Boolean): Int =
            when(hasFocus) {
                true -> this.white
                false -> this.primary
            }

//    private fun Colors.getHintColorList() = createColorStateList(
//            states = arrayOf(
//                    intArrayOf(state_focused), // Focused
//                    intArrayOf(-state_focused) // Unfocused
//            ), colors = intArrayOf(
//                    this.accent,
//                    this.text
//            )
//    )

    private fun getHintColorList(focused: Int, unfocused: Int) = createColorStateList(
            states = arrayOf(
                    intArrayOf(state_focused), // Focused
                    intArrayOf(-state_focused) // Unfocused
            ), colors = intArrayOf(
            focused,
            unfocused
    )
    )
}
