package seveida.firetvforreddit

import android.R.attr.state_focused
import android.app.Activity
import android.content.res.ColorStateList
import androidx.core.view.ViewCompat
import baron.severin.business_logic.StateObservable
import baron.severin.presentation_objects.Colors
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.toolbar.*
import seveida.firetvforreddit.view.components.TextInputAutoCompleteTextView
import java.lang.ref.WeakReference
import javax.inject.Inject

class ToolbarStyler @Inject constructor(private val stateObs: StateObservable) {

    fun observeToolbarState(activity: WeakReference<Activity>): List<Disposable> {
        return listOf(observeContent(activity), observeColors(activity))
    }

    private fun observeContent(activity: WeakReference<Activity>): Disposable =
            stateObs.get
                    .map { it.toolbarState }
                    .distinctUntilChanged()
                    .subscribe {
                        with (activity.get() ?: return@subscribe) {
                            toolbarLeftTV.text = it.title
                            toolbarRightET.setText("")
                            toolbarRightTIL.hint = it.inputHint
                        }
                    }

    private fun observeColors(activity: WeakReference<Activity>): Disposable =
            stateObs.get
                    .map { it.colors }
                    .distinctUntilChanged()
                    .subscribe { colors ->
                        with (activity.get() ?: return@subscribe) {
                            toolbarContainer.setBackgroundColor(colors.primary)
                            toolbarLeftTV.setTextColor(colors.text)


                            toolbarRightET.setTextColor(colors.text)

                            ViewCompat.setBackgroundTintList(toolbarRightET, colors.getHintColorList())
                            toolbarRightTIL.defaultHintTextColor = colors.getHintColorList()

                            toolbarRightET.setOnFocusChangeListener { v, hasFocus ->
                                (v as TextInputAutoCompleteTextView)
                                        .textInputLayout
                                        .setBackgroundColor(colors.getRightETBackground(hasFocus))
                            }
                            val hasFocusNow = toolbarRightET.hasFocus()
                            toolbarRightTIL.setBackgroundColor(colors.getRightETBackground(hasFocusNow))
                        }
                    }

    private fun Colors.getRightETBackground(hasFocus: Boolean): Int =
            when(hasFocus) {
                true -> this.white
                false -> this.primary
            }

    private fun Colors.getHintColorList() = ColorStateList(
            /* states */ arrayOf(
            intArrayOf(state_focused), // Focused
            intArrayOf(-state_focused) // Unfocused
    ),
            /* colors */ intArrayOf(
            this.accent,
            this.text
    )
    )
}
