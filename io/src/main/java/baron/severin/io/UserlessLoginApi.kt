package baron.severin.io

import android.app.Application
import android.util.Base64
import baron.severin.common.IgnoredSecrets
import baron.severin.response_objects.raw.response.login.UserlessAuthorization
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.nio.charset.Charset
import java.util.*

//internal
interface UserlessLoginApi {

    @POST("/api/v1/access_token")
    fun requestUserlessCredentialsRaw(
            @Body body: RequestBody,
            @Header("Authorization") authorization: String,
            @Header("User-Agent") userAgent: String
    ): Single<Response<UserlessAuthorization>>
}

fun UserlessLoginApi.requestUserlessCredentials(deviceId: UUID, app: Application): Single<Response<UserlessAuthorization>> {
    return requestUserlessCredentialsRaw(
            body = getRequestBody(deviceId),
            authorization = getAuthHeaderValue(),
            userAgent = getUserAgent(app)
    )
}

private fun getRequestBody(deviceId: UUID): RequestBody {
    val grantType = "https://oauth.reddit.com/grants/installed_client"
    return RequestBody.create(
            /* contentType */ MediaType.parse("application/x-www-form-urlencoded"),
            /* content */ "grant_type=$grantType&device_id=$deviceId"
    )
}

private fun getAuthHeaderValue(): String {
    val authValueRaw = "${IgnoredSecrets.CLIENT_ID}:"
    val authValueBytes = authValueRaw.toByteArray(Charset.forName("UTF-8"))
    val authValueEncoded = Base64.encodeToString(authValueBytes, Base64.NO_WRAP)
    return "Basic $authValueEncoded"
}

private fun getUserAgent(app: Application): String =
        "${app.packageName}:${BuildConfig.VERSION_NAME} (by /u/Baron_von_Severin)"
