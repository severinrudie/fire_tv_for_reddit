package baron.severin.io.Interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer

import java.io.IOException

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("SEVTEST: request: $request")
        println("SEVTEST: isHttps: ${request.isHttps}")
        println("SEVTEST: body: ${bodyToString(request)}")
        println("SEVTEST: cacheControl: ${request.cacheControl()}")
        println("SEVTEST: headers: ${request.headers()}")
        println("SEVTEST: method: ${request.method()}")
        println("SEVTEST: content-type: ${request.body()?.contentType()}")
        return chain.proceed(chain.request())
    }
}

private fun bodyToString(request: Request): String {

    try {
        val copy = request.newBuilder().build()
        val buffer = Buffer()
        copy.body()?.writeTo(buffer)
        return buffer.readUtf8()
    } catch (e: IOException) {
        return "did not work"
    }

}
