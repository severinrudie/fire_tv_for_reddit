package baron.severin.response_objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Preview(
        @Json(name = "images") val images: List<Image>? = null,
        @Json(name = "enabled") val enabled: Boolean = false
)
