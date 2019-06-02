package baron.severin.response_objects.raw.response.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Resolution(
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int = 0,
    @Json(name = "height") val height: Int = 0
)
