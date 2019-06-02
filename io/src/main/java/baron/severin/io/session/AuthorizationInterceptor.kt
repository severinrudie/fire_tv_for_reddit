package baron.severin.io.session

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor(private val session: Session) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        if (!session.isLoggedIn || response.code() in 400 until 500 ) {
            session.invalidate()
        }

        return response
    }
}