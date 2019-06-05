package baron.severin.response_objects.raw.response.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Child(
    @Json(name = "kind") val kind: String,
    @Json(name = "data") val data: Data_
)
