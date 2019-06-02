package baron.severin.io

import android.util.Base64
import baron.severin.common.IgnoredSecrets
import baron.severin.response_objects.raw.response.login.UserlessAuthorization
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.nio.charset.Charset
import java.util.*

//internal
interface LoginApi {

    @Headers(
    "User-Agent: Android:seveida.firetvforreddit:0.1-Alpha (by /u/Baron_von_Severin)" // TODO generalize
    )
    @POST("/api/v1/access_token")
    fun requestUserlessCredentials(
            @Body body: RequestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), "grant_type=https://oauth.reddit.com/grants/installed_client&device_id=${UUID.randomUUID()}"), // TODO cleanup
            @Header("Authorization") authorization: String = "Basic $auth"
            ): Single<Response<UserlessAuthorization>>
}

val auth = Base64.encodeToString("${IgnoredSecrets.CLIENT_ID}:".toByteArray(Charset.forName("UTF-8")), Base64.NO_WRAP)
