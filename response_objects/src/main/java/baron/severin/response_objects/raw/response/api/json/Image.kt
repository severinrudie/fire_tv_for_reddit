package baron.severin.response_objects.raw.response.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
        @Json(name = "source") val source: Source,
        @Json(name = "resolutions") val resolutions: List<Resolution>? = null,
        @Json(name = "variants") val variants: Variants,
        @Json(name = "id") val id: String
)
