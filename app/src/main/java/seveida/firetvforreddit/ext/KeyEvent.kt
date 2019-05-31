package seveida.firetvforreddit.ext

import android.view.KeyEvent

fun KeyEvent.isSelect(): Boolean {
    return this.action == KeyEvent.ACTION_DOWN &&
            (this.keyCode == KeyEvent.KEYCODE_ENTER ||
                    this.keyCode == KeyEvent.KEYCODE_BUTTON_SELECT)
}
