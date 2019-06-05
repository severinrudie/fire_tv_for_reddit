package baron.severin.io.session

import android.annotation.SuppressLint

// TODO incomplete and unused
// Based on https://medium.com/@tsaha.cse/advanced-retrofit2-part-2-authorization-handling-ea1431cb86be
internal class Session(private val username: String, private val password: String) {

    var token: String? = null

    val isLoggedIn: Boolean = token != null

    fun invalidate() {
        token == null
    }
}
