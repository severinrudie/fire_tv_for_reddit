package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "url") val url: String,
    @Json(name = "width") val width: Int = 0,
    @Json(name = "height") val height: Int = 0
)
