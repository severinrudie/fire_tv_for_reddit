package baron.severin.response_objects.raw.response.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserlessAuthorization(
        @Json(name = "access_token") val token: String,
        @Json(name = "token_type") val tokenType: String,
        @Json(name = "expires_in") val expiresInSeconds: Int,
        @Json(name = "scope") val scope: String
)
