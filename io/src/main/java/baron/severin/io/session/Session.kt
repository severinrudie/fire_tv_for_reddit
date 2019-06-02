package baron.severin.io.session

import android.annotation.SuppressLint

internal class Session(private val username: String, private val password: String) {

    var token: String? = null

    val isLoggedIn: Boolean = token != null

    fun invalidate() {
        token == null
    }
}
