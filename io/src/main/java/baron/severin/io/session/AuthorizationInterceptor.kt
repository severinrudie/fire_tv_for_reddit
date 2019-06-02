package baron.severin.io.session

import okhttp3.Interceptor
import okhttp3.Response

// TODO incomplete and unused
// Based on https://medium.com/@tsaha.cse/advanced-retrofit2-part-2-authorization-handling-ea1431cb86be
internal class AuthorizationInterceptor(private val session: Session) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        if (!session.isLoggedIn || response.code() in 400 until 500 ) {
            session.invalidate()
        }

        return response
    }
}
