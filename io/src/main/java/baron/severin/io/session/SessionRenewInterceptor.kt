package baron.severin.io.session

import okhttp3.Interceptor
import okhttp3.Response

/**
 * TODO
 */
internal class SessionRenewInterceptor(private val session: Session) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        // if 'x-auth-token' is available into the response header
        // save the new token into session.The header key can be
        // different upon implementation of backend.
        val newToken = response.header("access_token")

        if (newToken != null) session.token = newToken

        return response
    }

}